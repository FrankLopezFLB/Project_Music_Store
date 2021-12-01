package com.desarrollo.project_music_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_consulta_producto.*
import kotlinx.android.synthetic.main.activity_consulta_trabajador.*
import kotlinx.android.synthetic.main.activity_registrar_trabajador.*
import org.json.JSONArray

class ConsultaTrabajadorActivity : AppCompatActivity() {
    var cargo_seleccionado=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_trabajador)
        var rutaServicioTra = "http://192.168.1.150/servicio/servicioTrabajador/listar_cargos.php"
        var hilo1 = Thread(Runnable {
            var resultado = Utilitario.traerDatos_String(rutaServicioTra)
            runOnUiThread {
                loadTrabajadorSpinner(resultado)
            }
        })
        hilo1.start()

        spCargoCTra.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
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

        spCargoCTra.adapter = adapter;


    }

    fun BotonCerrarTrabajador(v: View) {
        finish()
    }

    fun BotonConsultarTrabajador(v: View) {
        try{
            var codigo = edtCodCTra.text.toString().toInt()
            if(codigo!=null){
                var hilo = Thread(Runnable {
                    var parametro = "?xcod=" + codigo
                    var ruta_servicio = "http://192.168.1.150/servicio/servicioTrabajador/trabajador_por_codigo.php" + parametro
                    var resultado = Utilitario.traerDatos_String(ruta_servicio)
                    runOnUiThread {
                        BuscarTrabajadorCodigo(resultado)
                    }
                })
                hilo.start()}

        }
        catch (ex: Exception) {
            Toast.makeText(this, "Error, codigo No Encontrado", Toast.LENGTH_SHORT).show()
            Log.e("mi_error", ex.message.toString())
        }

    }

    fun BotonEliminarTrabajador(v: View){
        try{
            var codigo = edtCodCTra.text.toString().toInt()
            if(codigo!=null){
                var hilo = Thread(Runnable {
                    var parametro = "?xcod=" + codigo
                    var ruta_servicio = "http://192.168.1.150/servicio/servicioTrabajador/eliminar_trabajador.php" + parametro
                    Utilitario.enviarDatos_String(ruta_servicio)
                    runOnUiThread {
                        edtCodCTra.setText("")
                        edtNomCTra.setText("")
                        edtApeCTra.setText("")
                        edtDNICTra.setText("")
                        edtTeleCTra.setText("")
                        edtDireCTra.setText("")
                        edtCorreoCTra.setText("")
                        spCargoCTra.setSelection(0)
                        Snackbar.make(v, "Trabajador Eliminado", 3000).show()
                    }
                })
                hilo.start()}
        }catch (ex: Exception) {
            Toast.makeText(this, "Error, codigo No Encontrado", Toast.LENGTH_SHORT).show()
            Log.e("mi_error", ex.message.toString())
        }
    }



    fun BuscarTrabajadorCodigo(cadena: String) {
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
                    edtNomCTra.setText(json.getJSONObject(i).getString("nombreTra"))
                    edtApeCTra.setText(json.getJSONObject(i).getString("apellidoTra"))
                    edtDNICTra.setText(json.getJSONObject(i).getString("dniTra"))
                    edtTeleCTra.setText(json.getJSONObject(i).getString("telefonoTra"))
                    edtDireCTra.setText(json.getJSONObject(i).getString("direccionTra"))
                    edtCorreoCTra.setText(json.getJSONObject(i).getString("correoTra"))
                    spCargoCTra.setSelection(json.getJSONObject(i).getInt("codigoCar"))

                }
                //
            } else {
                edtNomCTra.setText("")
                edtApeCTra.setText("")
                edtDNICTra.setText("")
                edtDNICTra.setText("")
                edtTeleCTra.setText("")
                edtDireCTra.setText("")
                edtCorreoCTra.setText("")
                spCargoCTra.setSelection(0)
                edtCodCTra.setText("")
                Toast.makeText(this, "Error, codigo No Encontrado", Toast.LENGTH_SHORT).show()
            }
        } catch (ex: Exception) {
            Log.e("mi_error", ex.message.toString())
        }
    }

    fun BotonActualizarTrabajador(v: View){
        try{

            var codigo = edtCodCTra.text.toString().toInt()
            var nombre= edtNomCTra.text.toString()
            var apellido = edtApeCTra.text.toString()
            var nombreCompleto = "$nombre $apellido"
            var dni = edtDNICTra.text.toString()
            var cargo = cargo_seleccionado.toInt()
            var telefono = edtTeleCTra.text.toString()
            var direccion = edtDireCTra.text.toString()
            var correo = edtCorreoCTra.text.toString()

            var parametros="?xcod=" + codigo +
                    "&xnom=" + nombre +
                    "&xape=" +apellido+
                    "&xdni=" + dni +
                    "&xcar=" +cargo+
                    "&xtele=" +telefono+
                    "&xdire=" +direccion+
                    "&xcorre="+correo

            Log.wtf("parametros",parametros)
            var ruta_servicio="http://192.168.1.150/servicio/servicioTrabajador/actualizar_trabajador.php"+parametros
            var hilo=Thread(Runnable{
                Utilitario.enviarDatos_String(ruta_servicio)
                runOnUiThread {
                    Snackbar.make(v,"Trabajador $nombreCompleto actualizado correctamente",3000).show()

                }
            })
            //
            hilo.start()}catch (ex: Exception) {
            Toast.makeText(this, "Inserte datos por favor", Toast.LENGTH_SHORT).show()
            Log.e("mi_error", ex.message.toString())
        }
    }
}