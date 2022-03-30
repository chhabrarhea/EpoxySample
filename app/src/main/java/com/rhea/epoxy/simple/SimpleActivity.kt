package com.rhea.epoxy.simple

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.rhea.epoxy.R
import com.rhea.epoxy.api.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SimpleActivity : AppCompatActivity(R.layout.activity_simple) {

    private val viewModel: SimpleViewModel by lazy {
        ViewModelProvider(this)[SimpleViewModel::class.java]
    }

    private val simpleController by lazy {
        SimpleController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setObservers()
        initRecycler()
        viewModel.getMovies()
    }

    private fun initRecycler() {
        val recycler = findViewById<EpoxyRecyclerView>(R.id.epoxy_recycler)
        recycler.setController(simpleController)
    }

    private fun setObservers() {
        viewModel.movieResponse.observe(this) {
            if (it is Resource.Success) {
                Log.i("observer", it.data.toString())
                simpleController.setData(it.data, viewModel.getHeaderValues())
            }
        }
    }
}