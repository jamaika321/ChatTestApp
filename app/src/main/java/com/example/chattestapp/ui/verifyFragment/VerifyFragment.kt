package com.example.chattestapp.ui.verifyFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.api.models.CheckAuthCodeRequestBody
import com.example.api.models.CheckAuthCodeResponseBody
import com.example.api.models.ErrorResponseBody
import com.example.chattestapp.App
import com.example.chattestapp.R
import com.example.chattestapp.databinding.FragmentVerifyBinding
import com.example.chattestapp.ui.base.BaseFragment
import com.example.chattestapp.ui.homeFragment.HomeFragment
import com.example.chattestapp.ui.registrationFragment.RegistrationFragment
import com.example.chattestapp.utils.replaceFragment
import com.example.chattestapp.utils.showToast
import com.example.entities.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class VerifyFragment : BaseFragment<FragmentVerifyBinding>() {
    
    private val TAG = "VerifyFragment"

    @Inject
    lateinit var viewModel: VerifyViewModel
    private var phoneNumber: String? = null

    override fun setListeners() {
        phoneNumber = this.requireArguments().getString("phone")
        binding.verifyBtn.setOnClickListener {
//            checkVerifyCode()
            showToast(phoneNumber!!)
        }
        super.setListeners()
    }

    private fun checkVerifyCode(){
        if (binding.etVerifyCode.text.toString().length < 6){
            showToast(getString(R.string.verification_code_empty))
        } else {
            sendAuthCode(binding.etVerifyCode.text.toString())
            // TODO
        }
    }
    
    private fun sendAuthCode(code: String){
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.checkAuthCode(CheckAuthCodeRequestBody(phoneNumber!!, code)).enqueue(object : Callback<Either<ErrorResponseBody, CheckAuthCodeResponseBody>> {
                override fun onResponse(
                    call: Call<Either<ErrorResponseBody, CheckAuthCodeResponseBody>>,
                    response: Response<Either<ErrorResponseBody, CheckAuthCodeResponseBody>>
                ) {
                    response.body().let {
                        it!!.either(::loadError, ::loadSuccess)
                    }
                }

                override fun onFailure(call: Call<Either<ErrorResponseBody, CheckAuthCodeResponseBody>>, t: Throwable) {

                }
            })
        }
    }

    private fun loadSuccess(response: CheckAuthCodeResponseBody) {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.saveUserToken(response)
        }
        if (response.isUserExists) {
            replaceFragment(HomeFragment(), false)
        } else {
            if (phoneNumber != null) {
                replaceFragment(RegistrationFragment(), true, "phone", phoneNumber!!)
            } else {
                replaceFragment(RegistrationFragment(), true)
            }
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
    ): FragmentVerifyBinding {
        return FragmentVerifyBinding.inflate(inflater, container, false)
    }

}