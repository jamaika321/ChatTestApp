package com.example.chattestapp.ui.registrationFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.api.models.ErrorResponseBody
import com.example.api.models.RegisterRequestBody
import com.example.api.models.RegisterResponseBody
import com.example.chattestapp.App
import com.example.chattestapp.databinding.FragmentRegistrationBinding
import com.example.chattestapp.ui.base.BaseFragment
import com.example.chattestapp.ui.homeFragment.HomeFragment
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

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>() {

    @Inject
    lateinit var viewModel: RegistrationViewModel

    override fun setListeners() {
        binding.root.setOnClickListener {
            hideKeyBoard()
        }

        val result = this.requireArguments().getString("phone")
        if (!result.isNullOrEmpty()) {
            binding.registrationPhone.keyListener = null
            binding.registrationPhone.setText(result)
        }

        binding.btnRegistration.setOnClickListener {
            val name = binding.registrationName.text.toString()
            val userName = binding.registrationUserName.text.toString()
            val phone = binding.registrationPhone.text.toString()
            if (phone.isEmpty()) {
                showToast("Введите номер телефона!")
            } else if (name.isEmpty()) {
                showToast("Введите имя!")
            } else if (userName.isEmpty()) {
                showToast("Введите имя пользователя!")
            } else {
                register(RegisterRequestBody(phone, name, userName))
            }
        }

        super.setListeners()
    }

    private fun register(user: RegisterRequestBody) {
        CoroutineScope(Dispatchers.Default).launch {
            viewModel.register(user).enqueue(object : Callback<Either<ErrorResponseBody, RegisterResponseBody>> {
                override fun onResponse(
                    call: Call<Either<ErrorResponseBody, RegisterResponseBody>>,
                    response: Response<Either<ErrorResponseBody, RegisterResponseBody>>
                ) {
                    response.body().let {
                        it!!.either(::loadError, ::loadSuccess)
                    }
                }

                override fun onFailure(call: Call<Either<ErrorResponseBody, RegisterResponseBody>>, t: Throwable) {

                }

            })
        }
    }

    private fun loadSuccess(response: RegisterResponseBody){

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
    ): FragmentRegistrationBinding {
        return FragmentRegistrationBinding.inflate(inflater, container, false)
    }
}