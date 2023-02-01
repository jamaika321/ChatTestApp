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

class VerifyFragment : Fragment() {
    
    private val TAG = "VerifyFragment"

    @Inject
    lateinit var viewModel: VerifyViewModel

    private lateinit var binding: FragmentVerifyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVerifyBinding.inflate(layoutInflater)

        binding.verifyBtn.setOnClickListener { 
            checkVerifyCode()
        }
        
        return binding.root
    }

    private fun checkVerifyCode(){
        if (binding.etVerifyCode.text.toString().length < 6){
            showToast(getString(R.string.verification_code_empty))
        } else {
            sendAuthCode(binding.etVerifyCode.text.toString())
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
                        if (it?.isUserExists == true) {
                            CoroutineScope(Dispatchers.IO).launch {
                                viewModel.saveUserToken(response.body()!!)
                            }
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

}