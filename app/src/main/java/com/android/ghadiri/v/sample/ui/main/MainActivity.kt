package com.android.ghadiri.v.sample.ui.main

import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.android.ghadiri.v.sample.R
import com.android.ghadiri.v.sample.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setupViewModels()
        subscribeForData()
        loadData()
    }

    private fun setupViewModels() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private fun subscribeForData() {
        viewModel.ipLiveData.observe(this, Observer<String> {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun loadData() {
        viewModel.getMyDeviceIp()
    }

}
