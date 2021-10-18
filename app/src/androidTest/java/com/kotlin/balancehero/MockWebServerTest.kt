package com.kotlin.balancehero

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.kotlin.balancehero.data.Beers
import com.kotlin.balancehero.ui.main.MainActivity
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Call
import java.io.IOException

@LargeTest
@RunWith(AndroidJUnit4::class)
class MockWebServerTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    private var mockBackEnd: MockWebServer = MockWebServer()
    private lateinit var apiService: Call<List<Beers>>

    @Before
    @Throws(IOException::class)
    fun setUp() {
        mockBackEnd.start()
    }

    @Test
    @Throws(Exception::class)
    fun getBeer() {
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

        mockBackEnd.setDispatcher(object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(
                        "[{\"id\":26," +
                                "\"name\":\"Skull Candy\"," +
                                "\"image_url\":\"https://images.punkapi.com/v2/keg.png\"," +
                                "\"checkbox\":\"false\"}]"
                    )
            }
        })

        activityRule.launchActivity(null)

        Thread.sleep(7000)
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        mockBackEnd.shutdown()
    }
}