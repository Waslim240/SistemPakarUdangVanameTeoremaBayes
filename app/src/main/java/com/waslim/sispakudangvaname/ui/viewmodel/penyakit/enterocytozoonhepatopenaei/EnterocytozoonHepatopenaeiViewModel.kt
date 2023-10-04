package com.waslim.sispakudangvaname.ui.viewmodel.penyakit.enterocytozoonhepatopenaei

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waslim.sispakudangvaname.model.dataclass.penyakit.Penyakit
import com.waslim.sispakudangvaname.model.repository.diagnosa.enterocytozoonhepatopenaei.EnterocytozoonHepatopenaei
import com.waslim.sispakudangvaname.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterocytozoonHepatopenaeiViewModel @Inject constructor(private val enterocytozoonHepatopenaei: EnterocytozoonHepatopenaei): ViewModel() {
    // LiveData untuk gejala dari penyakit enterocytozoon hepatopenaei
    private val _listGejalaEnterocytozoonHepatopenaei = MutableLiveData<Result<List<Penyakit>>> ()
    val gejalaEnterocytozoonHepatopenaei: LiveData<Result<List<Penyakit>>>
        get() = _listGejalaEnterocytozoonHepatopenaei

    fun getListGejalaEnterocytozoonHepatopenaei() {
        _listGejalaEnterocytozoonHepatopenaei.value = Result.Loading
        viewModelScope.launch {
            val result = enterocytozoonHepatopenaei.getGejalaEnterocytozoonHepatopenaei()
            _listGejalaEnterocytozoonHepatopenaei.value = result
        }
    }

}