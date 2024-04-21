package com.dicoding.asclepius.view.article

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.R
import com.dicoding.asclepius.adapter.ArticleAdapter
import com.dicoding.asclepius.databinding.ActivityArticleBinding
import com.dicoding.asclepius.data.Result

class ArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleBinding
    private lateinit var viewModel: ArticleViewModel
    private val adapter by lazy { ArticleAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.article)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupRecyclerView()
        setupViewModel()
        observeArticles()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupViewModel() {
        val factory: ArticleViewModelFactory = ArticleViewModelFactory.getInstance()

        viewModel = ViewModelProvider(
            this,
            factory
        )[ArticleViewModel::class.java]
    }

    private fun observeArticles() {
        viewModel.listArticle.observe(this){
            when(it){
                is Result.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is Result.Success -> {
                    binding.progressBar.isVisible = false
                    adapter.submitList(it.data)
                }
                is Result.Error -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(this@ArticleActivity, "An error occurred", Toast.LENGTH_SHORT).show()
            }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvArticle.setHasFixedSize(true)
        binding.rvArticle.layoutManager = LinearLayoutManager(this)
        binding.rvArticle.adapter = adapter
    }
}