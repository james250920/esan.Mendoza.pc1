package esan.mendoza.pc01.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerScafold(navController: NavController, content: @Composable () -> Unit) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Menu principal")
                HorizontalDivider()

                // Home navigation
                NavigationDrawerItem(
                    label = { Text(text = "Home") },
                    selected = false,
                    onClick = {
                        navController.navigate("home")
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "calculadora canina") },
                    selected = false,
                    onClick = {
                        navController.navigate("calculadoraCanina")
                        scope.launch { drawerState.close() }
                    }
                )

                // Permissions navigation
                NavigationDrawerItem(
                    label = { Text(text = "catalogo de productos") },
                    selected = false,
                    onClick = {
                        navController.navigate("catalogoProductos") {
                            popUpTo("permissions") { inclusive = true }
                        }
                        scope.launch { drawerState.close() }
                    }
                )

                // Favorites navigation
                NavigationDrawerItem(
                    label = { Text(text = "conversion divisas") },
                    selected = false,
                    onClick = {
                        navController.navigate("conversion")
                        scope.launch { drawerState.close() }
                    }
                )




            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "PC01 navigation") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            },
            content = { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    content()
                }
            }
        )
    }
}