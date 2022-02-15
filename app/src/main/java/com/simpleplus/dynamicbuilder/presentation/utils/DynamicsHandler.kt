package com.simpleplus.dynamicbuilder.presentation.utils

import com.simpleplus.dynamicbuilder.model.DynamicHeadersContainer
import com.simpleplus.dynamicbuilder.model.DynamicParent
import com.simpleplus.dynamicbuilder.model.Dynamics
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class DynamicsHandler {

    companion object {

        const val JSON_FOR_UI = "{\n" +
                "  \"parent\": {\n" +
                "    \"backgroundColor\": \"#FFFFFF\",\n" +
                "    \"btnText\": \"Btn text\"\n" +
                "  },\n" +
                "  \"uiElements\": [\n" +
                "    {\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"backgroundColor\": \"#FFFFFF\",\n" +
                "          \"iconTint\": \"#FFFFFF\",\n" +
                "          \"icon\": 0,\n" +
                "          \"type\": \"HEADER_ITEM\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"backgroundColor\": \"#FFFFFF\",\n" +
                "          \"iconTint\": \"#FFFFFF\",\n" +
                "          \"icon\": 0,\n" +
                "          \"type\": \"HEADER_ITEM\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"backgroundColor\": \"#FFFFFF\",\n" +
                "          \"iconTint\": \"#FFFFFF\",\n" +
                "          \"icon\": 0,\n" +
                "          \"type\": \"HEADER_ITEM\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"type\": \"HEADER\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Elias é um elefeante que incomoda muita gente.\",\n" +
                "      \"style\": 5,\n" +
                "      \"color\": \"#000000\",\n" +
                "      \"spaceTop\": 16,\n" +
                "      \"spaceBottom\": 16,\n" +
                "      \"spaceStart\": 16,\n" +
                "      \"spaceEnd\": 16,\n" +
                "      \"startHeaderItem\": {\n" +
                "        \"backgroundColor\": \"#FFFFFF\",\n" +
                "        \"iconTint\": \"#FFFFFF\",\n" +
                "        \"icon\": 0,\n" +
                "        \"type\": \"HEADER_ITEM\"\n" +
                "      },\n" +
                "      \"horizontalAlignment\": 1,\n" +
                "      \"type\": \"TEXT\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Qual imagem abaixo representa o elias?\",\n" +
                "      \"style\": 6,\n" +
                "      \"color\": \"#000000\",\n" +
                "      \"spaceTop\": 16,\n" +
                "      \"spaceBottom\": 16,\n" +
                "      \"spaceStart\": 16,\n" +
                "      \"spaceEnd\": 16,\n" +
                "      \"horizontalAlignment\": 1,\n" +
                "      \"type\": \"TEXT\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"image\": 0,\n" +
                "      \"width\": 88,\n" +
                "      \"height\": 88,\n" +
                "      \"spaceTop\": 24,\n" +
                "      \"spaceBottom\": 24,\n" +
                "      \"spaceStart\": 16,\n" +
                "      \"spaceEnd\": 16,\n" +
                "      \"horizontalAlignment\": 1,\n" +
                "      \"type\": \"IMAGE\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"isMultiChoice\": true,\n" +
                "      \"boxes\": [\n" +
                "        {\n" +
                "          \"text\": \"True\",\n" +
                "          \"image\": null,\n" +
                "          \"isRight\": true,\n" +
                "          \"boxId\": 0\n" +
                "        },\n" +
                "        {\n" +
                "          \"text\": \"True\",\n" +
                "          \"image\": null,\n" +
                "          \"isRight\": true,\n" +
                "          \"boxId\": 1\n" +
                "        },\n" +
                "        {\n" +
                "          \"text\": \"False\",\n" +
                "          \"image\": null,\n" +
                "          \"isRight\": false,\n" +
                "          \"boxId\": 2\n" +
                "        },\n" +
                "        {\n" +
                "          \"text\": \"False\",\n" +
                "          \"image\": null,\n" +
                "          \"isRight\": false,\n" +
                "          \"boxId\": 3\n" +
                "        }\n" +
                "      ],\n" +
                "      \"type\": \"CHOICE_LAYOUT\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"answers\": [\n" +
                "    0,\n" +
                "    1\n" +
                "  ]\n" +
                "}"

    }

    private lateinit var moshi: Moshi

    init {
        createMoshiInstance()
    }

    private fun createMoshiInstance() {
        //https://proandroiddev.com/moshi-polymorphic-adapter-is-d25deebbd7c5
        moshi = Moshi.Builder()
            .add(
                PolymorphicJsonAdapterFactory.of(Dynamics::class.java, "type")
                    .withSubtype(
                        DynamicParent::class.java,
                        Dynamics.DynamicsTypes.PARENT.name
                    )
                    .withSubtype(
                        DynamicHeadersContainer::class.java,
                        Dynamics.DynamicsTypes.HEADER.name
                    )
                    .withSubtype(
                        DynamicHeadersContainer.DynamicHeaderItem::class.java,
                        Dynamics.DynamicsTypes.HEADER_ITEM.name
                    )
                    .withSubtype(
                        com.simpleplus.dynamicbuilder.model.DynamicText::class.java,
                        Dynamics.DynamicsTypes.TEXT.name
                    )
                    .withSubtype(
                        com.simpleplus.dynamicbuilder.model.DynamicImage::class.java,
                        Dynamics.DynamicsTypes.IMAGE.name
                    )
                    .withSubtype(
                        com.simpleplus.dynamicbuilder.model.DynamicChoiceBoxContainer.DynamicChoiceBox::class.java,
                        Dynamics.DynamicsTypes.CHOICE_BOX.name
                    )
                    .withSubtype(
                        com.simpleplus.dynamicbuilder.model.DynamicChoiceBoxContainer::class.java,
                        Dynamics.DynamicsTypes.CHOICE_LAYOUT.name
                    )
            )
            .add(KotlinJsonAdapterFactory()).build()
    }

    fun <T> getData(jsonData: String, `class`: Class<T>): T? {
        val adapter: JsonAdapter<T> = moshi.adapter(`class`)

        return adapter.fromJson(jsonData)
    }

}