package com.utkarsh.search_presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.utkarsh.common_utils.Navigator
import com.utkarsh.search_presentation.databinding.ActivitySearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    companion object{
        fun launchActivity(activity: Activity){
            val intent= Intent(activity,SearchActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private var _binding:ActivitySearchBinding?=null
    val binding:ActivitySearchBinding
        get() = _binding!!

    private val viewModel:SearchViewModel by viewModels()
    private val newsAdapter:NewsAdapter= NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()

        setObserver()
    }

    private fun setObserver() {
        lifecycleScope.launch {
            viewModel.searchArticles.collectLatest {
                if(it.isLoading){
                    binding.progressBar.visibility= View.VISIBLE
                }
                if(it.error.isNotBlank()){
                    binding.progressBar.visibility= View.GONE
                    Toast.makeText(applicationContext,it.error,Toast.LENGTH_LONG).show()
                    Log.e("SearchActivity","error -> ${it.error}")
                }
                it.Success?.let{
                    binding.progressBar.visibility= View.GONE
                    newsAdapter.setData(it)
                }
            }
        }
    }
//    https://newsapi.org/v2/everything?q=apple&from=2026-03-24&to=2026-03-24&sortBy=popularity&apiKey=

    private fun initView() {
        binding.searchResult.adapter=newsAdapter

        binding.searchTitle.doAfterTextChanged {
            val map=mutableMapOf<String,String>()
            map[Constant.ApiKey]=""
            map[Constant.QUERY]=it.toString()
            viewModel.getSearchArticles(map)
        }
    }
}

object GoToSearchActivity: Navigator {
    override fun navigate(activity: Activity) {
        SearchActivity.launchActivity(activity)
    }
}