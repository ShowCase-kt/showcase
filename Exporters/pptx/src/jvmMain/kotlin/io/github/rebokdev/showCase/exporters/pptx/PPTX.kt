package io.github.rebokdev.showCase.exporters.pptx

import io.github.rebokdev.showCase.Presentation
import korlibs.io.file.VfsFile
import korlibs.io.lang.toWString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.awt.Desktop
import java.io.File

object PPTX: Presentation.Exporter(
    extension = "pptx",
    isOpeningFilesSupported = true
) {
    override suspend fun export(presentation: Presentation): ByteArray {
        return generatePPTX(presentation)
    }

    override suspend fun openFile(file: VfsFile) = withContext(Dispatchers.IO) {
        Desktop.getDesktop().open(File(file.path))
    }
}