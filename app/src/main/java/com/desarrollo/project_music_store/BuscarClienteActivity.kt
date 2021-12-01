package com.desarrollo.project_music_store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_buscar_cliente.*
import org.json.JSONArray
import java.lang.Exception

class BuscarClienteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_cliente)

        btnRegresarBC.setOnClickListener {
            var i = Intent(this,MantenimientoClienteActivity::class.java)
            startActivity(i)
        }
    }

    fun BuscarCliente(v: View)
    {
        var hilo=Thread(Runnable {
            var parametro = "?xcod=" + txtCodigoBC.text.toString()
            var ruta_servicio = "http://192.168.1.150/servicio/servicioCliente/buscar_cliente.php"+parametro
            var resultado = Utilitario.traerDatos_String(ruta_servicio)
            runOnUiThread {
                BuscarBC(resultado)
            }
        })
        hilo.start()
    }

    fun BuscarBC(cadena:String)
    {
        var lista=ArrayList<String>()
        var cad="";
        var json = JSONArray(cadena)
        var cant_elementos = json.length()-1
        if (cant_elementos>=0){
            for (i in 0..cant_elementos) {
                txtNombreBC.setText(json.getJSONObject(i).getString("nom"))
                txtApellidoBC.setText(json.getJSONObject(i).getString("ape"))
                txtTelefonoBC.setText(json.getJSONObject(i).getString("tel"))
                txtDireccionBC.setText(json.getJSONObject(i).getString("dir"))
                txtCorreoBC.setText(json.getJSONObject(i).getString("ema"))
                txtDniBC.setText(json.getJSONObject(i).getString("dni"))
            }
        }
        else {
            txtNombreBC.setText("")
            txtApellidoBC.setText("")
            txtTelefonoBC.setText("")
            txtDireccionBC.setText("")
            txtCorreoBC.setText("")
            txtDniBC.setText("")
        }
    }

    fun ActualizarCliente(v:View){
        try {
            var codigo = txtCodigoBC.text.toString().toInt()
            var nombre = txtNombreBC.text.toString()
            var apellido = txtApellidoBC.text.toString()
            var telefono = txtTelefonoBC.text.toString()
            var direccion = txtDireccionBC.text.toString()
            var correo = txtCorreoBC.text.toString()
            var dni = txtDniBC.text.toString()

            if(nombre=="" || apellido =="" || telefono =="" || direccion =="" || correo==""||dni==""){
                var hilo = Thread(Runnable {
                    runOnUiThread {
                        Snackbar.make(v, "Hubo Problemas al actualizar cliente", 5000).show()
                    }
                })
                hilo.start()
            }
            else{
                var parametros = "?xcod=" + codigo +
                        "&xnom=" + nombre +
                        "&xape=" + apellido +
                        "&xtel=" + telefono +
                        "&xdir=" + direccion +
                        "&xema=" + correo +
                        "&xdni=" + dni

                var ruta_servicio = "http://192.168.1.29/proyecto/actualizar_cliente.php" + parametros

                var hilo = Thread(Runnable {
                    var rpta = Utilitario.traerDatos_String(ruta_servicio)
                    runOnUiThread {
                        Snackbar.make(v, "Cliente " + nombre +" " + apellido + " actualizado", 5000).show()
                    }
                })
                hilo.start()
            }
        }
        catch (ex: Exception) {
            var hilo = Thread(Runnable {
                runOnUiThread {
                    Snackbar.make(v, "Problemas al actualizar cliente", 5000).show()
                }
            })
            hilo.start()
        }
    }

    fun BotonEliminar(v: View)
    {
        var parametro = "?xcod=" +txtCodigoBC.text.toString().toInt()
        var ruta_servicio = "http://192.168.1.29/proyecto/eliminar_cliente.php"+parametro

        var hilo = Thread(Runnable {
            var rpta = Utilitario.traerDatos_String(ruta_servicio)
                runOnUiThread {
                    Snackbar.make(v, "Cliente Eliminado", 5000).show()
                }
            })
            hilo.start()
            txtCodigoBC.setText("")
            txtNombreBC.setText("")
            txtApellidoBC.setText("")
            txtTelefonoBC.setText("")
            txtDireccionBC.setText("")
            txtCorreoBC.setText("")
            txtDniBC.setText("")
    }
}