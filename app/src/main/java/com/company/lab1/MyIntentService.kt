package com.company.lab1

import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import java.util.*

class MyIntentService : IntentService("IntentService") {

    override fun onHandleIntent(intent: Intent?) {

        val action = intent?.action ?: return
        val number = intent.getIntegerArrayListExtra(EXTRA_IN_NUMBER)
        val guess = intent.getIntegerArrayListExtra(EXTRA_IN_GUESS)
        val n = intent.getIntExtra(EXTRA_IN_NUMBER_SIZE, 4)
        val bundle = Bundle()

        val broadcastIntent = Intent()
        when (action) {
            ACTION_GENERATE_ARRAY -> {
                bundle.putIntegerArrayList(EXTRA_OUT_NUMBER, getRandomUnrepeating(n))
                broadcastIntent.action = ACTION_GENERATE_ARRAY
            }
            ACTION_GET_BULLS_COWS -> {
                if (number != null && guess != null){
                    bundle.putIntegerArrayList(EXTRA_OUT_BULL_COW, getBullsCows(number, guess))
                }
                broadcastIntent.action = ACTION_GET_BULLS_COWS
            }
            else ->
                throw RuntimeException("Unknown action!")
        }
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
        bull_cow.add(0)
        bull_cow.add(0)
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
        const val ACTION_GENERATE_ARRAY = BuildConfig.APPLICATION_ID + ".ACTION_GENERATE_ARRAY"
        const val ACTION_GET_BULLS_COWS = BuildConfig.APPLICATION_ID + ".ACTION_GET_BULLS_COWS"
        const val EXTRA_IN_NUMBER_SIZE = BuildConfig.APPLICATION_ID + "EXTRA_IN_NUMBER_SIZE"
        const val EXTRA_IN_NUMBER = BuildConfig.APPLICATION_ID + "EXTRA_IN_NUMBER"
        const val EXTRA_IN_GUESS = BuildConfig.APPLICATION_ID + "EXTRA_IN_GUESS"
        const val EXTRA_OUT_BULL_COW = BuildConfig.APPLICATION_ID + "EXTRA_OUT_BULL_COW"
        const val EXTRA_OUT_NUMBER = BuildConfig.APPLICATION_ID + "EXTRA_OUT_NUMBER"
    }
}