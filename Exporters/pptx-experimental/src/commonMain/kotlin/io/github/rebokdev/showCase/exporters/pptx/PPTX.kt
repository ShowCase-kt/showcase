package io.github.rebokdev.showCase.exporters.pptx

import io.github.rebokdev.showCase.Presentation

object PPTX: Presentation.Exporter(
    extension = "not_working.pptx",
    isOpeningFilesSupported = false
) {
    override suspend fun export(presentation: Presentation): ByteArray = generatePPTX(presentation)
}