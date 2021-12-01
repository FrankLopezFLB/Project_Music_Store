package com.desarrollo.project_music_store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_mantenimiento_cliente.*

class MantenimientoClienteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mantenimiento_cliente)

        btnListaClientes.setOnClickListener {
            var i = Intent(this,ListaClientesActivity::class.java)
            startActivity(i)
        }

        btnNuevoCliente.setOnClickListener {
            var i = Intent(this,NuevoClienteActivity::class.java)
            startActivity(i)
        }

        btnBuscarCliente.setOnClickListener {
            var i = Intent(this,BuscarClienteActivity::class.java)
            startActivity(i)
        }
    }
}