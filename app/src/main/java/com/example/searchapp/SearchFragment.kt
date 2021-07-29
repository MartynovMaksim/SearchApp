package com.example.searchapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.searchapp.databinding.FragmentSearchBinding
import com.example.searchapp.presenter.SearchViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class SearchFragment : Fragment() {
    private var _binging: FragmentSearchBinding? = null
    private val binding get() = requireNotNull(_binging)
    private lateinit var communicator: Communicator
    private lateinit var adapter: SearchAdapter

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var viewModel: SearchViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        communicator = activity as Communicator
        MainApp.instance.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel= ViewModelProvider(this, factory)[SearchViewModel::class.java]
        _binging = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerView.layoutManager = GridLayoutManager(activity, 2)
            adapter = SearchAdapter(requireContext())
            recyclerView.adapter = adapter
            searchButton.setOnClickListener {
                viewModel.search(searchEditText.text.toString())
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.searchResultFlow.collect { adapter.setImageList(it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binging = null
    }

}