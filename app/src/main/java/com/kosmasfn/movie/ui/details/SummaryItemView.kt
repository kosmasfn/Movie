package com.kosmasfn.movie.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kosmasfn.movie.R
import com.kosmasfn.movie.ui.component.CustomText
import com.kosmasfn.movie.utils.formatDate

@Composable
fun SummaryItemView(voteAverage: Double, voteCount: Int, releaseDate: String, originalLanguage: String) {
    Row(
        modifier = Modifier.padding(top = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(20.dp),
            imageVector = Icons.Filled.Star,
            contentDescription = "Icon Favorite",
        )
        CustomText("$voteAverage/10")
    }
    Row(
        modifier = Modifier.padding(top = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = R.drawable.ic_group),
            contentDescription = "Icon User Group",
        )
        CustomText("$voteCount votes")
    }
    Row(
        modifier = Modifier.padding(top = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(20.dp),
            painter = painterResource(R.drawable.ic_calendar),
            contentDescription = "Icon Calender",
        )
        CustomText(releaseDate.formatDate())
    }
    Row(
        modifier = Modifier.padding(top = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(R.drawable.ic_world),
            contentDescription = "Icon World",
        )
        CustomText(originalLanguage)
    }
}