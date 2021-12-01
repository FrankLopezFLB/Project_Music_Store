package com.desarrollo.project_music_store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_registrar_producto.*

class RegistrarProductoActivity : AppCompatActivity() {

    var categoria_seleccionada=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_producto)
        //
        btnBackProd.setOnClickListener {
            finish()
           // var i= Intent(this,ListadoProductoActivity::class.java)
          //  startActivity(i)

        }
        BTNListarP.setOnClickListener {
            var i= Intent(this,ListadoProductoActivity::class.java)
            startActivity(i)

        }
        BTNACONSUL.setOnClickListener {
            var i= Intent(this,ConsultaProductoActivity::class.java)
            startActivity(i)}
        spnCategoriaP.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
              categoria_seleccionada= p2.toString()


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
    fun BotonGrabarProducto(v: View){
        try{
        Log.wtf("combo",categoria_seleccionada)
        var codigo = null
        var nombre= edtNomProd.text.toString()
        var descripcion = edtDesProd.text.toString()
        var categoria = categoria_seleccionada.toInt()
        var stock = edtStockProd.text.toString().toInt()
        var precio =edtPreProd.text.toString().toDouble()
        var estado = 1;

        var parametros="?xnom=" + nombre +
                "&xdesc=" +descripcion+
                "&xcat=" + categoria +
                "&xstock=" +stock+
                "&xprecio=" +precio+
                "&xestado="+estado
        Log.wtf("parametros",parametros)
        var ruta_servicio="http://192.168.1.100:8085/servicio/servicioProducto/nuevo_producto.php"+parametros
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