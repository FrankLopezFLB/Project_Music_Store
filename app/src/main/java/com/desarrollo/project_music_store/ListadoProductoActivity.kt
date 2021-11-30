package com.desarrollo.project_music_store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_listado_producto.*
import org.json.JSONArray

class ListadoProductoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_producto)

        var ruta_servicio="http://192.168.1.100:8085/servicio/servicioProducto/listar_productos.php"
        //definimos el hilo
        var hilo=Thread(Runnable{
            //obtner los datos como cadena usando el metodo traerdatos
            var resultado=Utilitario.traerDatos_String(ruta_servicio)
            Log.i("datos",resultado)
            //
            runOnUiThread{
                mostrarProductos(resultado)
            }
        })
        //iniciar el hilo
        hilo.start()

        //

    }

    fun mostrarProductos(cadena:String){
        var lista=ArrayList<String>()
        //
        var cad=""
        //
        var json= JSONArray(cadena)
        //
        var cant_elementos=json.length()-1
        //recorrer los indices del JsonArray
        for(i in 0..cant_elementos)
        {
            //recuperar el codigo, nombre y el nro de horas
            cad="Codigo: "+ json.getJSONObject(i).getInt("codigoProd")+"\n"+
                    "Nombre: "+ json.getJSONObject(i).getString("nombre")+"\n"+
                    "Descripcion: "+ json.getJSONObject(i).getString("descripcion")+"\n"+
                    "Categoria: "+ json.getJSONObject(i).getString("nombrecat")+"\n"+
                    "Stock: "+ json.getJSONObject(i).getInt("stock")+"\n"+
                    "Precio: "+ json.getJSONObject(i).getDouble("precio")

            //agregar a la lista
            lista.add(cad)
        }
        //
        var adaptador= ArrayAdapter<String>(applicationContext,android.R.layout.simple_list_item_1,lista)
        //
        LVProductos.adapter=adaptador
    }
}