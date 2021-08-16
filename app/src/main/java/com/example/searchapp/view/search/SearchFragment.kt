package com.example.searchapp.view.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.searchapp.view.main.Communicator
import com.example.searchapp.Application
import com.example.searchapp.databinding.FragmentSearchBinding
import com.example.searchapp.view.search.adapter.SearchAdapter
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class SearchFragment : Fragment(), ItemClickListener {
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
        Application.instance.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel= ViewModelProvider(this, factory)[SearchViewModel::class.java]
        _binging = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerView.layoutManager = GridLayoutManager(activity, 2)
            SearchAdapter(requireContext(), this@SearchFragment).also { adapter = it }
            recyclerView.adapter = adapter
            searchEditText.doAfterTextChanged {
                if (requireNotNull(it?.length) > 2) {
                    viewModel.search(searchEditText.text.toString())
                }
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

    override fun onItemClickListener(photoUrl: String) {
        communicator.openPhotoFragment(photoUrl)
    }

}