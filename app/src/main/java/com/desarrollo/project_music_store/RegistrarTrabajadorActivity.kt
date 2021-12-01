package com.desarrollo.project_music_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_registrar_producto.*
import kotlinx.android.synthetic.main.activity_registrar_trabajador.*

class RegistrarTrabajadorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_trabajador)
    }

    fun BotonGrabarTrabajador(v: View){
        try{

            var nombre= edtNomTra.text.toString()
            var apellido = edtApeTra.text.toString()
            var dni = edtDNITra.text.toString()
            var cargo = 2
            var telefono = edtFonoTra.text.toString()
            var direccion = edtDireccionTra.text.toString()
            var correo = edtCorreoTra.text.toString()
            var clave = edtClaveTra.text.toString()
            var estado = 1;

            var parametros="?xnom=" + nombre +
                    "&xape=" +apellido+
                    "&xdni=" + dni +
                    "&xcar=" +cargo+
                    "&xtele=" +telefono+
                    "&xdire=" +direccion+
                    "&xcorre="+correo+
                    "&xclav="+clave+
                    "&xestado="+estado
            Log.wtf("parametros",parametros)
            var ruta_servicio="http://192.168.1.150/servicio/servicioProducto/nuevo_trabajador.php"+parametros
            var hilo=Thread(Runnable{
                Utilitario.enviarDatos_String(ruta_servicio)
                runOnUiThread {
                    Snackbar.make(v,"Nuevo trabajador grabado correctamente",3000).show()

                }
            })
            //
            hilo.start()}catch (ex: Exception) {
            Toast.makeText(this, "Inserte datos por favor", Toast.LENGTH_SHORT).show()
            Log.e("mi_error", ex.message.toString())
        }
    }
}