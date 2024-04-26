package com.crypto.application.newtransaction.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.crypto.application.R
import com.crypto.application.app.constants.MAX_SYMBOLS_INPUT
import com.crypto.application.app.ui.theme.Black
import com.crypto.application.app.ui.theme.Purple
import com.crypto.application.app.ui.theme.Turquoise
import com.crypto.application.newtransaction.ui.model.TransactionType
import com.crypto.application.newtransaction.ui.model.TransactionUiModel
import com.crypto.application.utills.debounced
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun NewTransactionContent(
    addNewTransaction: (TransactionUiModel) -> Unit = {},
    backPress: () -> Unit = {}
) {
    var transactionAmount by remember { mutableStateOf("") }
    val categoriesOptions = listOf(
        stringResource(R.string.groceries),
        stringResource(R.string.taxi),
        stringResource(R.string.electronics),
        stringResource(R.string.restaurant),
        stringResource(R.string.other)
    )
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(categoriesOptions[0]) }
    val expression = Regex("\\d*[.]?\\d*")

    Column(
        Modifier
            .fillMaxSize()
            .background(Black)
            .padding(16.dp)
    ) {
        Button(
            onClick = {
                debounced {
                    backPress()
                }
            },
            modifier = Modifier
                .width(50.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(Purple),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(6.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                modifier = Modifier.fillMaxSize(),
                contentDescription = "contentDescription",
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
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
                cursorColor = Turquoise,
                disabledIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle.Default.copy(fontSize = 16.sp),
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            categoriesOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = {
                                onOptionSelected(text)
                            }
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Purple
                        ),
                        onClick = { onOptionSelected(text) }
                    )
                    Text(
                        text = text,
                        modifier = Modifier.padding(start = 16.dp),
                        color = Purple,
                        fontSize = 18.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                addNewTransaction(
                    TransactionUiModel(
                        time = Calendar.getInstance(),
                        amount = transactionAmount.toFloat(),
                        category = selectedOption,
                        type = TransactionType.WITHDRAWAL
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(Purple),
            enabled = transactionAmount.isNotEmpty()
        ) {
            Text(
                color = Turquoise,
                text = stringResource(id = R.string.add_transaction),
                fontSize = 18.sp
            )
        }
    }
}
