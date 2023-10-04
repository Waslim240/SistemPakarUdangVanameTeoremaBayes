package com.waslim.sispakudangvaname.model.repository.gejala

import com.google.firebase.firestore.FirebaseFirestore
import com.waslim.sispakudangvaname.model.dataclass.gejala.Gejala
import com.waslim.sispakudangvaname.util.Result
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GejalaRepositoryImp @Inject constructor(private val firebaseFirestore: FirebaseFirestore): GejalaRepository {
    override suspend fun listGejala(): Result<List<Gejala>> {
        return suspendCoroutine {  continuation ->
            firebaseFirestore.collection("gejala").orderBy("gejala")
                .get()
                .addOnSuccessListener {
                    val listDataGejala = arrayListOf<Gejala>()
                    for (document in it) {
                        val dataGejala = document.toObject(Gejala::class.java)
                        listDataGejala.add(dataGejala)
                    }

                    continuation.resume(Result.Success(listDataGejala))
                }
                .addOnFailureListener { e->
                    continuation.resume(Result.Failure(e.localizedMessage))
                }
        }
    }
}