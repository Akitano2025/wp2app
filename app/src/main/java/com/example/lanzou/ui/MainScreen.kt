// MainScreen.kt
package com.example.lanzou.ui
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import com.example.lanzou.network.WebParser
import com.example.lanzou.model.SoftwareItem
import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.app.model.SoftwareItem
import com.example.app.state.AppState
import com.example.app.viewmodel.MainViewModel
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass


@Composable
fun MainScreen() {
    var items by remember { mutableStateOf(emptyList<SoftwareItem>()) }
    var searchText by remember { mutableStateOf("") }
    
    LaunchedEffect(Unit) {
        items = WebParser().parseData("https://lanzoux.top")
    }

    Column(Modifier.padding(16.dp)) {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = { Text("搜索软件...") },
            modifier = Modifier.fillMaxWidth()
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items.filter { 
                it.name.contains(searchText, true) 
            }) { item ->
                SoftwareCard(item)
            }
        }
    }
}
