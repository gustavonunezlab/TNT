package com.example.demo.Contest

import com.example.demo.R

class Contest(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val featuredImage: Int
) {
    companion object {
        val data
            get() = listOf(
                Contest(
                    1,
                    "Pesca de Tiburones",
                    "Increíbles premios a la captura del tiburón más grande.",
                    "05/11/2025",
                    R.drawable.hilux
                ),
                Contest(
                    2,
                    "¡Gran premio en dólares!",
                    "Participe del torneo de pesca de cornalitos.",
                    "17/05/2024",
                    R.drawable.dolares
                ),
                Contest(
                    3,
                    "UNA TIRA DE ASADO!!!",
                    "Sorteamos una tira de asado entre los participantes que atrapen un pez con patas.",
                    "07/12/2023",
                    R.drawable.asado
                ),
            )
    }
}