package com.example.myweatherapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myweatherapp.model.Location
import com.example.myweatherapp.ui.listitem.LocationItemList
import com.example.myweatherapp.ui.viewmodel.MainViewModel

@Composable
fun SearchBarScreen(viewModel: MainViewModel) {
    val locationList: List<Location> by viewModel
        .locationList
        .observeAsState(listOf())

    Column {
        SearchBar(autoSearch = { }) {
        }
        Divider(color = Color.Black)

        LazyColumn(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        ) {
            items(locationList) { location ->
                LocationItemList(location = location) {
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(autoSearch: () -> Unit, onSearchClick: () -> Unit) {
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
                    autoSearch.invoke()
                }
            }
        )
        IconButton(
            onClick = onSearchClick,
            modifier = Modifier
                .padding(start = 20.dp)
                .size(width = 70.dp, height = 30.dp)
                .background(Color.LightGray, shape = CircleShape)
                .clickable { onSearchClick.invoke() }
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Button",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
@Preview
private fun SearchBarPreview() {
    SearchBar(autoSearch = {}, onSearchClick = {})
}