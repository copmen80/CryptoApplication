package com.crypto.application.newtransaction.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController

@Composable
fun NewTransactionScreen(
    navController: NavController,
    viewModel: NewTransactionViewModel = hiltViewModel()
) {
    NewTransactionContent(
        addNewTransaction = {
            viewModel.addNewTransaction(it)
        },
        backPress = { navController.popBackStack() }
    )

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        viewModel.eventsFlow
            .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .collect { event ->
                when (event) {
                    NewTransactionAction.NotEnoughBalance -> {
                        Toast.makeText(context, "Not enough balance", Toast.LENGTH_SHORT)
                            .show()
                    }

                    NewTransactionAction.Success -> {
                        navController.popBackStack()
                    }
                }
            }
    }
}
