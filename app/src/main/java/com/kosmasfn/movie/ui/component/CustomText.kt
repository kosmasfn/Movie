package com.kosmasfn.movie.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomText(text: String) {
    Text(
        modifier = Modifier.padding(start = 4.dp),
        text = text,
        color = Color.Gray
    )
}