package com.company.lab1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController


class NavFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nav, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val levels = resources.getStringArray(R.array.levels)
        val spinner: Spinner = view.findViewById(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_checked,
                levels
            )
            spinner.adapter = adapter
        }


        val btn_start : Button = view.findViewById(R.id.btn_start)

        btn_start.setOnClickListener { view: View ->
            val bundle = Bundle()
            val selected = spinner.selectedItem.toString()
            if (selected == "Low") {
                bundle.putInt("N", 4)
            } else if (selected == "Medium") {
                bundle.putInt("N", 5)
            } else if (selected == "Hard") {
                bundle.putInt("N", 6)
            }
            findNavController().navigate(R.id.gameFragment, bundle)
        }

        val btn_about: Button = view.findViewById(R.id.btn_about)
        btn_about.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_navFragment_to_aboutFragment)
        }

        val btn_exit : Button = view.findViewById(R.id.btn_exit)
        btn_exit.setOnClickListener { view: View ->
            activity?.finish()
            System.exit(0)
        }
    }
}
