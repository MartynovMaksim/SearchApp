package com.example.searchapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.searchapp.databinding.FragmentSearchBinding
import com.example.searchapp.presenter.SearchViewModel

class SearchFragment : Fragment() {
    private var _binging: FragmentSearchBinding? = null
    private val binding get() = requireNotNull(_binging)
    private lateinit var communicator: Communicator
//    private val searchStoreRemote = SearchStoreRemote(Network)
//    private val searchRepository = SearchRepository(searchStoreRemote)
//    private val searchPresenter = SearchPresenter(searchRepository)
    private lateinit var adapter: SearchAdapter

    val viewModel: SearchViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        communicator = activity as Communicator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binging = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerView.layoutManager = GridLayoutManager(activity, 2)
            adapter = SearchAdapter(requireContext())
            recyclerView.adapter = adapter
        }
        viewModel.search { adapter.setImageList(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binging = null
    }

}