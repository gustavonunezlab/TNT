package com.example.demo.fragment.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.demo.R
import com.example.demo.databinding.FragmentLoginBinding

private var visible: Boolean = false

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        _binding!!.loginButton.setOnClickListener { checkLogin() }
        _binding!!.cancelButton.setOnClickListener { goBack() }

        _binding!!.editTextPassword.setOnTouchListener(OnTouchListener { v, event ->
            val DRAWABLE_LEFT = 0
            val DRAWABLE_TOP = 1
            val DRAWABLE_RIGHT = 2
            val DRAWABLE_BOTTOM = 3
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= _binding!!.editTextPassword.getRight() - _binding!!.editTextPassword.getCompoundDrawables()
                        .get(DRAWABLE_RIGHT).getBounds().width()
                ) {
                    turnVisibility()
                    return@OnTouchListener true
                }
            }
            false
        })
        return view
    }

    private fun turnVisibility() {
        if (!visible) {
            _binding!!.editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            _binding!!.editTextPassword.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.visible_on,
                0
            );
            visible = true;
        } else {
            _binding!!.editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            _binding!!.editTextPassword.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.visible_off,
                0
            );
            visible = false;
        }
    }

    private fun checkLogin() {
        if (checkUser()) {
            if (checkPassword()) {
                findNavController().navigate(R.id.goToHomeFragment)
            } else {
                showErrorMsg()
            }
        } else {
            showErrorMsg()
        }
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
        if (_binding?.editTextPassword?.text.toString() == "admin")
            return true;
        return false;
    }

    private fun showErrorMsg() {
        _binding?.loginFailedTextView?.visibility = View.VISIBLE

        var fadeOutAnimationObject = AlphaAnimation(1f, 0f)
        fadeOutAnimationObject.setDuration(2000)
        Handler(Looper.getMainLooper()).postDelayed({
            _binding?.loginFailedTextView?.startAnimation(fadeOutAnimationObject)
            _binding?.loginFailedTextView?.visibility = View.INVISIBLE
        }, 1000)

    }
}