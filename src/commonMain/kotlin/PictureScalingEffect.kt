sealed class PictureScalingEffect {
    class FitToNotScale(
        alignX: Float = AlignX.center,
        alignY: Float = AlignY.center
    ): PictureScalingEffect()

    object Fill: PictureScalingEffect()
}