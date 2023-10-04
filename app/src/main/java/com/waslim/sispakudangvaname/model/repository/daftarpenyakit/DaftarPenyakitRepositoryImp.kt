package com.waslim.sispakudangvaname.model.repository.daftarpenyakit

import com.google.firebase.firestore.FirebaseFirestore
import com.waslim.sispakudangvaname.model.dataclass.daftarpenyakit.DaftarPenyakit
import com.waslim.sispakudangvaname.util.Result
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DaftarPenyakitRepositoryImp @Inject constructor(private val firebaseFirestore: FirebaseFirestore): DaftarPenyakitRepository {
    override suspend fun daftarPenyakit(penyakit: String): Result<List<DaftarPenyakit>> {
        return suspendCoroutine { continuation ->
            firebaseFirestore.collection("daftar penyakit").whereEqualTo("penyakit", penyakit)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val listDaftarPenyakit = arrayListOf<DaftarPenyakit>()
                    for (document in querySnapshot) {
                        val dataPenyakit = document.toObject(DaftarPenyakit::class.java)
                        listDaftarPenyakit.add(dataPenyakit)
                    }
                    continuation.resume(Result.Success(listDaftarPenyakit))
                }
                .addOnFailureListener { e ->
                    continuation.resume(Result.Failure(e.localizedMessage))
                }
        }
    }
}