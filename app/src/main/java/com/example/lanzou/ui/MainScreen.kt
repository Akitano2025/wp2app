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

    Column(Modifier.padding(16.dp)) {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = { Text("搜索软件...") },
            modifier = Modifier.fillMaxWidth()
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(300.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items.filter { 
                it.name.contains(searchText, true) 
            }.size) { index ->
                SoftwareCard(item = items[index])
            }
        }
    }
}