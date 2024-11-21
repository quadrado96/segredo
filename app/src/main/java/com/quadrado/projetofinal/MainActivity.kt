package com.quadrado.projetofinal

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var listaCarros: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listaCarros = findViewById<ListView>(R.id.listaCarros)
        listaCarros.setOnItemClickListener { _, _, position, _ ->
            val carroSelecionado = Database.getInstance(this)!!.carroDAO().listarId()[position]
            val intentEdicao = Intent(this, EditarCarro::class.java)
            intentEdicao.putExtra("carroId", carroSelecionado.id)
            startActivity(intentEdicao)
        }

        val botaoAddCarro = findViewById<FloatingActionButton>(R.id.fabNovoCarro)
        val intentNovoCarro = Intent(this, NovoCarro::class.java)
        botaoAddCarro.setOnClickListener {
            startActivity(intentNovoCarro)
        }
    }

    override fun onResume() {
        super.onResume()
        atualizarLista()
    }

    fun atualizarLista() {
        val listaDeCarros = findViewById<ListView>(R.id.listaCarros)
        val listaBD = Database.getInstance(this)!!.carroDAO().listarId()

        if (listaBD.isEmpty()) {
            Toast.makeText(this, "Nenhum carro cadastrado.", Toast.LENGTH_SHORT).show()
        } else {
            val listaAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaBD)
            listaDeCarros.adapter = listaAdapter
        }
    }
}