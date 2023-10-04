package com.waslim.sispakudangvaname.model.repository.gejala

import com.waslim.sispakudangvaname.model.dataclass.gejala.Gejala
import com.waslim.sispakudangvaname.util.Result

interface GejalaRepository {
    suspend fun listGejala(): Result<List<Gejala>>
}