package com.desarrollo.project_music_store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_registrar_producto.*
import kotlinx.android.synthetic.main.activity_registrar_trabajador.*
import org.json.JSONArray

class RegistrarTrabajadorActivity : AppCompatActivity() {
    var cargo_seleccionado=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_trabajador)


        var rutaServicioTra = "http://192.168.1.100:8085/servicio/servicioTrabajador/listar_cargos.php"
        var hilo1 = Thread(Runnable {
            var resultado = Utilitario.traerDatos_String(rutaServicioTra)
            runOnUiThread {
                loadTrabajadorSpinner(resultado)
            }
        })
        hilo1.start()


        btnListarTrabajador.setOnClickListener {
            var i= Intent(this,ListadoTrabajadorActivity::class.java)
            startActivity(i)

        }
        btnConsultarTrabajador.setOnClickListener {
            var i= Intent(this,ConsultaTrabajadorActivity::class.java)
            startActivity(i)}

        spCargos.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                cargo_seleccionado= p2.toString()

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    fun loadTrabajadorSpinner(cadena: String) {
        var lista = ArrayList<String>()
        lista.add("Seleccione un Cargo")
        var trabajador = "";
        var json = JSONArray(cadena)
        var cantidadElementos = json.length() - 1

        for (i in 0..cantidadElementos) {
            trabajador = json.getJSONObject(i).getString("nombreCar")

            lista.add(trabajador)
        }

        var adapter = ArrayAdapter<String>(
            applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            lista
        )

        spCargos.adapter = adapter;


    }



    fun BotonGrabarTrabajador(v: View){
        try{

            var nombre= edtNomTra.text.toString()
            var apellido = edtApeTra.text.toString()
            var dni = edtDNITra.text.toString()
            var cargo = cargo_seleccionado.toInt()
            var telefono = edtFonoTra.text.toString()
            var direccion = edtDireccionTra.text.toString()
            var correo = edtCorreoTra.text.toString()
            var clave = edtClaveTra.text.toString()
            var estado = 1;

            var parametros="?xnom=" + nombre +
                    "&xape=" +apellido+
                    "&xdni=" + dni +
                    "&xcar=" +cargo+
                    "&xtele=" +telefono+
                    "&xdire=" +direccion+
                    "&xcorre="+correo+
                    "&xclav="+clave+
                    "&xestado="+estado
            Log.wtf("parametros",parametros)
            var ruta_servicio="http://192.168.1.100:8085/servicio/servicioTrabajador/nuevo_trabajador.php"+parametros
            var hilo=Thread(Runnable{
                Utilitario.enviarDatos_String(ruta_servicio)
                runOnUiThread {
                    Snackbar.make(v,"Nuevo trabajador grabado correctamente",3000).show()

                }
            })
            //
            hilo.start()}catch (ex: Exception) {
            Toast.makeText(this, "Inserte datos por favor", Toast.LENGTH_SHORT).show()
            Log.e("mi_error", ex.message.toString())
        }
    }


    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}