package com.simpleplus.dynamicbuilder.presentation.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.simpleplus.dynamicbuilder.R
import com.simpleplus.dynamicbuilder.model.*
import kotlin.concurrent.thread

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
            /*painterResource(id = dynamicImage.image)*/
            painterResource(id = R.drawable.ic_baseline_accessible_forward_24),
            contentDescription = null,
            modifier = Modifier
                .width(120.dp)
                .height(120.dp)
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
private fun DynamicChoiceBox(
    dynamicBox: DynamicChoiceBox,
    currentSelectedBoxesId: List<Int> = emptyList(),
    onClick: (DynamicChoiceBox) -> Unit = {}
) {

    Box(
        modifier = Modifier
            .background(
                if (currentSelectedBoxesId.contains(dynamicBox.boxId)) Color(0xFF9CCC65) else Color(
                    0xFF29B6F6
                ),
                RoundedCornerShape(16.dp)
            )
            .padding(top = 1.dp, bottom = 8.dp, start = 1.dp, end = 1.dp)
            .background(
                if (currentSelectedBoxesId.contains(dynamicBox.boxId)) Color(0xFFE2F5CD) else MaterialTheme.colors.surface,
                RoundedCornerShape(16.dp)
            )
            .clickable {
                onClick(dynamicBox)
            }
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

@Composable
fun DynamicChoiceLayout(
    dynamicChoiceLayout: DynamicChoiceLayout,
) {

    val currentSelectedBoxesId = remember {
        mutableStateListOf(-1)
    }


    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        mainAxisAlignment = FlowMainAxisAlignment.Center,
        mainAxisSpacing = 16.dp,
        crossAxisSpacing = 16.dp
    ) {
        dynamicChoiceLayout.choices.forEach {

            DynamicChoiceBox(
                dynamicBox = it,
                currentSelectedBoxesId = currentSelectedBoxesId
            ) { box ->

                if (!dynamicChoiceLayout.isMultiChoice) currentSelectedBoxesId.clear()

                if (currentSelectedBoxesId.contains(it.boxId)) {
                    currentSelectedBoxesId.remove(box.boxId)
                } else {
                    currentSelectedBoxesId.add(box.boxId)
                }
            }
        }
    }
}


@Preview
@Composable
fun BoxesPrev() {

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

@Preview
@Composable
fun BoxesLayoutPrev() {

    DynamicChoiceLayout(
        dynamicChoiceLayout = DynamicChoiceLayout(
            isMultiChoice = true, listOf(
                DynamicChoiceBox("Box 1", null, true, boxId = 0),
                DynamicChoiceBox("Box 2", null, true, boxId = 1),
                DynamicChoiceBox("Box 3", null, true, boxId = 2),
                DynamicChoiceBox("Box 4", null, true, boxId = 3),
                DynamicChoiceBox("Box 1", null, true, boxId = 4)
            )
        ),
    )
}