data class WidgetSize(var width: Float = 50F, var height: Float = 50F) {
    companion object {
        val max = WidgetSize(100F,100F)
    }
}
