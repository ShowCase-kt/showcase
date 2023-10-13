object ShowCase : CharSequence {
    val Core: String = dependency("ShowCase-Core")

    object Exporters: CharSequence {
        val pptx = dependency("ShowCase-Exporters.pptx")

        val pptx_experimental: String
            get() {
                println("Remember the experimental pptx exporter is not any kind of stable yet, that means it can have some bigger bugs and or lack functionality.")
                return dependency("ShowCase-Exporters.pptx-experimental")
            }

        override fun toString(): String {
            return dependency("ShowCase-Exporters")
        }

        override val length: Int = toString().length

        override fun get(index: Int): Char = toString()[index]

        override fun subSequence(startIndex: Int, endIndex: Int): CharSequence =
            toString().subSequence(startIndex, endIndex)
    }

    object Themes : CharSequence {
        var nova = dependency("ShowCase-Themes.nova")

        override fun toString(): String {
            return dependency("ShowCase-Themes")
        }

        @Deprecated(
            message = "Replace with just ShowCase.Themes, as now its a CharSequence",
            level = DeprecationLevel.ERROR
        )
        operator fun invoke() = toString()


        override val length: Int = toString().length

        override fun get(index: Int): Char = toString()[index]

        override fun subSequence(startIndex: Int, endIndex: Int): CharSequence =
            toString().subSequence(startIndex, endIndex)
    }

    override fun toString(): String {
        return dependency("ShowCase")
    }

    @Deprecated(
        message = "Replace with just ShowCase, as now its a CharSequence",
        level = DeprecationLevel.ERROR
    )
    operator fun invoke() = toString()


    override val length: Int = toString().length

    override fun get(index: Int): Char = toString()[index]

    override fun subSequence(startIndex: Int, endIndex: Int): CharSequence =
        toString().subSequence(startIndex, endIndex)
}

