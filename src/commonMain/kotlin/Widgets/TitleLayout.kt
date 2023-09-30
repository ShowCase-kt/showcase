package Widgets

import WidgetPosition
import PresentationWidget
import Theme
import WidgetSize
import WidgetOverride
import positionRelative

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
                        position = if (subtitle != null) WidgetPosition(
                            x = 5F.positionRelative,
                            y = 20F.positionRelative
                        ) else WidgetPosition(
                            x = 5F.positionRelative,
                            y = 30F.positionRelative
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
                            position = WidgetPosition(
                                x = 5F.positionRelative,
                                y = 60F.positionRelative
                            ),
                            theme = _theme
                        )
                    )
                )
            }

            return result
        }
}