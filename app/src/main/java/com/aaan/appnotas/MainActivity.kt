package com.aaan.appnotas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaan.appnotas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db : NotasDatabaseHelper
    private lateinit var notasAdaptador : NotasAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotasDatabaseHelper(this)

        notasAdaptador = NotasAdaptador(db.getAllNotas(), this)

        binding.notasRv.layoutManager = LinearLayoutManager(this)
        binding.notasRv.adapter = notasAdaptador

        binding.botonAgregarNota.setOnClickListener {
            startActivity(Intent(applicationContext, AgregarNotaActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        notasAdaptador.refrescarLista(db.getAllNotas())
    }
}