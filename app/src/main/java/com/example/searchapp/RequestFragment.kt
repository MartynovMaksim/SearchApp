package com.example.searchapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchapp.databinding.FragmentRequestBinding

class RequestFragment : Fragment(), ItemClickListener {
    private var _binging: FragmentRequestBinding? = null
    private val binding get() = requireNotNull(_binging)
    private lateinit var communicator: Communicator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        communicator = activity as Communicator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binging = FragmentRequestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                communicator.setEditText("")
                communicator.openQueryFragment()
            }
        }
        val genre = requireNotNull(arguments).getString(QUERY_KEY)
        val genres: ArrayList<String> = arrayListOf()
        for (i in 0..30) {
            genres.add("$genre #$i")
        }
        with(binding) {
            recycleView.layoutManager = LinearLayoutManager(activity)
            recycleView.adapter = GenreAdapter(genres,this@RequestFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binging = null
    }

    companion object {
        fun newInstance(genre: String): RequestFragment {
            val args = Bundle()
            val fragment = RequestFragment()
            args.putString(QUERY_KEY, genre)
            fragment.arguments = args
            return fragment
        }

        private const val QUERY_KEY = "QUERY_KEY"
    }

    override fun onItemClickListener(genre: String) {
        val toast = Toast.makeText(activity, genre, Toast.LENGTH_SHORT)
        toast.show()
    }


}