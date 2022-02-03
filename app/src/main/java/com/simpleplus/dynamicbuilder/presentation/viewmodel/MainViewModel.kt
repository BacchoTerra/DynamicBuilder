package com.simpleplus.dynamicbuilder.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.simpleplus.dynamicbuilder.model.DynamicChoiceBoxContainer
import com.simpleplus.dynamicbuilder.model.DynamicUI
import com.simpleplus.dynamicbuilder.presentation.utils.DynamicsHandler

class MainViewModel : ViewModel() {

    private val handler = DynamicsHandler()
    val uiItems = handler.getData<DynamicUI>(DynamicsHandler.JSON_FOR_UI, DynamicUI::class.java)!!

    val currentSelectedChoices = mutableStateListOf<Int>()

    fun checkAnswerFromChoiceLayout(onCorrect: (Boolean) -> Unit) {
        val rightBoxes =
            uiItems.uiElements.filterIsInstance<DynamicChoiceBoxContainer>()[0].boxes.filter { it.isRight }
                .map { it.boxId }

        if (currentSelectedChoices.containsAll(rightBoxes) && currentSelectedChoices.size == rightBoxes.size) {
            onCorrect(true)
        } else {
            onCorrect(false)
        }
    }
}