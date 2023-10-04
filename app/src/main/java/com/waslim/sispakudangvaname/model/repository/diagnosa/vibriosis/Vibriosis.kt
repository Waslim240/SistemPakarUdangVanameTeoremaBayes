package com.waslim.sispakudangvaname.model.repository.diagnosa.vibriosis

import com.waslim.sispakudangvaname.model.dataclass.penyakit.Penyakit
import com.waslim.sispakudangvaname.util.Result

interface Vibriosis {
    // get gejala penyakit vibriosis
    suspend fun getGejalaVibriosis (): Result<List<Penyakit>>

}