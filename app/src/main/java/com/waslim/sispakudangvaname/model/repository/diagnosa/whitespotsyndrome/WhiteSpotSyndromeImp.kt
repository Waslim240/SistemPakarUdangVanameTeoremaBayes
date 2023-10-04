package com.waslim.sispakudangvaname.model.repository.diagnosa.whitespotsyndrome

import com.google.firebase.firestore.FirebaseFirestore
import com.waslim.sispakudangvaname.model.dataclass.penyakit.Penyakit
import com.waslim.sispakudangvaname.util.Result
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class WhiteSpotSyndromeImp @Inject constructor(private val firebaseFirestore: FirebaseFirestore): WhiteSpotSyndrome {
    override suspend fun getGejalaWhiteSpotSyndrome(): Result<List<Penyakit>> {
        return suspendCoroutine { continuation ->
            firebaseFirestore.collection("white spot syndrome")
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