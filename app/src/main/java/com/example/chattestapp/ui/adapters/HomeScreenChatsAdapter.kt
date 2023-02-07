package com.example.chattestapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.api.models.ChatItemView
import com.example.chattestapp.R
import com.example.chattestapp.databinding.ChatItemBinding
import com.example.chattestapp.ui.homeFragment.HomeFragment
import com.squareup.picasso.Picasso

class HomeScreenChatsAdapter(): RecyclerView.Adapter<HomeScreenChatsAdapter.HomeScreenChatsViewHolder>() {

    var data: List<ChatItemView> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class HomeScreenChatsViewHolder(val binding: ChatItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeScreenChatsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChatItemBinding.inflate(inflater, parent, false)

        return HomeScreenChatsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeScreenChatsViewHolder, position: Int) {
        val item = data[position]
        val context = holder.itemView.context

        with(holder.binding){
            if (item.image.isEmpty()){
                ivAvatar.setImageResource(R.drawable.ic_login)
            } else {
                Picasso.get()
                    .load(item.image)
                    .into(ivAvatar)
            }
            tvName.text = item.name
            tvLastMessage.text = item.lastMessage
            tvDateLastMessage.text = item.lastMessageDate


            root.setOnClickListener {
                HomeFragment().openChat(item.id)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}