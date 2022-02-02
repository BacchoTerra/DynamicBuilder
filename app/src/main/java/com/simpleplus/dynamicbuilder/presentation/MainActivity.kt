package com.simpleplus.dynamicbuilder.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.simpleplus.dynamicbuilder.model.*
import com.simpleplus.dynamicbuilder.presentation.utils.*
import com.simpleplus.dynamicbuilder.ui.theme.DynamicBuilderTheme

class MainActivity : ComponentActivity() {

    companion object {
        const val TAG = "Porsche"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val handler = DynamicsHandler()

        val items = handler.getData<UIItems>(DynamicsHandler.EXAMPLE_FOR_UI, UIItems::class.java)


        setContent {
            DynamicBuilderTheme {
                // A surface container using the 'background' color from the theme
                Surface() {

                    val uiElements by remember {
                        mutableStateOf(items!!.items)
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(android.graphics.Color.parseColor(items!!.parent.backgroundColor)))
                    ) {
                        for (i in uiElements) {

                            when (i) {
                                is DynamicText -> DynamicText(dynamicText = i)
                                is DynamicImage -> DynamicImage(dynamicImage = i)
                                is DynamicChoiceLayout -> DynamicChoiceLayout(dynamicChoiceLayout = i)
                            }
                        }
                    }
                }
            }
        }
    }
}
