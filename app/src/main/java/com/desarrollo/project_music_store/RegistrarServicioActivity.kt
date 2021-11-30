package com.desarrollo.project_music_store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_registrar_servicio.*

class RegistrarServicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_servicio)
    }

        fun btnGrabarServicio(v: View){
    try{
        Log.wtf("combo",categoria_seleccionada)
        var codigo = null
        var nombre= txtNombre.text.toString()
        var descripcion = txtDescripcion.text.toString()
        var categoria = categoria_seleccionada.toInt()
        var precio =edtPreProd.text.toString().toDouble()

        var parametros="?xnom=" + nombre +
                "&xdesc=" +descripcion+
                "&xcat=" + categoria +
                "&xprecio=" +precio
        Log.wtf("parametros",parametros)
        var ruta_servicio="http://192.168.1.100:8085/servicio/servicioServicio/nuevo_servicio.php"+parametros
        var hilo=Thread(Runnable{
            Utilitario.enviarDatos_String(ruta_servicio)
            runOnUiThread {
                Snackbar.make(v,"Nuevo servicio grabado correctamente",3000).show()
            }
        })
        //
        hilo.start()}catch (ex: Exception) {
        Toast.makeText(this, "Inserte datos por favor", Toast.LENGTH_SHORT).show()
        Log.e("mi_error", ex.message.toString())
    }
   }
}