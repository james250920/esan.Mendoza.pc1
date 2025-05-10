package esan.mendoza.pc01.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import esan.mendoza.pc01.presentation.home.CalculadoraEdadCaninaScreen
import esan.mendoza.pc01.presentation.home.CatalogoProductosTecnológicoScreen
import esan.mendoza.pc01.presentation.home.ConversorDivisaScreen
import esan.mendoza.pc01.presentation.home.MenuPrincipalScreen

@Composable
fun NavigationMenu() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            DrawerScafold(navController) {
                MenuPrincipalScreen(navController)
            }
        }
        composable("calculadoraCanina") {
            DrawerScafold(navController) {
                CalculadoraEdadCaninaScreen()
            }
        }
        composable("conversion") {
            DrawerScafold(navController) {
                ConversorDivisaScreen()
            }
        }
        composable("catalogoProductos") {
            DrawerScafold(navController) {
                CatalogoProductosTecnológicoScreen()
            }
        }
    }
}