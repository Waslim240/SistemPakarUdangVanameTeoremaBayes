package com.waslim.sispakudangvaname.model.dataclass.gejala

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gejala(
    var gejala: String = ""
): Parcelable