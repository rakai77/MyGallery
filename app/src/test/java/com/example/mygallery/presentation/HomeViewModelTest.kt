package com.example.mygallery.presentation

import app.cash.turbine.test
import com.example.mygallery.data.emptyData
import com.example.mygallery.data.listPhoto
import com.example.mygallery.data.source.Resource
import com.example.mygallery.data.source.remote.dto.toDomain
import com.example.mygallery.domain.usecase.PhotoUseCase
import com.example.mygallery.presentation.home.HomeUiState
import com.example.mygallery.presentation.home.HomeViewModel
import com.example.mygallery.presentation.utils.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.spyk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    private val useCase = spyk<PhotoUseCase>()
    private lateinit var sut: HomeViewModel

    @get:Rule
    val mainDispatcher = MainDispatcherRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        sut = HomeViewModel(photoUseCase = useCase)
    }

    @Test
    fun `test load photo on Success empty data`() = runBlocking {
        coEvery {
            useCase.getPhotos()
        } returns flowOf(Resource.Success(emptyData))

        sut.getPhoto()

        sut.uiState.take(1).test {
            when(val result = awaitItem()) {
                is HomeUiState.Success -> {
                    assertEquals(
                        emptyData.map { it.toDomain() },
                        result.data
                    )
                    assertNotNull(result.data)
                }
                else -> Unit
            }
            awaitComplete()
        }

        coVerify(exactly = 1) {
            useCase.getPhotos()
        }

        confirmVerified(useCase)
    }

    @Test
    fun `test load photo on Success request data`() = runBlocking {
        coEvery {
            useCase.getPhotos()
        } returns flowOf(Resource.Success(listPhoto))

        sut.getPhoto()

        sut.uiState.take(1).test {
            when(val result = awaitItem()) {
                is HomeUiState.Success -> {
                    assertEquals(
                        listPhoto.map { it.toDomain() },
                        result.data
                    )
                    assertNotNull(result.data)
                }
                else -> Unit
            }
            awaitComplete()
        }

        coVerify(exactly = 1) {
            useCase.getPhotos()
        }

        confirmVerified(useCase)
    }

    @Test
    fun `test load photo on Failure from response`() = runBlocking {
        coEvery {
            useCase.getPhotos()
        } returns flowOf(Resource.Error(null, "Not Found"))

        sut.getPhoto()

        sut.uiState.take(1).test {
            when(val result = awaitItem()) {
                is HomeUiState.Error -> {
                    assertEquals(
                        "Not Found",
                        result.message
                    )
                }
                else -> Unit
            }
            awaitComplete()
        }

        coVerify(exactly = 1) {
            useCase.getPhotos()
        }

        confirmVerified(useCase)
    }

    @After
    fun teardown() { clearAllMocks() }
}