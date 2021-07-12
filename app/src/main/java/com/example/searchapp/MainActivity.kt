package com.example.searchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity(), Communicator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openRequestFragment()
    }

    override fun openRequestFragment() {
        val fragment = RequestFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment).commit()
    }


}