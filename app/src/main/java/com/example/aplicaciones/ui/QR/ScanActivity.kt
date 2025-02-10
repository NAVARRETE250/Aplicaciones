package com.example.aplicaciones.ui.QR

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicaciones.R
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class ScanActivity : AppCompatActivity() {
    private lateinit var btnScan: Button
    private lateinit var txtResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        btnScan = findViewById(R.id.btnScan)
        txtResult = findViewById(R.id.txtResult)

        btnScan.setOnClickListener {
            startQRCodeScanner()
        }
    }

    private fun startQRCodeScanner() {
        val options = ScanOptions().apply {
            setDesiredBarcodeFormats(ScanOptions.QR_CODE)
            setPrompt("Escanea un código QR")
            setBeepEnabled(true)
            setOrientationLocked(false)
            setCameraId(0)
        }

        qrCodeLauncher.launch(options)  // ✅ Lanza el escáner de QR
    }

    // ✅ En lugar de devolver el resultado al Fragment, lo mostramos en txtResult dentro de esta Activity
    private val qrCodeLauncher = registerForActivityResult(ScanContract()) { result ->
        if (result.contents != null) {
            txtResult.text = "Resultado: ${result.contents}"  // ✅ Muestra el QR en `txtResult`
        } else {
            txtResult.text = "No se detectó ningún QR"
        }
    }
}
