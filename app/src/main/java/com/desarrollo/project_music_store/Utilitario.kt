package com.desarrollo.project_music_store

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Utilitario {
    companion object{
        fun traerDatos_String(mi_url:String):String{

            var result=""
            var conexion: HttpURLConnection?=null
            //
            try {
                // definir la ruta del servicio a obtener
                val url = URL(mi_url)
                conexion = url.openConnection() as HttpURLConnection
                conexion.doInput=true
                conexion.doOutput=true

                val httpResult:Int=conexion.responseCode
                if (httpResult== HttpURLConnection.HTTP_OK) //200
                {
                    val input = conexion.inputStream
                    val lector = BufferedReader(InputStreamReader(input))
                    val sb = StringBuilder()
                    var linea:String?
                    // recorrer y acceder a cada linea para obtener los datos y almacenarlos en sb
                    while ((lector.readLine().also { linea = it })!=null)
                    {
                        sb.append(linea+"\n")
                    }
                    //
                    input.close()
                    //
                    result = sb.toString()
                }
            }
            catch (ex: IOException)
            {
                result="Error: "+ex.message
            }
            finally {
                conexion?.disconnect()
            }
            //
            return result
        }

        fun enviarDatos_String(mi_url:String):String{
            var result=""
            var conexion: HttpURLConnection?=null
            //
            try {
                // definir la ruta del servicio a obtener
                val url = URL(mi_url)
                conexion = url.openConnection() as HttpURLConnection
                conexion.doInput=true
                conexion.doOutput=true

                val httpResult:Int=conexion.responseCode

                if (httpResult== HttpURLConnection.HTTP_OK) //200
                {
                    result = "ok"
                }
            }
            catch (ex: IOException)
            {
                result="Error: "+ex.message
            }
            finally {
                conexion?.disconnect()
            }
            //
            return result

        }
    }
}