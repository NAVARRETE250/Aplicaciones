package com.example.aplicaciones.ui.notifications

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.aplicaciones.databinding.FragmentNotificationsBinding
import java.util.Locale

class NotificationsFragment : Fragment() {

        private var _binding: FragmentNotificationsBinding? = null
        private val binding get() = _binding!!
        private var t1: TextToSpeech? = null

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            val notificationsViewModel = ViewModelProvider(this)[NotificationsViewModel::class.java]

            _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
            val root: View = binding.root

            val textView: TextView = binding.textaquen
            notificationsViewModel.text.observe(viewLifecycleOwner) {
                textView.text = it
            }

            t1 = TextToSpeech(requireContext()) { status ->
                if (status != TextToSpeech.ERROR) {
                    t1!!.language = Locale.UK
                }
            }

            binding.vozarron.setOnClickListener {
                val toSpeak = binding.textaquen.text.toString()
                Toast.makeText(requireContext(), toSpeak, Toast.LENGTH_SHORT).show()
                t1!!.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, null)
            }

            return root
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
            t1?.stop()
            t1?.shutdown()
        }
    }

