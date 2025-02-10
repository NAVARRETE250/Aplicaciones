package com.example.aplicaciones.ui.QR

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.aplicaciones.databinding.FragmentQRBinding

class QRFragment : Fragment() {

    private var _binding: FragmentQRBinding? = null
    private val binding get() = _binding!!
    private val qrViewModel: QRViewModel by viewModels()

    companion object {
        private const val REQUEST_CODE_SCAN = 1  // Código para identificar la respuesta
    }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQRBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Observa los cambios en la imagen QR
        qrViewModel.qrBitmap.observe(viewLifecycleOwner) { bitmap ->
            if (bitmap != null) {
                binding.imgQR.setImageBitmap(bitmap)
            }
        }

        // Botón para generar QR
        binding.btnGenerate.setOnClickListener {
            val text = binding.edtInput.text.toString().trim()
            if (text.isNotEmpty()) {
                qrViewModel.generateQRCode(text)
            } else {
                Toast.makeText(requireContext(), "Ingresa un texto", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón para escanear QR
        binding.btnScanQR.setOnClickListener {
            val intent = Intent(requireContext(), ScanActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_SCAN)  // ✅ Abre el escáner
        }

        return root
    }

    // Recibe el resultado del escaneo
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SCAN && resultCode == Activity.RESULT_OK) {
            val scannedText = data?.getStringExtra("SCANNED_RESULT")  // ✅ Obtiene el texto escaneado
            scannedText?.let {
                binding.edtInput.setText(it)  // ✅ Muestra el QR escaneado en el EditText
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
