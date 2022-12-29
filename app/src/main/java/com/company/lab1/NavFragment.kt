package com.company.lab1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class NavFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nav, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val levels = resources.getStringArray(R.array.levels)
        val spinner: Spinner = view.findViewById(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                R.layout.spinner_list,
                levels
            )
            spinner.adapter = adapter
        }


        val btnStart: Button = view.findViewById(R.id.btn_start)

        btnStart.setOnClickListener {
            val bundle = Bundle()
            when (spinner.selectedItem.toString()) {
                "Low" -> bundle.putInt("N", 4)
                "Medium" -> bundle.putInt("N", 5)
                "Hard" -> bundle.putInt("N", 6)
            }
            findNavController().navigate(R.id.gameFragment, bundle)
        }

        val btnAbout: Button = view.findViewById(R.id.btn_about)
        btnAbout.setOnClickListener {
            findNavController().navigate(R.id.action_navFragment_to_aboutFragment)
        }

        val btnExit: Button = view.findViewById(R.id.btn_exit)
        btnExit.setOnClickListener {
            activity?.finish()
            System.exit(0)
        }
    }
}
