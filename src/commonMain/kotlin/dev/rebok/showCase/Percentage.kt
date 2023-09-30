package dev.rebok.showCase

data class Percentage(
    val value: Float,
    val orientation: Orientation = Orientation.DEFAULT
)

val Float.D
    get() = Percentage(this, Orientation.DEFAULT)

val Float.H
    get() = Percentage(this, Orientation.HORIZONTAL)

val Float.V
    get() = Percentage(this, Orientation.VERTICAL)