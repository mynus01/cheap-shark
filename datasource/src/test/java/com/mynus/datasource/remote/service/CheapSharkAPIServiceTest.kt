package com.mynus.datasource.remote.service

import com.mynus.datasource.base.BaseTest
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@ExperimentalCoroutinesApi
open class CheapSharkAPIServiceTest: BaseTest() {
    @Test
    fun `should return a list of deals when request is successful`() = runTest {
        val server = MockWebServer()
        val jsonString = ClassLoader.getSystemResource("deals.json")!!.readText()

        val retrofit = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()))
            .build()
        val service: CheapSharkAPIService = retrofit.create(CheapSharkAPIService::class.java)

        server.enqueue(MockResponse().setBody(jsonString))

        val result = service.get(0)

        server.takeRequest()
        server.shutdown()

        assertTrue(result.isNotEmpty())
    }
}