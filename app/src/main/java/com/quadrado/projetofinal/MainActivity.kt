package com.quadrado.projetofinal

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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

        listaCarros = findViewById<ListView>(R.id.listaCarros)
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

        listaCarros = findViewById<ListView>(R.id.listaCarros)
        listaCarros.setOnItemLongClickListener { _, _, position, _ ->
            val carroSelecionado = Database.getInstance(this)!!.carroDAO().listarId()[position]

            AlertDialog.Builder(this).apply {
                setTitle("Remover Carro")
                setMessage("Tem certeza?")
                setPositiveButton("Remover") { _, _ ->
                    Database.getInstance(this@MainActivity)!!.carroDAO().apagar(carroSelecionado)

                    atualizarLista()
                }
                setNegativeButton("Cancelar") { dialog, _ ->

                    dialog.dismiss()
                }
            }.create().show()

            true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_teste, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val listaDeCarros = findViewById<ListView>(R.id.listaCarros)
        when (item.itemId) {
            R.id.menuId -> {
                val listaBD = Database.getInstance(this)!!.carroDAO().listarId()
                val listaAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaBD)
                listaDeCarros.adapter = listaAdapter
            }
            R.id.menuMarca -> {
                val listaBD = Database.getInstance(this)!!.carroDAO().listarMarca()
                val listaAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaBD)
                listaDeCarros.adapter = listaAdapter
            }
            R.id.menuModelo -> {
                val listaBD = Database.getInstance(this)!!.carroDAO().listarModelo()
                val listaAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaBD)
                listaDeCarros.adapter = listaAdapter
            }
            R.id.menuAno -> {
                val listaBD = Database.getInstance(this)!!.carroDAO().listarAno()
                val listaAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaBD)
                listaDeCarros.adapter = listaAdapter
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        atualizarLista()
    }

    fun atualizarLista() {
        val listaDeCarros = findViewById<ListView>(R.id.listaCarros)
        val listaBD = Database.getInstance(this)!!.carroDAO().listarId()
        val listaAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaBD)
        listaDeCarros.adapter = listaAdapter

    }
}
