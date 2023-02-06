package com.example.chattestapp.ui.verifyFragment

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.api.models.CheckAuthCodeResponseBody
import com.example.chattestapp.App
import com.example.chattestapp.R
import com.example.chattestapp.databinding.FragmentVerifyBinding
import com.example.chattestapp.ui.base.BaseFragment
import com.example.chattestapp.ui.homeFragment.HomeFragment
import com.example.chattestapp.ui.registerFragment.RegistrationFragment
import com.example.chattestapp.utils.replaceFragment
import com.example.chattestapp.utils.showToast
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

    override fun setListeners() {
        binding.verifyBtn.setOnClickListener {
            checkVerifyCode()
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
            viewModel.checkAuthCode(code).enqueue(object : Callback<CheckAuthCodeResponseBody> {
                override fun onResponse(
                    call: Call<CheckAuthCodeResponseBody>,
                    response: Response<CheckAuthCodeResponseBody>
                ) {
                    Log.i(TAG, "onResponse: ${response.body()}")
                    response.body().let {
                        CoroutineScope(Dispatchers.IO).launch {
                            viewModel.saveUserToken(response.body()!!)
                        }
                        if (it?.isUserExists == true) {
                            replaceFragment(HomeFragment(), false)
                        } else {
                            replaceFragment(RegistrationFragment(), true)
                        }
                    }
                }

                override fun onFailure(call: Call<CheckAuthCodeResponseBody>, t: Throwable) {
                    Log.i(TAG, "onFailure: $t")
                }

            })
        }
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