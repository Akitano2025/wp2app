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
        it.name.contains(searchText, true) 
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("搜索软件") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 180.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = filteredItems,
                key = { it.id } // 假设SoftwareItem有唯一标识符
            ) { item ->
                SoftwareCard(
                    item = item,
                    modifier = Modifier.padding(4.dp)
            }
        }
    }
}

