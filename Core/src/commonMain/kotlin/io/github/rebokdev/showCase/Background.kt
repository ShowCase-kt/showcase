package io.github.rebokdev.showCase

import korlibs.image.bitmap.Bitmap1
import kotlin.reflect.KClass

class Background(
    private val image: io.github.rebokdev.showCase.Image
) {
    lateinit var Color: io.github.rebokdev.showCase.Color

    constructor(color: io.github.rebokdev.showCase.Color) : this(
        io.github.rebokdev.showCase.Image(Bitmap1(width = 1, height = 1, data = byteArrayOf(0)))
    ) {
        Color  = color
    }

    fun getType(): KClass<*> = if (::Color.isInitialized) Color::class else
        io.github.rebokdev.showCase.Image::class

    fun getData(): Any = if (::Color.isInitialized) Color else
        image
}