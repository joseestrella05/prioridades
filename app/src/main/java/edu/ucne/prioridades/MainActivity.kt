package edu.ucne.prioridades

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import edu.ucne.prioridades.presentation.PrioridadViewModel
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import edu.ucne.prioridades.presentation.PrioridadFormScreen
import edu.ucne.prioridades.presentation.PrioridadListScreen
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    private val viewModel: PrioridadViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(modifier = Modifier.wrapContentHeight()) {
                            PrioridadFormScreen(viewModel)
                        }

                        Divider(thickness = 1.dp)

                        Box(modifier = Modifier.weight(1f)) {
                            PrioridadListScreen(viewModel)
                        }
                    }
                }
            }
        }
    }
}