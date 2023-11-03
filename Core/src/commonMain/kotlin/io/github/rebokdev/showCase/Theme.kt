package io.github.rebokdev.showCase

data class Theme(
    val titleColor: Color = Color(0,0,0),
    val subTitleColor: Color = Color(100,100,100),
    val authorColor: Color = Color(0,0,0),
    val slideNumberColor: Color = Color(0,0,0),
    val background: Background? = null,
    val slideChangeTransition: SlideChangeTransition? = null,
    val pictureStyle: PictureStyle = PictureStyle(),
    val widgetsOnTheBottom: List<PresentationWidget> = listOf(),
    val textTheme: TextTheme = TextTheme(
        mini = TextStyle(
            color = authorColor,
            isBold = true,
            isItalic = true,
            fontSize = 10.0
        ),
        small = TextStyle(
            color = subTitleColor,
            isBold = false,
            isItalic = true,
            fontSize = 35.0
        ),
        normal = TextStyle(
            color = subTitleColor,
            isBold = false,
            isItalic = false,
            fontSize = 45.0
        ),
        big = TextStyle(
            color = subTitleColor,
            isBold = true,
            fontSize = 55.0
        ),
        enormous = TextStyle(
            color = titleColor,
            isBold = true,
            fontSize = 85.0
        )
    )
)