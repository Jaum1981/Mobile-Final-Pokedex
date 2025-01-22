package com.example.pokedexappv2.ui.screens

import ThemeManager
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.pokedexappv2.data.DataStoreUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    themeManager: ThemeManager,
    resetFavorites: () -> Unit,
    context: Context
) {
    var showDialog by remember { mutableStateOf(false) }
    var notificationsEnabled by remember { mutableStateOf(false) }

    // Obter estado inicial das notificações no DataStore
    LaunchedEffect(Unit) {
        notificationsEnabled = DataStoreUtils.areNotificationsEnabled(context).first()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Configurações",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Configuração de Tema Escuro
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Tema Escuro",
                style = MaterialTheme.typography.bodyMedium
            )
            Switch(
                checked = themeManager.themeMode == ThemeMode.Dark,
                onCheckedChange = { themeManager.toggleTheme() }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Configuração de Notificações
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Notificações",
                style = MaterialTheme.typography.bodyMedium
            )
            Switch(
                checked = notificationsEnabled,
                onCheckedChange = { isEnabled ->
                    notificationsEnabled = isEnabled
                    // Atualizar preferências no DataStore
                    updateNotificationsPreference(context, isEnabled)
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
                        resetFavorites()
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

// Função suspensa para atualizar o estado no DataStore
fun updateNotificationsPreference(context: Context, isEnabled: Boolean) {
    // Cria um escopo de corrotina e escreve no DataStore
    CoroutineScope(Dispatchers.IO).launch {
        DataStoreUtils.setNotificationsEnabled(context, isEnabled)
    }
}
