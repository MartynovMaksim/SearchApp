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
                        val localView = IntArray(2)
                        v.getLocationOnScreen(localView)
                        val photoY = localView[1]
                        val parentView = IntArray(2)
                        view.getLocationOnScreen(parentView)
                        val parentY = parentView[1]
                        val dh = view.height - v.height

                        if (photoY > parentY && photoY < parentY + dh) {
                            v.y += posY
                        } else {
                            v.y = v.top.toFloat()
                        }
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