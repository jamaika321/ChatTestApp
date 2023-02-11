package com.example.chattestapp.ui.loginFragment

import android.content.Context
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.api.models.AuthUserRequestBody
import com.example.api.models.AuthUserResponseBody
import com.example.api.models.ErrorResponseBody
import com.example.chattestapp.App
import com.example.chattestapp.R
import com.example.chattestapp.databinding.FragmentLoginBinding
import com.example.chattestapp.ui.base.BaseFragment
import com.example.chattestapp.ui.verifyFragment.VerifyFragment
import com.example.chattestapp.utils.replaceFragment
import com.example.chattestapp.utils.showToast
import javax.inject.Inject

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val TAG = "LoginFragment"

    @Inject
    lateinit var viewModel: LoginViewModel

    override fun setListeners() {
        binding.loginBtn.setOnClickListener {
            checkNumber()
        }

        binding.phoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        binding.countryCodePicker.setCountryForNameCode("RU")

        super.setListeners()
    }

    private fun checkNumber(){
        val phoneNumber = binding.phoneNumber.text.toString().replace("[^0-9]".toRegex(), "")
        if (phoneNumber.isEmpty()) {
            showToast(getString(R.string.empty_number))
        } else if (phoneNumber.length < 10){
            showToast(getString(R.string.error_format_number))
        } else {
//            sendAuthCodeWithNumber(AuthUserRequestBody("+" + binding.countryCodePicker.selectedCountryCode + phoneNumber))
        }
    }


    private fun loadSuccess(response: AuthUserResponseBody){
        if (response.is_success) {
            replaceFragment(VerifyFragment(), true, "phone", binding.phoneNumber.text.toString())
        }
    }

    override fun loadError(response: ErrorResponseBody) {
        showToast(response.detail[0].msg)
        super.loadError(response)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }
}