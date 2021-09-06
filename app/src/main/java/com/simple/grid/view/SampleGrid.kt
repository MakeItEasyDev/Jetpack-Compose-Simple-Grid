package com.simple.grid.view

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.simple.grid.R
import com.simple.grid.response.SampleData
import com.simple.grid.ui.theme.Purple500

@ExperimentalFoundationApi
@Composable
fun SampleGrid(navController: NavController) {
    val context = LocalContext.current
    val dataFileString = getJsonDataFromAsset(context, "SampleData.json")
    val gson = Gson()
    val gridSampleType = object : TypeToken<List<SampleData>>() {}.type
    var sampleData: List<SampleData> = gson.fromJson(dataFileString, gridSampleType)

    Column(
       modifier = Modifier
           .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Purple500),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Make It Easy Grid",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            modifier = Modifier
                .padding(10.dp)
        ) {
            items(sampleData) { data ->
                SampleDataGridItem(data, navController)
            }
        }
    }
}

@Composable
fun SampleDataGridItem(data: SampleData, navController: NavController) {
    Card(
        modifier = Modifier
            .clickable {
                val itemVal = Gson().toJson(data)
                navController.navigate("sample_grid_detail/$itemVal")
            }
            .padding(10.dp)
            .fillMaxSize(),
        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Image(
                painterResource(R.drawable.mie_img),
                contentDescription = "Grid Image",
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(5.dp))
            )

            Spacer(modifier = Modifier.padding(3.dp))

            Text(
                text = data.name,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                text = data.desc,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

fun getJsonDataFromAsset(context: Context, data: String): String {
    return context.assets.open(data).bufferedReader().use { it.readText() }
}













