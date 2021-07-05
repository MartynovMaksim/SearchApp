package com.example.searchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity(), Communicator {
    private lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchEditText = findViewById(R.id.SearchEditText)
        openQueryFragment()
    }

    override fun openQueryFragment() {
        val fragment = QueryFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment).commit()
    }

    override fun openRequestFragment(genre: String) {
        val fragment = RequestFragment.newInstance(genre)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment).commit()
    }

    override fun setEditText(query: String) {
        searchEditText.setText(query)
    }


}