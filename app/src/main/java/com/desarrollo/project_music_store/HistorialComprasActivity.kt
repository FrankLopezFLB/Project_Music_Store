package com.desarrollo.project_music_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HistorialComprasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial_compras)
    }
}