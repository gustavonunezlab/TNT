package com.example.demo.Contest

import com.example.demo.R

class Contest(
    val id: Int,
    val title: String,
    val description: String,
    val featuredImage: Int
) {
    companion object {
        val data
            get() = listOf(
                Contest(
                    1,
                    "Pesca de Tiburones",
                    "Increíbles premios a la captura del tiburón más grande",
                    R.drawable.hilux
                ),
                Contest(
                    2,
                    "¡Gran premio en dólares!",
                    "Participe del torneo de pesca de cornalitos",
                    R.drawable.dolares
                ),
                Contest(
                    3,
                    "UNA TIRA DE ASADO!!!",
                    "Sorteamos una tira de asado entre los participantes que atrapen un pez con patas",
                    R.drawable.asado
                ),
            )
    }
}