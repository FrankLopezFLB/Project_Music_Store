package com.desarrollo.project_music_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CanastaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canasta)
    }
}