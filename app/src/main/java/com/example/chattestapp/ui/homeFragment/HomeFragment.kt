package com.example.chattestapp.ui.homeFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api.models.ChatItemView
import com.example.chattestapp.App
import com.example.chattestapp.databinding.FragmentHomeBinding
import com.example.chattestapp.ui.adapters.HomeScreenChatsAdapter
import com.example.chattestapp.ui.chatFragment.ChatFragment
import com.example.chattestapp.utils.replaceFragment
import com.example.chattestapp.utils.showToast
import javax.inject.Inject

class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"

    @Inject
    lateinit var viewModel: HomeViewModel

    private lateinit var binding: FragmentHomeBinding
    private val adapter = HomeScreenChatsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.rcView.layoutManager = LinearLayoutManager(context)
        adapter.data = viewModel.getChatsList()
        binding.rcView.adapter = adapter

        return binding.root
    }

    fun openChat(id: Int){
        replaceFragment(ChatFragment(), true, id.toString())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }
}