package com.example.calculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorapp.ui.theme.CalculatorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorApp()
        }
    }
}

@Composable
fun CalculatorApp() {
    var display by remember { mutableStateOf("") }
    var firstNumber by remember { mutableStateOf(0) }
    var secondNumber by remember { mutableStateOf(0) }
    var isAdding by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = display, fontSize = 32.sp, modifier = Modifier.padding(16.dp))

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            NumberButton(number = 1) { display = handleNumberInput(display, 1, isAdding, firstNumber, secondNumber) }
            NumberButton(number = 2) { display = handleNumberInput(display, 2, isAdding, firstNumber, secondNumber) }
            NumberButton(number = 3) { display = handleNumberInput(display, 3, isAdding, firstNumber, secondNumber) }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            NumberButton(number = 4) { display = handleNumberInput(display, 4, isAdding, firstNumber, secondNumber) }
            NumberButton(number = 5) { display = handleNumberInput(display, 5, isAdding, firstNumber, secondNumber) }
            NumberButton(number = 6) { display = handleNumberInput(display, 6, isAdding, firstNumber, secondNumber) }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            NumberButton(number = 7) { display = handleNumberInput(display, 7, isAdding, firstNumber, secondNumber) }
            NumberButton(number = 8) { display = handleNumberInput(display, 8, isAdding, firstNumber, secondNumber) }
            NumberButton(number = 9) { display = handleNumberInput(display, 9, isAdding, firstNumber, secondNumber) }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                if (display.isNotEmpty()) {
                    firstNumber = display.toInt()
                    isAdding = true
                    display = ""
                }
            }, modifier = Modifier.padding(8.dp)) {
                Text("+")
            }

            NumberButton(number = 0) { display = handleNumberInput(display, 0, isAdding, firstNumber, secondNumber) }

            Button(onClick = {
                display = ""
                firstNumber = 0
                secondNumber = 0
                isAdding = false
            }, modifier = Modifier.padding(8.dp)) {
                Text("AC")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (isAdding) {
                secondNumber = display.toInt()
                display = (firstNumber + secondNumber).toString()
                isAdding = false
            }
        }, modifier = Modifier.padding(8.dp)) {
            Text("=")
        }
    }
}

@Composable
fun NumberButton(number: Int, onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier.padding(8.dp)) {
        Text(text = number.toString(), fontSize = 24.sp)
    }
}

fun handleNumberInput(
    display: String,
    number: Int,
    isAdding: Boolean,
    firstNumber: Int,
    secondNumber: Int
): String {
    return display + number.toString()
}