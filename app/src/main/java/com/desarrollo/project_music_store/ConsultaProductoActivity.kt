package com.desarrollo.project_music_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_consulta_producto.*

import org.json.JSONArray

class ConsultaProductoActivity : AppCompatActivity() {
    var categoria_seleccionada=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_producto)

        cboCategoriaServicio.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                categoria_seleccionada= p2.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
    fun BotonCerrar(v: View) {
        finish()
    }

    fun BotonConsultar(v:View) {
        try{
            var codigo = txtCodigoServicio.text.toString().toInt()
            if(codigo!=null){
            var hilo = Thread(Runnable {
                var parametro = "?xcod=" + codigo
                var ruta_servicio = "http://192.168.1.100:8085/servicio/servicioProducto/producto_por_codigo.php" + parametro
                var resultado = Utilitario.traerDatos_String(ruta_servicio)
                runOnUiThread {
                    BuscarProductoCodigo(resultado)
                }
            })
            hilo.start()}

        }
        catch (ex: Exception) {
            Toast.makeText(this, "Error, codigo No Encontrado", Toast.LENGTH_SHORT).show()
            Log.e("mi_error", ex.message.toString())
        }

    }

    fun BotonEliminar(v: View){
        try{
            var codigo = txtCodigoServicio.text.toString().toInt()
            if(codigo!=null){
        var hilo = Thread(Runnable {
            var parametro = "?xcod=" + codigo
            var ruta_servicio = "http://192.168.1.100:8085/servicio/servicioProducto/eliminar_producto.php" + parametro
            Utilitario.enviarDatos_String(ruta_servicio)
            runOnUiThread {
                txtCodigoServicio.setText("")
                txtNombreServicio.setText("")
                txtDescripcionServicio.setText("")
                cboCategoriaServicio.setSelection(0)
                EDTSTOCKBUSCA.setText("")
                txtPrecioServicio.setText("")
                Snackbar.make(v, "Producto Eliminado", 3000).show()

            }
        })
        hilo.start()}
        }catch (ex: Exception) {
            Toast.makeText(this, "Error, codigo No Encontrado", Toast.LENGTH_SHORT).show()
            Log.e("mi_error", ex.message.toString())
        }
    }



    fun BuscarProductoCodigo(cadena: String) {
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
                    txtNombreServicio.setText(json.getJSONObject(i).getString("nombre"))
                    txtDescripcionServicio.setText(json.getJSONObject(i).getString("descripcion"))
                    cboCategoriaServicio.setSelection(json.getJSONObject(i).getInt("codigoCat"))
                    EDTSTOCKBUSCA.setText(""+json.getJSONObject(i).getInt("stock"))
                    txtPrecioServicio.setText(""+json.getJSONObject(i).getDouble("precio"))

                }
                //
            } else {
                txtCodigoServicio.setText("")
                txtNombreServicio.setText("")
                txtDescripcionServicio.setText("")
                cboCategoriaServicio.setSelection(0)
                EDTSTOCKBUSCA.setText("")
                txtPrecioServicio.setText("")
                Toast.makeText(this, "Error, codigo No Encontrado", Toast.LENGTH_SHORT).show()
            }
        } catch (ex: Exception) {
            Log.e("mi_error", ex.message.toString())
        }
    }

    fun BotonActualizarProducto(v: View){
        try{
            Log.wtf("combo",categoria_seleccionada)
            var codigo = txtCodigoServicio.text.toString().toInt()
            var nombre= txtNombreServicio.text.toString()
            var descripcion = txtDescripcionServicio.text.toString()
            var categoria = categoria_seleccionada.toInt()
            var stock = EDTSTOCKBUSCA.text.toString().toInt()
            var precio =txtPrecioServicio.text.toString().toDouble()
            var estado = 1;

            var parametros="?xcod=" + codigo +
                    "&xnom="+nombre+
                    "&xdesc=" +descripcion+
                    "&xcat=" + categoria +
                    "&xstock=" +stock+
                    "&xprecio=" +precio+
                    "&xestado="+estado

            Log.wtf("parametros",parametros)
            var ruta_servicio="http://192.168.1.100:8085/servicio/servicioProducto/actualizar_productos.php"+parametros
            var hilo=Thread(Runnable{
                Utilitario.enviarDatos_String(ruta_servicio)
                runOnUiThread {
                    Snackbar.make(v,"Nuevo producto grabado correctamente",3000).show()

                }
            })
            //
            hilo.start()}catch (ex: Exception) {
            Toast.makeText(this, "Inserte datos por favor", Toast.LENGTH_SHORT).show()
            Log.e("mi_error", ex.message.toString())
        }
    }

}