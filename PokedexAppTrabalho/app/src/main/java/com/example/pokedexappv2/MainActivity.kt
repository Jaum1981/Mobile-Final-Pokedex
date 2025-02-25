package com.example.pokedexappv2

import ThemeManager
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.pokedexappv2.navigation.NavGraph
import com.example.pokedexappv2.ui.theme.NightTheme


@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    // Criação do ThemeManager
    private val themeManager by lazy { ThemeManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Criar o canal de notificação
        createNotificationChannel()

        // Verificar e solicitar permissão para notificações
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1001
            )
        }
        setContent {
            // Aplicando o tema dinamicamente conforme o estado do themeManager
            NightTheme(themeManager = themeManager) {
                // Passando o ThemeManager para o NavGraph
                NavGraph(
                    onSettingsClick = {
                        // Ação para abrir a tela de configurações
                    },
                    onHelpClick = {
                        // Ação para Ajuda (pode abrir uma nova tela ou exibir um diálogo)
                    },
                    onLogoutClick = {
                        // Ação para Logout
                    },
                    themeManager = themeManager // Passando o ThemeManager para o NavGraph
                )
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Favoritos"
            val descriptionText = "Notificações de Pokémon favoritado"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("FAVORITES_CHANNEL", name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}