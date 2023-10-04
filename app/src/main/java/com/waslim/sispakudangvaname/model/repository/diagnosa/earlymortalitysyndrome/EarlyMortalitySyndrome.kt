package com.waslim.sispakudangvaname.model.repository.diagnosa.earlymortalitysyndrome

import com.waslim.sispakudangvaname.model.dataclass.penyakit.Penyakit
import com.waslim.sispakudangvaname.model.repository.diagnosa.enterocytozoonhepatopenaei.EnterocytozoonHepatopenaei
import com.waslim.sispakudangvaname.util.Result

interface EarlyMortalitySyndrome {
    // get gejala penyakit early mortality syndrome
    suspend fun getGejalaEarlyMortalitySyndrome (): Result<List<Penyakit>>

}