package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FishingInfoActivity : AppCompatActivity(), Cordinadora {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fishing_info)
    }

    override fun onChangeFishingType(index: Int) {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_fishing_description2) as DescriptionFishingTypeFragment
        fragment.changeFishingType(index)

    }
}