import dev.rebok.showCase.Presentation
import korlibs.io.file.VfsFile
import korlibs.io.file.std.applicationDataVfs

suspend fun Presentation.generatePPTXAndSave(
    inDirectory: VfsFile = applicationDataVfs
): String {
    val fileName = if (this.name != null) this.name.toString()+".pptx"
        else "output.pptx"

    inDirectory[fileName].writeBytes(this.generatePPTX())

    return fileName
}