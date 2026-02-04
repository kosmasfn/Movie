package com.kosmasfn.movie.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OverviewItemView(overview: String) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = RectangleShape,
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = "Overview", fontSize = 26.sp)
            Text(text = overview)
        }
    }
}