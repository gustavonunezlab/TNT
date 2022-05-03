package com.example.demo.Historial

import com.example.demo.R

class Historial(
    val id: Int,
    val title: String,
    val description: String,
    val featuredImage: Int
) {
    companion object {
        val data
            get() = listOf(
                Historial(
                    1,
                    "Dorados",
                    "Pesca embarcado en Lago Puelo",
                    R.drawable.pez1
                ),
                Historial(
                    2,
                    "Peces de agua dulce",
                    "Incursiona en el mundo de la pesca en agua dulce",
                    R.drawable.pez2
                ),
                Historial(
                    3,
                    "Fondo del mar",
                    "¿Qué misterios esconde el fondo del mar?",
                    R.drawable.pez3
                ),
                Historial(
                    4,
                    "¡Qué boquita!",
                    "Una de las especias más raras",
                    R.drawable.pez4
                ),
                Historial(
                    5,
                    "Probando encarnar con anchoa",
                    "Resultado de variar la carnada en el Golfo Nuevo",
                    R.drawable.pez5
                ),
                Historial(
                    6,
                    "Terrible presa!",
                    "Captura realizada desde la playa, Puerto Pirámides",
                    R.drawable.pez6
                ),
                Historial(
                    7,
                    "Dibujo de mi sobrina",
                    "Pala vosh tío",
                    R.drawable.pez7
                ),
                Historial(
                    8,
                    "Uno escondido",
                    "Especie vista al bucear en las playas de Puerto Madryn",
                    R.drawable.pez8
                ),
                Historial(
                    9,
                    "Otro dorado",
                    "Captura del 2019 en el Delta del Tigre",
                    R.drawable.pez9
                ),
                Historial(
                    10,
                    "Primer Surubí",
                    "Captura realizada en los Rápidos del Paraná",
                    R.drawable.pez10
                )
            )
    }
}