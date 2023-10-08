package dev.rebok.showCase

sealed class ShapeType {
    class rectangle(): ShapeType()
    class roundedRectangle(): ShapeType()
}