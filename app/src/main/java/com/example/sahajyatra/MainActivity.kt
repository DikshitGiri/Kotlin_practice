package com.example.sahajyatra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sahajyatra.ui.theme.SahajYatraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SahajYatraTheme {
                MainApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val tabNavigationItems = listOf(
        TabNavigation.TopStories,
        TabNavigation.UKNews,
        TabNavigation.Politics,
        TabNavigation.Business,
        TabNavigation.WorldNews,
        TabNavigation.Sport,
        TabNavigation.Other
    )

    var tabIndex by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Text(stringResource(R.string.app_name))
                    },
                    modifier = Modifier.statusBarsPadding(),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                    )
                )
                PrimaryScrollableTabRow(
                    selectedTabIndex = tabIndex,
                    edgePadding = 0.dp
                ) {
                    tabNavigationItems.forEachIndexed { index, item ->
                        val isSelected = currentDestination?.hasRoute(item.route::class) == true
                        if (isSelected) tabIndex = index
                        Tab(
                            selected = isSelected,
                            text = { Text(item.label) },
                            onClick = {
                                if (!isSelected) {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Destination.TopStories,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable<Destination.TopStories> { ContentScreen(Destination.TopStories.label) }
            composable<Destination.UKNews> { ContentScreen(Destination.UKNews.label) }
            composable<Destination.Politics> { ContentScreen(Destination.Politics.label) }
            composable<Destination.Business> { ContentScreen(Destination.Business.label) }
            composable<Destination.WorldNews> { ContentScreen(Destination.WorldNews.label) }
            composable<Destination.Sport> { ContentScreen(Destination.Sport.label) }
            composable<Destination.Other> { ContentScreen(Destination.Other.label) }
        }
    }
}
