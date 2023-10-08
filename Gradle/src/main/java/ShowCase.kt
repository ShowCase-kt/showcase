object ShowCase {
    val Core: String = dependency("ShowCase-Core")
    object Exports {
        val pptx = dependency("ShowCase-Exports.pptx")

        operator fun invoke(): String {
            return dependency("ShowCase-Exports")
        }
    }

    object Themes {
        var nova = dependency("ShowCase-Themes.nova")

        operator fun invoke(): String {
            return dependency("ShowCase-Themes")
        }
    }

    operator fun invoke(): String {
        return dependency("ShowCase")
    }
}

