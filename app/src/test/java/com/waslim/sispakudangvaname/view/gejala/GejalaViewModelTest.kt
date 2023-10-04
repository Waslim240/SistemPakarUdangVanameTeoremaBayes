package com.waslim.sispakudangvaname.view.gejala

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.waslim.sispakudangvaname.DummyData
import com.waslim.sispakudangvaname.MainCoroutinesRule
import com.waslim.sispakudangvaname.getOrAwaitValue
import com.waslim.sispakudangvaname.model.dataclass.gejala.Gejala
import com.waslim.sispakudangvaname.model.repository.gejala.GejalaRepository
import com.waslim.sispakudangvaname.ui.viewmodel.gejala.GejalaViewModel
import com.waslim.sispakudangvaname.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class GejalaViewModelTest {

    private lateinit var gejalaViewModel: GejalaViewModel
    private lateinit var gejalaRepository: GejalaRepository

    private val dummyData = DummyData.showGejala()
    private val error = "failure"

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        gejalaRepository = mock(GejalaRepository::class.java)
        gejalaViewModel = GejalaViewModel(gejalaRepository)
    }

    @Test
    fun `ketika berhasil mengambil data`() = runTest {
        val expectedData = MutableLiveData<Result<List<Gejala>>>()
        expectedData.value = Result.Success(dummyData)

        `when`(
            gejalaRepository.listGejala()
        ).thenReturn(expectedData.value)

        gejalaViewModel.getListGejala()

        val actual = gejalaViewModel.dataGejala.getOrAwaitValue()

        verify(gejalaRepository).listGejala()
        Assert.assertNotNull(actual)
        Assert.assertTrue(actual is Result.Success)
        Assert.assertEquals(dummyData, (actual as Result.Success).data)
    }

    @Test
    fun `ketika gagal mengambil data`() = runTest {
        val expectedData = MutableLiveData<Result<List<Gejala>>>()
        expectedData.value = Result.Failure(error)

        `when`(
            gejalaRepository.listGejala()
        ).thenReturn(expectedData.value)

        gejalaViewModel.getListGejala()

        val actual = gejalaViewModel.dataGejala.getOrAwaitValue()

        verify(gejalaRepository).listGejala()
        Assert.assertNotNull(actual)
        Assert.assertTrue(actual is Result.Failure)
        Assert.assertEquals(error, (actual as Result.Failure).error)
    }
}