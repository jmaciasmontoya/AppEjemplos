package com.example.appejemplos

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.appejemplos.ui.theme.AppEjemplosTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppEjemplosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EjemplosAvanzados(
                        onBackClick = { finish() },
                        onNavigateToImages = {
                            startActivity(Intent(this, ImageActivity::class.java))
                        }
                    )
                }
            }
        }
    }
}

/**
 * Pantalla que muestra ejemplos avanzados de componentes de Material 3
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EjemplosAvanzados(
    onBackClick: () -> Unit,
    onNavigateToImages: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    var showMenu by remember { mutableStateOf(false) }
    val progress = 0.7f

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ejemplos Avanzados") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menú")
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Opción 1") },
                            onClick = { showMenu = false }
                        )
                        DropdownMenuItem(
                            text = { Text("Opción 2") },
                            onClick = { showMenu = false }
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Botón para navegar a la actividad de imágenes
            Button(
                onClick = onNavigateToImages,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver Ejemplos de Imágenes")
            }

            // Ejemplo de Tabs
            TabRow(selectedTabIndex = selectedTab) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Tab 1") }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("Tab 2") }
                )
                Tab(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    text = { Text("Tab 3") }
                )
            }

            // Contenido de las tabs
            when (selectedTab) {
                0 -> Text("Contenido de la Tab 1")
                1 -> Text("Contenido de la Tab 2")
                2 -> Text("Contenido de la Tab 3")
            }

            // Ejemplo de IconButton con Badge
            BadgedBox(
                badge = {
                    Badge {
                        Text("5")
                    }
                }
            ) {
                IconButton(onClick = { /* Acción */ }) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = "Favoritos",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Ejemplo de botón que muestra un Dialog
            Button(
                onClick = { showDialog = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Mostrar Diálogo")
            }

            // Ejemplo de botón que muestra un BottomSheet
            Button(
                onClick = { showBottomSheet = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Mostrar Bottom Sheet")
            }

            // Ejemplo de LinearProgressIndicator con la nueva sintaxis
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                progress = { progress }
            )

            // Ejemplo de CircularProgressIndicator
            CircularProgressIndicator()

            // Ejemplo de HorizontalDivider (antes Divider)
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 2.dp
            )

            // Ejemplo de ListItem
            ListItem(
                headlineContent = { Text("Título del elemento") },
                supportingContent = { Text("Descripción del elemento") },
                leadingContent = {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            )
        }

        // Diálogo
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Título del Diálogo") },
                text = { Text("Este es un ejemplo de diálogo en Material 3") },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Aceptar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }

        // Bottom Sheet
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        "Bottom Sheet",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text("Este es un ejemplo de Bottom Sheet en Material 3")
                    Button(
                        onClick = { showBottomSheet = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Cerrar")
                    }
                }
            }
        }
    }
} 