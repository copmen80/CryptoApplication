package com.crypto.application.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.crypto.application.dashboard.ui.DashboardScreen
import com.crypto.application.newtransaction.ui.NewTransactionScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Dashboard.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Dashboard.route) {
            DashboardScreen(navController)
        }
        composable(NavigationItem.NewTransaction.route) {
            NewTransactionScreen(navController)
        }
    }
}