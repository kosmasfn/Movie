package com.kosmasfn.movie.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.kosmasfn.movie.ui.model.MovieUIModel
import com.kosmasfn.movie.ui.theme.MovieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MovieTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopBar { finish() }
                    }) { innerPadding ->
                    DetailContent(
                        innerPadding,
                        getData()
                    )
                }
            }
        }
    }

    private fun getData(): MovieUIModel.MovieItemUIModel =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(
                MOVIE_ITEM_BUNDLE,
                MovieUIModel.MovieItemUIModel::class.java
            ) as MovieUIModel.MovieItemUIModel
        } else {
            intent.getSerializableExtra(MOVIE_ITEM_BUNDLE) as MovieUIModel.MovieItemUIModel
        }

    companion object {
        const val MOVIE_ITEM_BUNDLE = "MOVIE_ITEM_BUNDLE"
        fun launchIntent(
            context: Context,
            movie: MovieUIModel.MovieItemUIModel
        ) {
            val intent = Intent(context, DetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable(MOVIE_ITEM_BUNDLE, movie)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }
}