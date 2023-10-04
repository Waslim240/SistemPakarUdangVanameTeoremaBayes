package com.waslim.sispakudangvaname.ui.viewmodel.penyakit.whitespotsyndrome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waslim.sispakudangvaname.model.dataclass.penyakit.Penyakit
import com.waslim.sispakudangvaname.model.repository.diagnosa.whitespotsyndrome.WhiteSpotSyndrome
import com.waslim.sispakudangvaname.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WhiteSpotSyndromeViewModel @Inject constructor(private val whiteSpotSyndrome: WhiteSpotSyndrome): ViewModel(){
    // LiveData untuk penyakit dari penyakit white spot syndrome
    private val _listGejalaWhiteSpotSyndrome = MutableLiveData<Result<List<Penyakit>>> ()
    val gejalaWhiteSpotSyndrome : LiveData<Result<List<Penyakit>>>
        get() = _listGejalaWhiteSpotSyndrome

    fun getListGejalaWhiteSpotSyndrome() {
        _listGejalaWhiteSpotSyndrome.value = Result.Loading
        viewModelScope.launch {
            val result = whiteSpotSyndrome.getGejalaWhiteSpotSyndrome()
            _listGejalaWhiteSpotSyndrome.value = result
        }
    }
}