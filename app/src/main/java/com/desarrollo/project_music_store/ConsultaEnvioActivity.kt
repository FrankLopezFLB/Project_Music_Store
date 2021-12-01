package com.desarrollo.project_music_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_consulta_envio.*
import kotlinx.android.synthetic.main.activity_consulta_producto.*
import kotlinx.android.synthetic.main.activity_nuevo_envio.*
import kotlinx.android.synthetic.main.activity_nuevo_envio.edtCantidadEnvioRegistrar
import kotlinx.android.synthetic.main.activity_nuevo_envio.edtDireccionEnvioRegistrar
import kotlinx.android.synthetic.main.activity_nuevo_envio.edtDistritoEnvioRegistrar
import kotlinx.android.synthetic.main.activity_nuevo_envio.spClientesEnvioRegistrar
import kotlinx.android.synthetic.main.activity_nuevo_envio.spProductosEnvioRegistrar
import org.json.JSONArray
import java.util.ArrayList

class ConsultaEnvioActivity : AppCompatActivity() {
    var clienteSeleccionado=""
    var productoSeleccionado=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_envio)

        var rutaServicioProd = "http://192.168.1.100:8085/servicio/servicioEnvio/listar_productos.php"
        var hilo1 = Thread(Runnable {
            var resultado = Utilitario.traerDatos_String(rutaServicioProd)
            runOnUiThread {
                loadProductoSpinner(resultado)
            }
        })
        hilo1.start()

        var rutaServicioCliente = "http://192.168.1.100:8085/servicio/servicioEnvio/listar_clientes.php"
        var hilo2 = Thread(Runnable {
            var resultado = Utilitario.traerDatos_String(rutaServicioCliente)
            runOnUiThread {
                loadClienteSpinner(resultado)
            }
        })

        hilo2.start()



    }

    fun BotonCerrarEnvio(v: View) {
        finish()
    }

    fun BotonConsultarEnvio(v:View) {
        try{
            var codigo = edtCodigoConsultarEnvio.text.toString().toInt()
            if(codigo!=null){
                var hilo = Thread(Runnable {
                    var parametro = "?xcod=" + codigo
                    var ruta_servicio = "http://192.168.1.100:8085/servicio/servicioEnvio/envio_por_codigo.php" + parametro
                    var resultado = Utilitario.traerDatos_String(ruta_servicio)
                    runOnUiThread {
                        BuscarEnvioCodigo(resultado)
                    }
                })
                hilo.start()}

        }
        catch (ex: Exception) {
            Toast.makeText(this, "Error, codigo No Encontrado", Toast.LENGTH_SHORT).show()
            Log.e("mi_error", ex.message.toString())
        }

    }

    fun BotonEliminarEnvio(v: View){
        try{
            var codigo = edtCodigoConsultarEnvio.text.toString().toInt()
            if(codigo!=null){
                var hilo = Thread(Runnable {
                    var parametro = "?xcod=" + codigo
                    var ruta_servicio = "http://192.168.1.100:8085/servicio/servicioEnvio/eliminar_envio.php" + parametro
                    Utilitario.enviarDatos_String(ruta_servicio)
                    runOnUiThread {
                       clear()
                        Snackbar.make(v, "Envio Eliminado", 3000).show()

                    }
                })
                hilo.start()}
        }catch (ex: Exception) {
            Toast.makeText(this, "Error, codigo No Encontrado", Toast.LENGTH_SHORT).show()
            Log.e("mi_error", ex.message.toString())
        }
    }



    fun BuscarEnvioCodigo(cadena: String) {
        try {
            //
            var cad: String = "";
            //
            var json = JSONArray(cadena)
            //
            var cant_elementos = json.length() - 1
            // recorrer los indices del json Array
            if (cant_elementos >= 0) {
                for (i in 0..cant_elementos) {
                    // recuperar el nombre, el nro de horas y el codigo de carrera
                    edtDescripcionConsultarEnvio.setText(json.getJSONObject(i).getString("descripcion"))
                    spProductosConsultarEnvio.setSelection(json.getJSONObject(i).getInt("idProd"))
                    spClientesConsultarEnvio.setSelection(json.getJSONObject(i).getInt("idCli"))
                    edtDireccionConsultarEnvio.setText(json.getJSONObject(i).getString("direccion"))
                    edtDistritoConsultarEnvio.setText(json.getJSONObject(i).getString("distrito"))
                    edtCantidadConsultarEnvio.setText(""+json.getJSONObject(i).getInt("cantidad"))

                }
                //
            } else {
               clear()
                Toast.makeText(this, "Error, codigo No Encontrado", Toast.LENGTH_SHORT).show()
            }
        } catch (ex: Exception) {
            Log.e("mi_error", ex.message.toString())
        }
    }


    private fun loadProductoSpinner(cadena: String) {
        var lista = ArrayList<String>()
        lista.add("Seleccione un Producto")
        var cliente = "";
        var json = JSONArray(cadena)
        var cantidadElementos = json.length() - 1

        for (i in 0..cantidadElementos) {
            cliente = json.getJSONObject(i).getString("nombre")

            lista.add(cliente)
        }

        var adapter = ArrayAdapter<String>(
            applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            lista
        )

        spProductosConsultarEnvio.adapter = adapter;
    }

    fun loadClienteSpinner(cadena: String) {
        var lista = ArrayList<String>()
        lista.add("Seleccione un Cliente")
        var cliente = "";
        var json = JSONArray(cadena)
        var cantidadElementos = json.length() - 1

        for (i in 0..cantidadElementos) {
            cliente = json.getJSONObject(i).getString("nom")

            lista.add(cliente)
        }

        var adapter = ArrayAdapter<String>(
            applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            lista
        )

        spClientesConsultarEnvio.adapter = adapter
    }



    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun clear() {
        edtDescripcionConsultarEnvio.text = null;
        spProductosConsultarEnvio.setSelection(0)
        spClientesConsultarEnvio.setSelection(0)
        edtDireccionConsultarEnvio.text = null
        edtDistritoConsultarEnvio.text = null
        edtCantidadConsultarEnvio.text = null
    }
}