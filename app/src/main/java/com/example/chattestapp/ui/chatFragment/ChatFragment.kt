package com.example.chattestapp.ui.chatFragment

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api.models.Message
import com.example.chattestapp.App
import com.example.chattestapp.R
import com.example.chattestapp.databinding.FragmentChatBinding
import com.example.chattestapp.ui.adapters.MessagesAdapter
import com.example.chattestapp.ui.homeFragment.HomeFragment
import com.example.chattestapp.utils.replaceFragment
import com.example.chattestapp.utils.showToast
import javax.inject.Inject

class ChatFragment : Fragment() {

    @Inject
    lateinit var viewModel: ChatViewModel
    private lateinit var binding: FragmentChatBinding
    private lateinit var adapter: MessagesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(layoutInflater)

        App.user = "Alex"

        adapter = MessagesAdapter(requireContext())
        adapter.addMessage(
            listOf(Message(App.user,
                "Hi",
                343525),Message("Zohan",
                "Hello",
                343645),Message(App.user,
                "How are you",
                343795),Message("Zohan",
                "Good",
                343825),
            )

        )
        binding.rcViewMessages.layoutManager = LinearLayoutManager(context)
        binding.rcViewMessages.adapter = adapter


        val result = this.requireArguments().getString("bundleKey")
        if (!result.isNullOrEmpty()){
            showId(result)
        }


        return binding.root
    }

    private fun showId(id: String){
        showToast(id)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }
}