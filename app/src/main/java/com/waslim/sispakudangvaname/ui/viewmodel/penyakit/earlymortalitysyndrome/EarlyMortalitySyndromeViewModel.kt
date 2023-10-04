package com.waslim.sispakudangvaname.ui.viewmodel.penyakit.earlymortalitysyndrome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waslim.sispakudangvaname.model.dataclass.penyakit.Penyakit
import com.waslim.sispakudangvaname.model.repository.diagnosa.earlymortalitysyndrome.EarlyMortalitySyndrome
import com.waslim.sispakudangvaname.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EarlyMortalitySyndromeViewModel @Inject constructor(private val earlyMortalitySyndrome: EarlyMortalitySyndrome): ViewModel(){
    // LiveData untuk gejala dari penyakit early mortality syndrome
    private val _listGejalaEarlyMortalitySyndrome = MutableLiveData<Result<List<Penyakit>>> ()
    val gejalaEarlyMortalitySyndrome: LiveData<Result<List<Penyakit>>>
        get() = _listGejalaEarlyMortalitySyndrome

    fun getListGejalaEarlyMortalitySyndrome() {
        _listGejalaEarlyMortalitySyndrome.value = Result.Loading
        viewModelScope.launch {
            val result = earlyMortalitySyndrome.getGejalaEarlyMortalitySyndrome()
            _listGejalaEarlyMortalitySyndrome.value = result
        }
    }
}