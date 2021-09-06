package com.simple.grid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.simple.grid.response.SampleData
import com.simple.grid.ui.theme.SimpleGridTheme
import com.simple.grid.view.SampleDataDetails
import com.simple.grid.view.SampleGrid

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleGridTheme {
                NavigatePage()
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun NavigatePage() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = "sample_data"
    ) {
        composable("sample_data") {
            SampleGrid(navHostController)
        }
        composable("sample_grid_detail/{item}",
            arguments = listOf(
                navArgument("item") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            navBackStackEntry?.arguments?.getString("item")?.let { json ->
                val item = Gson().fromJson(json, SampleData::class.java)
                SampleDataDetails(data = item)
            }
        }
    }
}









