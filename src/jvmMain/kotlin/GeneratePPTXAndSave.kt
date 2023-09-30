import korlibs.io.file.VfsFile

suspend fun Presentation.generatePPTXAndSave(
    to: VfsFile
) {
    to.writeBytes(this.generatePPTX())
}