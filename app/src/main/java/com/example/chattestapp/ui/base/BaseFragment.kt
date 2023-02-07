package com.example.chattestapp.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.api.models.ErrorResponseBody
import javax.inject.Inject

abstract class BaseFragment<B : ViewBinding>() : Fragment() {
    protected lateinit var binding: B

    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = initBinding(inflater, container, savedInstanceState)

        initOnBackPressedCallback()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVars()
        setListeners()
    }

    private fun initOnBackPressedCallback() {
        onBackPressedCallback = object : OnBackPressedCallback(false) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
    }

    fun setBackPressedEnable(enable: Boolean) {
        onBackPressedCallback.isEnabled = enable
    }




    protected fun hideKeyBoard() {
        activity?.currentFocus?.let { view ->
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    protected open fun loadError(response: ErrorResponseBody) = Unit

    protected abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): B
    protected open fun onBackPressed() = Unit
    protected open fun initVars() = Unit
    protected open fun setListeners() = Unit
}