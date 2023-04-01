package com.nasa.gallery.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nasa.gallery.util.getOrAwaitValue
import com.nasa.gallery.repository.NasaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ImageViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: NasaRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_FetchImages_ExpectedEmptyList() = runTest {
        Mockito.`when`(repository.getImages()).thenReturn(
            com.nasa.gallery.utils.Result.Success(
                emptyList()
            )
        )

        val sut = ImageViewModel(repository)
        sut.fetchImages()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.imageListLiveData.getOrAwaitValue()
        assertEquals(0, result.size)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}