package com.desarrollo.project_music_store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_redirect.*
import kotlinx.android.synthetic.main.activity_registrar_servicio.*

class RedirectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redirect)

        btnRedirectServicios.setOnClickListener {
            var i= Intent(this,RegistrarServicioActivity::class.java)
            startActivity(i)
        }
        btnRedirectPruductos.setOnClickListener {
            var i= Intent(this,RegistrarProductoActivity::class.java)
            startActivity(i)
        }
        btnRedirectCliente.setOnClickListener {
            var i= Intent(this,NuevoClienteActivity::class.java)
            startActivity(i)
        }
        btnRedirectEnvios.setOnClickListener {
            var i= Intent(this,NuevoEnvioActivity::class.java)
            startActivity(i)
        }
        btnRedirectTrabajador.setOnClickListener {
            var i= Intent(this,RegistrarTrabajadorActivity::class.java)
            startActivity(i)
        }
    }
}