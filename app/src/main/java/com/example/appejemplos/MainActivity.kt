package com.example.appejemplos

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.appejemplos.ui.theme.AppEjemplosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppEjemplosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EjemplosBasicos(
                        onNavigateToSecond = {
                            startActivity(Intent(this, SecondActivity::class.java))
                        }
                    )
                }
            }
        }
    }
}

/**
 * Pantalla principal que muestra ejemplos de componentes básicos de Material 3
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EjemplosBasicos(onNavigateToSecond: () -> Unit) {
    var texto by remember { mutableStateOf("") }
    var switchState by remember { mutableStateOf(false) }
    var sliderValue by remember { mutableStateOf(0f) }
    var radioOption by remember { mutableStateOf("Opción 1") }
    var checkboxState by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título principal
        Text(
            text = "Ejemplos de Componentes Básicos",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        // Botón para navegar a la segunda actividad
        Button(
            onClick = onNavigateToSecond,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver Más Ejemplos")
        }

        // Ejemplo de TextField
        OutlinedTextField(
            value = texto,
            onValueChange = { texto = it },
            label = { Text("Campo de texto") },
            modifier = Modifier.fillMaxWidth()
        )

        // Ejemplo de Switch
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Switch")
            Switch(
                checked = switchState,
                onCheckedChange = { switchState = it }
            )
        }

        // Ejemplo de Slider
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Slider: ${sliderValue.toInt()}")
            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                valueRange = 0f..100f,
                steps = 100
            )
        }

        // Ejemplo de RadioButtons
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Radio Buttons", style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                RadioButton(
                    selected = radioOption == "Opción 1",
                    onClick = { radioOption = "Opción 1" }
                )
                Text("Opción 1")
                RadioButton(
                    selected = radioOption == "Opción 2",
                    onClick = { radioOption = "Opción 2" }
                )
                Text("Opción 2")
            }
        }

        // Ejemplo de Checkbox
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkboxState,
                onCheckedChange = { checkboxState = it }
            )
            Text("Checkbox")
        }

        // Ejemplo de Botones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { /* Acción del botón */ },
                modifier = Modifier.weight(1f)
            ) {
                Text("Botón Primario")
            }
            OutlinedButton(
                onClick = { /* Acción del botón */ },
                modifier = Modifier.weight(1f)
            ) {
                Text("Botón Secundario")
            }
        }

        // Ejemplo de Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Tarjeta de Ejemplo",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Esta es una tarjeta de ejemplo que muestra cómo usar el componente Card en Material 3.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        // Ejemplo de FAB (Floating Action Button)
        FloatingActionButton(
            onClick = { /* Acción del FAB */ },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("+")
        }
    }
}