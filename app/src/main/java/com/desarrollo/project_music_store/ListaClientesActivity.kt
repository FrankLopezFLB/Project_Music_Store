package com.desarrollo.project_music_store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_lista_clientes.*
import org.json.JSONArray

class ListaClientesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_clientes)

        btnRegresarLC.setOnClickListener {
            var i = Intent(this,MantenimientoClienteActivity::class.java)
            startActivity(i)
        }


        var ruta_servicio = "http://192.168.1.100:8085/servicio/servicioCliente/listar_clientes.php"

        var hilo = Thread(Runnable {
            var resultado = Utilitario.traerDatos_String(ruta_servicio)
            //
            runOnUiThread {
                mostrarClientes(resultado)
            }
        })
        hilo.start()
    }

    fun mostrarClientes(cadena:String)
    {
        var lista=ArrayList<String>()
        var cad="";
        var json = JSONArray(cadena)
        var cant_elementos = json.length()-1
        for (i in 0..cant_elementos)
        {
            cad = "Codigo: "+json.getJSONObject(i).getInt("cod") +"\n"+
                    "Nombre: "+  json.getJSONObject(i).getString("nom")+" "+ json.getJSONObject(i).getString("ape") +"\n"+
                    "Telefono: "+  json.getJSONObject(i).getString("tel") +"\n"+
                    "Dirección: "+json.getJSONObject(i).getString("dir")+"\n"+
                    "Correo: "+json.getJSONObject(i).getString("ema")+"\n"+
                    "DNI: "+json.getJSONObject(i).getString("dni")
            // agregarlo a la lista
            lista.add(cad)
        }
        //
        var adaptador = ArrayAdapter<String>(
            applicationContext,
            android.R.layout.simple_list_item_1,
            lista
        )
        //
        lvClientes.adapter = adaptador
    }
}