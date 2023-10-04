package com.waslim.sispakudangvaname.model.repository.diagnosa.infectiousmyonecrosis

import com.google.firebase.firestore.FirebaseFirestore
import com.waslim.sispakudangvaname.model.dataclass.penyakit.Penyakit
import com.waslim.sispakudangvaname.util.Result
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class InfectiousMyoNecrosisImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore): InfectiousMyoNecrosis {
    override suspend fun getGejalaInfectiousMyoNecrosis(): Result<List<Penyakit>> {
        return suspendCoroutine { continuation ->
            firebaseFirestore.collection("infectious myo necrosis")
                .get()
                .addOnSuccessListener {
                    val listDataPenyakit = arrayListOf<Penyakit>()
                    for (document in it) {
                        val dataPenyakit = document.toObject(Penyakit::class.java)
                        listDataPenyakit.add(dataPenyakit)
                    }

                    continuation.resume(Result.Success(listDataPenyakit))
                }
                .addOnFailureListener { e ->
                    continuation.resume(Result.Failure(e.localizedMessage))
                }
        }
    }
}