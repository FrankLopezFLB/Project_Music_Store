package com.desarrollo.project_music_store

import android.content.Intent
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
import java.lang.Exception
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

        btnIrListarEnvios.setOnClickListener {
            var i = Intent(this, ListaEnviosActivity::class.java)
            startActivity(i)
        }

    }

    fun grabarEnvio(v: View) {

        try {
            var descripcion = edtDescripcionEnvioRegistrar.text.toString().trim()

            if (descripcion.isNullOrEmpty()) {
                showToast("El campo descripción es obligatorio")
                return;
            }

            var idProd = spProductosEnvioRegistrar.selectedItemPosition

            if (idProd == 0) {
                showToast("Seleccione un Producto válido")
                return;
            }

            var idCli = spClientesEnvioRegistrar.selectedItemPosition

            if (idCli == 0) {
                showToast("Seleccione un Cliente Válido")
                return;
            }

            var direccion = edtDireccionEnvioRegistrar.text.toString().trim()

            if (direccion.isNullOrEmpty()) {
                showToast("El campo descripción es obligatorio")
                return;
            }

            var distrito = edtDistritoEnvioRegistrar.text.toString().trim()

            if (distrito.isNullOrEmpty()) {
                showToast("El campo distrito es obligatorio")
                return;
            }

            var strCantidad = edtCantidadEnvioRegistrar.text.toString().trim()

            if (strCantidad.isNullOrEmpty()) {
                showToast("El campo cantidad es obligatorio")
                return;
            }

            var cantidad = strCantidad.toInt()

            if (cantidad <= 0) {
                showToast("La cantidad debe ser un número mayor a cero")
                return;
            }

            var parametros = "?xdescripcion=${descripcion}&xidprod=${idProd}&xidcli=${idCli}&xdireccion=${direccion}&xdistrito=${distrito}&xcantidad=${cantidad}"

            var rutaServicio = "http://192.168.18.5:8085/servicio/servicioEnvio/nuevo_envio.php${parametros}"

            var hilo = Thread(Runnable {
                var rpta = Utilitario.enviarDatos_String(rutaServicio)

                runOnUiThread {
                    if (rpta == "ok") {
                        clear()
                        showToast("Nuevo Envío realizado")
                    } else {
                        showToast("Hubo problemas al generar el envío")
                    }

                }
            })

            hilo.start()

        } catch (err: Exception) {
            showToast("Error inesperado, verifique que todos los campos estén correctamente ingresados")
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
        edtDescripcionEnvioRegistrar.text = null;
        spProductosEnvioRegistrar.setSelection(0)
        spClientesEnvioRegistrar.setSelection(0)
        edtDireccionEnvioRegistrar.text = null
        edtDistritoEnvioRegistrar.text = null
        edtCantidadEnvioRegistrar.text = null
    }


}