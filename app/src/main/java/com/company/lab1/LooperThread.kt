package com.company.lab1

import android.os.Handler
import android.os.Looper
import android.util.Log


class LooperThread(
    private val listener: OnHandlerCreatedListener
) : Thread() {

    companion object {
        private const val BREAK_LOOP = -1
        private const val TAG = "AAAA"
    }

    private lateinit var handler: Handler
    override fun run() {
        val threadId = Thread.currentThread().getId()
        Log.d(TAG, "Started thread with ID=$threadId")
        Looper.prepare()
        val myLooper: Looper = Looper.myLooper()!!
        handler = Handler(myLooper) {
            if (it.what == BREAK_LOOP) {
                myLooper.quitSafely()
                true
            } else false
        }
        notifyHandlerCreated()
        Looper.loop()
        Log.d(TAG, "Looper has been stopped")
    }

    fun shutdown() {
        handler.obtainMessage(BREAK_LOOP).sendToTarget()
    }

    private fun notifyHandlerCreated() {
        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post { listener.invoke(this, handler) }
    }
}