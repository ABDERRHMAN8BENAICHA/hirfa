package com.example.hirfa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hirfa.presentation.view.AddCraftsmanScreen
import com.example.hirfa.ui.theme.HirfaTheme
import com.example.hirfa.presentation.view.MainScreen
import com.example.hirfa.presentation.viewmodel.CategoryViewModel
import com.example.hirfa.presentation.viewmodel.CraftsmanViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

//
//
//@AndroidEntryPoint
//class MainActivity : ComponentActivity() {
//
//    data class BottomNavBarItems(
//        val title: String,
//        val selectedIcon : ImageVector,
//        val unselectedIcon : ImageVector,
//    )
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            HirfaTheme {
//                val navbar_items = listOf(
//                    BottomNavBarItems(
//                        title = "Home",
//                        selectedIcon = Icons.Filled.Home,
//                        unselectedIcon = Icons.Outlined.Home,
//
//                        ),
//                    BottomNavBarItems(
//                        title = "Map",
//                        selectedIcon = Icons.Filled.LocationOn,
//                        unselectedIcon = Icons.Outlined.LocationOn,
//
//                        ),
//                    BottomNavBarItems(
//                        title = "Profile",
//                        selectedIcon = Icons.Filled.Person,
//                        unselectedIcon = Icons.Outlined.Person,
//
//                        ),
//                )
//                var itemindex by remember { mutableStateOf(0) }
//                Scaffold(modifier = Modifier.fillMaxSize(),
//                    bottomBar = {
//                        NavigationBar {
//                            navbar_items.forEachIndexed { index, item ->
//
//                                NavigationBarItem(
//
//                                    selected = itemindex == index,
//                                    onClick = {itemindex = index
//                                        //navControler.navigate(item.title)
//                                    },
//                                    label = { Text(item.title) },
//                                    alwaysShowLabel = false,
//                                    icon = {
//                                        BadgedBox(
//                                            badge = {  }
//                                        ) {
//                                            Icon(
//                                                imageVector = if (itemindex == index) item.selectedIcon else item.unselectedIcon,
//                                                contentDescription = item.title
//
//                                            )
//                                        }
//
//
//                                    }
//
//                                )
//                            }
//                        }
//                    }
//                ) { innerPadding ->
//                    val categoryViewModel = hiltViewModel<CategoryViewModel>()
//                    val craftsmanViewModel = hiltViewModel<CraftsmanViewModel>()
//                    MainScreen(
//                        categoryViewModel,
//                        craftsmanViewModel,
//                        modifier = Modifier.padding(innerPadding)
//
//                    )
//                }
//            }
//        }
//    }
//}


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HirfaTheme {
                val navController = rememberNavController()
                MainScreenWithNavigation(navController)
//                AddCraftsmanScreen()
            }
        }
    }
}
//
//@Composable
//fun MainScreenWithNavigation(navController: NavHostController) {
//    val items = listOf(
//        BottomNavBarItem("home", "Home", Icons.Filled.Home, Icons.Outlined.Home),
//        BottomNavBarItem("map", "Map", Icons.Filled.LocationOn, Icons.Outlined.LocationOn),
//        BottomNavBarItem("profile", "Profile", Icons.Filled.Person, Icons.Outlined.Person),
//    )
//
//    var selectedIndex by remember { mutableStateOf(0) }
//
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//        bottomBar = {
//            NavigationBar {
//                items.forEachIndexed { index, item ->
//                    NavigationBarItem(
//                        selected = selectedIndex == index,
//                        onClick = {
//                            selectedIndex = index
//                            navController.navigate(item.route) {
//                                popUpTo(navController.graph.startDestinationId) { saveState = true }
//                                launchSingleTop = true
//                                restoreState = true
//                            }
//                        },
//                        label = { Text(item.title) },
//                        alwaysShowLabel = false,
//                        icon = {
//                            Icon(
//                                imageVector = if (selectedIndex == index) item.selectedIcon else item.unselectedIcon,
//                                contentDescription = item.title
//                            )
//                        }
//                    )
//                }
//            }
//        }
//    ) { innerPadding ->
//        val categoryViewModel = hiltViewModel<CategoryViewModel>()
//        val craftsmanViewModel = hiltViewModel<CraftsmanViewModel>()
//
//        NavHost(
//            navController = navController,
//            startDestination = "home",
//            modifier = Modifier.padding(innerPadding)
//        ) {
//            composable("home") {
//                MainScreen(categoryViewModel, craftsmanViewModel)
//            }
//            composable("map") {
////                MapScreen()
//            }
//            composable("profile") {
////                ProfileScreen()
//            }
//            composable("add_craftsman") {
//                AddCraftsmanScreen()
//            }
//        }
//    }
//}
//

@Composable
fun MainScreenWithNavigation(navController: NavHostController) {
    val items = listOf(
        BottomNavBarItem("home", "Home", Icons.Filled.Home, Icons.Outlined.Home),
        BottomNavBarItem("map", "Map", Icons.Filled.LocationOn, Icons.Outlined.LocationOn),
        BottomNavBarItem("add_craftsman", "Add Craftsman", Icons.Filled.Add, Icons.Filled.Add),
        BottomNavBarItem("profile", "Profile", Icons.Filled.Person, Icons.Outlined.Person),
    )

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = { Text(item.title) },
                        alwaysShowLabel = true,
                        icon = {
                            Icon(
                                imageVector = if (selectedIndex == index) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        val categoryViewModel = hiltViewModel<CategoryViewModel>()
        val craftsmanViewModel = hiltViewModel<CraftsmanViewModel>()

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                MainScreen(categoryViewModel, craftsmanViewModel,navController)
            }
//            composable("map") {
////                MapScreen()
//            }
//            composable("profile") {
////                ProfileScreen()
//            }
            composable("add_craftsman") {
                AddCraftsmanScreen()
            }
        }
    }
}

data class BottomNavBarItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
