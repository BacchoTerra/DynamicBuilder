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
import com.simpleplus.dynamicbuilder.presentation.viewmodel.MainViewModel

@Composable
fun DynHeadersContainer(items: List<DynamicHeadersContainer.DynamicHeaderItem>) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        items.forEach {
            Spacer(modifier = Modifier.width(8.dp))
            DynHeaderItem(item = it)
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun DynHeaderItem(item: DynamicHeadersContainer.DynamicHeaderItem) {

    Box(
        modifier = Modifier
            .size(48.dp)
            .background(
                color = Color(0xFFAB47BC),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(top = 1.dp, end = 1.dp, start = 1.dp, bottom = 6.dp)
            .background(
                color = Color(android.graphics.Color.parseColor(item.backgroundColor)),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(6.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_accessible_forward_24),
            contentDescription = null,
            tint = Color(android.graphics.Color.parseColor(item.iconTint))
        )
    }
}

@Composable
fun DynText(dynamicText: DynamicText) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (dynamicText.startHeaderItem != null) {
            DynHeaderItem(item = dynamicText.startHeaderItem)
        }

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
}

@Composable
fun DynImage(dynamicImage: DynamicImage) {

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
fun DynChoiceBoxesContainer(
    dynamicChoiceBoxContainer: DynamicChoiceBoxContainer,
    vm: MainViewModel
) {


    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        mainAxisAlignment = FlowMainAxisAlignment.Center,
        mainAxisSpacing = 16.dp,
        crossAxisSpacing = 16.dp
    ) {
        dynamicChoiceBoxContainer.boxes.forEach {

            DynChoiceBox(
                dynamicBox = it,
                currentSelectedBoxesId = vm.currentSelectedChoices
            ) { box ->

                if (!dynamicChoiceBoxContainer.isMultiChoice) vm.currentSelectedChoices.clear()

                if (vm.currentSelectedChoices.contains(it.boxId)) {
                    vm.currentSelectedChoices.remove(box.boxId)
                } else {
                    vm.currentSelectedChoices.add(box.boxId)
                }
            }
        }
    }
}

@Composable
private fun DynChoiceBox(
    dynamicBox: DynamicChoiceBoxContainer.DynamicChoiceBox,
    currentSelectedBoxesId: List<Int> = emptyList(),
    onClick: (DynamicChoiceBoxContainer.DynamicChoiceBox) -> Unit = {}
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


@Preview
@Composable
fun BoxesPrev() {

    val box by remember {
        mutableStateOf(
            DynamicChoiceBoxContainer.DynamicChoiceBox(
                "Simple Text",
                R.drawable.ic_baseline_adb_24,
                true
            )
        )
    }

    val box2 by remember {
        mutableStateOf(
            DynamicChoiceBoxContainer.DynamicChoiceBox(
                "Slightly larger text",
                R.drawable.ic_baseline_adb_24,
                false
            )
        )
    }

    Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxSize()) {

        DynChoiceBox(dynamicBox = box)
        DynChoiceBox(dynamicBox = box2)

    }
}


@Preview
@Composable
fun BoxesLayoutPrev() {

    DynChoiceBoxesContainer(
        dynamicChoiceBoxContainer = DynamicChoiceBoxContainer(
            isMultiChoice = true, listOf(
                DynamicChoiceBoxContainer.DynamicChoiceBox("Box 1", null, true, boxId = 0),
                DynamicChoiceBoxContainer.DynamicChoiceBox("Box 2", null, true, boxId = 1),
                DynamicChoiceBoxContainer.DynamicChoiceBox("Box 3", null, true, boxId = 2),
                DynamicChoiceBoxContainer.DynamicChoiceBox("Box 4", null, true, boxId = 3),
                DynamicChoiceBoxContainer.DynamicChoiceBox("Box 1", null, true, boxId = 4)
            )
        ),
        MainViewModel()
    )
}

@Preview
@Composable
fun HeaderPrev() {

    val items: List<DynamicHeadersContainer.DynamicHeaderItem> = remember {
        mutableStateListOf(
            DynamicHeadersContainer.DynamicHeaderItem("#FF26C6DA", "#FF9CCC65", 0),
            DynamicHeadersContainer.DynamicHeaderItem("#FF26C6DA", "#FF9CCC65", 0),
            DynamicHeadersContainer.DynamicHeaderItem("#FF26C6DA", "#FF9CCC65", 0),
            DynamicHeadersContainer.DynamicHeaderItem("#FF26C6DA", "#FF9CCC65", 0)
        )
    }

    DynHeadersContainer(items = items)

}
