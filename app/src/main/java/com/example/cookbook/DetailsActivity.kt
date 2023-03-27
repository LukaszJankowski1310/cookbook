package com.example.cookbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val frag : DishDetailFragment =
            supportFragmentManager.
            findFragmentById(R.id.detail_frag) as DishDetailFragment

        val dishId : Int = intent.getIntExtra(DISH_ID, 1);
        frag.setDishIds(dishId)
    }

    companion object {
        val DISH_ID : String = "dish_id"
    }
}