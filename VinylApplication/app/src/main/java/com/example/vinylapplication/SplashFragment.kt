package com.example.vinylapplication

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.vinylapplication.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    lateinit var binding: FragmentSplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)

        //A terrible way to do a Splash page, BUT, normally i would think the app would be loading up, and when it was done
        //the splash would dissappear. Since nothing is needed to be loaded up, i just did this for a visual effect
        var handler = Handler()
        handler.postDelayed({
            binding.progressBar.visibility = View.GONE
        },8000)
        handler.postDelayed({
            binding.vynylLogo.visibility = View.GONE
            findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)
        },10000)

        // Inflate the layout for this fragment
        return binding.root
    }
}