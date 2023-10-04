package com.waslim.sispakudangvaname.model.dataclass.daftarpenyakit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DaftarPenyakit(
    var penyakit: String = "",
    var ringkasan: String = "",
    var penanganan: String = ""
): Parcelable