package com.example.pokedexappv2

import ThemeManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.example.pokedexappv2.navigation.NavGraph
import com.example.pokedexappv2.ui.theme.NightTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    // Criação do ThemeManager
    private val themeManager = ThemeManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
}
