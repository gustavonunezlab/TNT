package com.example.demo

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.demo.databinding.ActivityFormDisplayBinding
import java.util.*

class FormDisplayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormDisplayBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fishingType = intent.getStringExtra("fishingType")
        val title = intent.getStringExtra("title")

        binding.fishingTypeTextView.text = fishingType
        binding.titleTextView.text = title

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")

        val currentDate = sdf.format(Date())
        binding.dateTextView.text = currentDate

    }

}