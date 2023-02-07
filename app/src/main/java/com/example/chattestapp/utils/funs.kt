package com.example.chattestapp.utils

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.chattestapp.ui.MainActivity
import com.example.chattestapp.R

fun replaceFragment(fragment: Fragment, addStack: Boolean = true, key: String = "",  message : String = "") {
    val args = Bundle()
    args.putString(key, message)
    if (addStack) {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.fragment_container,
                fragment::class.java,
                args
            ).commit()
    } else {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                fragment::class.java,
                args
            ).commit()
    }

}

fun restartActivity() {
    val intent = Intent(APP_ACTIVITY, MainActivity::class.java)
    APP_ACTIVITY.startActivity(intent)
    APP_ACTIVITY.finish()
}

fun showToast(message: String) {
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_SHORT).show()
}