package com.waslim.sispakudangvaname.model.dataclass.penyakit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Penyakit(
    var gejala: String = "",
    var probabilitas: Double = 0.0
): Parcelable
