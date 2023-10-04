package com.waslim.sispakudangvaname.ui.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.waslim.sispakudangvaname.R
import com.waslim.sispakudangvaname.databinding.ActivityMainBinding
import com.waslim.sispakudangvaname.ui.view.about.AboutActivity
import com.waslim.sispakudangvaname.ui.view.daftarpenyakit.DaftarPenyakitActivity
import com.waslim.sispakudangvaname.ui.view.diagnosa.DiagnosaActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var doubleBackToExit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.apply {
            goDiagnosaActivity()
            goDaftarPenyakit()
            goAbout()
        }

        doubleBackExit()

    }

    private fun goDiagnosaActivity() = binding.buttonDiagnose.setOnClickListener {
        startActivity(Intent(applicationContext, DiagnosaActivity::class.java))
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }

    private fun goDaftarPenyakit() = binding.buttonDaftarPenyakit.setOnClickListener {
        startActivity(Intent(applicationContext, DaftarPenyakitActivity::class.java))
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }

    private fun goAbout() = binding.buttonTentang.setOnClickListener {
        startActivity(Intent(applicationContext, AboutActivity::class.java))
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }

    private fun doubleBackExit() = onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            when {
                doubleBackToExit -> {
                    finish()
                }
                else -> {
                    doubleBackToExit = true
                    Toast.makeText(applicationContext, getString(R.string.keluar_klik_dua_kali), Toast.LENGTH_SHORT).show()

                    Handler(Looper.getMainLooper()).postDelayed({
                        kotlin.run {
                            doubleBackToExit = false
                        }
                    }, DURATION)
                }
            }
        }
    })

    companion object {
        private const val DURATION = 2000L
    }
}