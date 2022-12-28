package com.company.lab1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.compose.runtime.mutableStateListOf
import androidx.fragment.app.Fragment
import java.util.*

class GameFragment : Fragment() {
    var counter = 0

    var cnt = 0
    val resultList = arrayOf<String>()
    //"-1 Bulls: 0 Cows: 0"


    private fun get_button_by_text(
        text: String,
        btn1: Button,
        btn2: Button,
        btn3: Button,
        btn4: Button,
        btn5: Button,
        btn6: Button,
        btn7: Button,
        btn8: Button,
        btn9: Button,
        btn0: Button,
    ): Button {
        if (text == "1")
            return btn1
        else if (text == "2")
            return btn2
        else if (text == "3")
            return btn3
        else if (text == "4")
            return btn4
        else if (text == "5")
            return btn5
        else if (text == "6")
            return btn6
        else if (text == "7")
            return btn7
        else if (text == "8")
            return btn8
        else if (text == "9")
            return btn9
        else
            return btn0
    }

    private fun delete_last(
        counter: Int,

        el1: TextView,
        el2: TextView,
        el3: TextView,
        el4: TextView,
        el5: TextView,
        el6: TextView,

        btn1: Button,
        btn2: Button,
        btn3: Button,
        btn4: Button,
        btn5: Button,
        btn6: Button,
        btn7: Button,
        btn8: Button,
        btn9: Button,
        btn0: Button
    ) {
        if (counter == 1) {
            get_button_by_text(
                el1.text as String,
                btn1,
                btn2,
                btn3,
                btn4,
                btn5,
                btn6,
                btn7,
                btn8,
                btn9,
                btn0
            ).isEnabled = true
            el1.text = "----"
            this.counter -= 1
        } else if (counter == 2) {
            get_button_by_text(
                el2.text as String,
                btn1,
                btn2,
                btn3,
                btn4,
                btn5,
                btn6,
                btn7,
                btn8,
                btn9,
                btn0
            ).isEnabled = true
            el2.text = "----"
            this.counter -= 1
        } else if (counter == 3) {
            get_button_by_text(
                el3.text as String,
                btn1,
                btn2,
                btn3,
                btn4,
                btn5,
                btn6,
                btn7,
                btn8,
                btn9,
                btn0
            ).isEnabled = true
            el3.text = "----"
            this.counter -= 1
        } else if (counter == 4) {
            get_button_by_text(
                el4.text as String,
                btn1,
                btn2,
                btn3,
                btn4,
                btn5,
                btn6,
                btn7,
                btn8,
                btn9,
                btn0
            ).isEnabled = true
            el4.text = "----"
            this.counter -= 1
        } else if (counter == 5) {
            get_button_by_text(
                el5.text as String,
                btn1,
                btn2,
                btn3,
                btn4,
                btn5,
                btn6,
                btn7,
                btn8,
                btn9,
                btn0
            ).isEnabled = true
            el5.text = "----"
            this.counter -= 1
        } else if (counter == 6) {
            get_button_by_text(
                el6.text as String,
                btn1,
                btn2,
                btn3,
                btn4,
                btn5,
                btn6,
                btn7,
                btn8,
                btn9,
                btn0
            ).isEnabled = true
            el6.text = "----"
            this.counter -= 1
        }
    }

    private fun write(
        btn: Button,
        text: String,
        n: Int,
        counter: Int,
        el1: TextView,
        el2: TextView,
        el3: TextView,
        el4: TextView,
        el5: TextView,
        el6: TextView
    ) {
        if (counter < n) {
            if (n == 4) {
                when (counter) {
                    0 -> {
                        el1.text = text
                        this.counter += 1
                        btn.isEnabled = false
                    }
                    1 -> {
                        el2.text = text
                        this.counter += 1
                        btn.isEnabled = false
                    }
                    2 -> {
                        el3.text = text
                        this.counter += 1
                        btn.isEnabled = false
                    }
                    3 -> {
                        el4.text = text
                        this.counter += 1
                        btn.isEnabled = false
                    }
                }
            } else if (n == 5) {
                when (counter) {
                    0 -> {
                        el1.text = text
                        this.counter += 1
                        btn.isEnabled = false
                    }
                    1 -> {
                        el2.text = text
                        this.counter += 1
                        btn.isEnabled = false
                    }
                    2 -> {
                        el3.text = text
                        this.counter += 1
                        btn.isEnabled = false
                    }
                    3 -> {
                        el4.text = text
                        this.counter += 1
                        btn.isEnabled = false
                    }
                    4 -> {
                        el5.text = text
                        this.counter += 1
                        btn.isEnabled = false
                    }
                }
            } else if (n == 6) {
                when (counter) {
                    0 -> {
                        el1.text = text
                        this.counter += 1
                        btn.isEnabled = false
                    }
                    1 -> {
                        el2.text = text
                        this.counter += 1
                        btn.isEnabled = false
                    }
                    2 -> {
                        el3.text = text
                        this.counter += 1
                        btn.isEnabled = false
                    }
                    3 -> {
                        el4.text = text
                        this.counter += 1
                        btn.isEnabled = false
                    }
                    4 -> {
                        el5.text = text
                        this.counter += 1
                        btn.isEnabled = false
                    }
                    5 -> {
                        el6.text = text
                        this.counter += 1
                        btn.isEnabled = false
                    }
                }
            }
        }
    }

    private fun get_random_unrepeating(digits: Int): Int {
        val sb = StringBuilder()
        val nums = mutableStateListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        Collections.shuffle(nums)
        for (i in 1..digits) {
            sb.append(nums[i])
        }
        val res = Integer.parseInt(sb.toString())
        when (digits) {
            4 -> {
                if (res < 1000)
                    return get_random_unrepeating(digits)
            }
            5 -> {
                if (res < 10000)
                    return get_random_unrepeating(digits)
            }
            6 -> {
                if (res < 100000)
                    return get_random_unrepeating(digits)
            }
        }
        return res
    }

    private fun getDigits(num: Int): ArrayList<Int> {
        val res = ArrayList<Int>()
        val str = arrayOf(num.toString())
        for (i in str) {
            res.add(Integer.parseInt(i))
        }
        return res
    }
    private fun contain(number: Int, array: ArrayList<Int>): Boolean{
        for (el in array){
            if (number == el)
                return true
        }
        return false
    }

    private fun getBullsCows(number: Int, guess: Int): Array<Int> {
        val bull_cow = arrayOf(0, 0)
        val numberArray = getDigits(number)
        val guessArray = getDigits(guess)

        for (i in 0..numberArray.size) {
            if (contain(guessArray[i], numberArray)) {
                if (guessArray[i] == numberArray[i]){
                    bull_cow[0] += 1
                } else {
                    bull_cow[1] += 1
                }
            }
        }
        return bull_cow
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val n = arguments?.getInt("N")
        if (n != null) {

            val btn_number1: Button = view.findViewById(R.id.number1)
            val btn_number2: Button = view.findViewById(R.id.number2)
            val btn_number3: Button = view.findViewById(R.id.number3)
            val btn_number4: Button = view.findViewById(R.id.number4)
            val btn_number5: Button = view.findViewById(R.id.number5)
            val btn_number6: Button = view.findViewById(R.id.number6)
            val btn_number7: Button = view.findViewById(R.id.number7)
            val btn_number8: Button = view.findViewById(R.id.number8)
            val btn_number9: Button = view.findViewById(R.id.number9)
            val btn_number0: Button = view.findViewById(R.id.number0)

            val btn_delete_last: Button = view.findViewById(R.id.btn_delete)
            val btn_ok: Button = view.findViewById(R.id.btn_ok)

            val listOfResults: ListView = view.findViewById(R.id.results)

            val arrayAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1, resultList)
            listOfResults.adapter = arrayAdapter

            val el1: TextView = view.findViewById(R.id.first)
            val el2: TextView = view.findViewById(R.id.second)
            val el3: TextView = view.findViewById(R.id.third)
            val el4: TextView = view.findViewById(R.id.fourth)
            val el5: TextView = view.findViewById(R.id.fifth)
            val el6: TextView = view.findViewById(R.id.sixth)

            if (n == 4) {
                el5.visibility = View.INVISIBLE
                el6.visibility = View.INVISIBLE
            } else if (n == 5) {
                el6.visibility = View.INVISIBLE
            }


            btn_number1.setOnClickListener {
                write(btn_number1, "1", n, counter, el1, el2, el3, el4, el5, el6)
            }
            btn_number2.setOnClickListener {
                write(btn_number2, "2", n, counter, el1, el2, el3, el4, el5, el6)
            }
            btn_number3.setOnClickListener {
                write(btn_number3, "3", n, counter, el1, el2, el3, el4, el5, el6)
            }
            btn_number4.setOnClickListener {
                write(btn_number4, "4", n, counter, el1, el2, el3, el4, el5, el6)
            }
            btn_number5.setOnClickListener {
                write(btn_number5, "5", n, counter, el1, el2, el3, el4, el5, el6)
            }
            btn_number6.setOnClickListener {
                write(btn_number6, "6", n, counter, el1, el2, el3, el4, el5, el6)
            }
            btn_number7.setOnClickListener {
                write(btn_number7, "7", n, counter, el1, el2, el3, el4, el5, el6)
            }
            btn_number8.setOnClickListener {
                write(btn_number8, "8", n, counter, el1, el2, el3, el4, el5, el6)
            }
            btn_number9.setOnClickListener {
                write(btn_number9, "9", n, counter, el1, el2, el3, el4, el5, el6)
            }
            btn_number0.setOnClickListener {
                write(btn_number0, "0", n, counter, el1, el2, el3, el4, el5, el6)
            }
            btn_delete_last.setOnClickListener {
                delete_last(
                    counter,
                    el1,
                    el2,
                    el3,
                    el4,
                    el5,
                    el6,
                    btn_number1,
                    btn_number2,
                    btn_number3,
                    btn_number4,
                    btn_number5,
                    btn_number6,
                    btn_number7,
                    btn_number8,
                    btn_number9,
                    btn_number0
                )
            }
            btn_ok.setOnClickListener {
                if (counter == n) {
                    val sb = StringBuilder()
                    sb.append(el1.text).append(el2.text).append(el3.text).append(el4.text)
                    if (counter == 5) {
                        sb.append(el5.text)
                    }
                    if (counter == 6) {
                        sb.append(el5.text).append(el6.text)
                    }
                    val guess = Integer.parseInt(sb.toString())
                    val number = get_random_unrepeating(n)

                    val trie = getBullsCows(number, guess)

                    val msg = "№" + cnt + " Bulls :" + trie[0] + " Cows: " + trie[1]
                    resultList[cnt] = msg
                    cnt += 1

                    arrayAdapter.setNotifyOnChange(true)

                    btn_number0.isEnabled = true
                    btn_number1.isEnabled = true
                    btn_number2.isEnabled = true
                    btn_number3.isEnabled = true
                    btn_number4.isEnabled = true
                    btn_number5.isEnabled = true
                    btn_number6.isEnabled = true
                    btn_number7.isEnabled = true
                    btn_number8.isEnabled = true
                    btn_number9.isEnabled = true

                    el1.text = "----"
                    el2.text = "----"
                    el3.text = "----"
                    el4.text = "----"
                    el5.text = "----"
                    el6.text = "----"

                    this.counter = 0
                }
            }
        }
    }

}