package com.example.chattestapp.ui.homeFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api.models.ChatItemView
import com.example.chattestapp.App
import com.example.chattestapp.R
import com.example.chattestapp.databinding.FragmentHomeBinding
import com.example.chattestapp.ui.adapters.HomeScreenChatsAdapter
import com.example.chattestapp.ui.base.BaseFragment
import com.example.chattestapp.ui.chatFragment.ChatFragment
import com.example.chattestapp.ui.profileFragment.ProfileFragment
import com.example.chattestapp.utils.replaceFragment
import com.example.chattestapp.utils.showToast
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.system.exitProcess

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val TAG = "HomeFragment"

    @Inject
    lateinit var viewModel: HomeViewModel

    private val adapter = HomeScreenChatsAdapter()

    override fun setListeners() {
        setHasOptionsMenu(true)

        binding.rcView.layoutManager = LinearLayoutManager(context)
        getChatsList()

        super.setListeners()
    }

    private fun getChatsList() {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Log.i(TAG, "handler : $throwable")
        }
        CoroutineScope(Dispatchers.Main + SupervisorJob() + handler).launch {
            val chatList = withContext(Dispatchers.IO){ viewModel.getChatsList() }
            adapter.data = chatList
            binding.rcView.adapter = adapter
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        exitProcess(1)
    }


    fun openChat(id: Int){
        replaceFragment(ChatFragment(), true,"chat", id.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.profile -> replaceFragment(ProfileFragment(), true)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }
}