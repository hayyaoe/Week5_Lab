package com.hayyaoe.guessthenumber.ui.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hayyaoe.guessthenumber.R
import com.hayyaoe.guessthenumber.model.GPAUiState
import com.hayyaoe.guessthenumber.viewmodel.GPACounterViewModel

@Composable
fun GPACounterView(
gpaCounterViewModel: GPACounterViewModel
) {
    val uiState by gpaCounterViewModel.uiState.collectAsState()

    LazyColumn {

        Log.d("CEK2","$uiState")

        item { InputBoxes(gpaCounterViewModel) }
        items(uiState){
            gpaUiState->
            ScoreCard(
                gpaUiState = gpaUiState,
                uiState = gpaCounterViewModel
            )
        }

    }
}

@Composable
fun InputBoxes(gpaCounterViewModel: GPACounterViewModel) {

    var sks by rememberSaveable { mutableStateOf("") }
    var score by rememberSaveable { mutableStateOf("") }
    var courseName by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(horizontal = 18.dp),
    ) {
        Text(
            text = "Courses",
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
        )
        Text(
            text = "Total SKS: ${gpaCounterViewModel.getSks()}",
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "IPK: ${gpaCounterViewModel.getGpa()}",
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextBox(
                value = sks,
                onValueChanged = { sks = it },
                placeHolder = "Input SKS",
                keyboardType = KeyboardType.Number,
                modifier = Modifier.width(180.dp)
            )

            TextBox(
                value = score,
                onValueChanged = { score = it },
                placeHolder = "Input Score",
                keyboardType = KeyboardType.Number
            )
        }

        Row(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextBox(
                value = courseName,
                onValueChanged = { courseName = it },
                placeHolder = "Course Name",
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )

            Button(
                onClick = {
                    gpaCounterViewModel.addScore(score = score, sks = sks, courseName = courseName)
                    score=""
                    sks=""
                    courseName=""
                }
            )
            {
                Text(
                    text = "+",
                    fontSize = 32.sp
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextBox(
    value: String,
    onValueChanged: (String) -> Unit,
    placeHolder: String,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Next
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        placeholder = { Text(text = placeHolder) },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType,
            imeAction = imeAction
        )
    )
}

@Composable
fun ScoreCard(gpaUiState: GPAUiState, uiState : GPACounterViewModel) {
    Card (
        modifier = Modifier.padding(horizontal = 18.dp , vertical = 4.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier =Modifier.weight(1f),
            ) {
                Text(
                    text = "Name: ${gpaUiState.courseName}",
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "SKS: ${gpaUiState.sks}"
                )
                Text(
                    text = "Score: ${gpaUiState.score}"
                )
            }

            Image(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "Delete",
                modifier = Modifier.clickable {  uiState.removeScore(gpaUiState)}
            )
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GPACounterPreview() {
    GPACounterView(GPACounterViewModel())
//    ScoreCard()
}