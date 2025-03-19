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
