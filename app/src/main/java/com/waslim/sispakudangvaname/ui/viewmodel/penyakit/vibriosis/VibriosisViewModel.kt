package com.waslim.sispakudangvaname.ui.viewmodel.penyakit.vibriosis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waslim.sispakudangvaname.model.dataclass.penyakit.Penyakit
import com.waslim.sispakudangvaname.model.repository.diagnosa.vibriosis.Vibriosis
import com.waslim.sispakudangvaname.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VibriosisViewModel @Inject constructor(private val vibriosis: Vibriosis): ViewModel() {
    // LiveData untuk gejala dari penyakit vibriosis
    private val _listGejalaVibriosis = MutableLiveData<Result<List<Penyakit>>> ()
    val gejalaVibriosis: LiveData<Result<List<Penyakit>>>
        get() = _listGejalaVibriosis

    fun getListGejalaVibriosis() {
        _listGejalaVibriosis.value = Result.Loading
        viewModelScope.launch {
            val result = vibriosis.getGejalaVibriosis()
            _listGejalaVibriosis.value = result
        }
    }
}