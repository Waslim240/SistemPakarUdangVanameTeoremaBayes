package com.waslim.sispakudangvaname.model.repository.daftarpenyakit

import com.waslim.sispakudangvaname.model.dataclass.daftarpenyakit.DaftarPenyakit
import com.waslim.sispakudangvaname.util.Result

interface DaftarPenyakitRepository {
    suspend fun daftarPenyakit(penyakit: String) : Result<List<DaftarPenyakit>>
}