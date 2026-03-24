package com.utkarsh.news_presentation

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
import androidx.lifecycle.lifecycleScope
import com.utkarsh.common_utils.Navigator
import com.utkarsh.news_presentation.databinding.ActivityNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {
    companion object{
        fun launchActivity(activity: Activity){
            val intent= Intent(activity,NewsActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private var _binding:ActivityNewsBinding?=null
    private val binding
        get() = _binding!!

    private val newsViewModel:NewsViewModel by viewModels()

    private val newsAdapter= NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityNewsBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        setObservers()
    }

    private fun initViews() {
        binding.rvArticles.adapter=newsAdapter
    }

    private fun setObservers(){
        lifecycleScope.launchWhenStarted {
            newsViewModel.newsArticle.collectLatest {
                if(it.Loading){
                    binding.progressBar.visibility= View.VISIBLE
                }
                if(it.Error.isNotBlank()){
                    binding.progressBar.visibility= View.GONE
                    Log.e("NewsActivity",it.Error)
                    Toast.makeText(this@NewsActivity,it.Error,Toast.LENGTH_LONG).show()
                }
                it.data?.let {
                    binding.progressBar.visibility= View.GONE
                    newsAdapter.setData(it)
                }
            }
        }
    }
}

object GoToNewsActivity: Navigator {
    override fun navigate(activity: Activity) {
        NewsActivity.launchActivity(activity)
    }

}
