package com.example.searchapp.view.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.searchapp.databinding.FragmentPhotoBinding
import kotlin.math.round

private const val PHOTO_URL = "param1"

class PhotoFragment : Fragment() {
    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var photoUrl: String? = null
    private var lastTouchY: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            photoUrl = it.getString(PHOTO_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility") // don't support performClick for photo
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            Glide.with(requireContext()).load(photoUrl).into(photo)

            photo.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        lastTouchY = event.rawY
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val posY = event.rawY - lastTouchY
                        val dH = (v.top - view.top) * 2.0F
                        if (v.y + posY < dH / 2) {
                            val floatAlpha = ((v.y + posY).coerceIn(0F, dH / 2) / (dH / 2))
                            view.background.alpha = round(floatAlpha * 255).toInt()
                        } else {
                            val floatAlpha = ((dH - ((v.y + posY).coerceIn(dH / 2, dH))) / (dH / 2))
                            view.background.alpha = round(floatAlpha * 255).toInt()
                        }
                        if (v.y + posY < 0) {
                            activity?.supportFragmentManager?.popBackStack()
                        } else if (v.y + posY > dH) {
                            activity?.supportFragmentManager?.popBackStack()
                        }
                        v.y = (v.y + posY)
                    }
                    MotionEvent.ACTION_UP -> {
                        v.y = v.top.toFloat()
                        view.background.alpha = 255
                    }
                }
                lastTouchY = event.rawY
                true
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(photoUrl: String) =
            PhotoFragment().apply {
                arguments = Bundle().apply {
                    putString(PHOTO_URL, photoUrl)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}