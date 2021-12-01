package com.desarrollo.project_music_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_nuevo_envio.*
import org.json.JSONArray
import java.util.ArrayList

class NuevoEnvioActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_envio)

        var rutaServicioProd = "http://192.168.18.5:8085/servicio/servicioEnvio/listar_productos.php"
        var hilo1 = Thread(Runnable {
            var resultado = Utilitario.traerDatos_String(rutaServicioProd)
            runOnUiThread {
                loadProductoSpinner(resultado)
            }
        })
        hilo1.start()

        var rutaServicioCliente = "http://192.168.18.5:8085/servicio/servicioEnvio/listar_clientes.php"
        var hilo2 = Thread(Runnable {
            var resultado = Utilitario.traerDatos_String(rutaServicioCliente)
            runOnUiThread {
                loadClienteSpinner(resultado)
            }
        })

        hilo2.start()
    }

    fun grabarEnvio(v: View) {
        Log.wtf("e", "Prueba")
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

        spProductosEnvioRegistrar.adapter = adapter;
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

        spClientesEnvioRegistrar.adapter = adapter
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun clear() {
        edtCodigoEnvioRegistrar.text = null;
        edtDescripcionEnvioRegistrar.text = null;
        spProductosEnvioRegistrar.setSelection(0)
        spClientesEnvioRegistrar.setSelection(0)
        edtDireccionEnvioRegistrar.text = null
        edtDistritoEnvioRegistrar.text = null
        edtCantidadEnvioRegistrar.text = null
    }


}