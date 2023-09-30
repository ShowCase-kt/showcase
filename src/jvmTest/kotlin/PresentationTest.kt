import Themes.Nova
import Widgets.*
import korlibs.image.format.readBitmap
import korlibs.image.format.readVectorImage
import korlibs.io.file.std.resourcesVfs
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class PresentationTest {
    @Test
    fun example() = runBlocking {
        presentation {
            presentationTheme = Nova(Color.fromHex("#9D70F4"))

            slide("title") {
                titleLayout(
                    title = Title("Example presentation"),
                    subtitle = Subtitle("made with showcase")
                )
            }

            slide("content") {
                widgetAndImageLayout(
                    firstPane = SimpleCenteredText("""
                        Showcase is framework for making presentations
                        fully in kotlin. It is also open-source.
                        For now it does only support powerpoint(.pptx) files
                            and this export only works on JVM, plus
                            the DSL is written in common module, so
                            you can always make own exports.
                    """.trimIndent(), fontSize = 25.0),
                    image = Picture(
                        image = Image(resourcesVfs["example.png"].readBitmap())
                    )
                )
            }

            slide("end") {
                titleLayout(
                    title = Title("Thanks for watching",false),
                    subtitle = Subtitle("Bye :bye:")
                )
            }
        }.generatePPTX()
    }
}