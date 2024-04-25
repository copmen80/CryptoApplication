package com.crypto.application.dashboard.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.crypto.application.R
import com.crypto.application.app.ui.theme.Black
import com.crypto.application.app.ui.theme.Purple
import com.crypto.application.app.ui.theme.Turquoise
import com.crypto.application.dashboard.ui.item.TransactionItem
import com.crypto.application.newtransaction.ui.model.DashboardItemUiModel
import com.crypto.application.newtransaction.ui.model.TransactionDateUiModel
import com.crypto.application.newtransaction.ui.model.TransactionUiModel
import kotlinx.coroutines.flow.Flow

@Composable
fun DashboardContent(
    transactionsList: Flow<PagingData<DashboardItemUiModel>>,
    currency: String,
    addBalance: () -> Unit,
    addTransaction: () -> Unit,
    balance: Float
) {

    val transactionsPagingItems: LazyPagingItems<DashboardItemUiModel> =
        transactionsList.collectAsLazyPagingItems()

    Column(
        Modifier
            .fillMaxSize()
            .background(Black)
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .background(Purple, RoundedCornerShape(12.dp))
                    .border(BorderStroke(1.dp, Turquoise), RoundedCornerShape(12.dp))
            ) {
                Text(
                    text = "Balance: $balance",
                    modifier = Modifier.padding(8.dp),
                    color = Turquoise,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_add_balance),
                contentDescription = "contentDescription",
                modifier = Modifier.clickable { addBalance() }
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .background(Purple, RoundedCornerShape(12.dp))
                    .border(BorderStroke(1.dp, Turquoise), RoundedCornerShape(12.dp))
            ) {
                Text(
                    text = "Currency: $currency",
                    modifier = Modifier.padding(8.dp),
                    color = Turquoise,
                    fontSize = 16.sp
                )
            }

        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { addTransaction() },
            colors = ButtonDefaults.buttonColors(Purple),
        ) {
            Text(
                color = Turquoise,
                text = stringResource(R.string.add_transaction),
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(bottom = 74.dp),
        ) {

            items(transactionsPagingItems.itemCount) {
                when (val dashboardUiModel = transactionsPagingItems[it]) {
                    is TransactionDateUiModel -> Text(text = dashboardUiModel.time?.time.toString())
                    is TransactionUiModel -> TransactionItem(dashboardUiModel)
                    null -> Unit
                }

            }
        }
    }
}
