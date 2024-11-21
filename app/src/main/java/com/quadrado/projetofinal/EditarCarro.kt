package com.quadrado.projetofinal

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EditarCarro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar_carro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val botaoConfirmarEdicao = findViewById<ImageButton>(R.id.btnSalvarEdicao)
        botaoConfirmarEdicao.setOnClickListener{

        }

        val botaoCancelarEdicao = findViewById<ImageButton>(R.id.btnCancelarEdicao)
        botaoCancelarEdicao.setOnClickListener{
            finish()
        }


    }
}