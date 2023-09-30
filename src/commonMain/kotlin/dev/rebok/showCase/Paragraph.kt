package dev.rebok.showCase

class Paragraph(
    val horizontalAlign: HorizontalTextAlign = HorizontalTextAlign.Left,
    vararg val runs: Run
)