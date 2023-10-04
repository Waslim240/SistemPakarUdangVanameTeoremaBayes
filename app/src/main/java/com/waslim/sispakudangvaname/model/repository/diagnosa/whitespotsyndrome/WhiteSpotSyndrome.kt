package com.waslim.sispakudangvaname.model.repository.diagnosa.whitespotsyndrome

import com.waslim.sispakudangvaname.model.dataclass.penyakit.Penyakit
import com.waslim.sispakudangvaname.util.Result

interface WhiteSpotSyndrome {
    // get gejala penyakit white spot syndrome
    suspend fun getGejalaWhiteSpotSyndrome (): Result<List<Penyakit>>

}