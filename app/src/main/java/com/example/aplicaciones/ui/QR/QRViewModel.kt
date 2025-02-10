package com.example.aplicaciones.ui.QR

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder


class QRViewModel : ViewModel() {

    private val _qrBitmap = MutableLiveData<Bitmap?>()
    val qrBitmap: LiveData<Bitmap?> = _qrBitmap

    fun generateQRCode(text: String) {
        try {
            val bitMatrix: BitMatrix = MultiFormatWriter().encode(
                text, BarcodeFormat.QR_CODE, 500, 500
            )
            val bitmap: Bitmap = BarcodeEncoder().createBitmap(bitMatrix)
            _qrBitmap.value = bitmap
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }
}




