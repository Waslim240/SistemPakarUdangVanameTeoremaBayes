package com.waslim.sispakudangvaname.ui.view.hasildiagnosa

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import com.waslim.sispakudangvaname.util.Result
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.waslim.sispakudangvaname.R
import com.waslim.sispakudangvaname.databinding.ActivityHasilDiagnosaBinding
import com.waslim.sispakudangvaname.model.dataclass.hasildiagnosa.HasilDiagnosa
import com.waslim.sispakudangvaname.ui.view.diagnosa.DiagnosaActivity
import com.waslim.sispakudangvaname.ui.view.home.MainActivity
import com.waslim.sispakudangvaname.ui.viewmodel.daftarpenyakit.DaftarPenyakitViewModel
import com.waslim.sispakudangvaname.ui.viewmodel.penyakit.earlymortalitysyndrome.EarlyMortalitySyndromeViewModel
import com.waslim.sispakudangvaname.ui.viewmodel.penyakit.enterocytozoonhepatopenaei.EnterocytozoonHepatopenaeiViewModel
import com.waslim.sispakudangvaname.ui.viewmodel.penyakit.infectiousmyonecrosis.InfectiousMyoNecrosisViewModel
import com.waslim.sispakudangvaname.ui.viewmodel.penyakit.vibriosis.VibriosisViewModel
import com.waslim.sispakudangvaname.ui.viewmodel.penyakit.whitefacessyndrome.WhiteFecesSyndromeViewModel
import com.waslim.sispakudangvaname.ui.viewmodel.penyakit.whitespotsyndrome.WhiteSpotSyndromeViewModel
import com.waslim.sispakudangvaname.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("InlinedApi")
class HasilDiagnosaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHasilDiagnosaBinding
    private val earlyMortalitySyndromeViewModel by viewModels<EarlyMortalitySyndromeViewModel>()
    private val enterocytozoonHepatopenaeiViewModel by viewModels<EnterocytozoonHepatopenaeiViewModel>()
    private val infectiousMyoNecrosisViewModel by viewModels<InfectiousMyoNecrosisViewModel>()
    private val vibriosisViewModel by viewModels<VibriosisViewModel>()
    private val whiteFecesSyndromeViewModel by viewModels<WhiteFecesSyndromeViewModel>()
    private val whiteSpotSyndromeViewModel by viewModels<WhiteSpotSyndromeViewModel>()
    private val daftarPenyakit by viewModels<DaftarPenyakitViewModel>()
    private val hasilDiagnosaList = mutableListOf<HasilDiagnosa>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilDiagnosaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        earlyMortalitySyndrome()
        enterocytozoonHepatopenaei()
        infectiousMyoNecrosis()
        vibriosis()
        whiteFacesSyndrome()
        whiteSpotSyndrome()
        tampilkanHasilDiagnosaTerbesar()
    }

    private fun earlyMortalitySyndrome() {
        // Mendapatkan data listGejalaInput dari intent
        val listGejalaInput = intent.getStringArrayListExtra("listGejalaInput") ?: emptyList()

        earlyMortalitySyndromeViewModel.gejalaEarlyMortalitySyndrome.observe(this) { status ->
            when (status) {
                is Result.Success -> {
                    val probabilitasList = mutableListOf<Double>()
                    val gejala = mutableListOf<String>()

                    status.data.forEach { gejalaData ->
                        // Memeriksa apakah gejalaData.gejala ada dalam listGejalaInput
                        if (gejalaData.gejala in listGejalaInput) {
                            probabilitasList.add(gejalaData.probabilitas)
                            gejala.add(gejalaData.gejala)
                        }
                    }

                    // menjumlahkan total probabilitas gejala
                    val totalProbabilitasGejala = probabilitasList.sum()

                    if (totalProbabilitasGejala != 0.0) {
                        // menghitung nilai semesta, P(E|Hi) / total probabilitas
                        val semestaPh = probabilitasList.map { it / totalProbabilitasGejala }

                        // menghitung probabilitas H tanpa memandang evidence, P(Hi) x P(E|Hi)
                        val hasilKali = probabilitasList.mapIndexed {index, probabilitasGejala ->
                            probabilitasGejala * semestaPh[index]
                        }

                        // total hipotesa H, P(H)
                        val totalPH = hasilKali.sum()

                        // mencari nilai probabilitas Hi benar jika diberikan evidence E, P(Hi) * P(E|Hi) / P(H)
                        val hasilPhie = hasilKali.mapIndexed { index, hasil ->
                            hasil * probabilitasList[index] / totalPH
                        }

                        // total nilai bayes, bayaes1 + bayes2 + ... + bayesn
                        val totalBayesEMS = hasilPhie.sum()
                        val formattedNilaiBayes = "%.4f".format(totalBayesEMS)

                        hasilDiagnosaList.add(HasilDiagnosa("early mortality syndrome", totalBayesEMS))

                        binding.tvHasilEarlyMortalitySyndrome.text = formattedNilaiBayes
                    } else {
                        binding.tvHasilEarlyMortalitySyndrome.text = 0.0.toString()
                    }

                    tampilkanHasilDiagnosaTerbesar()
                }
                else -> {}
            }
        }

        earlyMortalitySyndromeViewModel.getListGejalaEarlyMortalitySyndrome()
    }

    private fun enterocytozoonHepatopenaei() {
        // Mendapatkan data listGejalaInput dari intent
        val listGejalaInput = intent.getStringArrayListExtra("listGejalaInput") ?: emptyList()

        enterocytozoonHepatopenaeiViewModel.gejalaEnterocytozoonHepatopenaei.observe(this) { status ->
            when (status) {
                is Result.Success -> {
                    val probabilitasList = mutableListOf<Double>()
                    val gejala = mutableListOf<String>()

                    status.data.forEach { gejalaData ->
                        // Memeriksa apakah gejalaData.gejala ada dalam listGejalaInput
                        if (gejalaData.gejala in listGejalaInput) {
                            probabilitasList.add(gejalaData.probabilitas)
                            gejala.add(gejalaData.gejala)
                        }
                    }

                    // Menghitung total probabilitas gejala
                    val totalProbabilitasGejala = probabilitasList.sum()

                    if (totalProbabilitasGejala != 0.0) {
                        // menghitung nilai semesta, P(E|Hi) / total probabilitas
                        val semestaPh = probabilitasList.map { it / totalProbabilitasGejala }

                        // menghitung probabilitas H tanpa memandang evidence, P(Hi) x P(E|Hi)
                        val hasilKali = probabilitasList.mapIndexed {index, probabilitasGejala ->
                            probabilitasGejala * semestaPh[index]
                        }

                        // total hipotesa H, P(H)
                        val totalPH = hasilKali.sum()

                        // mencari nilai probabilitas Hi benar jika diberikan evidence E, P(Hi) * P(E|Hi) / P(H)
                        val hasilPhie = hasilKali.mapIndexed { index, hasil ->
                            hasil * probabilitasList[index] / totalPH
                        }

                        // total nilai bayes, bayaes1 + bayes2 + ... + bayesn
                        val totalBayesEHP = hasilPhie.sum()
                        val formattedNilaiBayes = "%.4f".format(totalBayesEHP)

                        hasilDiagnosaList.add(HasilDiagnosa("enterocytozoon hepatopenaei", totalBayesEHP))

                        binding.tvHasilEnterocytozoonHepatopenaei.text = formattedNilaiBayes
                    } else {
                        binding.tvHasilEnterocytozoonHepatopenaei.text = 0.0.toString()
                    }

                    tampilkanHasilDiagnosaTerbesar()
                }
                else -> {}
            }
        }

        enterocytozoonHepatopenaeiViewModel.getListGejalaEnterocytozoonHepatopenaei()
    }

    private fun infectiousMyoNecrosis() {
        // Mendapatkan data listGejalaInput dari intent
        val listGejalaInput = intent.getStringArrayListExtra("listGejalaInput") ?: emptyList()

        infectiousMyoNecrosisViewModel.gejalaInfectiousMyoNecrosis.observe(this) { status ->
            when (status) {
                is Result.Success -> {
                    val probabilitasList = mutableListOf<Double>()
                    val gejala = mutableListOf<String>()

                    status.data.forEach { gejalaData ->
                        // Memeriksa apakah gejalaData.gejala ada dalam listGejalaInput
                        if (gejalaData.gejala in listGejalaInput) {
                            probabilitasList.add(gejalaData.probabilitas)
                            gejala.add(gejalaData.gejala)
                        }
                    }

                    // Menghitung total probabilitas gejala
                    val totalProbabilitasGejala = probabilitasList.sum()

                    if (totalProbabilitasGejala != 0.0) {
                        // menghitung nilai semesta, P(E|Hi) / total probabilitas
                        val semestaPh = probabilitasList.map { it / totalProbabilitasGejala }

                        // menghitung probabilitas H tanpa memandang evidence, P(Hi) x P(E|Hi)
                        val hasilKali = probabilitasList.mapIndexed {index, probabilitasGejala ->
                            probabilitasGejala * semestaPh[index]
                        }

                        // total hipotesa H, P(H)
                        val totalPH = hasilKali.sum()

                        // mencari nilai probabilitas Hi benar jika diberikan evidence E, P(Hi) * P(E|Hi) / P(H)
                        val hasilPhie = hasilKali.mapIndexed { index, hasil ->
                            hasil * probabilitasList[index] / totalPH
                        }

                        // total nilai bayes, bayaes1 + bayes2 + ... + bayesn
                        val totalBayesIMN = hasilPhie.sum()
                        val formattedNilaiBayes = "%.4f".format(totalBayesIMN)

                        hasilDiagnosaList.add(HasilDiagnosa("infectious myo necrosis", totalBayesIMN))

                        binding.tvHasilInfectiousMyoNecrosis.text = formattedNilaiBayes
                    } else {
                        binding.tvHasilInfectiousMyoNecrosis.text = 0.0.toString()
                    }

                    tampilkanHasilDiagnosaTerbesar()
                }
                else -> {}
            }
        }

        infectiousMyoNecrosisViewModel.getListGejalaInfectiousMyoNecrosis()
    }

    private fun vibriosis() {
        // Mendapatkan data listGejalaInput dari intent
        val listGejalaInput = intent.getStringArrayListExtra("listGejalaInput") ?: emptyList()

        vibriosisViewModel.gejalaVibriosis.observe(this) { status ->
            when (status) {
                is Result.Success -> {
                    val probabilitasList = mutableListOf<Double>()
                    val gejala = mutableListOf<String>()

                    status.data.forEach { gejalaData ->
                        // Memeriksa apakah gejalaData.gejala ada dalam listGejalaInput
                        if (gejalaData.gejala in listGejalaInput) {
                            probabilitasList.add(gejalaData.probabilitas)
                            gejala.add(gejalaData.gejala)
                        }
                    }

                    // Menghitung total probabilitas gejala
                    val totalProbabilitasGejala = probabilitasList.sum()

                    if (totalProbabilitasGejala != 0.0) {
                        // menghitung nilai semesta, P(E|Hi) / total probabilitas
                        val semestaPh = probabilitasList.map { it / totalProbabilitasGejala }

                        // menghitung probabilitas H tanpa memandang evidence, P(Hi) x P(E|Hi)
                        val hasilKali = probabilitasList.mapIndexed {index, probabilitasGejala ->
                            probabilitasGejala * semestaPh[index]
                        }

                        // total hipotesa H, P(H)
                        val totalPH = hasilKali.sum()

                        // mencari nilai probabilitas Hi benar jika diberikan evidence E, P(Hi) * P(E|Hi) / P(H)
                        val hasilPhie = hasilKali.mapIndexed { index, hasil ->
                            hasil * probabilitasList[index] / totalPH
                        }

                        // total nilai bayes, bayaes1 + bayes2 + ... + bayesn
                        val totalBayesVibrio = hasilPhie.sum()
                        val formattedNilaiBayes = "%.4f".format(totalBayesVibrio)

                        hasilDiagnosaList.add(HasilDiagnosa("vibriosis", totalBayesVibrio))

                        binding.tvHasilVibriosis.text = formattedNilaiBayes
                    } else {
                        binding.tvHasilVibriosis.text = 0.0.toString()
                    }

                    tampilkanHasilDiagnosaTerbesar()
                }
                else -> {}
            }
        }

        vibriosisViewModel.getListGejalaVibriosis()
    }

    private fun whiteFacesSyndrome() {
        // Mendapatkan data listGejalaInput dari intent
        val listGejalaInput = intent.getStringArrayListExtra("listGejalaInput") ?: emptyList()

        whiteFecesSyndromeViewModel.gejalaWhiteFacesSyndrome.observe(this) { status ->
            when (status) {
                is Result.Success -> {
                    val probabilitasList = mutableListOf<Double>()
                    val gejala = mutableListOf<String>()

                    status.data.forEach { gejalaData ->
                        // Memeriksa apakah gejalaData.gejala ada dalam listGejalaInput
                        if (gejalaData.gejala in listGejalaInput) {
                            probabilitasList.add(gejalaData.probabilitas)
                            gejala.add(gejalaData.gejala)
                        }
                    }

                    // Menghitung total probabilitas gejala
                    val totalProbabilitasGejala = probabilitasList.sum()

                    if (totalProbabilitasGejala != 0.0) {
                        // menghitung nilai semesta, P(E|Hi) / total probabilitas
                        val semestaPh = probabilitasList.map { it / totalProbabilitasGejala }

                        // menghitung probabilitas H tanpa memandang evidence, P(Hi) x P(E|Hi)
                        val hasilKali = probabilitasList.mapIndexed {index, probabilitasGejala ->
                            probabilitasGejala * semestaPh[index]
                        }

                        // total hipotesa H, P(H)
                        val totalPH = hasilKali.sum()

                        // mencari nilai probabilitas Hi benar jika diberikan evidence E, P(Hi) * P(E|Hi) / P(H)
                        val hasilPhie = hasilKali.mapIndexed { index, hasil ->
                            hasil * probabilitasList[index] / totalPH
                        }

                        // total nilai bayes, bayaes1 + bayes2 + ... + bayesn
                        val totalBayesWFS = hasilPhie.sum()
                        val formattedNilaiBayes = "%.4f".format(totalBayesWFS)

                        hasilDiagnosaList.add(HasilDiagnosa("white feces syndrome", totalBayesWFS))

                        binding.tvHasilWhiteFacesSyndrome.text = formattedNilaiBayes
                    } else {
                        binding.tvHasilWhiteFacesSyndrome.text = 0.0.toString()
                    }

                    tampilkanHasilDiagnosaTerbesar()
                }
                else -> {}
            }
        }

        whiteFecesSyndromeViewModel.getListGejalaWhiteFacesSyndrome()
    }

    private fun whiteSpotSyndrome() {
        // Mendapatkan data listGejalaInput dari intent
        val listGejalaInput = intent.getStringArrayListExtra("listGejalaInput") ?: emptyList()

        whiteSpotSyndromeViewModel.gejalaWhiteSpotSyndrome.observe(this) { status ->
            when (status) {
                is Result.Success -> {
                    val probabilitasList = mutableListOf<Double>()
                    val gejala = mutableListOf<String>()

                    status.data.forEach { gejalaData ->
                        // Memeriksa apakah gejalaData.gejala ada dalam listGejalaInput
                        if (gejalaData.gejala in listGejalaInput) {
                            probabilitasList.add(gejalaData.probabilitas)
                            gejala.add(gejalaData.gejala)
                        }
                    }

                    // Menghitung total probabilitas gejala
                    val totalProbabilitasGejala = probabilitasList.sum()

                    if (totalProbabilitasGejala != 0.0) {
                        // menghitung nilai semesta, P(E|Hi) / total probabilitas
                        val semestaPh = probabilitasList.map { it / totalProbabilitasGejala }

                        // menghitung probabilitas H tanpa memandang evidence, P(Hi) x P(E|Hi)
                        val hasilKali = probabilitasList.mapIndexed {index, probabilitasGejala ->
                            probabilitasGejala * semestaPh[index]
                        }

                        // total hipotesa H, P(H)
                        val totalPH = hasilKali.sum()

                        // mencari nilai probabilitas Hi benar jika diberikan evidence E, P(Hi) * P(E|Hi) / P(H)
                        val hasilPhie = hasilKali.mapIndexed { index, hasil ->
                            hasil * probabilitasList[index] / totalPH
                        }

                        // total nilai bayes, bayaes1 + bayes2 + ... + bayesn
                        val totalBayesWSS = hasilPhie.sum()
                        val formattedNilaiBayes = "%.4f".format(totalBayesWSS)

                        hasilDiagnosaList.add(HasilDiagnosa("white spot syndrome", totalBayesWSS))

                        binding.tvHasilWhiteSpotSyndrome.text = formattedNilaiBayes
                    } else {
                        binding.tvHasilWhiteSpotSyndrome.text = 0.0.toString()
                    }

                    tampilkanHasilDiagnosaTerbesar()
                }
                else -> {}
            }
        }

        whiteSpotSyndromeViewModel.getListGejalaWhiteSpotSyndrome()
    }

    // Fungsi untuk menampilkan hasil diagnosa terbesar pada tvGejalapihi
    fun tampilkanHasilDiagnosaTerbesar() {
        val penyakitTerbesar: HasilDiagnosa? = hasilDiagnosaList.maxByOrNull { it.nilaiBayes }
        val listGejalaInput = intent.getStringArrayListExtra("listGejalaInput") ?: emptyList()
        val gejalaTerpilihText = StringBuilder()

        listGejalaInput.forEachIndexed { index, gejala ->
            val nomorGejala = index + 1
            gejalaTerpilihText.append("$nomorGejala. $gejala\n")
        }

        binding.apply {
            hasilAkhir.text = penyakitTerbesar?.let { hasilDiagnosa ->
                getDataPenyakit(hasilDiagnosa.namaPenyakit)

                val nilaiBayesPersen = hasilDiagnosa.nilaiBayes * 100
                val formattedNilaiBayesPersen = "%.2f".format(nilaiBayesPersen)

                "Berdasarkan gejala yang dipilih, udang anda terserang penyakit ${hasilDiagnosa.namaPenyakit} dengan nilai probabilitas $formattedNilaiBayesPersen%"
            } ?: getString(R.string.data_tidak_ditemukan)

            tvTotalGejala.text = gejalaTerpilihText
        }
    }

    // get data penyakit
    private fun getDataPenyakit(penyakit: String) {
        daftarPenyakit.dataPenyakit.observe(this) { status ->
            when (status) {
                is Result.Success -> {
                    for (i in status.data) {
                        binding.apply {
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

    private fun tampilKonfiramasi() {
        AlertDialog.Builder(this)
            .setTitle("KONFIRMASI")
            .setMessage("Apakah anda ingin diagnosa ulang?")

            .setPositiveButton("YA"){ dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
                goDiagnosa()
            }

            .setNegativeButton("TIDAK"){ dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
                goHome()
            }

            .setNeutralButton("NANTI"){ dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
            .show()
    }

    private fun goHome() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

    private fun goDiagnosa() {
        startActivity(Intent(applicationContext, DiagnosaActivity::class.java))
        finish()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

    override fun onBackPressed() {
        tampilKonfiramasi()
    }

}