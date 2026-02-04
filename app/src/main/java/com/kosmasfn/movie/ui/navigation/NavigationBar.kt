package com.kosmasfn.movie.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kosmasfn.movie.ui.genre.GenreListScreen

@Composable
fun NavigationBar(
    page: String = "Genre"
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = page,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            composable(Screens.Genre.route) {
                GenreListScreen { genre ->
                    navController.navigate("movies/${genre.id}/${genre.name}")
                }
            }
        }
    }
}