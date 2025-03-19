package com.example.lanzou
import androidx.activity.ComponentActivity
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.activity.compose.setContent
import com.example.lanzou.ui.MainScreen
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}
