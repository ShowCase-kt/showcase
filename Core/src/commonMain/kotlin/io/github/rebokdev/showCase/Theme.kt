package io.github.rebokdev.showCase

data class Theme(
    val titleColor: Color = Color(0,0,0),
    val subTitleColor: Color = Color(100,100,100),
    val authorColor: Color = Color(0,0,0),
    val slideNumberColor: Color = Color(0,0,0),
    val background: io.github.rebokdev.showCase.Background? = null,
    val slideChangeTransition: SlideChangeTransition? = null,
    val pictureStyle: PictureStyle = PictureStyle(),
    val widgetsOnTheBottom: List<PresentationWidget> = listOf()
)