package com.company.lab1

import android.app.AlertDialog
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
import androidx.fragment.app.FragmentManager
import com.company.lab1.R.string
import java.util.*

class GameFragment : Fragment() {
    var counter = 0

    var cnt = 0
    val resultList = ArrayList<String>()
    var number = ArrayList<Int>()
    var generated = false

    private fun getButtonByText(
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
        return when (text) {
            "1" -> btn1
            "2" -> btn2
            "3" -> btn3
            "4" -> btn4
            "5" -> btn5
            "6" -> btn6
            "7" -> btn7
            "8" -> btn8
            "9" -> btn9
            else -> btn0
        }
    }

    private fun deleteLast(
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
        val elements = arrayOf(el1, el2, el3, el4, el5, el6)
        if (counter in 1..6) {
            getButtonByText(
                elements[counter - 1].text as String,
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
            elements[counter - 1].text = "----"
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
        val elements = arrayOf(el1, el2, el3, el4, el5, el6)
        if (counter < n) {
            if (counter in 0..5) {
                elements[counter].text = text
                this.counter += 1
                btn.isEnabled = false
            }
        }
    }


    private fun goBack() {
        val fm: FragmentManager = requireActivity().supportFragmentManager
        fm.popBackStack()
    }

    private fun setRandomUnrepeating(digits: Int) {
        val res = ArrayList<Int>()
        val nums = mutableStateListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        Collections.shuffle(nums)
        for (i in 0..digits - 1) {
            res.add(nums[i])
        }
        this.number = res
        this.generated = true
    }

    private fun contain(number: Int, array: ArrayList<Int>): Boolean {
        for (el in array) {
            if (number == el)
                return true
        }
        return false
    }

    private fun getBullsCows(number: ArrayList<Int>, guess: ArrayList<Int>): Array<Int> {
        val bull_cow = arrayOf(0, 0)
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

            val btnNumber1: Button = view.findViewById(R.id.number1)
            val btnNumber2: Button = view.findViewById(R.id.number2)
            val btnNumber3: Button = view.findViewById(R.id.number3)
            val btnNumber4: Button = view.findViewById(R.id.number4)
            val btnNumber5: Button = view.findViewById(R.id.number5)
            val btnNumber6: Button = view.findViewById(R.id.number6)
            val btnNumber7: Button = view.findViewById(R.id.number7)
            val btnNumber8: Button = view.findViewById(R.id.number8)
            val btnNumber9: Button = view.findViewById(R.id.number9)
            val btnNumber0: Button = view.findViewById(R.id.number0)

            val btnDeleteLast: Button = view.findViewById(R.id.btn_delete)
            val btnOk: Button = view.findViewById(R.id.btn_ok)
            val btnBack: Button = view.findViewById(R.id.back)

            val listOfResults: ListView = view.findViewById(R.id.results)

            val arrayAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, resultList)
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


            btnNumber1.setOnClickListener {
                write(btnNumber1, "1", n, counter, el1, el2, el3, el4, el5, el6)
            }
            btnNumber2.setOnClickListener {
                write(btnNumber2, "2", n, counter, el1, el2, el3, el4, el5, el6)
            }
            btnNumber3.setOnClickListener {
                write(btnNumber3, "3", n, counter, el1, el2, el3, el4, el5, el6)
            }
            btnNumber4.setOnClickListener {
                write(btnNumber4, "4", n, counter, el1, el2, el3, el4, el5, el6)
            }
            btnNumber5.setOnClickListener {
                write(btnNumber5, "5", n, counter, el1, el2, el3, el4, el5, el6)
            }
            btnNumber6.setOnClickListener {
                write(btnNumber6, "6", n, counter, el1, el2, el3, el4, el5, el6)
            }
            btnNumber7.setOnClickListener {
                write(btnNumber7, "7", n, counter, el1, el2, el3, el4, el5, el6)
            }
            btnNumber8.setOnClickListener {
                write(btnNumber8, "8", n, counter, el1, el2, el3, el4, el5, el6)
            }
            btnNumber9.setOnClickListener {
                write(btnNumber9, "9", n, counter, el1, el2, el3, el4, el5, el6)
            }
            btnNumber0.setOnClickListener {
                write(btnNumber0, "0", n, counter, el1, el2, el3, el4, el5, el6)
            }
            btnDeleteLast.setOnClickListener {
                deleteLast(
                    counter,
                    el1,
                    el2,
                    el3,
                    el4,
                    el5,
                    el6,
                    btnNumber1,
                    btnNumber2,
                    btnNumber3,
                    btnNumber4,
                    btnNumber5,
                    btnNumber6,
                    btnNumber7,
                    btnNumber8,
                    btnNumber9,
                    btnNumber0
                )
            }
            btnBack.setOnClickListener {
                goBack()
            }
            btnOk.setOnClickListener {
                if (counter == n) {
                    val guess = ArrayList<Int>()
                    guess.add(Integer.parseInt(el1.text as String))
                    guess.add(Integer.parseInt(el2.text as String))
                    guess.add(Integer.parseInt(el3.text as String))
                    guess.add(Integer.parseInt(el4.text as String))

                    if (counter == 5) {
                        guess.add(Integer.parseInt(el5.text as String))
                    }
                    if (counter == 6) {
                        guess.add(Integer.parseInt(el5.text as String))
                        guess.add(Integer.parseInt(el6.text as String))
                    }

                    if (!generated) {
                        setRandomUnrepeating(n)
                    }

                    val trie = getBullsCows(number, guess)
                    cnt += 1
                    val msg = "№" + cnt + " Bulls :" + trie[0] + " Cows: " + trie[1] + "\t" + guess
                    resultList.add(msg)

                    arrayAdapter.notifyDataSetChanged()

                    if (trie[0] == n) {
                        val builder = AlertDialog.Builder(requireContext())
                        builder.setTitle(string.winMessage)
                        builder.setMessage("You won on try $cnt")

                        builder.setPositiveButton(string.ok) { dialog, which ->
                            goBack()
                        }

                        builder.setNegativeButton(string.cancel) { dialog, which ->
                            goBack()
                        }
                        builder.show()
                    }

                    btnNumber0.isEnabled = true
                    btnNumber1.isEnabled = true
                    btnNumber2.isEnabled = true
                    btnNumber3.isEnabled = true
                    btnNumber4.isEnabled = true
                    btnNumber5.isEnabled = true
                    btnNumber6.isEnabled = true
                    btnNumber7.isEnabled = true
                    btnNumber8.isEnabled = true
                    btnNumber9.isEnabled = true

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