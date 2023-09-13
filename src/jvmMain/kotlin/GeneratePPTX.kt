import Widgets.Backdrop
import Widgets.Picture
import Widgets.Shape
import Widgets.Text
import korlibs.image.bitmap.Bitmap
import korlibs.image.format.PNG
import korlibs.image.vector.SizedDrawable
import korlibs.image.vector.toSvg
import org.apache.poi.sl.usermodel.PictureData
import org.apache.poi.sl.usermodel.TextParagraph
import org.apache.poi.sl.usermodel.VerticalAlignment
import org.apache.poi.xslf.usermodel.XMLSlideShow
import org.openxmlformats.schemas.presentationml.x2006.main.STTransitionSideDirectionType
import org.openxmlformats.schemas.presentationml.x2006.main.STTransitionSpeed
import java.awt.Dimension
import java.awt.geom.Rectangle2D
import java.io.FileOutputStream
import org.apache.poi.sl.usermodel.ShapeType as poiShapeType
import java.awt.Color as awtColor

fun Presentation.generatePPTX() {
    val result = XMLSlideShow()
    val fileOut = FileOutputStream("presentation.pptx")
    val presentationSize = result.pageSize

    this.slides.forEachIndexed { index, slideRaw ->
        var slide = result.createSlide()
        if (index != 0) {
            when (slideRaw.slideChangeTransition) {
                is SlideChangeTransition.Push -> {
                    var transition = slide.xmlObject.addNewTransition().addNewPush()
                    transition.dir = STTransitionSideDirectionType.U
                    slide.xmlObject.timing
                }

                null -> Unit
                else -> Unit
            }
        }

        if (slide.xmlObject.transition != null) {
            slideRaw.slideChangeTransition?.durationMilis?.let {

                /*
                * apache POI ooxml does support PowerPoint versions up to 2007,
                *   PowerPoint 2010 introduced option for slide change transitions to be timed precisely,
                *   in the versions older than 2010 they only option to set the slide change  transition was to
                *   change transition speed, the only available values are listed here ↓ ,
                *   there is temporary solution: provided slide change transition time would be rounded up
                *   to the closest speed
                *
                * STTransitionSpeed values
                * - FAST = 0.25s
                * - MED = 0.5s
                * - SLOW = 0.75s
                * */

                slide.xmlObject.transition.spd = when (slideRaw.slideChangeTransition.durationMilis.roundWithLimitedPossibilities(250,500,750)) {
                    250L -> STTransitionSpeed.FAST
                    500L -> STTransitionSpeed.MED
                    750L -> STTransitionSpeed.SLOW
                    else -> STTransitionSpeed.MED
                }
            }
        }

        var isBackgroundColor = false

        slideRaw.background?.let {
            if (it.getType() == Color::class) {
                var color = it.getData() as Color
                slide.background.fillColor = awtColor(color.red, color.green, color.blue, color.alpha)
                isBackgroundColor = true
            }
        }

        parse(
            it = slideRaw.getContentsWith(!isBackgroundColor),
            add = { widget ->
                if (widget is Text) {
                    val text = slide.createTextBox()
                    if (widget.size.width != -1.0F) {
                        text.anchor = widget.position.toRectangle2d(
                            presentationSize = presentationSize,
                            widgetSize = widget.size,
                            local = Rectangle2D.Float(
                                0F,
                                0F,
                                presentationSize.width.toFloat(),
                                presentationSize.height.toFloat()
                            )
                        )

                        text.verticalAlignment = when (widget.text.verticalAlign) {
                            VerticalTextAlign.Top -> VerticalAlignment.TOP
                            VerticalTextAlign.Center -> VerticalAlignment.MIDDLE
                            VerticalTextAlign.Bottom -> VerticalAlignment.BOTTOM
                        }
                    }

                    widget.text.paragraphs.toList().forEach {
                        val paragraph = text.addNewTextParagraph()

                        paragraph.textAlign = when (it.horizontalAlign) {
                            HorizontalTextAlign.Left -> TextParagraph.TextAlign.LEFT
                            HorizontalTextAlign.Center -> TextParagraph.TextAlign.CENTER
                            HorizontalTextAlign.Right -> TextParagraph.TextAlign.RIGHT
                            HorizontalTextAlign.Jusify -> TextParagraph.TextAlign.JUSTIFY
                        }

                        it.runs.forEach {
                            val run = paragraph.addNewTextRun()
                            run.setText(it.text)
                            if (it.color != Color()) {
                                run.setFontColor(awtColor(it.color.red, it.color.green, it.color.blue, it.color.alpha))
                            }
                            run.isBold = it.bold
                            run.isItalic = it.italic
                            run.fontSize = it.fontSize
                        }
                    }
                } else if (widget is Picture) {
                    val imageData = result.addPicture(
                        widget.image.getImage().run {
                            if (this is SizedDrawable) {
                                this.toSvg().text.encodeToByteArray()
                            } else {
                                PNG.encode(this as Bitmap)
                            }
                        },
                        widget.image.getImageType().run {
                            when (this) {
                                SizedDrawable::class -> PictureData.PictureType.PNG
                                else -> PictureData.PictureType.SVG
                            }
                        }
                    )


                    val image = slide.createPicture(imageData)

                    /*
                    * rounding in .pttx is limited
                    *   so, no matter is your rounding value 2, 360, 30
                    *   it will be the same
                    * */
                    if (widget.getPictureStyle().rounding > 0) {
                        image.shapeType = poiShapeType.ROUND_RECT
                    }

                    image.anchor = widget.position.toRectangle2d(
                        presentationSize = presentationSize,
                        widgetSize = widget.size,
                        local = Rectangle2D.Float(
                            0F, 0F,
                            presentationSize.width.toFloat(),
                            presentationSize.height.toFloat()
                        )
                    )

                } else if (widget is Shape) {
                    var shape = slide.createAutoShape()

                    shape.fillColor = awtColor(
                        widget.color.red,
                        widget.color.green,
                        widget.color.blue
                    )

                    shape.shapeType = when (widget.shapeType) {
                        is ShapeType.rectangle -> poiShapeType.RECT
                        is ShapeType.roundedRectangle -> poiShapeType.ROUND_RECT
                    }

                    shape.anchor = widget.position.toRectangle2d(
                        presentationSize = presentationSize,
                        widgetSize = widget.size,
                        local = Rectangle2D.Float(
                            0F, 0F,
                            presentationSize.width.toFloat(),
                            presentationSize.height.toFloat()
                        )
                    )
                }
            }
        )
    }

    result.write(fileOut)
    fileOut.close()
}

fun parse(it: List<PresentationWidget>, add: (Widget: PresentationWidget) -> Unit) {
    for (i in it) {
        if (i.contents == null) {
            add.invoke(i)
        } else {
            i.contents?.let {
                parse(
                    it = it,
                    add = add
                )
            }
        }
    }
}

fun WidgetPositon.toRectangle2d(presentationSize: Dimension, widgetSize: WidgetSize, local: Rectangle2D): Rectangle2D {
    val x: Int = when (this.x.Type) {
        WidgetPositionElementType.Relative -> {
            (((local.width / 100) * this.x.Value) + local.x).toInt()
        }

        WidgetPositionElementType.Absolute -> {
            ((presentationSize.width / 100) * this.x.Value).toInt()
        }

        WidgetPositionElementType.LocalAlign -> {
            when (this.x.Value) {
                -1F -> {
                    local.x.toInt()
                }

                0F -> {
                    ((local.x + (local.width / 2)) - (((local.x / 100) * widgetSize.width) / 2)).toInt()
                }

                1F -> {
                    ((local.x + local.width) - ((local.width / 100) * widgetSize.width)).toInt()
                }

                else -> {
                    0
                }
            }
        }

        WidgetPositionElementType.GlobalAlign -> {
            when (this.x.Value) {
                -1F -> {
                    0
                }

                0F -> {
                    (presentationSize.width / 2) - ((((presentationSize.width.toDouble() / 100) * widgetSize.width) / 2).toInt())
                }

                1F -> {
                    (presentationSize.width - ((presentationSize.width / 100) * widgetSize.width)).toInt()
                }

                else -> {
                    0
                }
            }
        }
    }

    val y: Int = when (this.y.Type) {
        WidgetPositionElementType.Relative -> {
            (((local.height / 100) * this.y.Value) + local.y).toInt()
        }

        WidgetPositionElementType.Absolute -> {
            ((presentationSize.height / 100) * this.y.Value).toInt()
        }

        WidgetPositionElementType.LocalAlign -> {
            when (this.y.Value) {
                -1F -> {
                    local.y.toInt()
                }

                0F -> {
                    ((local.y + (local.height / 2)) - (((local.y / 100) * widgetSize.height) / 2)).toInt()
                }

                1F -> {
                    ((local.y + local.height) - ((local.height / 100) * widgetSize.height)).toInt()
                }

                else -> {
                    0
                }
            }
        }

        WidgetPositionElementType.GlobalAlign -> {
            when (this.y.Value) {
                -1F -> {
                    0
                }

                0F -> {
                    (presentationSize.height / 2) - ((((presentationSize.height.toDouble() / 100) * widgetSize.height) / 2).toInt())
                }

                1F -> {
                    (presentationSize.height - ((presentationSize.height / 100) * widgetSize.height)).toInt()
                }

                else -> {
                    0
                }
            }
        }
    }



    return Rectangle2D.Float(
        x.toFloat(), y.toFloat(),
        ((presentationSize.width.toFloat() / 100) * widgetSize.width).toFloat(),
        ((presentationSize.height.toFloat() / 100) * widgetSize.height).toFloat()
    )
}

inline fun <reified A, B> MutableMap<A, B>.withAdded(key: A, value: B): MutableMap<A, B> {
    var result = this as MutableMap<A, B>
    result[key] = value
    return result
}

// the limit is the maximum double can hold
fun Long.roundWithLimitedPossibilities(vararg possibilities: Long): Long {
    // store scores, with corresponding possibility
    var result: MutableMap<Double, Long> = mutableMapOf()
    for (a in possibilities) {
        if (this > a) {
            result[(this - a).toDouble()] = a
        } else {
            result[(a - this ).toDouble()] = a
        }
    }

    return result[result.keys.sorted()[0]]!!
}