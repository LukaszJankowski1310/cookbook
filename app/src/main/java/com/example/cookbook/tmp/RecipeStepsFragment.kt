package com.example.cookbook.tmp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cookbook.DishesViewModel
import com.example.cookbook.R


class RecipeStepsFragment : Fragment() {

    private lateinit var prevBtn : Button
    private lateinit var nextBtn : Button
    private lateinit var detailStepTv : TextView
    private lateinit var stepNumberTv : TextView

    private lateinit var viewModel : DishesViewModel

    private lateinit var stopper : StopperFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[DishesViewModel::class.java]

        if (savedInstanceState == null) {
            val ft2 = childFragmentManager.beginTransaction()
            stopper = StopperFragment()
            stopper.setSeconds(5)
            ft2.replace(R.id.stopper_frag, stopper);
            ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft2.commit()
        } else {
            stopper = childFragmentManager.findFragmentById(R.id.stopper_frag) as StopperFragment
        }

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_recipe_steps, container, false)

        prevBtn = view.findViewById(R.id.prev_btn)
        nextBtn = view.findViewById(R.id.next_btn)
        detailStepTv = view.findViewById(R.id.detail_step_tv)
        stepNumberTv =  view.findViewById(R.id.step_number_tv)

        viewModel.getCurrentStep().observe(viewLifecycleOwner, Observer {
            val stepNumberText = "${it+1} / ${viewModel.getDishSteps().value!!.size}"
            stepNumberTv.text = stepNumberText
            detailStepTv.text = viewModel.getDishSteps().value?.get(it)?.step


            val ft2 = childFragmentManager.beginTransaction()
            stopper = StopperFragment()
            viewModel.getDishSteps().value?.get(it)?.length?.number?.let { it1 ->
                stopper.setSeconds(
                    it1
                )
            }
            ft2.replace(R.id.stopper_frag, stopper);
            ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft2.commit()

        })


        nextBtn.setOnClickListener {
            viewModel.moveToNextStep()
        }

        prevBtn.setOnClickListener {
            viewModel.moveToPreviousStep()
        }
        return view
    }




}