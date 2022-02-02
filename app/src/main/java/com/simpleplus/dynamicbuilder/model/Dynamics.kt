package com.simpleplus.dynamicbuilder.model

open class Dynamics(val type: DynamicsTypes) {

    companion object {
        const val H_ALIGNMENT_START = 0
        const val H_ALIGNMENT_CENTER = 1
        const val H_ALIGNMENT_END = 2
    }

    enum class DynamicsTypes {

        PARENT,
        HEADER,
        HEADER_ITEM,
        TEXT,
        IMAGE,
        CHOICE_BOX,
        CHOICE_LAYOUT,
    }

}

data class DynamicParent(
    val backgroundColor: String = "#FFFFFF",
    val btnText : String = "Continue"
) : Dynamics(DynamicsTypes.PARENT)

data class DynamicHeaders(val items: List<DynamicHeaderItem>) : Dynamics(DynamicsTypes.HEADER) {

    data class DynamicHeaderItem(
        val backgroundColor: String,
        val iconTint: String,
        val icon: Int
    ):Dynamics(DynamicsTypes.HEADER_ITEM)

}

data class DynamicText(
    val text: String,
    val style: Int = STYLE_NORMAL,
    val color: String = "#000000",
    val spaceTop: Int = 0,
    val spaceBottom: Int = 0,
    val spaceStart: Int = 0,
    val spaceEnd: Int = 0,
    val horizontalAlignment: Int = H_ALIGNMENT_START
) : Dynamics(DynamicsTypes.TEXT) {


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
) : Dynamics(DynamicsTypes.IMAGE)

class DynamicChoiceLayout(
    val isMultiChoice: Boolean = false,
    val choices: List<DynamicChoiceBox>,
) : Dynamics(DynamicsTypes.CHOICE_LAYOUT) {

    data class DynamicChoiceBox(
        val text: String? = null,
        val image: Int? = null,
        val isRight: Boolean,
        val boxId: Int = 0 // This id could be used to track the right choices.
    ) : Dynamics(DynamicsTypes.CHOICE_BOX)

}



