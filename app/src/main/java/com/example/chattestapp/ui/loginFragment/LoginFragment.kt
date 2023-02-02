package com.example.chattestapp.ui.loginFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.api.models.AuthUserResponseBody
import com.example.chattestapp.App
import com.example.chattestapp.R
import com.example.chattestapp.databinding.FragmentLoginBinding
import com.example.chattestapp.ui.verifyFragment.VerifyFragment
import com.example.chattestapp.utils.replaceFragment
import com.example.chattestapp.utils.showToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginFragment : Fragment() {

    private val TAG = "LoginFragment"

    @Inject
    lateinit var viewModel: LoginViewModel

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)

        binding.loginBtn.setOnClickListener {
            checkNumber()
        }

        return binding.root
    }

    private fun checkNumber(){
        if (binding.phoneNumber.text.isNullOrEmpty()){
            showToast(getString(R.string.empty_number))
        } else {
//            sendAuthCode("+7" + binding.phoneNumber.text.toString())
            // TODO
            replaceFragment(VerifyFragment(), true)
        }
    }

    private fun sendAuthCode(phoneNumber: String){
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.sendAuthCode(phoneNumber).enqueue(object : Callback<AuthUserResponseBody> {
                override fun onResponse(
                    call: Call<AuthUserResponseBody>,
                    response: Response<AuthUserResponseBody>
                ) {
                    Log.i(TAG, "onResponse: ${response.body()}")
                    replaceFragment(VerifyFragment(), true)
                }

                override fun onFailure(call: Call<AuthUserResponseBody>, t: Throwable) {
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