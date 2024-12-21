import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ThemeManager {
    var themeMode by mutableStateOf(ThemeMode.Dark) // Valor inicial como tema escuro

    fun toggleTheme() {
        themeMode = if (themeMode == ThemeMode.Dark) ThemeMode.Light else ThemeMode.Dark
    }

    fun setTheme(mode: ThemeMode) {
        themeMode = mode
    }
}

enum class ThemeMode {
    Light, Dark
}
