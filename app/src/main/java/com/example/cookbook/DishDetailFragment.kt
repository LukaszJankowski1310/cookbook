package com.example.cookbook

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.cookbook.tmp.RecipeStepsFragment
import com.example.cookbook.api.model.Result

class DishDetailFragment : Fragment() {

     private lateinit var dishTitle : TextView
     private lateinit var dishDescription : TextView
     private lateinit var dishImage : ImageView
     private lateinit var viewModel : DishesViewModel

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[DishesViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_dish_detail, container, false)
        dishTitle = view.findViewById(R.id.textTitle)
        dishDescription = view.findViewById(R.id.textDescription)
        dishImage = view.findViewById(R.id.dishImage)

        // steps fragment
        if (savedInstanceState == null) {
            val ft2 = childFragmentManager.beginTransaction()
            val recipeSteps = RecipeStepsFragment()
            ft2.replace(R.id.steps_container, recipeSteps);
            ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft2.addToBackStack(null)
            ft2.commit()
        }
        viewModel.getDish().observe(viewLifecycleOwner) {
            dishTitle.text = it.title
            dishDescription.text = Html.fromHtml(it.summary, Html.FROM_HTML_MODE_COMPACT)
            Glide.with(this)
                .load(it.image)
                .into(dishImage);
        }


        return view
    }

    companion object {
        const val DISH_ID = "dish_id"
        const val TAG = "DetailTag"
    }

}