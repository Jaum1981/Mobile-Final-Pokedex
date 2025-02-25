package com.example.pokedexappv2

import ThemeManager
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.pokedexappv2.data.AuthRepository
import com.example.pokedexappv2.navigation.NavGraph
import com.example.pokedexappv2.ui.theme.NightTheme
import com.example.pokedexappv2.viewmodel.AuthViewModel
import com.example.pokedexappv2.viewmodel.AuthViewModelFactory

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    // Instância do ThemeManager
    private val themeManager by lazy { ThemeManager(this) }

    // Instância correta do ViewModel de autenticação
    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(AuthRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Criar o canal de notificação
        createNotificationChannel()

        // Verificar e solicitar permissão para notificações (Android 13+)
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
                NavGraph(
                    onSettingsClick = { /* Ação para configurações */ },
                    onHelpClick = { /* Ação para ajuda */ },
                    onLogoutClick = { authViewModel.logout() }, // Chama logout corretamente
                    themeManager = themeManager,
                    authViewModel = authViewModel // Passando o ViewModel para o NavGraph
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
