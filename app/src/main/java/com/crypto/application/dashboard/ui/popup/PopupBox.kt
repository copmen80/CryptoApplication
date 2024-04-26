package com.crypto.application.dashboard.ui.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.crypto.application.R
import com.crypto.application.app.constants.MAX_SYMBOLS_INPUT
import com.crypto.application.app.ui.theme.Purple
import com.crypto.application.app.ui.theme.Turquoise
import com.crypto.application.newtransaction.ui.model.TransactionType
import com.crypto.application.newtransaction.ui.model.TransactionUiModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionDialog(
    onDismissRequest: () -> Unit = {},
    addNewTransaction: (TransactionUiModel) -> Unit = {}
) {
    var transactionAmount by remember { mutableStateOf("") }
    val expression = Regex("\\d*[.]?\\d*")


    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        ),
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Purple)
                .fillMaxWidth(0.7f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.add_balance),
                color = Turquoise,
                fontSize = 24.sp
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                value = transactionAmount,
                onValueChange = {
                    if (it.length <= MAX_SYMBOLS_INPUT && it.matches(expression)) {
                        transactionAmount = it
                    }
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.enter_the_amount),
                        color = Turquoise,
                        fontSize = 16.sp
                    )
                },
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Purple,
                    unfocusedIndicatorColor = Purple,
                    focusedLabelColor = Purple,
                    cursorColor = Purple,
                    disabledIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle.Default.copy(fontSize = 16.sp),
                singleLine = true,
            )
            Divider()
            Row {
                Box(
                    modifier = Modifier
                        .clickable { onDismissRequest() }
                        .weight(1f),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(R.string.cancel),
                        modifier = Modifier.padding(
                            vertical = 12.dp,
                            horizontal = 24.dp,
                        ),
                        color = Turquoise,
                    )
                }
                Divider(
                    modifier = Modifier
                        .width(1.dp)
                        .height(44.dp)
                )
                Box(
                    modifier = Modifier
                        .clickable(enabled = transactionAmount.isNotEmpty()) {
                            addNewTransaction(
                                TransactionUiModel(
                                    time = Calendar.getInstance(),
                                    amount = transactionAmount.toFloat(),
                                    type = TransactionType.DEPOSIT
                                )
                            )
                        }
                        .weight(1f),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(id = R.string.add),
                        modifier = Modifier.padding(
                            vertical = 12.dp,
                            horizontal = 24.dp,
                        ),
                        fontWeight = FontWeight.Bold,
                        color = Turquoise,
                    )
                }
            }
        }
    }
}
