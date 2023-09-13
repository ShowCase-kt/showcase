import korlibs.image.bitmap.Bitmap
import korlibs.image.bitmap.Bitmap1
import korlibs.image.vector.SizedDrawable
import kotlin.reflect.KClass

class Image(
    private val imageBitmap: Bitmap
) {
    lateinit var ImageVector: SizedDrawable

    constructor(imageVector: SizedDrawable): this(Bitmap1(width = 1, height = 1, data = byteArrayOf(0))) {
       ImageVector = imageVector
    }

    fun getImage(): Any = if (::ImageVector.isInitialized) { ImageVector } else { imageBitmap }

    fun getImageType(): KClass<*> = if (::ImageVector.isInitialized) SizedDrawable::class else Bitmap::class
}