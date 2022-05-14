package com.example.demo.model

class Regulation (
    val id: Int,
    val title: String,
    val description: String,
    ) {
        companion object {
            val data
                get() = listOf(
                    Regulation(
                        1,
                        "Pesca deportiva",
                        "REGLAMENTO DE PESCA DEPORTIVA CONTINENTAL PATAGONICO 2021/2022",
                    ),
                )
        }
    }