package com.kosmasfn.movie.ui.component

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kosmasfn.movie.BuildConfig
import com.kosmasfn.movie.ui.detail.DetailActivity
import com.kosmasfn.movie.ui.model.MovieUIModel

@Composable
fun ItemMovieCard(
    context: Context,
    item: MovieUIModel.MovieItemUIModel
) {
    ElevatedCard(
        modifier = Modifier
            .padding(4.dp)
            .testTag("movie_card_${item.id}"),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(4.dp),
        onClick = {
            DetailActivity.launchIntent(context, item)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ImageCustomView(
                context,
                Modifier.fillMaxWidth(),
                BuildConfig.POSTER_BASE_URL + item.posterPath
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 10.dp, end = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(0.06f))
                Text(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    text = item.overview,
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}