package com.mad.navigation_fragment_demo_102

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ThankyouFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_thankyou, container, false)
        val scoreDisaplay: TextView = view.findViewById(R.id.textViewScoreDisplay)
//        val name = arguments?.getString("name")
//        tvName.text = name
        val args = ThankyouFragmentArgs.fromBundle(requireArguments())

        scoreDisaplay.text = String.format("%.2f", args.score);

   return view
    }


}