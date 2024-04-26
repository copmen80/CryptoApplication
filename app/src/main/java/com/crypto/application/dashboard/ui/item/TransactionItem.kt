package com.crypto.application.dashboard.ui.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.crypto.application.app.ui.theme.Purple
import com.crypto.application.app.ui.theme.Turquoise
import com.crypto.application.newtransaction.ui.model.TransactionType
import com.crypto.application.newtransaction.ui.model.TransactionUiModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TransactionItem(transactionUiModel: TransactionUiModel) {
    val dateFormat = SimpleDateFormat("EEEE MMMM dd HH:mm", Locale.getDefault())

    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Purple)
            .padding(12.dp)
    ) {
        Column {
            Text(text = transactionUiModel.type.name, color = Turquoise, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = transactionUiModel.category ?: "", color = Turquoise, fontSize = 14.sp)
        }
        Spacer(Modifier.weight(1f))
        Column(horizontalAlignment = Alignment.End) {
            when (transactionUiModel.type) {
                TransactionType.DEPOSIT -> Text(
                    text = "+₿${transactionUiModel.amount}",
                    color = Turquoise,
                    fontSize = 20.sp
                )

                TransactionType.WITHDRAWAL -> Text(
                    text = "-₿${transactionUiModel.amount}",
                    color = Turquoise,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = dateFormat.format(transactionUiModel.time.time),
                color = Turquoise,
                fontSize = 10.sp
            )
        }
    }
}
