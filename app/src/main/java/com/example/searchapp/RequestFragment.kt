package com.example.searchapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.searchapp.Common.Common
import com.example.searchapp.Interface.RetrofitServices
import com.example.searchapp.Model.Image
import com.example.searchapp.Model.HitsResponse
import com.example.searchapp.databinding.FragmentRequestBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestFragment : Fragment() {
    private var _binging: FragmentRequestBinding? = null
    private val binding get() = requireNotNull(_binging)
    private lateinit var communicator: Communicator

    private lateinit var mService: RetrofitServices
    private lateinit var adapter: ImageAdapter

    private var imageList: List<Image> = emptyList()
    private val KEY = "22385290-8633bd548612ec6195b902710"
    private val QUERY = "red flower"

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
        with(binding) {
            recycleView.layoutManager = GridLayoutManager(activity, 2)
            adapter = ImageAdapter(requireContext())
            recycleView.adapter = adapter
        }
        mService = Common.retrofitServices
        getImageList()
        adapter.setImageList(imageList)
    }

    private fun getImageList() {
        mService.getImageList(KEY,QUERY).enqueue(object: Callback<HitsResponse?> {
            override fun onFailure(call: Call<HitsResponse?>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<HitsResponse?>,
                response: Response<HitsResponse?>
            ) {
                val hitsResponse = response.body()
                imageList = requireNotNull(hitsResponse?.hits)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binging = null
    }

}