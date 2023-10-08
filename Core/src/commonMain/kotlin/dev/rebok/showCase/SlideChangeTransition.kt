package dev.rebok.showCase

sealed class SlideChangeTransition(
    val durationMilis: Long
) {
    class Push(
        val direction: Direction = Direction.Left,
        val duration: Long = 750
    ): SlideChangeTransition(duration)
}