package com.example.myweatherapp.ui.listitem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweatherapp.model.Location

@Composable
fun LocationItemList(location: Location, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick.invoke() }
    ) {
        Text(
            modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
            text = location.name,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Text(
            modifier = Modifier
                .padding(bottom = 5.dp)
                .align(Alignment.CenterHorizontally),
            text = location.country,
            color = Color.Gray,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp
        )

        Divider(color = Color.Black, modifier = Modifier.padding(bottom = 10.dp))
    }
}

@Composable
@Preview
private fun LocationItemListPreview() {
    LocationItemList(location = Location("name", "country", 10.00, 26.30)) {}
}