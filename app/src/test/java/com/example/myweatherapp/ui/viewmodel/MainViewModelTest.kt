package com.example.myweatherapp.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myweatherapp.ConnectionManager
import com.example.myweatherapp.MockResponse
import com.example.myweatherapp.model.Forecast
import com.example.myweatherapp.model.ForecastDay
import com.example.myweatherapp.model.ForecastItem
import com.example.myweatherapp.model.Location
import com.example.myweatherapp.ui.states.UiState
import com.example.myweatherapp.usecase.GetForecastUseCase
import com.example.myweatherapp.usecase.SearchLocationUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
internal class MainViewModelTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var locationUseCase: SearchLocationUseCase

    @RelaxedMockK
    private lateinit var forecastUseCase: GetForecastUseCase

    @RelaxedMockK
    private lateinit var connectionManager: ConnectionManager

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(locationUseCase, forecastUseCase, connectionManager)
        coEvery { connectionManager() } returns true
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewModel calls searchLocation return successful, UiState should return success`() =
        runTest {
            // GIVEN
            val response = MockResponse.provideLocationResponse()

            coEvery { locationUseCase("Location") } returns Response.success(response)

            // WHEN
            mainViewModel.searchLocation("Location")

            // THEN
            val expectedValue = listOf(
                Location("Location1", "country1", 1.0, 1.0),
                Location("Location2", "country2", 2.0, 2.0),
                Location("Location3", "country3", 3.0, 3.0)
            )

            assert(mainViewModel.uiState.value == UiState.Success)
            assert(mainViewModel.locationList.value == expectedValue)
        }

    @Test
    fun `when searchLocation return successful empty response UiState return EmptyResultError`() =
        runTest {
            // GIVEN
            val response = MockResponse.provideLocationEmptyResponse()

            coEvery { locationUseCase("Location") } returns Response.success(response)

            // WHEN
            mainViewModel.searchLocation("Location")

            // THEN
            assert(mainViewModel.uiState.value == UiState.Error.EmptyResultError)
            assert(mainViewModel.locationList.value!!.isEmpty())
        }

    @Test
    fun `when searchLocation and no internet connection, UiState should return ConnectionError`() =
        runTest {
            // GIVEN
            coEvery { connectionManager() } returns false

            // WHEN
            mainViewModel.searchLocation("Location")

            // THEN
            assert(mainViewModel.uiState.value == UiState.Error.ConnectionError)
            assert(mainViewModel.locationList.value!!.isEmpty())
        }

    @Test
    fun `when getForecast return successful, UiState should return success`() =
        runTest {
            // GIVEN
            val response = MockResponse.provideForecastResponse()

            coEvery { forecastUseCase(0.0, 0.0) } returns Response.success(response)

            // WHEN
            mainViewModel.getForecast(0.0, 0.0)

            // THEN
            val expectedValue = Forecast(
                "Place",
                listOf(
                    ForecastItem(ForecastDay.TODAY, "weatherText1", "icon1", 1.1),
                    ForecastItem(ForecastDay.TODAY, "weatherText2", "icon2", 2.2),
                    ForecastItem(ForecastDay.TODAY, "weatherText3", "icon3", 3.3)
                )
            )

            assert(mainViewModel.uiState.value == UiState.Success)
            assert(mainViewModel.forecast.value == expectedValue)
        }

    @Test
    fun `when getForecast and no internet connection, UiState should return ConnectionError`() =
        runTest {
            // GIVEN
            coEvery { connectionManager() } returns false

            // WHEN
            mainViewModel.getForecast(0.0, 0.0)

            // THEN
            assert(mainViewModel.uiState.value == UiState.Error.ConnectionError)
        }
}