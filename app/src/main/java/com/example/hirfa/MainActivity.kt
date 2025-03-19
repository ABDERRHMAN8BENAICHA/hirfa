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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hirfa.ui.theme.HirfaTheme
import com.example.hirfa.view.MainScreen
import com.example.hirfa.viewmodel.CategoryViewModel
import com.example.hirfa.viewmodel.CraftsmanViewModel

class MainActivity : ComponentActivity() {

    data class BottomNavBarItems(
        val title: String,
        val selectedIcon : ImageVector,
        val unselectedIcon : ImageVector,
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HirfaTheme {
                val navbar_items = listOf(
                    BottomNavBarItems(
                        title = "Home",
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,

                        ),
                    BottomNavBarItems(
                        title = "Map",
                        selectedIcon = Icons.Filled.LocationOn,
                        unselectedIcon = Icons.Outlined.LocationOn,

                        ),
                    BottomNavBarItems(
                        title = "Profile",
                        selectedIcon = Icons.Filled.Person,
                        unselectedIcon = Icons.Outlined.Person,

                        ),
                )
                var itemindex by remember { mutableStateOf(0) }
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            navbar_items.forEachIndexed { index, item ->

                                NavigationBarItem(

                                    selected = itemindex == index,
                                    onClick = {itemindex = index
                                        //navControler.navigate(item.title)
                                    },
                                    label = { Text(item.title) },
                                    alwaysShowLabel = false,
                                    icon = {
                                        BadgedBox(
                                            badge = {  }
                                        ) {
                                            Icon(
                                                imageVector = if (itemindex == index) item.selectedIcon else item.unselectedIcon,
                                                contentDescription = item.title

                                            )
                                        }


                                    }

                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    val categoryViewModel = viewModel<CategoryViewModel>()
                    val craftsmanViewModel = viewModel<CraftsmanViewModel>()
                    MainScreen(
                        categoryViewModel,
                        craftsmanViewModel,
                        modifier = Modifier.padding(innerPadding)

                    )
                }
            }
        }
    }
}

