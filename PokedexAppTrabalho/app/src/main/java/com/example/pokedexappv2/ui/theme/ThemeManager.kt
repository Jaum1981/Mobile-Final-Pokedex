import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.pokedexappv2.data.DataStoreUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Calendar

class ThemeManager(private val context: Context) {
    var themeMode by mutableStateOf(ThemeMode.Dark) // Valor inicial como tema escuro
        private set

    // Criar um escopo para gerenciar as corrotinas
    private val coroutineScope = CoroutineScope(Job() + Dispatchers.Main)

    init {
        coroutineScope.launch {
            val isDarkModeEnabled = DataStoreUtils.isDarkModeEnabled(context).first()
            themeMode = if (isDarkModeEnabled) ThemeMode.Dark else ThemeMode.Light

            // Verificar a hora e ajustar o tema automaticamente entre 6 PM e 6 AM
            if (isNightTime()) {
                themeMode = ThemeMode.Dark
            }
        }
    }

    fun toggleTheme() {
        themeMode = if (themeMode == ThemeMode.Dark) ThemeMode.Light else ThemeMode.Dark
        coroutineScope.launch {
            DataStoreUtils.setDarkModeEnabled(context, themeMode == ThemeMode.Dark)
        }
    }

    fun setTheme(mode: ThemeMode) {
        themeMode = mode
        coroutineScope.launch {
            DataStoreUtils.setDarkModeEnabled(context, mode == ThemeMode.Dark)
        }
    }

    // Função para verificar se a hora atual está entre 6 PM e 6 AM
    private fun isNightTime(): Boolean {
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return currentHour >= 18 || currentHour < 6
    }
}

enum class ThemeMode {
    Light, Dark
}
