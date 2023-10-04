package com.waslim.sispakudangvaname.ui.viewmodel.penyakit.whitefacessyndrome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waslim.sispakudangvaname.model.dataclass.penyakit.Penyakit
import com.waslim.sispakudangvaname.model.repository.diagnosa.whitefecessyndrome.WhiteFecesSyndrome
import com.waslim.sispakudangvaname.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WhiteFecesSyndromeViewModel @Inject constructor(private val whiteFecesSyndrome: WhiteFecesSyndrome): ViewModel() {
    // LiveData untuk gejala dari penyakit white faces syndrome
    private val _listGejalaWhiteFacesSyndrome = MutableLiveData<Result<List<Penyakit>>> ()
    val gejalaWhiteFacesSyndrome : LiveData<Result<List<Penyakit>>>
        get() = _listGejalaWhiteFacesSyndrome

    fun getListGejalaWhiteFacesSyndrome() {
        _listGejalaWhiteFacesSyndrome.value = Result.Loading
        viewModelScope.launch {
            val result = whiteFecesSyndrome.getGejalaWhiteFacesSyndrome()
            _listGejalaWhiteFacesSyndrome.value = result
        }
    }
}