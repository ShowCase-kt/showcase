data class WidgetSize(var width: Percentage, var height: Percentage) {
    constructor(width: Float = 50F, height: Float = 50F) : this(width.D, height.D)

    companion object {
        val max = WidgetSize(100F,100F)
    }
}
