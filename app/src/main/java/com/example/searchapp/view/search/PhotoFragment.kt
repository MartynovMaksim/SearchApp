package com.example.searchapp.view.search

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.searchapp.databinding.FragmentPhotoBinding
import kotlin.math.abs

private const val PHOTO_URL = "param1"

class PhotoFragment : Fragment() {
    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var photoUrl: String? = null
    private val TAG = "PhotoFragment"

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
            var lastTouchY = 0F
            var alpha = 0F
            view.background.alpha = 255
            val animatorSet = AnimatorSet()
            photo.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                            lastTouchY = event.rawY
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val posY = event.rawY - lastTouchY
                        v.y += posY
                        lastTouchY = event.rawY
                        alpha = (1 - (abs(v.top - v.y)) / v.top).coerceIn(0F, 1F)
                        view.background.alpha = (255 * alpha).toInt()
                    }
                    MotionEvent.ACTION_UP -> {
                        if (alpha == 0F || alpha == 1F) {
                            activity?.supportFragmentManager?.popBackStack()
                        } else {
                            val animatedAlpha = ValueAnimator.ofFloat(alpha, 1F)
                            animatedAlpha.addUpdateListener {
                                view.background.alpha = (255 * (it.animatedValue as Float)).toInt()
                            }
                            val animatedPhoto = ValueAnimator.ofFloat(v.y, v.top.toFloat())
                            animatedPhoto.addUpdateListener {
                                v.y = it.animatedValue as Float
                            }
                            animatorSet.apply {
                                duration = 1000
                                playTogether(animatedAlpha, animatedPhoto)
                                start()
                            }
                        }
                    }
                }
                !animatorSet.isRunning
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