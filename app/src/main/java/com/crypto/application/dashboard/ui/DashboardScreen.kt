package com.crypto.application.dashboard.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.crypto.application.app.navigation.NavigationItem
import com.crypto.application.dashboard.ui.popup.ActionDialog

@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: DashboardViewModel = hiltViewModel(),
) {
    var showPopup by remember { mutableStateOf(false) }

    val balance by viewModel.balance.collectAsState()
    val currency by viewModel.currency.collectAsState()

    DashboardContent(
        transactionsList = viewModel.transactionsData,
        currency = currency,
        addBalance = { showPopup = !showPopup },
        addTransaction = { navController.navigate(NavigationItem.NewTransaction.route) },
        balance = balance,

        )
    if (showPopup) {
        ActionDialog(
            onDismissRequest = { showPopup = !showPopup },
            addNewTransaction = {
                viewModel.addNewDeposit(it)
                showPopup = !showPopup
            })
    }
}
