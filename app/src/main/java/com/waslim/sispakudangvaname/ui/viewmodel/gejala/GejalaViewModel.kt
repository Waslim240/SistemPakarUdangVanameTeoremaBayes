package com.waslim.sispakudangvaname.ui.viewmodel.gejala

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waslim.sispakudangvaname.model.dataclass.gejala.Gejala
import com.waslim.sispakudangvaname.model.repository.gejala.GejalaRepository
import com.waslim.sispakudangvaname.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GejalaViewModel @Inject constructor(private val  gejalaRepository: GejalaRepository): ViewModel() {
    private val _listDataGejala = MutableLiveData<Result<List<Gejala>>>()
    val dataGejala: LiveData<Result<List<Gejala>>>
        get() = _listDataGejala

    fun getListGejala() {
        _listDataGejala.value = Result.Loading
        viewModelScope.launch {
            val result = gejalaRepository.listGejala()
            _listDataGejala.value = result
        }
    }
}