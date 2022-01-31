package com.simpleplus.dynamicbuilder.presentation.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simpleplus.dynamicbuilder.R
import com.simpleplus.dynamicbuilder.model.DynamicChoiceBox
import com.simpleplus.dynamicbuilder.model.DynamicImage
import com.simpleplus.dynamicbuilder.model.DynamicText
import com.simpleplus.dynamicbuilder.model.Dynamics

@Composable
fun DynamicText(dynamicText: DynamicText) {

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
fun DynamicImage(dynamicImage: DynamicImage) {

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

@Composable
fun DynamicChoiceBox(dynamicBox: DynamicChoiceBox) {

    Box(
        modifier = Modifier
            .background(
                if (dynamicBox.isRight) Color(0xFF9CCC65) else Color(0xFF29B6F6),
                RoundedCornerShape(16.dp)
            )
            .padding(top = 1.dp, bottom = 8.dp, start = 1.dp, end = 1.dp)
            .background(
                if (dynamicBox.isRight) Color(0xFFE2F5CD) else MaterialTheme.colors.surface,
                RoundedCornerShape(16.dp)
            )
    ) {

        Column(
            modifier = Modifier
                .height(150.dp)
                .width(180.dp)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            dynamicBox.text?.let {

                Text(
                    text = it,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                )

            }

            dynamicBox.image?.let {

                Icon(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .size(40.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun Prev() {

    val box by remember {
        mutableStateOf(DynamicChoiceBox("Simple Text", R.drawable.ic_baseline_adb_24, true))
    }

    val box2 by remember {
        mutableStateOf(
           DynamicChoiceBox(
                "Slightly larger text",
                R.drawable.ic_baseline_adb_24,
                false
            )
        )
    }

    Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxSize()) {

        DynamicChoiceBox(dynamicBox = box)
        DynamicChoiceBox(dynamicBox = box2)

    }


}