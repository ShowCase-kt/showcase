package Widgets

import WidgetPositon
import PresentationWidget
import Theme
import WidgetSize
import WidgetOverride
import relative

class TitleLayout(
    val title: PresentationWidget,
    val subtitle: PresentationWidget? = null,
    val _theme: Theme = Theme()
) : PresentationWidget(
    size = WidgetSize.max
) {
    override val contents: List<PresentationWidget>
        get() {
            val result = mutableListOf(
                title.overriden(
                    WidgetOverride(
                        size = WidgetSize(90F, 40F),
                        position = if (subtitle != null) WidgetPositon(
                            relative(5F),
                            relative(20F)
                        ) else WidgetPositon(
                            relative(5F),
                            relative(30F)
                        ),
                        theme = _theme
                    )
                )
            )

            subtitle?.let {
                result.add(
                    subtitle.overriden(
                        WidgetOverride(
                            size = WidgetSize(90F, 20F),
                            position = WidgetPositon(
                                relative(5F),
                                relative(60F)
                            ),
                            theme = _theme
                        )
                    )
                )
            }

            return result
        }
}