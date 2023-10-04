package com.waslim.sispakudangvaname.model.repository.diagnosa.whitefecessyndrome

import com.google.firebase.firestore.FirebaseFirestore
import com.waslim.sispakudangvaname.model.dataclass.penyakit.Penyakit
import com.waslim.sispakudangvaname.util.Result
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class WhiteFecesSyndromeImp @Inject constructor(private val firebaseFirestore: FirebaseFirestore): WhiteFecesSyndrome {
    override suspend fun getGejalaWhiteFacesSyndrome(): Result<List<Penyakit>> {
        return suspendCoroutine { continuation ->
            firebaseFirestore.collection("white faces syndrome")
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