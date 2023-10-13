package io.github.rebokdev.showCase.exporters.pptx

import io.github.rebokdev.showCase.Presentation
import korlibs.io.file.std.createZipFromTree
import korlibs.io.file.std.tempVfs
import korlibs.io.util.UUID

suspend fun generatePPTX(presentation: Presentation): ByteArray {
    val tempDir = tempVfs[UUID.randomUUID().toString()]
    tempDir.mkdir()

    val result = tempDir.createZipFromTree()
    tempDir.delete()
    return result
}