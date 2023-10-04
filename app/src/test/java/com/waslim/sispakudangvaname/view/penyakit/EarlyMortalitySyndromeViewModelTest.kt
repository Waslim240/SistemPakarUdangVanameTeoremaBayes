package com.waslim.sispakudangvaname.view.penyakit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.waslim.sispakudangvaname.DummyData
import com.waslim.sispakudangvaname.MainCoroutinesRule
import com.waslim.sispakudangvaname.getOrAwaitValue
import com.waslim.sispakudangvaname.model.dataclass.daftarpenyakit.DaftarPenyakit
import com.waslim.sispakudangvaname.model.dataclass.penyakit.Penyakit
import com.waslim.sispakudangvaname.model.repository.daftarpenyakit.DaftarPenyakitRepository
import com.waslim.sispakudangvaname.model.repository.diagnosa.earlymortalitysyndrome.EarlyMortalitySyndrome
import com.waslim.sispakudangvaname.ui.viewmodel.daftarpenyakit.DaftarPenyakitViewModel
import com.waslim.sispakudangvaname.ui.viewmodel.penyakit.earlymortalitysyndrome.EarlyMortalitySyndromeViewModel
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
class EarlyMortalitySyndromeViewModelTest {

    private lateinit var earlyMortalitySyndromeViewModel: EarlyMortalitySyndromeViewModel
    private lateinit var earlyMortalitySyndrome: EarlyMortalitySyndrome

    private val dummyData = DummyData.showDataPenyakit()

    private val error = "failure"

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        earlyMortalitySyndrome = mock(EarlyMortalitySyndrome::class.java)
        earlyMortalitySyndromeViewModel = EarlyMortalitySyndromeViewModel(earlyMortalitySyndrome)
    }

    @Test
    fun `ketika berhasil mengambil data`() = runTest {
        val expectedData = MutableLiveData<Result<List<Penyakit>>>()
        expectedData.value = Result.Success(dummyData)

        `when`(
            earlyMortalitySyndrome.getGejalaEarlyMortalitySyndrome()
        ).thenReturn(expectedData.value)

        earlyMortalitySyndromeViewModel.getListGejalaEarlyMortalitySyndrome()

        val actual = earlyMortalitySyndromeViewModel.gejalaEarlyMortalitySyndrome.getOrAwaitValue()

        verify(earlyMortalitySyndrome).getGejalaEarlyMortalitySyndrome()
        Assert.assertNotNull(actual)
        Assert.assertTrue(actual is Result.Success)
        Assert.assertEquals(dummyData, (actual as Result.Success).data)
    }

    @Test
    fun `ketika gagal mengambil data`() = runTest {
        val expectedData = MutableLiveData<Result<List<Penyakit>>>()
        expectedData.value = Result.Failure(error)

        `when`(
            earlyMortalitySyndrome.getGejalaEarlyMortalitySyndrome()
        ).thenReturn(expectedData.value)

        earlyMortalitySyndromeViewModel.getListGejalaEarlyMortalitySyndrome()

        val actual = earlyMortalitySyndromeViewModel.gejalaEarlyMortalitySyndrome.getOrAwaitValue()

        verify(earlyMortalitySyndrome).getGejalaEarlyMortalitySyndrome()
        Assert.assertNotNull(actual)
        Assert.assertTrue(actual is Result.Failure)
        Assert.assertEquals(error, (actual as Result.Failure).error)

    }
}