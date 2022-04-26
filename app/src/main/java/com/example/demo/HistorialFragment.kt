package com.example.demo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.databinding.FragmentHistorialBinding

class HistorialFragment : Fragment() {

    private var _binding: FragmentHistorialBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        _binding = FragmentHistorialBinding.inflate(inflater, container, false)

        val articleList: RecyclerView = binding.list
        val articleAdapter = ArticleAdapter() // (2)
        articleList.adapter = articleAdapter // (3)
        articleAdapter.articles = Article.data // (4)


        val view = binding.root
        return view
    }

      
}