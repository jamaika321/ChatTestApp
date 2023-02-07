package com.example.chattestapp.ui.profileFragment

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.example.api.models.ErrorResponseBody
import com.example.api.models.PutUserInfoRequestBody
import com.example.api.models.PutUserInfoResponseBody
import com.example.chattestapp.App
import com.example.chattestapp.R
import com.example.chattestapp.databinding.FragmentProfileBinding
import com.example.chattestapp.ui.base.BaseFragment
import com.example.entities.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    @Inject
    lateinit var viewModel: ProfileViewModel

    override fun setListeners() {


        super.setListeners()
    }

    private fun putUserInfo(userInfo: PutUserInfoRequestBody){
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.putUserInfo(userInfo).enqueue(object: Callback<Either<ErrorResponseBody, PutUserInfoResponseBody>> {
                override fun onResponse(
                    call: Call<Either<ErrorResponseBody, PutUserInfoResponseBody>>,
                    response: Response<Either<ErrorResponseBody, PutUserInfoResponseBody>>
                ) {
                    response.body()!!.either(::errorResponse, ::avatarResponse)
                }

                override fun onFailure(
                    call: Call<Either<ErrorResponseBody, PutUserInfoResponseBody>>,
                    t: Throwable
                ) {
                    TODO("Not yet implemented")
                }


            })
        }
    }

    private fun avatarResponse(response: PutUserInfoResponseBody){

    }

    private fun errorResponse(response: ErrorResponseBody){

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