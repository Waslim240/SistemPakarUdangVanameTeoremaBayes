package com.waslim.sispakudangvaname.view.penyakit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.waslim.sispakudangvaname.DummyData
import com.waslim.sispakudangvaname.MainCoroutinesRule
import com.waslim.sispakudangvaname.getOrAwaitValue
import com.waslim.sispakudangvaname.model.dataclass.penyakit.Penyakit
import com.waslim.sispakudangvaname.model.repository.diagnosa.earlymortalitysyndrome.EarlyMortalitySyndrome
import com.waslim.sispakudangvaname.model.repository.diagnosa.infectiousmyonecrosis.InfectiousMyoNecrosis
import com.waslim.sispakudangvaname.ui.viewmodel.penyakit.earlymortalitysyndrome.EarlyMortalitySyndromeViewModel
import com.waslim.sispakudangvaname.ui.viewmodel.penyakit.infectiousmyonecrosis.InfectiousMyoNecrosisViewModel
import com.waslim.sispakudangvaname.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class InfectiousMyoNecrosisViewModelTest {

    private lateinit var infectiousMyoNecrosis: InfectiousMyoNecrosis
    private lateinit var infectiousMyoNecrosisViewModel: InfectiousMyoNecrosisViewModel

    private val dummyData = DummyData.showDataPenyakit()

    private val error = "failure"

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        infectiousMyoNecrosis = mock(InfectiousMyoNecrosis::class.java)
        infectiousMyoNecrosisViewModel = InfectiousMyoNecrosisViewModel(infectiousMyoNecrosis)
    }

    @Test
    fun `ketika berhasil mengambil data`() = runTest {
        val expectedData = MutableLiveData<Result<List<Penyakit>>>()
        expectedData.value = Result.Success(dummyData)

        `when`(
            infectiousMyoNecrosis.getGejalaInfectiousMyoNecrosis()
        ).thenReturn(expectedData.value)

        infectiousMyoNecrosisViewModel.getListGejalaInfectiousMyoNecrosis()

        val actual = infectiousMyoNecrosisViewModel.gejalaInfectiousMyoNecrosis.getOrAwaitValue()

        verify(infectiousMyoNecrosis).getGejalaInfectiousMyoNecrosis()
        Assert.assertNotNull(actual)
        Assert.assertTrue(actual is Result.Success)
        Assert.assertEquals(dummyData, (actual as Result.Success).data)
    }

    @Test
    fun `ketika gagal mengambil data`() = runTest {
        val expectedData = MutableLiveData<Result<List<Penyakit>>>()
        expectedData.value = Result.Failure(error)

        `when`(
            infectiousMyoNecrosis.getGejalaInfectiousMyoNecrosis()
        ).thenReturn(expectedData.value)

        infectiousMyoNecrosisViewModel.getListGejalaInfectiousMyoNecrosis()

        val actual = infectiousMyoNecrosisViewModel.gejalaInfectiousMyoNecrosis.getOrAwaitValue()

        verify(infectiousMyoNecrosis).getGejalaInfectiousMyoNecrosis()
        Assert.assertNotNull(actual)
        Assert.assertTrue(actual is Result.Failure)
        Assert.assertEquals(error, (actual as Result.Failure).error)

    }
}