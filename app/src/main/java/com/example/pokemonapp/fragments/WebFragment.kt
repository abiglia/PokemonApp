package com.example.pokemonapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentMainBinding
import com.example.pokemonapp.databinding.FragmentWebBinding


class WebFragment : Fragment() {

    private lateinit var binding: FragmentWebBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWebBinding.inflate(inflater, container, false)


        webView()


        return binding.root
    }

    private fun webView() {
        binding.webView.apply {
            binding.webView.loadUrl("https://www.pokemon.com/el/pokedex/")
            binding.webView.webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }
    }

}