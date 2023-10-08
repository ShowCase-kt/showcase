import dev.rebok.showCase.Presentation
import korlibs.crypto.MD5
import korlibs.io.file.std.applicationDataVfs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.awt.Desktop
import java.io.File

suspend fun presentationLauncher(
    data: Map<String, ByteArray> = mapOf(),
    name: String,
    presentationGenerator: suspend Map<String, ByteArray>.() -> Presentation
) {
    var isUpToDate: Boolean = false

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
        presentationGenerator(data).generatePPTXAndSaveTo(applicationDataVfs["$name.pptx"])

        data.forEach {
            applicationDataVfs[it.key + ".sum"].writeString(MD5.digest(it.value).base64)
        }
    }

    val file = File(applicationDataVfs["$name.pptx"].path)

    withContext(Dispatchers.IO) {
        Desktop.getDesktop().open(file)
        delay(500)
    }
}