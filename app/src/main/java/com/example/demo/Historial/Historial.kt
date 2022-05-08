package com.example.demo.Historial

import com.example.demo.R

class Historial(
    val id: Int,
    val title: String,
    val fishingType: String,
    val featuredImage: Int
) {
    companion object {
        val data
            get() = listOf(
                Historial(
                    1,
                    "Dorados",
                    "Pesca deportiva",
                    R.drawable.pez1
                ),
                Historial(
                    2,
                    "Peces de agua dulce",
                    "Pesca científica",
                    R.drawable.pez2
                ),
                Historial(
                    3,
                    "Fondo del mar",
                    "Pesca comercial",
                    R.drawable.pez3
                ),
                Historial(
                    4,
                    "¡Qué boquita!",
                    "Pesca deportiva",
                    R.drawable.pez4
                ),
                Historial(
                    5,
                    "Probando encarnar con anchoa",
                    "Pesca comercial",
                    R.drawable.pez5
                ),
                Historial(
                    6,
                    "Terrible presa!",
                    "Pesca deportiva",
                    R.drawable.pez6
                ),
                Historial(
                    7,
                    "Dibujo de mi sobrina",
                    "Pesca turística",
                    R.drawable.pez7
                ),
                Historial(
                    8,
                    "Uno escondido",
                    "Pesca turística",
                    R.drawable.pez8
                ),
                Historial(
                    9,
                    "Otro dorado",
                    "Pesca turística",
                    R.drawable.pez9
                ),
                Historial(
                    10,
                    "Primer Surubí",
                    "Pesca turística",
                    R.drawable.pez10
                )
            )
    }
}