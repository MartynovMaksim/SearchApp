package com.example.searchapp

interface Communicator {
    fun openQueryFragment()
    fun openRequestFragment(genre: String)
    fun setEditText(query: String)
}