data class WidgetPositon(
    val x: WidgetPositionElement = relative(0F),
    val y: WidgetPositionElement = relative(0F)
)

enum class WidgetPositionElementType {
    Relative, Absolute, LocalAlign, GlobalAlign
}

data class WidgetPositionElement(val Type: WidgetPositionElementType, val Value: Float)

object AlignX {
    val left: Float = -1F
    val center: Float = 0F
    val right: Float = 1F
}

object AlignY {
    val top: Float = -1F
    val center: Float = 0F
    val bottom: Float = 1F
}

fun relative(value: Float) = WidgetPositionElement(WidgetPositionElementType.Relative,value)

fun absolute(value: Float) = WidgetPositionElement(WidgetPositionElementType.Absolute,value)

fun localAlign(value: Float) = WidgetPositionElement(WidgetPositionElementType.LocalAlign,value)

fun globalAlign(value: Float) = WidgetPositionElement(WidgetPositionElementType.GlobalAlign, value)
