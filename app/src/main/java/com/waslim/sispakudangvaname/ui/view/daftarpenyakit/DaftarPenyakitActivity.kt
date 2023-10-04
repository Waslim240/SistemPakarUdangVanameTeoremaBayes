package com.waslim.sispakudangvaname.ui.view.daftarpenyakit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.waslim.sispakudangvaname.R
import com.waslim.sispakudangvaname.databinding.ActivityDaftarPenyakitBinding
import com.waslim.sispakudangvaname.ui.view.detailpenyakit.DetailPenyakitActivity

class DaftarPenyakitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDaftarPenyakitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarPenyakitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            buttonVibriosis.setOnClickListener {
                val intent = Intent(applicationContext, DetailPenyakitActivity::class.java)
                intent.putExtra("daftarPenyakit", "vibriosis")
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }

            buttonWhitefacessyndrome.setOnClickListener {
                val intent = Intent(applicationContext, DetailPenyakitActivity::class.java)
                intent.putExtra("daftarPenyakit", "white feces syndrome")
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }

            buttonWhitespotsyndrome.setOnClickListener {
                val intent = Intent(applicationContext, DetailPenyakitActivity::class.java)
                intent.putExtra("daftarPenyakit", "white spot syndrome")
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }

            buttonInfectiousmyonecrosis.setOnClickListener {
                val intent = Intent(applicationContext, DetailPenyakitActivity::class.java)
                intent.putExtra("daftarPenyakit", "infectious myo necrosis")
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }

            buttonEarlymortalitysyndrome.setOnClickListener {
                val intent = Intent(applicationContext, DetailPenyakitActivity::class.java)
                intent.putExtra("daftarPenyakit", "early mortality syndrome")
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }

            buttonEnterocytozoonhepatopenaei.setOnClickListener {
                val intent = Intent(applicationContext, DetailPenyakitActivity::class.java)
                intent.putExtra("daftarPenyakit", "enterocytozoon hepatopenaei")
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }
}