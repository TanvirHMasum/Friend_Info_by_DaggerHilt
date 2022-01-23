package com.example.friendsinfo

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendsinfo.adapter.FriendAdapter
import com.example.friendsinfo.databinding.ActivityMainBinding
import com.example.friendsinfo.viewmodel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var friendAdapter: FriendAdapter

    private val llm: LinearLayoutManager = LinearLayoutManager(this)
    private var glm: GridLayoutManager = GridLayoutManager(this, 2)

    private val viewModel: ImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.progressBar.isVisible = true

        friendAdapter = FriendAdapter()

        llm.orientation = LinearLayoutManager.VERTICAL
        val orientation: Int = this.resources.configuration.orientation

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            glm
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            glm = GridLayoutManager(this, 3)
        }

        binding.recyclerView.apply {
            adapter = friendAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
        binding.recyclerView.layoutManager = glm

        viewModel.responseImages.observe(this, { response ->
            if (response != null) {
                binding.progressBar.isVisible = false
                friendAdapter.submitList(response.results)
            }
        })
    }
}