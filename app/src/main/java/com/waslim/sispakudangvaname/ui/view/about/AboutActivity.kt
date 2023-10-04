package com.waslim.sispakudangvaname.ui.view.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.waslim.sispakudangvaname.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }
}