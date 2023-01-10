package com.company.lab1

import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import androidx.compose.runtime.mutableStateListOf
import java.util.*

class MyIntentService : IntentService("IntentService") {

    private val ACTION_UPDATE = "com.company.lab1.MyIntentService.UPDATE"
    private val BULLS_COWS = "BULLS_COWS"
    private val NUMBER = "NUMBER"
    private val GUESS = "GUESS"

    override fun onHandleIntent(intent: Intent?) {

        val action = intent?.action ?: return
        val number = intent.getIntegerArrayListExtra(NUMBER)
        val guess = intent.getIntegerArrayListExtra(GUESS)
        val n = intent.getIntExtra("n", 4)
        val bundle = Bundle()

        when (action) {
            ACTION_GENERATE_ARRAY -> {
                bundle.putIntegerArrayList(NUMBER, getRandomUnrepeating(n))
            }
            ACTION_GET_BULLS_COWS -> {
                if (number != null && guess != null)
                    bundle.putIntegerArrayList(BULLS_COWS, getBullsCows(number, guess))
            }
            else ->
                throw RuntimeException("Unknown action!")
        }
        val broadcastIntent = Intent()
        broadcastIntent.action = ACTION_UPDATE
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT)
        broadcastIntent.putExtras(bundle)
        sendBroadcast(broadcastIntent)
    }

    private fun getRandomUnrepeating(digits: Int): ArrayList<Int> {
        val res = ArrayList<Int>()
        val nums = mutableStateListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        Collections.shuffle(nums)
        for (i in 0..digits - 1) {
            res.add(nums[i])
        }
        return res
    }

    private fun contain(number: Int, array: ArrayList<Int>): Boolean {
        for (el in array) {
            if (number == el)
                return true
        }
        return false
    }

    private fun getBullsCows(number: ArrayList<Int>, guess: ArrayList<Int>): ArrayList<Int> {
        val bull_cow = ArrayList<Int>()
        bull_cow[0] = 0
        bull_cow[1] = 0
        for (i in 0..(number.size - 1)) {
            if (contain(guess[i], number)) {
                if (guess[i] == number[i]) {
                    bull_cow[0] += 1
                } else {
                    bull_cow[1] += 1
                }
            }
        }
        return bull_cow
    }

    companion object {
        const val ACTION_GENERATE_ARRAY =
            BuildConfig.APPLICATION_ID + ".ACTION_GENERATE_ARRAY"
        const val ACTION_GET_BULLS_COWS =
            BuildConfig.APPLICATION_ID + ".ACTION_GET_BULLS_COWS"
        const val EXTRA_ARRAY_SIZE = "EXTRA_ARRAY_SIZE"
        @JvmField val TAG: String =
            MyIntentService::class.java.simpleName
    }
}