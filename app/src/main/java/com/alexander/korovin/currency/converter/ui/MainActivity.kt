package com.alexander.korovin.currency.converter.ui

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.alexander.korovin.currency.converter.R
import com.alexander.korovin.currency.converter.ui.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        toStartScreen()
    }

    private fun toStartScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, MainFragment())
            .commitNow()
    }
}
