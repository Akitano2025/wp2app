// MainScreen.kt
package com.example.lanzou.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import com.example.lanzou.model.SoftwareItem
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
