package com.example.searchapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.searchapp.searchrepository.SearchApi
import com.example.searchapp.model.HitsResponse
import com.example.searchapp.databinding.FragmentSearchBinding
import com.example.searchapp.network.Network
import com.example.searchapp.presenter.SearchPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    private var _binging: FragmentSearchBinding? = null
    private val binding get() = requireNotNull(_binging)
    private lateinit var communicator: Communicator

//    private lateinit var mService: SearchApi
    private lateinit var adapter: SearchAdapter

//    private val KEY = "22385290-8633bd548612ec6195b902710"
//    private val QUERY = "red flower"

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
        SearchPresenter(adapter).show()
//        mService = Network.makeConnectionToSearchApi
//        getImageList()
    }

//    private fun getImageList() {
//        mService.getImageList(KEY,QUERY).enqueue(object: Callback<HitsResponse?> {
//            override fun onFailure(call: Call<HitsResponse?>, t: Throwable) {
//
//            }
//
//            override fun onResponse(
//                call: Call<HitsResponse?>,
//                response: Response<HitsResponse?>
//            ) {
//                if (response.isSuccessful) {
//                    val hitsResponse = response.body()
//                    adapter.setImageList(requireNotNull(hitsResponse?.hits))
//                }
//
//            }
//        })
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binging = null
    }

}