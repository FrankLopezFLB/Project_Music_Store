package com.desarrollo.project_music_store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_registrar_producto.*
import kotlinx.android.synthetic.main.activity_registrar_servicio.*

class RegistrarServicioActivity : AppCompatActivity() {

    var categoria_seleccionada=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_servicio)

        btnListarServicio.setOnClickListener {
            var i= Intent(this,ListadoServiciosActivity::class.java)
            startActivity(i)
        }

        btnreturn.setOnClickListener {
            finish()
            var i= Intent(this,RedirectActivity::class.java)
            startActivity(i)

        }
    }

        fun btnGrabarServicio(v: View){
    try{
        Log.wtf("combo",categoria_seleccionada)
        var nombre= txtNombre.text.toString()
        var descripcion = txtDescripcion.text.toString()
        var categoria = categoria_seleccionada.toInt()
        var precio =txtPrecio.text.toString().toDouble()
        var estado = 1;

        var parametros="?xnom=" + nombre +
                "&xdesc=" +descripcion+
                "&xcat=" + categoria +
                "&xprecio=" +precio+
                "&xestado="+estado
        Log.wtf("parametros",parametros)
        var ruta_servicio="http://192.168.0.107:80/servicio/servicioServicio/nuevo_servicio.php"+parametros
        var hilo=Thread(Runnable{
            Utilitario.enviarDatos_String(ruta_servicio)
            runOnUiThread {
                Snackbar.make(v,"Nuevo servicio grabado correctamente",3000).show()
            }
        })
        //
        hilo.start()}catch (ex: Exception) {
        Toast.makeText(this, "Inserte datos por favor", Toast.LENGTH_SHORT).show()
        Log.e("ERROR", ex.message.toString())
    }
   }
}