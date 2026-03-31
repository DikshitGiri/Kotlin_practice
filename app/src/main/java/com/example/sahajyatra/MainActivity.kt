package com.example.sahajyatra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.sahajyatra.ui.theme.SahajYatraTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SahajYatraTheme {
                NavigationApp()
            }
        }
    }
}

@Serializable
data object Home

@Serializable
data class ColorRoute(val name: String, val value: Long)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorScreen(navController: NavController, colorName: String, colorValue: Long) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Color Screen") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(ComposeColor(colorValue)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "$colorName SCREEN", fontSize = 24.sp, color = ComposeColor.White)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ColorBox(
                name = "RED",
                color = ComposeColor(0xFFFF0000),
                onClick = { navController.navigate(ColorRoute("RED", 0xFFFF0000)) }
            )
            Spacer(modifier = Modifier.height(24.dp))
            ColorBox(
                name = "GREEN",
                color = ComposeColor(0xFF00FF00),
                onClick = { navController.navigate(ColorRoute("GREEN", 0xFF00FF00)) }
            )
            Spacer(modifier = Modifier.height(24.dp))
            ColorBox(
                name = "BLUE",
                color = ComposeColor(0xFF0000FF),
                onClick = { navController.navigate(ColorRoute("BLUE", 0xFF0000FF)) }
            )
        }
    }
}

@Composable
fun ColorBox(name: String, color: ComposeColor, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(150.dp)
            .border(4.dp, color, shape = RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = name, color = color, fontSize = 24.sp)
    }
}

@Composable
fun NavigationApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(navController)
        }
        composable<ColorRoute> { navBackStackEntry ->
            val color = navBackStackEntry.toRoute<ColorRoute>()
            ColorScreen(navController, color.name, color.value)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationAppPreview() {
    SahajYatraTheme {
        NavigationApp()
    }
}
