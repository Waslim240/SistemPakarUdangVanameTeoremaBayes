package com.waslim.sispakudangvaname.model.repository.diagnosa.infectiousmyonecrosis

import com.waslim.sispakudangvaname.model.dataclass.penyakit.Penyakit
import com.waslim.sispakudangvaname.util.Result

interface InfectiousMyoNecrosis {
    // Infectious Myo Necrosis
    suspend fun getGejalaInfectiousMyoNecrosis (): Result<List<Penyakit>>

}