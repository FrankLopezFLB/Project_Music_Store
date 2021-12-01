package com.desarrollo.project_music_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_listado_producto.*
import kotlinx.android.synthetic.main.activity_listado_servicios.*
import org.json.JSONArray

class ListadoServiciosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_servicios)

        var ruta_servicio="http://192.168.1.150/servicio/servicioServicio/listar_servicio.php"
        var hilo=Thread(Runnable{
            var resultado=Utilitario.traerDatos_String(ruta_servicio)
            Log.i("datos",resultado)
            //
            runOnUiThread{
                mostrarServicios(resultado)
            }
        })
        hilo.start()
    }

    fun mostrarServicios(cadena:String){
        var lista=ArrayList<String>()
        var cad=""
        var json= JSONArray(cadena)
        var cant_elementos=json.length()-1
        for(i in 0..cant_elementos)
        {
                cad="Codigo: "+ json.getJSONObject(i).getInt("codigoServ")+"\n"+
                    "Nombre: "+ json.getJSONObject(i).getString("nombre")+"\n"+
                    "Descripcion: "+ json.getJSONObject(i).getString("descripcion")+"\n"+
                    "Categoria: "+ json.getJSONObject(i).getString("nombrecat")+"\n"+
                    "Precio: "+ json.getJSONObject(i).getDouble("precio")
            lista.add(cad)
        }
        var adaptador= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista)
        lvServicios.adapter=adaptador
    }
}