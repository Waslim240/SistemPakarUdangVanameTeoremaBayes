package com.waslim.sispakudangvaname.ui.viewmodel.penyakit.infectiousmyonecrosis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waslim.sispakudangvaname.model.dataclass.penyakit.Penyakit
import com.waslim.sispakudangvaname.model.repository.diagnosa.infectiousmyonecrosis.InfectiousMyoNecrosis
import com.waslim.sispakudangvaname.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfectiousMyoNecrosisViewModel @Inject constructor(private val infectiousMyoNecrosis: InfectiousMyoNecrosis): ViewModel() {
    // LiveData untuk gejala dari penyakit infectious myo necrosis
    private val _listGejalaInfectiousMyoNecrosis = MutableLiveData<Result<List<Penyakit>>> ()
    val gejalaInfectiousMyoNecrosis: LiveData<Result<List<Penyakit>>>
        get() = _listGejalaInfectiousMyoNecrosis

    fun getListGejalaInfectiousMyoNecrosis() {
        _listGejalaInfectiousMyoNecrosis.value = Result.Loading
        viewModelScope.launch {
            val result = infectiousMyoNecrosis.getGejalaInfectiousMyoNecrosis()
            _listGejalaInfectiousMyoNecrosis.value = result
        }
    }

}