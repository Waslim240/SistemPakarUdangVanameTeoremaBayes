package com.waslim.sispakudangvaname

import com.waslim.sispakudangvaname.model.dataclass.daftarpenyakit.DaftarPenyakit
import com.waslim.sispakudangvaname.model.dataclass.gejala.Gejala
import com.waslim.sispakudangvaname.model.dataclass.penyakit.Penyakit

object DummyData {
    fun showDaftarPenyakit(): List<DaftarPenyakit> {
        val penyakitList = arrayListOf<DaftarPenyakit>()
        for (i in 0..5) {
            val listPenyakit = DaftarPenyakit(
                "vibriosis",
                "ringkasan",
                "penanganan",
            )
            penyakitList.add(listPenyakit)
        }
        return penyakitList
    }

    fun showGejala(): List<Gejala> {
        val gejalaList = arrayListOf<Gejala>()
        for (i in 0..5) {
            val listGejala = Gejala(
                "Nafsu makan menurun",
            )
            gejalaList.add(listGejala)
        }
        return gejalaList
    }

    fun showDataPenyakit(): List<Penyakit> {
        val penyakitList = arrayListOf<Penyakit>()
        for (i in 0..5) {
            val listGejala = Penyakit(
                "Nafsu makan menurun",
                0.3
            )
            penyakitList.add(listGejala)
        }
        return penyakitList
    }
}