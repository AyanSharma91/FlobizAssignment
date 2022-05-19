package com.world.flobiz_assgnment.ui

import android.content.Context
import android.content.SharedPreferences
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.squareup.picasso.Picasso
import com.world.flobiz_assgnment.ListRecyclerAdapter
import com.world.flobiz_assgnment.ListRecyclerViewModel
import com.world.flobiz_assgnment.R
import com.world.flobiz_assgnment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val viewModel: ListRecyclerViewModel by viewModels()
    lateinit var sharedPreferences: SharedPreferences
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        sharedPreferences = this@MainActivity.getSharedPreferences(
            getString(R.string.bannervisible),
            Context.MODE_PRIVATE
        )

        setUpViews()
    }

    private fun setUpViews() {
        Picasso.get().load("https://picsum.photos/198").into(binding.bannerImg)

        viewModel.getListData()

        getRecyclerData()
        val isVisible = sharedPreferences.getString(getString(R.string.visible), null)
        if (isVisible.isNullOrEmpty()) binding.parentLayout.visibility = View.VISIBLE
        else if (isVisible == "no") binding.parentLayout.visibility = View.GONE

        binding.cross.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString(getString(R.string.visible), "no")
            editor.apply()
            binding.parentLayout.visibility = View.GONE
        }

    }


    private fun getRecyclerData() {
        lifecycleScope.launchWhenStarted {

            viewModel.dataState.collect { event ->

                when (event) {

                    is ListRecyclerViewModel.MainStateEvent.Success -> {
                        val adapter = ListRecyclerAdapter(this@MainActivity, event.result)
                        val linearLayoutManager = LinearLayoutManager(this@MainActivity)
                        binding.recyclerView.layoutManager = linearLayoutManager
                        binding.recyclerView.adapter = adapter


                    }
                    is ListRecyclerViewModel.MainStateEvent.Failure -> {

                    }
                    is ListRecyclerViewModel.MainStateEvent.Loading -> {
                    }
                    else -> Unit
                }

            }
        }

    }
}