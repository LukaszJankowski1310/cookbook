package com.example.cookbook

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider

class DetailsActivity : AppCompatActivity() {
    private val viewModel: DishesViewModel by lazy {
        (application as App).myViewModel
    }
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedPreferences = getSharedPreferences("theme_prefs", MODE_PRIVATE)
        val theme = sharedPreferences.getInt("theme", 0)
        setTheme(theme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            val details = DishDetailFragment();
            val id : Int = intent.getIntExtra(DISH_ID, 0);
            val dish = viewModel.getData().value?.results?.get(id.toInt())
            if (dish != null) {
                viewModel.setDish(dish)
            }

            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragment_container, details);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()

        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivityForResult(intent, 1)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val selectedTheme = data?.getIntExtra("selectedTheme", 0)
            selectedTheme?.let { applyTheme(it) }
        }
    }

    private fun applyTheme(theme: Int) {
        setTheme(theme)
        recreate()
    }

    companion object {
        const val DISH_ID : String = "dish_id"
    }
}