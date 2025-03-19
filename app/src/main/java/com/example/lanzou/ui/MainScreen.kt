package com.example.lanzou.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lanzou.model.SoftwareItem
import com.example.lanzou.network.WebParser
import com.example.lanzou.ui.components.SoftwareCard

@Composable
fun MainScreen() {
    var items by remember { mutableStateOf(emptyList<SoftwareItem>()) }
    var searchText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        items = WebParser().parseData("https://lanzoux.top")
    }

    val filteredItems = items.filter {
        it.name.contains(searchText, ignoreCase = true) ||
        it.description.contains(searchText, ignoreCase = true)
    }

    Column(Modifier.padding(16.dp)) {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = { Text("搜索软件...") },
            modifier = Modifier.fillMaxWidth()
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(300.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                items = filteredItems,
                key = { it.downloadUrl } // 使用唯一标识作为key
            ) { item ->
                SoftwareCard(
                    item = item,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}