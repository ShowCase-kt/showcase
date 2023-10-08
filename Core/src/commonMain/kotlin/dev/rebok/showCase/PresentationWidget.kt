package dev.rebok.showCase

abstract class PresentationWidget(
    var size: WidgetSize = WidgetSize(),
    var position: WidgetPosition = WidgetPosition(),
    var theme: Theme = Theme()
) {
    abstract val contents: List<PresentationWidget>?

    fun overriden(override: WidgetOverride): PresentationWidget {
        val toReturn = this
        override.size?.let { toReturn.size = it }
        override.position?.let { toReturn.position = it }
        override.theme?.let { toReturn.theme = it }
        return toReturn
    }

    open fun onOverride() { }
}