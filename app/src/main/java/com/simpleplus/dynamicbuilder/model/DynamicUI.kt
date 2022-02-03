package com.simpleplus.dynamicbuilder.model

class DynamicUI(
    val parent : DynamicParent,
    val uiElements : List<Dynamics>,
    val answers : List<Int> // This list can contain 1 or more ids of choice/answer blocks.
)