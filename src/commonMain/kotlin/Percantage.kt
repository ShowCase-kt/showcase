data class Percentage(
    val value: Float,
    val type: Orientation = Orientation.DEFAULT
)

val Float.H
    get() = Percentage(this, Orientation.HORIZONTAL)

val Float.V
    get() = Percentage(this, Orientation.VERTICAL)