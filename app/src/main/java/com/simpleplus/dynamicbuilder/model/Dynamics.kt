package com.simpleplus.dynamicbuilder.model

open class Dynamics() {

    companion object {
        const val H_ALIGNMENT_START = 0
        const val H_ALIGNMENT_CENTER = 1
        const val H_ALIGNMENT_END = 2
    }

}

data class DynamicParent(
    val backgroundColor: String = "#FFFFFF",
) : Dynamics()

data class DynamicText(
    val text: String,
    val style: Int = STYLE_NORMAL,
    val color: String = "#000000",
    val spaceTop: Int = 0,
    val spaceBottom: Int = 0,
    val spaceStart: Int = 0,
    val spaceEnd: Int = 0,
    val horizontalAlignment: Int = H_ALIGNMENT_START
) : Dynamics() {

    companion object {

        const val STYLE_H1 = 0
        const val STYLE_H2 = 1
        const val STYLE_H3 = 2
        const val STYLE_H4 = 3
        const val STYLE_H5 = 4
        const val STYLE_H6 = 5
        const val STYLE_NORMAL = 6

    }

}

data class DynamicImage(
    val image: Int,
    val width: Int = 24,
    val height: Int = 24,
    val spaceTop: Int = 0,
    val spaceBottom: Int = 0,
    val spaceStart: Int = 0,
    val spaceEnd: Int = 0,
    val horizontalAlignment: Int = H_ALIGNMENT_START
) : Dynamics()

