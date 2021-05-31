package com.rsschool.android2021

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText


class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var editMin: EditText? = null
    private var editMax: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        editMin = view.findViewById(R.id.min_value)
        editMax = view.findViewById(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        generateButton?.setOnClickListener {
            val intf = activity as FragmentsInterface
            val min = editMin?.text.toString().toIntOrNull()
            val max = editMax?.text.toString().toIntOrNull()

            if ((min != null) && (max != null)) {
                if ((min < 0) || (max < 0)){
                    Toast.makeText(activity, "Please enter Min and Max values as integer non-negative numbers", Toast.LENGTH_LONG ).show()
                } else if (min > max) {
                    Toast.makeText(activity, "Please enter Min and Max values where Min is less or equals Max", Toast.LENGTH_LONG ).show()
                } else {
                    intf.openSecond(min, max)
                }
            } else {
                Toast.makeText(activity, "Please enter Min and Max values as integer non-negative numbers", Toast.LENGTH_LONG ).show()
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}