import dev.rebok.showCase.Presentation
import korlibs.io.file.VfsFile

suspend fun Presentation.generatePPTXAndSaveTo(
    to: VfsFile
) {
    to.writeBytes(this.generatePPTX())
}