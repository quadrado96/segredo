package com.quadrado.projetofinal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Carro(
    @PrimaryKey(autoGenerate= true)
    val id: Int?,
    val marca: String,
    val modelo: String,
    var ano: Int,
    var placa: String
) {

    override fun toString():String {
        return "$id - $marca $modelo $ano - $placa"
    }

}




