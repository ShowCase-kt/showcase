import korlibs.image.bitmap.Bitmap1
import kotlin.reflect.KClass

class Background(
    private val image: Image
) {
    lateinit var Color: Color

    constructor(color: Color) : this(
        Image(Bitmap1(width = 1, height = 1, data = byteArrayOf(0)))
    ) {
        Color  = color
    }

    fun getType(): KClass<*> = if (::Color.isInitialized) Color::class else
        Image::class

    fun getData(): Any = if (::Color.isInitialized) Color else
        image
}