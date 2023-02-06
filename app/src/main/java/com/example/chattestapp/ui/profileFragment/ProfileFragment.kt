package com.example.chattestapp.ui.profileFragment

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.example.chattestapp.App
import com.example.chattestapp.R
import com.example.chattestapp.databinding.FragmentProfileBinding
import com.example.chattestapp.ui.base.BaseFragment
import javax.inject.Inject

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    @Inject
    lateinit var viewModel: ProfileViewModel

    override fun setListeners() {




        super.setListeners()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }
}