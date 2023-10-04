package com.waslim.sispakudangvaname.view.daftarpenyakit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.waslim.sispakudangvaname.DummyData
import com.waslim.sispakudangvaname.MainCoroutinesRule
import com.waslim.sispakudangvaname.getOrAwaitValue
import com.waslim.sispakudangvaname.model.dataclass.daftarpenyakit.DaftarPenyakit
import com.waslim.sispakudangvaname.model.repository.daftarpenyakit.DaftarPenyakitRepository
import com.waslim.sispakudangvaname.ui.viewmodel.daftarpenyakit.DaftarPenyakitViewModel
import com.waslim.sispakudangvaname.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class DaftarPenyakitViewModelTest {

    private lateinit var daftarPenyakitViewModel: DaftarPenyakitViewModel
    private lateinit var daftarPenyakitRepository: DaftarPenyakitRepository

    private val dummyData = DummyData.showDaftarPenyakit()

    private val penyakit = "vibriosis"
    private val error = "failure"

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        daftarPenyakitRepository = mock(DaftarPenyakitRepository::class.java)
        daftarPenyakitViewModel = DaftarPenyakitViewModel(daftarPenyakitRepository)
    }

    @Test
    fun `ketika berhasil mengambil data`() = runTest {
        val expectedData = MutableLiveData<Result<List<DaftarPenyakit>>>()
        expectedData.value = Result.Success(dummyData)

        `when`(
            daftarPenyakitRepository.daftarPenyakit(penyakit)
        ).thenReturn(expectedData.value)

        daftarPenyakitViewModel.getDaftarPenyakit(penyakit)

        val actual = daftarPenyakitViewModel.dataPenyakit.getOrAwaitValue()

        verify(daftarPenyakitRepository).daftarPenyakit(penyakit)
        assertNotNull(actual)
        assertTrue(actual is Result.Success)
        assertEquals(dummyData, (actual as Result.Success).data)
    }

    @Test
    fun `ketika gagal mengambil data`() = runTest {
        val expectedData = MutableLiveData<Result<List<DaftarPenyakit>>>()
        expectedData.value = Result.Failure(error)

        `when`(
            daftarPenyakitRepository.daftarPenyakit(error)
        ).thenReturn(expectedData.value)

        daftarPenyakitViewModel.getDaftarPenyakit(error)

        val actual = daftarPenyakitViewModel.dataPenyakit.getOrAwaitValue()

        verify(daftarPenyakitRepository).daftarPenyakit(error)
        assertNotNull(actual)
        assertTrue(actual is Result.Failure)
        assertEquals(error, (actual as Result.Failure).error)

    }
}