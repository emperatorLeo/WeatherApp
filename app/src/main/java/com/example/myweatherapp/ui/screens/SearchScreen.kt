package com.example.myweatherapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myweatherapp.R
import com.example.myweatherapp.model.Location
import com.example.myweatherapp.navigation.Screen
import com.example.myweatherapp.ui.listitem.LocationItemList
import com.example.myweatherapp.ui.states.ErrorScreen
import com.example.myweatherapp.ui.states.UiState
import com.example.myweatherapp.ui.theme.lightBlue
import com.example.myweatherapp.ui.viewmodel.MainViewModel
import com.example.myweatherapp.ui.views.Loader

@Composable
fun SearchBarScreen(viewModel: MainViewModel, navController: NavController) {
    val locationList: List<Location> by viewModel
        .locationList
        .observeAsState(listOf())

    val uiState: UiState? by viewModel
        .uiState
        .observeAsState(null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightBlue)
    ) {
        SearchBar(autoSearch = { viewModel.searchLocation(it) }, viewModel)
        Divider(color = Color.Black)
        when (uiState) {
            UiState.Loading -> {
                Loader(
                    modifier = Modifier
                        .padding(top = 100.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            UiState.Error.EmptySearch -> {
                ErrorScreen(
                    message = stringResource(id = R.string.error_empty_search),
                    icon = painterResource(id = R.drawable.nothing_to_search)
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

            UiState.Error.EmptyResultError -> {
                ErrorScreen(
                    message = stringResource(id = R.string.error_empty_result),
                    icon = painterResource(
                        id = R.drawable.empty_result
                    )
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                ) {
                    items(locationList) { location ->
                        LocationItemList(location = location) { lat, long ->
                            viewModel.getForecast(lat, long)
                            navController.navigate(Screen.Detail.route)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(autoSearch: (String) -> Unit, viewModel: MainViewModel) {
    val modifier = Modifier.fillMaxWidth()
    var text by remember {
        mutableStateOf("")
    }

    Row(
        modifier = modifier
            .background(Color.DarkGray),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier
                .padding(start = 10.dp)
                .background(Color.DarkGray),
            value = text,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                containerColor = Color.DarkGray
            ),
            label = { Text(text = "Enter a city", color = Color.Gray) },
            onValueChange = {
                text = it

                if (it.length > 2) {
                    autoSearch.invoke(text)
                }
            }
        )
        IconButton(
            onClick = {
                viewModel.searchLocation(text)
            },
            modifier = Modifier
                .padding(start = 20.dp)
                .size(width = 70.dp, height = 30.dp)
                .background(Color.LightGray, shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Button",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}