package com.simpleplus.dynamicbuilder.presentation.utils

import com.simpleplus.dynamicbuilder.model.DynamicHeaders
import com.simpleplus.dynamicbuilder.model.DynamicParent
import com.simpleplus.dynamicbuilder.model.Dynamics
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class DynamicsHandler {

    companion object {

        const val EXAMPLE_JSON_FOR_LOG = "{\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"text\": \"This is the text\",\n" +
                "      \"style\": 0,\n" +
                "      \"color\": \"#000000\",\n" +
                "      \"spaceTop\": 16,\n" +
                "      \"spaceBottom\": 16,\n" +
                "      \"spaceStart\": 16,\n" +
                "      \"spaceEnd\": 16,\n" +
                "      \"horizontalAlignment\": 0,\n" +
                "      \"type\": \"TEXT\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"This is the text\",\n" +
                "      \"style\": 0,\n" +
                "      \"color\": \"#000000\",\n" +
                "      \"spaceTop\": 16,\n" +
                "      \"spaceBottom\": 16,\n" +
                "      \"spaceStart\": 16,\n" +
                "      \"spaceEnd\": 16,\n" +
                "      \"horizontalAlignment\": 0,\n" +
                "      \"type\": \"TEXT\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"image\": 5,\n" +
                "      \"width\": 24,\n" +
                "      \"height\": 24,\n" +
                "      \"spaceTop\": 16,\n" +
                "      \"spaceBottom\": 16,\n" +
                "      \"spaceStart\": 16,\n" +
                "      \"spaceEnd\": 16,\n" +
                "      \"horizontalAlignment\": 0,\n" +
                "      \"type\": \"IMAGE\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"This is the text\",\n" +
                "      \"image\": null,\n" +
                "      \"isRight\": true,\n" +
                "      \"boxId\": 0,\n" +
                "      \"type\": \"CHOICE_BOX\"\n" +
                "    }\n" +
                "  ]\n" +
                "}"

        const val EXAMPLE_FOR_UI = "{\n" +
                "  \"parent\": {\n" +
                "    \"backgroundColor\": \"#FFFFFF\",\n" +
                "    \"btnText\": \"Btn text\"\n" +
                "  },\n" +
                "  \"items\": [\n" +
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
                "      \"isMultiChoice\": false,\n" +
                "      \"choices\": [\n" +
                "        {\n" +
                "          \"text\": \"True\",\n" +
                "          \"image\": null,\n" +
                "          \"isRight\": true,\n" +
                "          \"boxId\": 0\n" +
                "        },\n" +
                "        {\n" +
                "          \"text\": \"False\",\n" +
                "          \"image\": null,\n" +
                "          \"isRight\": false,\n" +
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
                "          \"boxId\": 23\n" +
                "        },\n" +     "        {\n" +
                "          \"text\": \"False\",\n" +
                "          \"image\": null,\n" +
                "          \"isRight\": false,\n" +
                "          \"boxId\": 28\n" +
                "        }\n" +
                "      ],\n" +
                "      \"type\": \"CHOICE_LAYOUT\"\n" +
                "    }\n" +
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
                        DynamicHeaders::class.java,
                        Dynamics.DynamicsTypes.HEADER.name
                    )
                    .withSubtype(
                        DynamicHeaders.DynamicHeaderItem::class.java,
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
                        com.simpleplus.dynamicbuilder.model.DynamicChoiceLayout.DynamicChoiceBox::class.java,
                        Dynamics.DynamicsTypes.CHOICE_BOX.name
                    )
                    .withSubtype(
                        com.simpleplus.dynamicbuilder.model.DynamicChoiceLayout::class.java,
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