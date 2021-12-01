package com.desarrollo.project_music_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lista_envios.*
import org.json.JSONArray

class ListaEnviosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_envios)

        var rutaServicio = "http://192.168.18.5:8085/servicio/servicioEnvio/listar_envios.php"
        var hilo = Thread(Runnable {
            var resultado = Utilitario.traerDatos_String(rutaServicio)
            runOnUiThread {
                mostrarEnvios(resultado)
            }
        })

        hilo.start()

        lvEnvios.setOnItemClickListener { parent, view, position, id ->
            // Mostrar el elemento seleccionado
            var text = lvEnvios.getItemAtPosition(position).toString();

            var items = text.split(";")
            var obj = items[0].split(":")
            var value = obj[1].trim() // ID

            Toast.makeText(
                applicationContext,
                value,
                Toast.LENGTH_SHORT)
                .show()
        }

    }

    fun mostrarEnvios(cadena: String) {
        var lista = ArrayList<String>()
        var cad = ""
        var json = JSONArray(cadena)
        var cantidadElementos = json.length() - 1

        for (i in 0..cantidadElementos) {
            cad = "Id: " + json.getJSONObject(i).getString("id") + ";\n" +
                  "Descripción: " + json.getJSONObject(i).getString("descripcion") + ";\n" +
                  "CodProd: " + json.getJSONObject(i).getString("idProd") + ";\n" +
                  "Cantidad: " + json.getJSONObject(i).getString("cantidad") + ";\n" +
                  "CodCliente: " + json.getJSONObject(i).getString("idCli") + ";"


            lista.add(cad)
        }

        var adaptador = ArrayAdapter<String> (
            applicationContext,
            android.R.layout.simple_list_item_1,
            lista
        )

        lvEnvios.adapter = adaptador

    }

}