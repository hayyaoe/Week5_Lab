package com.hayyaoe.guessthenumber.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hayyaoe.guessthenumber.viewmodel.GuessTheNumberViewModel
import kotlin.system.exitProcess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessTheNumberView(
    guessTheNumberViewModel: GuessTheNumberViewModel  = viewModel()
) {
    val guessTheNumberUIState by guessTheNumberViewModel.uiState.collectAsState()
    var guess by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Text(
            text = "Guess The Number",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp),
            color = Color.Black
        )

        Card(
            modifier = Modifier.padding(horizontal = 50.dp),
            colors = CardDefaults.cardColors(Color(0xfff1f3f4)),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                Box(
                    modifier = Modifier
                        .background(color = Color.DarkGray, shape = RoundedCornerShape(50))
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Number of Guesses: ${guessTheNumberUIState.tries}",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        modifier = Modifier
                    )
                }
            }


            Text(
                text = "${guessTheNumberUIState.theNumber}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Black
            )

            Text(
                text = "Number Between 1 to 10",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Score: ${guessTheNumberUIState.score}",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            OutlinedTextField(
                value = guess,
                onValueChange = { guess = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 14.dp),
                maxLines = 1,
                shape = RoundedCornerShape(24),
                placeholder = { Text(text = "Input Number") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
        }

        Button(
            onClick = {
                guessTheNumberViewModel.checkTry(guess)
                guess = ""
            },
            modifier = Modifier.padding(top = 12.dp),
        ) {
            Text(
                text = "Submit",
                fontSize = 18.sp
            )
        }


        if (guessTheNumberUIState.isWon) {
            Dialog(
                onDismissRequest = {
                    guessTheNumberViewModel.reset()
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(Color.White, shape = RoundedCornerShape(20.dp))
                        .fillMaxWidth(),


                    ) {
                    Text(
                        text = "Nice Try",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(20.dp)
                    )

                    Text(
                        text = "You Scored : ${guessTheNumberUIState.score}",
                        modifier = Modifier.padding(start = 24.dp)
                    )
                    Row (
                        modifier = Modifier.padding(start = 12.dp)
                    ){
                        Button(
                            onClick = {
                                      exitProcess(0)
                            },
                            modifier = Modifier
                                .padding(vertical = 20.dp, horizontal = 4.dp)
                        ) {
                            Text("Exit")
                        }
                        Button(
                            onClick = {
                                      guessTheNumberViewModel.reset()
                            },
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                        ) {
                            Text("Play Again")
                        }
                    }

                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun GuessTheNumberPreview() {
    GuessTheNumberView()
}