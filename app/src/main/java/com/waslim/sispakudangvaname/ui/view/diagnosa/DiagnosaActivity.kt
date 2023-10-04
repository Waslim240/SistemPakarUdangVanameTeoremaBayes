package com.waslim.sispakudangvaname.ui.view.diagnosa

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.waslim.sispakudangvaname.R
import com.waslim.sispakudangvaname.databinding.ActivityDiagnosaBinding
import com.waslim.sispakudangvaname.ui.adapter.GejalaAdapter
import com.waslim.sispakudangvaname.ui.view.hasildiagnosa.HasilDiagnosaActivity
import com.waslim.sispakudangvaname.ui.viewmodel.gejala.GejalaViewModel
import com.waslim.sispakudangvaname.util.Result
import com.waslim.sispakudangvaname.util.showLoading
import com.waslim.sispakudangvaname.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("NewApi")
@AndroidEntryPoint
class DiagnosaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiagnosaBinding
    private val gejalaViewModel by viewModels<GejalaViewModel>()
    private val gejalaAdapter: GejalaAdapter by lazy(::GejalaAdapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiagnosaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkConnection()
        setUpRecyclerView()

        binding.buttonProses.setOnClickListener {
            val selectedChips = gejalaAdapter.getSelectedChips().map { it.gejala }.toMutableList()

            if (selectedChips.isEmpty()) {
                showToast(getString(R.string.minimal_pilih_1_gejala), Toast.LENGTH_SHORT)
            } else {
                val intent = Intent(applicationContext, HasilDiagnosaActivity::class.java)
                intent.putStringArrayListExtra("listGejalaInput", ArrayList(selectedChips))
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                finish()
            }
        }

    }

    private fun setUpRecyclerView() {
        binding.listItemGejala.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = gejalaAdapter
            setHasFixedSize(true)
        }
    }

    private fun checkConnection() = when {
        isOnline(applicationContext) -> getListGejala()
        else -> {
            showToast(getString(R.string.tidak_ada_koneksi), Toast.LENGTH_LONG)
            openNetworkSettings()
        }
    }

    // mengambil data gejala yang ada pada firebase
    private fun getListGejala() {
        gejalaViewModel.dataGejala.observe(this) { status ->
            when (status) {
                is Result.Success -> {
                    showLoading(false, binding.progressMain)
                    gejalaAdapter.setListGejala(status.data) // memperbarui data pada adapater
                }
                is Result.Loading -> {
                    showLoading(true, binding.progressMain)
                }
                is Result.Failure -> {
                    showLoading(false, binding.progressMain)
                    showToast(getString(R.string.data_tidak_ditemukan), Toast.LENGTH_SHORT)
                }
            }
        }

        gejalaViewModel.getListGejala()
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