package com.example.cookbook

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class SettingsActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var greenThemeRadioButton : RadioButton
    private lateinit var blueThemeRadioButton : RadioButton
    private lateinit var purpleThemeRadioButton : RadioButton
    private lateinit var applyButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = getSharedPreferences("theme_prefs", MODE_PRIVATE)
        val theme = sharedPreferences.getInt("theme", 0)
        setTheme(theme)



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        greenThemeRadioButton = findViewById(R.id.greenThemeRadioButton)
        blueThemeRadioButton = findViewById(R.id.blueThemeRadioButton)
        purpleThemeRadioButton = findViewById(R.id.purpleThemeRadioButton)
        applyButton = findViewById(R.id.applyButton)

        if (theme == R.style.CustomPurpleTheme)
        {
            purpleThemeRadioButton.isChecked = true
        }
        if (theme == R.style.CustomGreenTheme)
        {
            greenThemeRadioButton.isChecked = true
        }

        if (theme == R.style.CustomBlueTheme)
        {
            blueThemeRadioButton.isChecked = true
        }


        applyButton.setOnClickListener {
            val selectedTheme = when {
                blueThemeRadioButton.isChecked -> R.style.CustomBlueTheme
                greenThemeRadioButton.isChecked -> R.style.CustomGreenTheme
                else -> R.style.CustomPurpleTheme
            }
            sharedPreferences.edit().putInt("theme", selectedTheme).apply()
            val resultIntent = Intent()
            resultIntent.putExtra("selectedTheme", selectedTheme)
            setResult(Activity.RESULT_OK, resultIntent)
//            finish()
            recreate()
        }
    }



}