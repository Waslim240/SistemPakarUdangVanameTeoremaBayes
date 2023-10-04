package com.waslim.sispakudangvaname.ui.viewmodel.daftarpenyakit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waslim.sispakudangvaname.model.dataclass.daftarpenyakit.DaftarPenyakit
import com.waslim.sispakudangvaname.model.repository.daftarpenyakit.DaftarPenyakitRepository
import com.waslim.sispakudangvaname.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DaftarPenyakitViewModel @Inject constructor(private val  daftarPenyakitRepository: DaftarPenyakitRepository): ViewModel() {
    private val _listDaftarPenyakit = MutableLiveData<Result<List<DaftarPenyakit>>>()
    val dataPenyakit: LiveData<Result<List<DaftarPenyakit>>>
        get() = _listDaftarPenyakit

    fun getDaftarPenyakit(penyakit: String) {
        _listDaftarPenyakit.value = Result.Loading
        viewModelScope.launch {
            val result = daftarPenyakitRepository.daftarPenyakit(penyakit)
            _listDaftarPenyakit.value = result
        }
    }
}