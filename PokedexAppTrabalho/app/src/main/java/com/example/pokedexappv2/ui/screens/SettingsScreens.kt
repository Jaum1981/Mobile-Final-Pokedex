import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Composable
fun SettingsScreen(themeManager: ThemeManager, resetFavorites: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Configurações",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Tema Escuro",
                style = MaterialTheme.typography.bodyMedium
            )

            // Switch para alternar entre os temas
            Switch(
                checked = themeManager.themeMode == ThemeMode.Dark,
                onCheckedChange = {
                    themeManager.toggleTheme()
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para redefinir os favoritos
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Redefinir Pokémons Favoritos")
        }
    }

    // Pop-up de confirmação
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Confirmação") },
            text = { Text(text = "Você tem certeza que deseja redefinir todos os Pokémons favoritos?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        resetFavorites()  // Função para redefinir os favoritos
                        showDialog = false
                    }
                ) {
                    Text("Sim")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false }
                ) {
                    Text("Não")
                }
            }
        )
    }
}
