package com.desarrollo.project_music_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_consulta_producto.*
import kotlinx.android.synthetic.main.activity_registrar_servicio.*
import org.json.JSONArray

class ConsultaServicioActivity : AppCompatActivity() {
    var categoria_seleccionada=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_servicio)
    }

    fun btnCerrar(v: View) {
        finish()
    }

    fun btnConsultarServicio(v:View) {
        try{
            var codigo = txtCodigoServicio.text.toString().toInt()
            if(codigo!=null){
                var hilo = Thread(Runnable {
                    var parametro = "?xcod=" + codigo
                    var ruta_servicio = "http://192.168.0.107:80/servicio/servicioServicio/consulta_servicio.php" + parametro
                    var resultado = Utilitario.traerDatos_String(ruta_servicio)
                    runOnUiThread {
                        buscarServicio(resultado)
                    }
                })
                hilo.start()}

        }
        catch (ex: Exception) {
            Toast.makeText(this, "¡Código ingresado no existe!", Toast.LENGTH_SHORT).show()
            Log.e("Error", ex.message.toString())
        }

    }

    fun buscarServicio(cadena: String) {
        try {
            var cad: String = "";
            var json = JSONArray(cadena)
            var cant_elementos = json.length() - 1
            if (cant_elementos >= 0) {
                for (i in 0..cant_elementos) {
                    txtNombreServicio.setText(json.getJSONObject(i).getString("nombre"))
                    txtDescripcionServicio.setText(json.getJSONObject(i).getString("descripcion"))
                    cboCategoriaServicio.setSelection(json.getJSONObject(i).getInt("codigoCat"))
                    txtPrecioServicio.setText(""+json.getJSONObject(i).getDouble("precio"))
                }
            } else {
                txtCodigoServicio.setText("")
                txtNombreServicio.setText("")
                txtDescripcionServicio.setText("")
                cboCategoriaServicio.setSelection(0)
                txtPrecioServicio.setText("")
                Toast.makeText(this, "¡Código ingresado no existe!", Toast.LENGTH_SHORT).show()
            }
        } catch (ex: Exception) {
            Log.e("Error", ex.message.toString())
        }
    }

    fun btnActualizarServicio(v: View){
        try{
            Log.wtf("combo",categoria_seleccionada)
            var codigo = txtCodigoServicio.text.toString().toInt()
            var nombre= txtNombreServicio.text.toString()
            var descripcion = txtDescripcionServicio.text.toString()
            var categoria = cboCategoriaServicio.selectedItemPosition
            var precio =txtPrecioServicio.text.toString().toDouble()
            var estado = 1;

            var parametros="?xcod=" + codigo +
                    "&xnom="+nombre+
                    "&xdesc=" +descripcion+
                    "&xcat=" + categoria +
                    "&xprecio=" +precio+
                    "&xestado="+estado

            Log.wtf("parametros",parametros)
            var ruta_servicio="http://192.168.0.107:80/servicio/servicioServicio/actualizar_servicio.php"+parametros
            var hilo=Thread(Runnable{
                Utilitario.enviarDatos_String(ruta_servicio)
                runOnUiThread {
                    Snackbar.make(v,"Servicio actualizado con éxito",3000).show()

                }
            })
            hilo.start()}catch (ex: Exception) {
            Toast.makeText(this, "Inserte datos por favor", Toast.LENGTH_SHORT).show()
            Log.e("Error", ex.message.toString())
        }
    }

    fun btnEliminarServicio(v: View){
        try{
            var codigo = txtCodigoServicio.text.toString().toInt()
            if(codigo!=null){
                var hilo = Thread(Runnable {
                    var parametro = "?xcod=" + codigo
                    var ruta_servicio = "http://192.168.0.107:80/servicio/servicioServicio/eliminar_servicio.php" + parametro
                    runOnUiThread {
                        txtCodigoServicio.setText("")
                        txtNombreServicio.setText("")
                        txtDescripcionServicio.setText("")
                        cboCategoriaServicio.setSelection(0)
                        txtPrecioServicio.setText("")
                        Snackbar.make(v, "Servicio eliminado correctamente", 3000).show()

                    }
                })
                hilo.start()}
        }catch (ex: Exception) {
            Toast.makeText(this, "Error al encontrar el código", Toast.LENGTH_SHORT).show()
            Log.e("mi_error", ex.message.toString())
        }
    }
}