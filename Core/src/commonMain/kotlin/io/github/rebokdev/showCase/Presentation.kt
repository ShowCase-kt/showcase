package io.github.rebokdev.showCase

import korlibs.crypto.MD5
import korlibs.io.file.VfsFile
import korlibs.io.file.std.applicationDataVfs

data class Presentation(val name: String, val slides: List<Slide>) {
    abstract class Exporter(
        val extension: String,
        val isOpeningFilesSupported: Boolean = false
    ) {
        abstract suspend fun export(
            presentation: Presentation
        ): ByteArray

        open suspend fun openFile(
            file: VfsFile
        ) = println("Opening .${extension} files is not supported by the current exporter.")

        suspend fun launcher(
            name: String,
            data: Map<String, ByteArray> = mapOf(),
            openAfter: Boolean = isOpeningFilesSupported,
            presentation: suspend Map<String, ByteArray>.() -> Presentation
        ) {
            var isUpToDate = false

            for (entry in data) {
                val checksumFile = applicationDataVfs[entry.key + ".sum"]

                if (checksumFile.exists() && checksumFile.isFile()) {
                    val checksum = MD5.digest(entry.value).base64

                    isUpToDate = checksum == checksumFile.readString()

                    if (!isUpToDate) break
                } else {
                    isUpToDate = false
                    break
                }
            }

            if (!isUpToDate) {
                presentation(data).save(this, applicationDataVfs["$name.$extension"])

                data.forEach {
                    applicationDataVfs[it.key + ".sum"].writeString(MD5.digest(it.value).base64)
                }
            }

            if (openAfter) {
                if (isOpeningFilesSupported) {
                    openFile(
                        file = applicationDataVfs["$name.$extension"]
                    )
                } else {
                    throw Exception("Opening .${extension} files is not supported by the current exporter.")
                }
            }
        }
    }

    suspend fun save(
        exporter: Exporter,
        toFile: VfsFile? = null
    ) {
        val output = exporter.export(this)

        if (toFile != null) {
            toFile.writeBytes(output)
        } else {
            applicationDataVfs["${this.name}.${exporter.extension}"]
        }
    }
}