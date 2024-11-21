package com.quadrado.projetofinal

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@androidx.room.Database(entities = [Carro::class], version = 1)
abstract class Database: RoomDatabase() {

    abstract fun carroDAO(): CarroDAO

    companion object {

        private var database: Database? = null
        private val DATABASE = "CARROS"

        fun getInstance(context: Context): Database? {
            if(database == null) {
                return criaBanco(context)
            } else {
                return database
            }
        }

        private fun criaBanco(context: Context): Database? {
            return Room.databaseBuilder(
                context, Database::class.java, DATABASE)
                .allowMainThreadQueries().build()
        }
    }


}