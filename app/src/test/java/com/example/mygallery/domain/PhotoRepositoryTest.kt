package com.example.mygallery.domain

import app.cash.turbine.test
import com.example.mygallery.data.repository.PhotoRepositoryImpl
import com.example.mygallery.data.source.Resource
import com.example.mygallery.data.source.remote.ApiService
import com.example.mygallery.data.source.remote.dto.PhotosResponse
import com.example.mygallery.domain.repository.PhotoRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class PhotoRepositoryTest {

    private val service = mockk<ApiService>()
    private lateinit var sut: PhotoRepository

    @Before
    fun setup() { sut = PhotoRepositoryImpl(apiService = service) }

    @Test
    fun `test load photo on Failure Timeout`() = runBlocking {
        coEvery {
            service.getPhotos()
        } throws SocketTimeoutException()

        sut.getPhoto().test {
            when(val result = awaitItem()) {
                is Resource.Error -> {
                    assertEquals(
                        "Timeout",
                        result.message
                    )
                }
                else -> Unit
            }
            awaitComplete()
        }

        coVerify(exactly = 1) {
            service.getPhotos()
        }

        confirmVerified(service)
    }

    @Test
    fun `test load photo on Failure Bad Connection`() = runBlocking {
        coEvery {
            service.getPhotos()
        } throws UnknownHostException()

        sut.getPhoto().test {
            when(val result = awaitItem()) {
                is Resource.Error -> {
                    assertEquals(
                        "Check your internet connection",
                        result.message
                    )
                }
                else -> Unit
            }
            awaitComplete()
        }

        coVerify(exactly = 1) {
            service.getPhotos()
        }

        confirmVerified(service)
    }

    @Test
    fun `test load photo on Failure Unknown Error`() = runBlocking {
        coEvery {
            service.getPhotos()
        } throws IOException()

        sut.getPhoto().test {
            when(val result = awaitItem()) {
                is Resource.Error -> {
                    assertEquals(
                        "Something went wrong",
                        result.message
                    )
                }
                else -> Unit
            }
            awaitComplete()
        }

        coVerify(exactly = 1) {
            service.getPhotos()
        }

        confirmVerified(service)
    }

    @Test
    fun `test load photo on Failure HttpException Internal Server Error`() = runBlocking {

        val response = Response.error<ApiService>(
            500,
            "".toResponseBody(null)
        )

        coEvery {
            service.getPhotos()
        } throws HttpException(response)

        sut.getPhoto().test {
            when(val result = awaitItem()) {
                is Resource.Error -> {
                    assertEquals(
                        response.code(),
                        result.errorCode
                    )
                }
                else -> Unit
            }
            awaitComplete()
        }

        coVerify(exactly = 1) {
            service.getPhotos()
        }

        confirmVerified(service)
    }

    @Test
    fun `test load photo on Success Request Data`() = runBlocking {

        val response = listOf(
            PhotosResponse(1, 1, "accusamus beatae ad facilis cum similique qui sunt", "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952"),
            PhotosResponse(1, 2, "reprehenderit est deserunt velit ipsam", "https://via.placeholder.com/600/771796", "https://via.placeholder.com/150/771796"),
        )

        coEvery {
            service.getPhotos()
        } returns Response.success(response)

        sut.getPhoto().test {
            when(val result = awaitItem()) {
                is Resource.Success -> {
                    assertEquals(response, result.data)
                    assertNotNull(result.data)
                }
                else -> Unit
            }
            awaitComplete()
        }

        coVerify(exactly = 1) {
            service.getPhotos()
        }

        confirmVerified(service)
    }

    @After
    fun teardown() { clearAllMocks() }
}