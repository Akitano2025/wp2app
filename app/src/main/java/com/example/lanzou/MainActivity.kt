import androidx.activity.ComponentActivity
import android.os.Bundle
import androidx.annotation.CallSuper
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}
