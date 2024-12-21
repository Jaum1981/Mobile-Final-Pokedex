import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(themeManager: ThemeManager) {
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
    }
}