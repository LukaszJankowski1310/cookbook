package com.example.cookbook

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity(), DishListFragment.Listener {

    private  var fragmentContainer : View? = null
    private val viewModel: DishesViewModel by lazy {
        (application as App).myViewModel
    }
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var toolBar : Toolbar
    private lateinit var pager : ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = getSharedPreferences("theme_prefs", MODE_PRIVATE)
        val theme = sharedPreferences.getInt("theme", 0)
        setTheme(theme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        viewModel.getData().observe(this
        ) {
            Log.i("Observer", "MainActivity")
            if (savedInstanceState == null) {
                setDefaultDetailItem()
            }
        }

        toolBar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar)
        fragmentContainer  = findViewById(R.id.fragment_container)
        pager = findViewById(R.id.pager)
        val pagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        pager.adapter = pagerAdapter

        tabLayout = findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(pager)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val selectedTheme = data?.getIntExtra("selectedTheme", 0)
            selectedTheme?.let { applyTheme(it) }
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

    override fun itemClicked(id: Long) {
        if (fragmentContainer != null) {
            // tablet
            Log.i("TABLET", "TEBLET")
            val details = DishDetailFragment();
            val dish = viewModel.getData().value?.results?.get(id.toInt())
            if (dish != null) {
                viewModel.setDish(dish)
            }

            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragment_container, details);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()

        } else {
            // telefon

            Log.i("NEWINTENT", "NEWINTENT")
            val intent: Intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.DISH_ID, id.toInt())
            startActivity(intent)
        }
    }

    private fun setDefaultDetailItem() {
            if (fragmentContainer != null) {
                Log.i("SETDEFAULTITEM", "SETDEFAULTITEM")
                val details = DishDetailFragment();
                val dish = viewModel.getData().value?.results?.get(0)
                if (dish != null && viewModel.getDish().value == null) {
                    viewModel.setDish(dish)
                }
                val ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.fragment_container, details);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                ft.commit()
        }
    }

    private fun applyTheme(theme: Int) {
        setTheme(theme)
        recreate()
    }



}



//val apiKey = "691bb0541f544c4ab7888a600b242b7c"
//val baseUrl = "https://jsonplaceholder.typicode.com/"