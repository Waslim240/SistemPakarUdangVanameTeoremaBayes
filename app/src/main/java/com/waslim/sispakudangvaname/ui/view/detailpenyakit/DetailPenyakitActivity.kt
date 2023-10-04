package com.waslim.sispakudangvaname.ui.view.detailpenyakit

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.text.Html
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.text.AllCapsTransformationMethod
import com.waslim.sispakudangvaname.R
import com.waslim.sispakudangvaname.databinding.ActivityDetailPenyakitBinding
import com.waslim.sispakudangvaname.ui.viewmodel.daftarpenyakit.DaftarPenyakitViewModel
import com.waslim.sispakudangvaname.util.Result
import com.waslim.sispakudangvaname.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("RestrictedApi", "NewApi")
@AndroidEntryPoint
class DetailPenyakitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPenyakitBinding
    private val daftarPenyakit by viewModels<DaftarPenyakitViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPenyakitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkConnection()
    }

    private fun checkConnection() = when {
        isOnline(applicationContext) -> showDataPenyakit()
        else -> {
            showToast(getString(R.string.tidak_ada_koneksi), Toast.LENGTH_LONG)
            openNetworkSettings()
        }
    }

    private fun showDataPenyakit() {
        val dataPenyakit = intent.getStringExtra("daftarPenyakit")

        if (dataPenyakit != null) {
            getDataPenyakit(dataPenyakit)
        }
    }

    // get data penyakit
    private fun getDataPenyakit(penyakit: String) {
        daftarPenyakit.dataPenyakit.observe(this) { status ->
            when (status) {
                is Result.Success -> {
                    for (i in status.data) {
                        binding.apply {
                            namaPenyakit.text = penyakit
                            namaPenyakit.transformationMethod = AllCapsTransformationMethod(applicationContext)
                            namaPenyakit.paintFlags = namaPenyakit.paintFlags or Paint.UNDERLINE_TEXT_FLAG

                            hasilRingkasan.text = i.ringkasan
                            val dataArray = i.penanganan.split("- ")
                            val hasilSolusiText = dataArray.joinToString("\n") { penanganan ->
                                penanganan.trim()
                            }

                            val formattedText = "<ul><li>${hasilSolusiText.replace("\n", "</li><li>")}</li></ul>"
                            hasilSolusi.text = Html.fromHtml(formattedText, Html.FROM_HTML_MODE_COMPACT)
                        }
                    }
                }
                is Result.Failure -> {
                    showToast(getString(R.string.data_tidak_ditemukan), Toast.LENGTH_SHORT)
                }
                is Result.Loading -> {}
            }
        }

        daftarPenyakit.getDaftarPenyakit(penyakit)
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return when {
            capabilities != null -> {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        true
                    }
                    else -> capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                }
            }
            else -> false
        }
    }

    private fun openNetworkSettings() {
        val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        checkConnection()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }
}