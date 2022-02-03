package com.simpleplus.dynamicbuilder.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.simpleplus.dynamicbuilder.model.DynamicUI
import com.simpleplus.dynamicbuilder.presentation.utils.DynamicsHandler

class MainViewModel : ViewModel() {

    private val handler = DynamicsHandler()
    val uiItems = handler.getData<DynamicUI>(DynamicsHandler.JSON_FOR_UI, DynamicUI::class.java)!!

    val currentSelectedChoices = mutableStateListOf<Int>() // All answers/choices should be stored in this list

    fun checkAnswerFromChoiceLayout(onAnswered: (Boolean) -> Unit) {

        if (currentSelectedChoices.containsAll(uiItems.answers) && currentSelectedChoices.size == uiItems.answers.size) {
            onAnswered(true)
        } else {
            onAnswered(false)
        }
    }
}