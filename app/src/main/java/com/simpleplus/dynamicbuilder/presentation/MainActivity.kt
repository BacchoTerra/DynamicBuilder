package com.simpleplus.dynamicbuilder.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.simpleplus.dynamicbuilder.model.*
import com.simpleplus.dynamicbuilder.presentation.utils.DynamicImage
import com.simpleplus.dynamicbuilder.presentation.utils.DynamicText
import com.simpleplus.dynamicbuilder.presentation.utils.DynamicsHandler
import com.simpleplus.dynamicbuilder.ui.theme.DynamicBuilderTheme
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainActivity : ComponentActivity() {

    companion object {
        const val TAG = "Porsche"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val handler = DynamicsHandler()

        val items = handler.getData<UIItems>(DynamicsHandler.EXAMPLE_FOR_UI, UIItems::class.java)

        items?.let { uiItems ->

            uiItems.items.forEach {
                Log.i(TAG, "onCreate: ${it::class.simpleName}")
            }
        }

        setContent {
            DynamicBuilderTheme {
                // A surface container using the 'background' color from the theme
                Surface() {

                    val list by remember {
                        mutableStateOf(items!!.items)
                    }

                    Column(modifier = Modifier.fillMaxSize()) {
                        for (i in list) {

                            when (i) {
                                is DynamicText -> DynamicText(dynamicText = i)
                                is DynamicImage -> DynamicImage(dynamicImage = i)
                            }
                        }
                    }
                }
            }
        }
    }
}
