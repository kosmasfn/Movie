package com.kosmasfn.movie.ui.navigation

sealed class Screens(val route: String) {
    data object Genre : Screens("genre")
    data object DiscoverMovies : Screens("discover")
}