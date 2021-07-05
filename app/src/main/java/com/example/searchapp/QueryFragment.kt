package com.example.searchapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchapp.databinding.FragmentQueryBinding

class QueryFragment : Fragment(), ItemClickListener {
    private var _binding: FragmentQueryBinding? = null
    private val binding get() = requireNotNull(_binding)
    private lateinit var communicator: Communicator
    private val genres = arrayListOf(
        "аниме", "биографический", "боевик", "вестерн", "военный", "детектив",
        "детский", "документальный", "драма", "исторический", "кинокомикс", "комедия", "концерт",
        "короткометражный", "криминал", "мелодрама", "мистика", "музыка", "мультфильм", "мюзикл",
        "научный", "нуар", "приключения", "реалити-шоу", "семейный", "спорт", "ток-шоу", "триллер",
        "ужасы", "фантастика", "фэнтези", "эротика"
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        communicator = activity as Communicator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQueryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recycleView.layoutManager = LinearLayoutManager(activity)
            recycleView.adapter = GenreAdapter(genres, this@QueryFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClickListener(genre: String) {
        communicator.setEditText(genre)
        communicator.openRequestFragment(genre)
    }
}