sealed class PictureScalingEffect {
    class FitToNotScale(
        alignX: Float = AlignX.CENTER.value.toFloat(),
        alignY: Float = AlignY.CENTER.value.toFloat()
    ): PictureScalingEffect()

    data object Fill: PictureScalingEffect()
}