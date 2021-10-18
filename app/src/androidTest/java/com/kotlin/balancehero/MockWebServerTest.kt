package com.kotlin.balancehero

import com.kotlin.balancehero.data.Beers
import junit.framework.Assert.assertEquals
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import java.io.IOException


class MockWebServerTest {
    private lateinit var mockBackEnd: MockWebServer
    private lateinit var apiService: Call<List<Beers>>

    @Before
    @Throws(IOException::class)
    fun setUp() {
        mockBackEnd = MockWebServer()
        mockBackEnd.start()
    }

    @Test
    @Throws(Exception::class)
    fun getEmployeeById() {
        val baseUrl: String = String.format(
            "http://localhost:%s",
            mockBackEnd.port
        )

        mockBackEnd.enqueue(
            MockResponse()
                .setBody(
                    "[{\"id\":26," +
                            "\"name\":\"Skull Candy\"," +
                            "\"image_url\":\"https://images.punkapi.com/v2/keg.png\"," +
                            "\"checkbox\":\"false\"}]"
                )
                .addHeader("Content-Type", "application/json")
        )

//        mockBackEnd.dispatcher = object : Dispatcher() {
//            override fun dispatch(request: MockWebServer.Recorded): MockResponse {
//                return MockResponse()
//                    .setResponseCode(200)
//                    .setBody(
//                        "[{\"id\":26," +
//                                "\"name\":\"Skull Candy\"," +
//                                "\"image_url\":\"https://images.punkapi.com/v2/keg.png\"," +
//                                "\"checkbox\":\"false\"}]"
//                    )
//            }

//            val beer: List<Beers> =
//                ApiClient.getClient(baseUrl).create(ApiInterface::class.java)
//                    .getList(100
//
//            assertEquals(beer.get(0).name, "Skull Candy")
        val recordedRequest = mockBackEnd.takeRequest()
        assertEquals(recordedRequest.method, "GET")
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        mockBackEnd.shutdown()
    }
}