package com.example.myweatherapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.myweatherapp.R
import com.example.myweatherapp.model.Forecast
import com.example.myweatherapp.model.ForecastItem
import com.example.myweatherapp.ui.states.ErrorScreen
import com.example.myweatherapp.ui.states.UiState
import com.example.myweatherapp.ui.theme.lightBlue
import com.example.myweatherapp.ui.viewmodel.MainViewModel
import com.example.myweatherapp.ui.views.Loader

@Composable
fun DetailScreen(viewModel: MainViewModel) {
    val forecast: Forecast by viewModel
        .forecast
        .observeAsState(Forecast("", listOf()))

    val uiState: UiState? by viewModel
        .uiState
        .observeAsState(null)

    Details(forecast, uiState!!)
}

@Composable
private fun Details(forecast: Forecast, uiState: UiState) {
    val modifier = Modifier
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(lightBlue)
    ) {
        when (uiState) {
            UiState.Loading -> {
                Loader(
                    modifier = Modifier
                        .padding(top = 100.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            UiState.Error.ConnectionError -> {
                ErrorScreen(
                    message = stringResource(id = R.string.error_no_internet),
                    icon = painterResource(id = R.drawable.no_internet)
                )
            }

            UiState.Error.ServerError -> {
                ErrorScreen(
                    message = stringResource(id = R.string.error_server),
                    icon = painterResource(id = R.drawable.server_issue)
                )
            }

            UiState.Error.BadRequestError -> {
                ErrorScreen(
                    message = stringResource(id = R.string.error_bad_request),
                    icon = painterResource(id = R.drawable.bad_request)
                )
            }

            UiState.Error.TimeOut -> {
                ErrorScreen(
                    message = stringResource(id = R.string.error_timeout),
                    icon = painterResource(id = R.drawable.time_out)
                )
            }

            UiState.Error.UnknownError -> {
                ErrorScreen(
                    message = stringResource(id = R.string.error_unknown),
                    icon = painterResource(id = R.drawable.mysterious_error)
                )
            }

            else -> {
                Text(
                    modifier = Modifier
                        .padding(top = 50.dp)
                        .align(Alignment.CenterHorizontally),
                    text = forecast.place,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic
                )
                ForecastItem(
                    modifier = modifier.padding(top = 60.dp),
                    forecastItem = forecast.list[0]
                )
                ForecastItem(
                    modifier = modifier.padding(top = 70.dp),
                    forecastItem = forecast.list[1]
                )
                ForecastItem(
                    modifier = modifier.padding(top = 60.dp),
                    false,
                    forecastItem = forecast.list[2]
                )
            }
        }
    }
}

@Composable
fun ForecastItem(modifier: Modifier, visibility: Boolean = true, forecastItem: ForecastItem) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val (dayRef, forecastRef, iconRef, avgTextRef, avgRef) = createRefs()
        val itemModifier = Modifier

        Text(
            text = "${forecastItem.forecastDay.dayName}'s forecast: ",
            fontSize = 15.sp,
            color = Color.White,
            fontStyle = FontStyle.Italic,
            modifier = itemModifier
                .constrainAs(dayRef) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .padding(start = 10.dp, top = 10.dp)
        )

        Text(
            text = forecastItem.weatherText,
            fontSize = 12.sp,
            color = Color.White,
            modifier = itemModifier
                .padding(top = 10.dp)
                .constrainAs(forecastRef) {
                    centerHorizontallyTo(parent)
                    top.linkTo(dayRef.bottom)
                }
        )

        AsyncImage(
            model = forecastItem.weatherIcon,
            contentDescription = "forecast Icon",

            modifier = itemModifier
                .padding(start = 10.dp)
                .constrainAs(iconRef) {
                    start.linkTo(forecastRef.end)
                    top.linkTo(forecastRef.top)
                    bottom.linkTo(forecastRef.bottom)
                }
                .size(30.dp)
        )

        Text(
            text = "Average temperature:",
            fontSize = 12.sp,
            color = Color.White,
            modifier = itemModifier
                .padding(top = 20.dp)
                .constrainAs(avgTextRef) {
                    start.linkTo(forecastRef.start)
                    top.linkTo(forecastRef.bottom)
                }
        )

        Text(
            text = "${forecastItem.averageTemp} C",
            fontSize = 12.sp,
            color = Color.White,
            modifier = itemModifier
                .padding(start = 5.dp)
                .constrainAs(avgRef) {
                    start.linkTo(avgTextRef.end)
                    bottom.linkTo(avgTextRef.bottom)
                }
        )
    }
    if (visibility) {
        Divider(
            modifier
                .padding(start = 10.dp, top = 15.dp, end = 15.dp)
                .fillMaxWidth()
                .height(2.dp),
            color = Color.White
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun DetailScreenPreview() {
    Details(Forecast("Place", listOf()), UiState.Success)
}