package com.example.sahajyatra
import androidx.compose.runtime.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sahajyatra.ui.theme.SahajYatraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SahajYatraTheme {

            }
        }
    }
}

@Composable
fun CounterApp() {

    var count by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Count: $count")

        Button(onClick = {
            count++   // increase count
        }) {
            Text("Click Me")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterPreview() {
    SahajYatraTheme {
      CounterApp()
    }
}
//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
/*new push*/