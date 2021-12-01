package com.desarrollo.project_music_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_listado_producto.*
import kotlinx.android.synthetic.main.activity_listado_trabajador.*
import org.json.JSONArray

class ListadoTrabajadorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_trabajador)

        var ruta_servicio="http://192.168.1.150/servicio/servicioTrabajador/listar_trabajadores.php"
        //definimos el hilo
        var hilo=Thread(Runnable{
            //obtner los datos como cadena usando el metodo traerdatos
            var resultado=Utilitario.traerDatos_String(ruta_servicio)
            Log.i("datos",resultado)
            //
            runOnUiThread{
                mostrarTrabajadores(resultado)
            }
        })
        //iniciar el hilo
        hilo.start()
    }

    fun mostrarTrabajadores(cadena:String){
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
            cad="ID: "+ json.getJSONObject(i).getInt("codigoTra")+"\n"+
                    "Nombre: "+ json.getJSONObject(i).getString("nombreTra")+" " + json.getJSONObject(i).getString("apellidoTra")+"\n"+
                    "DNI: "+ json.getJSONObject(i).getString("dniTra")+"\n"+
                    "Cargo: "+ json.getJSONObject(i).getString("nombreCar")+"\n"+
                    "Telefono: "+ json.getJSONObject(i).getString("telefonoTra")+"\n"+
                    "Correo: "+ json.getJSONObject(i).getString("correoTra")

            //agregar a la lista
            lista.add(cad)
        }
        //
        var adaptador= ArrayAdapter<String>(applicationContext,android.R.layout.simple_list_item_1,lista)
        //
        LVTRABAJADORES.adapter=adaptador
    }
}