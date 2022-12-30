package com.evernext10.marketplace.product.list.data.repository

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import com.evernext10.core.domain.model.recipes.response.MarketplaceRecipesListResponse
import com.evernext10.core.domain.network.Either
import com.evernext10.core.domain.network.request
import com.evernext10.marketplace.product.list.data.remote.network.RecipesListService
import com.evernext10.marketplace.product.list.data.remote.validProductList
import com.google.common.truth.Truth
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MarketplaceProductListRepositoryImplTest {

    private lateinit var repository: MarketplaceRecipesListRepositoryImpl
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var api: RecipesListService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()

        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()

        api = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl("https://api.mercadolibre.com/sites/")
            .build()
            .create(RecipesListService::class.java)

        repository = MarketplaceRecipesListRepositoryImpl(
            api = mockk(relaxed = true),
            context = InstrumentationRegistry.getInstrumentation().targetContext
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getErrorGettingProductListsFromServer() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(500)
        )

        val result = request(
            api.getRecipesBySearch(),
            { it },
            MarketplaceRecipesListResponse()
        )
        Log.i("Result", result.toString())
        Truth.assertThat(result is Either.Left).isTrue()
    }

    @Test
    fun getSuccessProductListsFromServer() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validProductList)
        )

        val result = request(
            api.getRecipesBySearch(),
            { it },
            MarketplaceRecipesListResponse()
        )
        Log.i("Result", result.toString())
        Truth.assertThat(result is Either.Left).isTrue()
    }
}
