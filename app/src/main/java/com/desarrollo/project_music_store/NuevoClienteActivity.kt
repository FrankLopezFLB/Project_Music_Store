package com.desarrollo.project_music_store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_nuevo_cliente.*
import java.lang.Exception

class NuevoClienteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_cliente)

        btnRegresarNC.setOnClickListener {
            var i = Intent(this,MantenimientoClienteActivity::class.java)
            startActivity(i)
        }
    }

    fun GrabarCliente(v: View)
    {
        try {
            var nombre = txtNombreNC.text.toString()
            var apellido = txtApellidoNC.text.toString()
            var telefono = txtTelefonoNC.text.toString()
            var direccion = txtDireccionNC.text.toString()
            var correo = txtEmailNC.text.toString()
            var dni = txtDniNC.text.toString()

            if(nombre=="" || apellido =="" || telefono =="" || direccion =="" || correo==""||dni==""){
                var hilo = Thread(Runnable {
                    runOnUiThread {
                        Snackbar.make(v, "Hubo Problemas al grabar los datos", 5000).show()
                    }
                })
                hilo.start()
            }
            else{
                var parametros = "?xnom=" + nombre +
                        "&xape=" + apellido +
                        "&xtel=" + telefono +
                        "&xdir=" + direccion +
                        "&xema=" + correo +
                        "&xdni=" + dni

                var ruta_servicio = "http://192.168.1.150/servicio/servicioCliente/nuevo_cliente.php" + parametros

                var hilo = Thread(Runnable {
                    var rpta = Utilitario.traerDatos_String(ruta_servicio)
                    runOnUiThread {
                        Snackbar.make(v, "Cliente " + nombre +" " + apellido + " agregado", 5000).show()
                    }
                })
                hilo.start()
            }
        }
        catch (ex: Exception) {
            var hilo = Thread(Runnable {
                runOnUiThread {
                    Snackbar.make(v, "Hubo Problemas al grabar los datos", 5000).show()
                }
            })
            hilo.start()
        }
        txtNombreNC.setText("")
        txtApellidoNC.setText("")
        txtTelefonoNC.setText("")
        txtDireccionNC.setText("")
        txtEmailNC.setText("")
        txtDniNC.setText("")
    }
}