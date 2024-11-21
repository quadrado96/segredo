package com.quadrado.projetofinal

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CarroDAO {

    @Insert
    fun salvar(c: Carro)

    @Query("SELECT * FROM Carro ORDER BY id")
    fun listarId(): List<Carro>

    @Query("SELECT * FROM Carro ORDER BY marca")
    fun listarMarca(): List<Carro>

    @Query("SELECT * FROM Carro ORDER BY modelo")
    fun listarModelo(): List<Carro>

    @Query("SELECT * FROM Carro ORDER BY ano")
    fun listarAno(): List<Carro>

    @Update
    fun atualizar(c: Carro)

    @Delete
    fun apagar(c: Carro)

}