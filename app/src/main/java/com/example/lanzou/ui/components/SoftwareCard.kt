package com.example.lanzou.ui
import androidx.compose.runtime.Composable
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.platform.LocalContext
import android.content.Intent
import android.net.Ui
import coil.compose.AsyncImage  // 需要添加Coil依赖
@Composable
fun SoftwareCard(item: SoftwareItem) {
    val context = LocalContext.current
    
    Card(
        onClick = { 
            context.startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(item.downloadUrl))
        },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            AsyncImage(
                model = item.iconUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            
            Text(item.name, style = MaterialTheme.typography.titleMedium)
            Text(item.description, style = MaterialTheme.typography.bodySmall)
        }
    }
        }
