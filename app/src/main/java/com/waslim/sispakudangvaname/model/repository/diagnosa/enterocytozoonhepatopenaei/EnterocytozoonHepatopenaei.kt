package com.waslim.sispakudangvaname.model.repository.diagnosa.enterocytozoonhepatopenaei

import com.waslim.sispakudangvaname.model.dataclass.penyakit.Penyakit
import com.waslim.sispakudangvaname.util.Result

interface EnterocytozoonHepatopenaei {
    // enterocytozoon hepatopenaei
    suspend fun getGejalaEnterocytozoonHepatopenaei (): Result<List<Penyakit>>

}