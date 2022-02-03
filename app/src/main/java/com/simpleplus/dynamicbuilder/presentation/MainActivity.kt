package com.simpleplus.dynamicbuilder.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.simpleplus.dynamicbuilder.model.*
import com.simpleplus.dynamicbuilder.presentation.utils.*
import com.simpleplus.dynamicbuilder.presentation.viewmodel.MainViewModel
import com.simpleplus.dynamicbuilder.ui.theme.DynamicBuilderTheme

class MainActivity : ComponentActivity() {

    companion object {
        const val TAG = "Porsche"
    }

    private val vm : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            DynamicBuilderTheme {
                Surface() {

                    val uiElements by remember {
                        mutableStateOf(vm.uiItems.uiElements)
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(android.graphics.Color.parseColor(vm.uiItems.parent.backgroundColor)))
                            .verticalScroll(rememberScrollState())
                    ) {
                        for (i in uiElements) {

                            when (i) {
                                is DynamicText -> DynText(dynamicText = i)
                                is DynamicImage -> DynImage(dynamicImage = i)
                                is DynamicChoiceBoxContainer -> DynChoiceBoxesContainer(dynamicChoiceBoxContainer = i,vm)
                                is DynamicHeadersContainer -> DynHeadersContainer(items = i.items)
                            }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp,top = 32.dp),
                            contentAlignment = Alignment.BottomCenter
                        ) {

                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    vm.checkAnswerFromChoiceLayout {isCorrect ->
                                        if (isCorrect){
                                            Toast.makeText(this@MainActivity,"Congratulations!",Toast.LENGTH_SHORT).show()
                                        }else{
                                            Toast.makeText(this@MainActivity,"Try again!",Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                            ) {
                                Text(text = vm.uiItems.parent.btnText)
                            }
                        }
                    }
                }
            }
        }
    }
}
