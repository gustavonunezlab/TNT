package com.example.demo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.compose.animation.fadeOut
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.demo.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        _binding!!.loginButton.setOnClickListener{ checkLogin() }
        _binding!!.cancelButton.setOnClickListener{ goBack() }
        return view
    }

    private fun checkLogin () {
        if(checkUser()) {
            if (checkPassword()) {
                findNavController().navigate(R.id.succesLoginAction)
            } else {
                showErrorMsg()
            }
        } else {
            showErrorMsg()        }
    }

    private fun goBack() {
        findNavController().navigate(R.id.main_fragment)
    }

    private fun checkUser(): Boolean {
        if (_binding?.editTextUserName?.text.toString() == "admin")
            return true
        return false
    }

    private fun checkPassword(): Boolean {
        if (_binding?.editTextTextPassword?.text.toString() == "admin")
            return true;
        return false;
    }

    private fun showErrorMsg() {
        _binding?.loginFailedTextView?.visibility = View.VISIBLE

        var fadeOutAnimationObject = AlphaAnimation(1f, 0f)
        fadeOutAnimationObject.setDuration(2500)
        Handler(Looper.getMainLooper()).postDelayed({
            _binding?.loginFailedTextView?.startAnimation(fadeOutAnimationObject)
            _binding?.loginFailedTextView?.visibility = View.INVISIBLE
        }, 2500)

    }
}