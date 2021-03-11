package com.example.vinylapplication

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.vinylapplication.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private lateinit var binding : FragmentWelcomeBinding
    private lateinit var viewModel: WelcomeFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(WelcomeFragmentViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_welcome, container,false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel;
        viewModel?.navigationStatus?.observe(requireActivity(), EventObserver { result ->
            when(result){
                NavigateStatus.SIGN_IN -> findNavController().navigate(R.id.action_welcomeFragment_to_signInFragment)
                NavigateStatus.REGISTER -> findNavController().navigate(R.id.action_welcomeFragment_to_registerFragment)
            }
        })
        binding.vynylLogo.visibility = View.VISIBLE
        return binding.root
    }
}