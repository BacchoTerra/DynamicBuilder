package com.simpleplus.dynamicbuilder.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.simpleplus.dynamicbuilder.model.DynamicChoiceLayout
import com.simpleplus.dynamicbuilder.model.UIItems
import com.simpleplus.dynamicbuilder.presentation.utils.DynamicsHandler

class MainViewModel : ViewModel() {

    private val handler = DynamicsHandler()

    val uiItems = handler.getData<UIItems>(DynamicsHandler.EXAMPLE_FOR_UI, UIItems::class.java)!!

    val currentSelectedChoices = mutableStateListOf<Int>()

    fun checkAnswerFromChoiceLayout(onCorrect: (Boolean) -> Unit) {
        val rightBoxes =
            uiItems.items.filterIsInstance<DynamicChoiceLayout>()[0].choices.filter { it.isRight }
                .map { it.boxId }

        if (currentSelectedChoices.containsAll(rightBoxes) && currentSelectedChoices.size == rightBoxes.size) {
            onCorrect(true)
        } else {
            onCorrect(false)
        }
    }

}