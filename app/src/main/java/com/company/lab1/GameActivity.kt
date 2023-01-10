package com.company.lab1

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import android.content.IntentFilter

class GameActivity : AppCompatActivity() {

    var counter = 0

    var cnt = 0
    private val resultList = ArrayList<String>()
    var number = ArrayList<Int>()
    var generated = false

    var trie = ArrayList<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val n = intent.getIntExtra("N", 4)
        if (n != null) {

            val btnNumber1: Button = findViewById(R.id.number1)
            val btnNumber2: Button = findViewById(R.id.number2)
            val btnNumber3: Button = findViewById(R.id.number3)
            val btnNumber4: Button = findViewById(R.id.number4)
            val btnNumber5: Button = findViewById(R.id.number5)
            val btnNumber6: Button = findViewById(R.id.number6)
            val btnNumber7: Button = findViewById(R.id.number7)
            val btnNumber8: Button = findViewById(R.id.number8)
            val btnNumber9: Button = findViewById(R.id.number9)
            val btnNumber0: Button = findViewById(R.id.number0)

            val btnDeleteLast: Button = findViewById(R.id.btn_delete)
            val btnOk: Button = findViewById(R.id.btn_ok)
            val btnBack: Button = findViewById(R.id.back)

            val listOfResults: ListView = findViewById(R.id.results)

            val arrayAdapter =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
            listOfResults.adapter = arrayAdapter

            val el1: TextView = findViewById(R.id.first)
            val el2: TextView = findViewById(R.id.second)
            val el3: TextView = findViewById(R.id.third)
            val el4: TextView = findViewById(R.id.fourth)
            val el5: TextView = findViewById(R.id.fifth)
            val el6: TextView = findViewById(R.id.sixth)

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

                    val bundle: Bundle = Bundle()
                    if (!generated) {
                        bundle.putInt("N", intent.getIntExtra("N", 4))
                    }
                    val intentService: Intent = Intent(this, MyIntentService()::class.java)
                    intentService.putExtras(bundle)
                    startService(intentService)

                    val filter = IntentFilter(MyIntentService.ACTION_GENERATE_ARRAY)
                    filter.addCategory(Intent.CATEGORY_DEFAULT)
                    registerReceiver(receiver, filter)

                    val filter2 = IntentFilter(MyIntentService.ACTION_GET_BULLS_COWS)
                    filter2.addCategory(Intent.CATEGORY_DEFAULT)
                    registerReceiver(receiver, filter2)

                    cnt += 1
                    val msg = "№" + cnt + " Bulls :" + trie[0] + " Cows: " + trie[1] + "\t" + guess
                    resultList.add(msg)

                    arrayAdapter.notifyDataSetChanged()

                    if (trie[0] == n) {
                        val builder = AlertDialog.Builder(this)
                        builder.setTitle(R.string.winMessage)
                        builder.setMessage("You won on try $cnt")

                        builder.setPositiveButton(R.string.ok) { dialog, which ->
                            goBack()
                        }

                        builder.setNegativeButton(R.string.cancel) { dialog, which ->
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

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    private var receiver = object : MyBroadCastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (!generated){
                number = intent?.getIntegerArrayListExtra("NUMBER")!!
                generated = true
            }
            trie = intent?.getIntegerArrayListExtra("BULLS_COWS")!!
        }
    }

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
        this.finish()
    }
}