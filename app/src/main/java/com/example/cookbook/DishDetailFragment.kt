package com.example.cookbook

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class DishDetailFragment : Fragment() {
     var dishId : Int = 0;
     private lateinit var dishTitle : TextView
     private lateinit var dishDescription : TextView


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(DISH_ID, dishId);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        if (savedInstanceState != null) {
            dishId = savedInstanceState.getInt(DISH_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_cocktail_detail, container, false)
        Log.i(TAG, "onCreateView")
        dishTitle = view.findViewById(R.id.textTitle)
        dishDescription = view.findViewById(R.id.textDescription)

        return view
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
        val dish : Dish = Dish.dishes[dishId.toInt()]
        dishTitle.text = dish.name
        dishDescription.text = dish.recipe

    }

    fun setDishIds(id: Int) {
        this.dishId = id
    }


    companion object {
        const val DISH_ID = "dish_id"

        const val TAG = "DetailTag"
    }

}