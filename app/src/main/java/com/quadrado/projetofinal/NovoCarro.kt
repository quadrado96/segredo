package com.quadrado.projetofinal

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NovoCarro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_novo_carro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.hide()

        val botaoSalvar = findViewById<ImageButton>(R.id.btnSalvar)
        botaoSalvar.setOnClickListener {
            val marca = findViewById<EditText>(R.id.edtMarca).text.toString()
            val modelo = findViewById<EditText>(R.id.edtModelo).text.toString()
            val ano = findViewById<EditText>(R.id.edtAno).text.toString().toInt()
            val placa = findViewById<EditText>(R.id.edtPlaca).text.toString()

            val c = Carro(null, marca, modelo, ano, placa)

            Database.getInstance(this)!!.carroDAO().salvar(c)

            finish()
        }

        val botaoCancelar = findViewById<ImageButton>(R.id.btnCancelar)
        botaoCancelar.setOnClickListener{
            finish()
        }


    }
}