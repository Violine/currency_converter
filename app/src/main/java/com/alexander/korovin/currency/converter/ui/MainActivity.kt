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
    private val STORAGE_PERMISSION_REQUEST_CODE: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
      //  checkStoragePermission()
        toStartScreen()
    }

    private fun checkStoragePermission() {
        val storagePermissionState = ContextCompat.checkSelfPermission(
            this, Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (storagePermissionState == PackageManager.PERMISSION_GRANTED) {
            toStartScreen()
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ) {
                AlertDialog.Builder(this)
                    .setMessage(getString(R.string.dialog_message_allow_permission))
                    .setNegativeButton(
                        R.string.cancel_button_text,
                        DialogInterface.OnClickListener({ dialogInterface: DialogInterface, _: Int ->
                            dialogInterface.dismiss()
                        })
                    )
                    .setPositiveButton(
                        R.string.ok_button_text,
                        DialogInterface.OnClickListener({ dialogInterface: DialogInterface, _: Int ->
                            dialogInterface.dismiss()
                            requestStoragePermission()
                        })
                    )
            } else {
                requestStoragePermission()
            }
        }
    }

    private fun requestStoragePermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), STORAGE_PERMISSION_REQUEST_CODE
        )
    }

    private fun toStartScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, MainFragment())
            .commitNow()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            toStartScreen()
        }
    }

}
