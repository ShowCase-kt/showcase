object emojiParser {
    private val emoji: Map<String, Char> = mapOf(
        "bye" to '✋'
    )

    fun parse(text: String): String {
        var result: String = ""
        var temporary: String = ""
        var state: Int = 0

        text.forEach {
            fun addChar() {
                result += it
            }

            if (it == ':') {
                if (state == 0) {
                    state = 1
                } else {
                    if (emoji.containsKey(temporary)) {
                        result += emoji[temporary]
                    } else {
                        result += ":${temporary}:"
                    }

                    state = 0
                }
            } else if (state == 1) {
                temporary += it
            } else {
                addChar()
            }
        }

        result = result.replace("\$_",":")

        return result
    }
}

fun String.parseEmoji(): String = emojiParser.parse(this)