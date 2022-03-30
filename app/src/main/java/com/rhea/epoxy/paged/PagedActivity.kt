package com.rhea.epoxy.paged

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagedList
import com.airbnb.epoxy.EpoxyRecyclerView
import com.rhea.epoxy.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PagedActivity: AppCompatActivity(R.layout.activity_paged) {

    private val pagedController by lazy { PagedController() }
    private val viewModel by lazy {
        ViewModelProvider(this)[PagedViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        setPagingData()
    }

    private fun setPagingData() {
        lifecycleScope.launchWhenResumed {
            viewModel.pagedData.collectLatest {
                pagedController.submitData(it)
            }
        }
    }

    private fun initViews() {
        val recyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxy_recycler)
        recyclerView.setController(pagedController)
        pagedController.headerData = viewModel.getHeaderValues()
        pagedController.requestModelBuild()
    }
}