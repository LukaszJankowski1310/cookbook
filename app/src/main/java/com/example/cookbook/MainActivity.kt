package com.example.cookbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View


class MainActivity : AppCompatActivity(), DishListFragment.Listener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    override fun itemClicked(id: Long) {
        Log.i("info", id.toString());
        val intent : Intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.DISH_ID, id.toInt())
        startActivity(intent)
    }
}