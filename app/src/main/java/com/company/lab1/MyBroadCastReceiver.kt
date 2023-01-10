package com.company.lab1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.*

open class MyBroadCastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        //number = intent?.getIntegerArrayListExtra("number") as ArrayList<Int>
        //trie = intent?.getIntegerArrayListExtra("BULLS_COWS")
        Log.d(TAG, "Receive")
    }
    companion object {
        const val TAG = "MyBroadCastReceiver"
        val LEVEL = 4
        val NUMBER = ArrayList<Int>()
        val GUESS = ArrayList<Int>()
    }
}