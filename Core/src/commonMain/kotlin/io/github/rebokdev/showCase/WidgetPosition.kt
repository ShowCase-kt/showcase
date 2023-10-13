package io.github.rebokdev.showCase

data class WidgetPosition(
    val x: WidgetPositionElement = AlignX.CENTER.local,
    val y: WidgetPositionElement = AlignY.CENTER.local
)

sealed class WidgetPositionElement() {
    data class Absolute(val percentage: Float) : WidgetPositionElement()
    data class Relative(val percentage: Float) : WidgetPositionElement()

    data class LocalAlign(
        val type: AlignX
    ) : WidgetPositionElement() {
        constructor(type: AlignY) : this(
            type = when (type) {
                AlignY.TOP -> AlignX.LEFT
                AlignY.CENTER -> AlignX.CENTER
                AlignY.BOTTOM -> AlignX.RIGHT
            }
        )
    }

    data class GlobalAlign(
        val type: AlignX
    ) : WidgetPositionElement() {
        constructor(type: AlignY) : this(
            type = when (type) {
                AlignY.TOP -> AlignX.LEFT
                AlignY.CENTER -> AlignX.CENTER
                AlignY.BOTTOM -> AlignX.RIGHT
            }
        )
    }
}

enum class AlignY(val value: Int) {
    TOP(-1), CENTER(0), BOTTOM(1)
}

enum class AlignX(val value: Int) {
    LEFT(-1), CENTER(0), RIGHT(1)
}

val AlignY.global: WidgetPositionElement
    get() = WidgetPositionElement.GlobalAlign(this)

val AlignX.global: WidgetPositionElement
    get() = WidgetPositionElement.GlobalAlign(this)

val AlignY.local: WidgetPositionElement
    get() = WidgetPositionElement.LocalAlign(this)

val AlignX.local: WidgetPositionElement
    get() = WidgetPositionElement.LocalAlign(this)

val Float.positionAbsolute: WidgetPositionElement
    get() = WidgetPositionElement.Absolute(this)

val Float.positionRelative: WidgetPositionElement
    get() = WidgetPositionElement.Absolute(this)
