package com.waslim.sispakudangvaname.model.repository.diagnosa.whitefecessyndrome

import com.waslim.sispakudangvaname.model.dataclass.penyakit.Penyakit
import com.waslim.sispakudangvaname.util.Result

interface WhiteFecesSyndrome {
    // get gejala penyakit white faces syndrome
    suspend fun getGejalaWhiteFacesSyndrome (): Result<List<Penyakit>>

}