package com.example.mygallery.domain.usecase

import app.cash.turbine.test
import com.example.mygallery.data.interactor.PhotoInteractor
import com.example.mygallery.data.source.Resource
import com.example.mygallery.data.listPhoto
import com.example.mygallery.domain.repository.PhotoRepository
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.spyk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PhotoUseCaseTest {

    private val repository = spyk<PhotoRepository>()
    private lateinit var sut: PhotoUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        sut = PhotoInteractor(photoRepository = repository)
    }

    @Test
    fun `test load get photo`() = runBlocking {
        coEvery {
            repository.getPhoto()
        } returns flowOf()

        sut.getPhotos().test {
            awaitComplete()
        }

        coVerify(exactly = 1) {
            repository.getPhoto()
        }

        confirmVerified(repository)
    }

    @Test
    fun `test load get photo with data`() = runBlocking {
        coEvery {
            repository.getPhoto()
        } returns flowOf(Resource.Success(listPhoto))

        sut.getPhotos().test {
            when(val result = awaitItem()) {
                is Resource.Success -> {
                    assertEquals(listPhoto, result.data)
                }
                else -> Unit
            }
            awaitComplete()
        }

        coVerify(exactly = 1) {
            repository.getPhoto()
        }

        confirmVerified(repository)
    }

    @After
    fun teardown() { clearAllMocks() }
}