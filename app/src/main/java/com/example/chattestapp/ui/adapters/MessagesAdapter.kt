package com.example.chattestapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.api.models.Message
import com.example.chattestapp.App
import com.example.chattestapp.R
import com.example.chattestapp.utils.DateUtils
import com.example.chattestapp.utils.VIEW_TYPE_MY_MESSAGE
import com.example.chattestapp.utils.VIEW_TYPE_OTHER_MESSAGE

class MessagesAdapter(val context: Context): RecyclerView.Adapter<MessagesViewHolder>() {

    private val messages: ArrayList<Message> = ArrayList()

    fun addMessage(message: List<Message>){
        messages.addAll(message)
        notifyDataSetChanged()
    }

    inner class MyMessageViewHolder (view: View) : MessagesViewHolder(view) {
        private var messageText: TextView = view.findViewById(R.id.txtMyMessage)
        private var timeText: TextView = view.findViewById(R.id.txtMyMessageTime)

        override fun bind(message: Message) {
            messageText.text = message.message
            timeText.text = DateUtils.fromMillisToTimeString(message.time)
        }
    }

    inner class OtherMessageViewHolder (view: View) : MessagesViewHolder(view) {
        private var messageText: TextView = view.findViewById(R.id.txtOtherMessage)
        private var userText: TextView = view.findViewById(R.id.txtOtherUser)
        private var timeText: TextView = view.findViewById(R.id.txtOtherMessageTime)

        override fun bind(message: Message) {
            messageText.text = message.message
            userText.text = message.user
            timeText.text = DateUtils.fromMillisToTimeString(message.time)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val message = messages.get(position)

        return if(App.user == message.user) {
            VIEW_TYPE_MY_MESSAGE
        }
        else {
            VIEW_TYPE_OTHER_MESSAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesViewHolder {
        return if(viewType == VIEW_TYPE_MY_MESSAGE) {
            MyMessageViewHolder(
                LayoutInflater.from(context).inflate(R.layout.my_message, parent, false)
            )
        } else {
            OtherMessageViewHolder(
                LayoutInflater.from(context).inflate(R.layout.other_user_message, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: MessagesViewHolder, position: Int) {
        val message = messages.get(position)

        holder?.bind(message)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

}

open class MessagesViewHolder(view: View): RecyclerView.ViewHolder(view) {
    open fun bind(message: Message) {}
}
