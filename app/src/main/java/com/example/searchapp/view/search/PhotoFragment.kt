package com.example.searchapp.view.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.searchapp.databinding.FragmentPhotoBinding

private const val PHOTO_URL = "param1"

class PhotoFragment : Fragment() {
    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var photoUrl: String? = null
    private var lastTouchX: Float = 0F
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
                        lastTouchX = event.rawX
                        lastTouchY = event.rawY
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val posX = event.rawX - lastTouchX
                        val posY = event.rawY - lastTouchY
                        val l = IntArray(2)
                        v.getLocationOnScreen(l)
                        val x = l[0]
                        val y = l[1]
                        val p = IntArray(2)
                        view.getLocationOnScreen(p)
                        val xP = p[0]
                        val yP = p[1]
                        Log.d(
                            "PhotoFragment",
                            "Ptop = ${view.top}, Pbottom = ${view.bottom}, Pheight = ${view.height}, xView = $x, yView = $y, yParent = $yP"
                        )
                        val dh = view.height - v.height
                        if (y > yP && y < yP + dh) {
                        v.x += posX
                        v.y += posY
                        } else {
                            v.y = v.top.toFloat()
                        }

                    }
                }
                lastTouchX = event.rawX
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