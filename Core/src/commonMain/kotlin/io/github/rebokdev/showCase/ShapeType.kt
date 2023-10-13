package io.github.rebokdev.showCase

sealed class ShapeType {
    class rectangle(): ShapeType()
    class roundedRectangle(): ShapeType()
}