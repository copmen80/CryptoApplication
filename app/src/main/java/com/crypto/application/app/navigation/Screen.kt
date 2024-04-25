package com.crypto.application.app.navigation

enum class Screen {
    DASHBOARD,
    NEW_TRANSACTION,
}

sealed class NavigationItem(val route: String) {
    object Dashboard : NavigationItem(Screen.DASHBOARD.name)
    object NewTransaction : NavigationItem(Screen.NEW_TRANSACTION.name)
}
