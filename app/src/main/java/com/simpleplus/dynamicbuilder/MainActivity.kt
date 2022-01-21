package com.simpleplus.dynamicbuilder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.simpleplus.dynamicbuilder.model.DynamicImage
import com.simpleplus.dynamicbuilder.model.DynamicText
import com.simpleplus.dynamicbuilder.model.Dynamics
import com.simpleplus.dynamicbuilder.ui.theme.DynamicBuilderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uiItems = listOf<Dynamics>(

            DynamicImage(
                R.drawable.ic_android_black_24dp,
                96,
                96,
                spaceTop = 32,
                horizontalAlignment = Dynamics.Companion.H_ALIGNMENT_CENTER
            ),
            DynamicText(
                "Qual som o animal acima faz?",
                style = DynamicText.STYLE_H5,
                spaceTop = 24,
                horizontalAlignment = Dynamics.H_ALIGNMENT_CENTER
            ),
            DynamicText(
                "Clique na alternatica correta!",
                style = DynamicText.STYLE_NORMAL,
                spaceTop = 16,
                horizontalAlignment = Dynamics.H_ALIGNMENT_CENTER
            )
        )

        setContent {
            DynamicBuilderTheme {
                // A surface container using the 'background' color from the theme
                Surface() {

                    val list by remember {
                        mutableStateOf(uiItems)
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


@Composable
private fun DynamicText(dynamicText: DynamicText) {

    Text(
        text = dynamicText.text,
        color = Color(android.graphics.Color.parseColor(dynamicText.color)),
        style = when (dynamicText.style) {
            DynamicText.STYLE_NORMAL -> MaterialTheme.typography.body2
            DynamicText.STYLE_H1 -> MaterialTheme.typography.h1
            DynamicText.STYLE_H2 -> MaterialTheme.typography.h2
            DynamicText.STYLE_H3 -> MaterialTheme.typography.h3
            DynamicText.STYLE_H4 -> MaterialTheme.typography.h4
            DynamicText.STYLE_H5 -> MaterialTheme.typography.h5
            DynamicText.STYLE_H6 -> MaterialTheme.typography.h6
            else -> MaterialTheme.typography.body2
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = dynamicText.spaceTop.dp,
                bottom = dynamicText.spaceBottom.dp,
                start = dynamicText.spaceStart.dp,
                end = dynamicText.spaceEnd.dp
            ),
        textAlign = when (dynamicText.horizontalAlignment) {
            Dynamics.H_ALIGNMENT_START -> TextAlign.Start
            Dynamics.H_ALIGNMENT_CENTER -> TextAlign.Center
            else -> TextAlign.End
        }
    )
}

@Composable
private fun DynamicImage(dynamicImage: DynamicImage) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = when (dynamicImage.horizontalAlignment) {
            Dynamics.H_ALIGNMENT_CENTER -> Arrangement.Center
            Dynamics.H_ALIGNMENT_END -> Arrangement.End
            else -> Arrangement.Start

        }
    ) {
        Image(
            painterResource(id = dynamicImage.image),
            contentDescription = null,
            modifier = Modifier
                .width(dynamicImage.width.dp)
                .height(dynamicImage.height.dp)
                .padding(
                    top = dynamicImage.spaceTop.dp,
                    bottom = dynamicImage.spaceBottom.dp,
                    start = dynamicImage.spaceStart.dp,
                    end = dynamicImage.spaceEnd.dp
                )
        )
    }
}
