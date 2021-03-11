package com.example.vinylapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.vinylapplication.databinding.FragmentSignInBinding
class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var viewModel: SignInFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SignInFragmentViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.setUserName(arguments?.getString("userName") ?: "")
        viewModel.setPassword(arguments?.getString("password") ?: "")

        viewModel?.navigationStatus?.observe(requireActivity(), EventObserver { result ->
            when(result){
                NavigateStatus.SIGN_IN -> {
                    val bundle = bundleOf("userName" to binding.editTextUsername.text.toString())
                    findNavController().navigate(R.id.action_signInFragment_to_mainScreenFragment, bundle)
                }
                NavigateStatus.REGISTER -> {
                    val bundle = bundleOf("userName" to binding.editTextUsername.text.toString(), "password" to binding.editTextPassword.text.toString())
                    findNavController().navigate(R.id.action_signInFragment_to_registerFragment, bundle)
                }
                NavigateStatus.FAIL_PASSWORD -> Toast.makeText(this.context,"Password must contain 1 number, 1 letter, 1 special character and be 8 characters long!", Toast.LENGTH_LONG).show()
                NavigateStatus.FAIL_USERNAME -> Toast.makeText(this.context,"Username must have at least 4 characters!", Toast.LENGTH_LONG).show()
            }
        })
        // Inflate the layout for this fragment
        return binding.root
    }
}