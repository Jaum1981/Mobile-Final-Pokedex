package com.example.pokedexappv2.ui.screens

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.pokedexappv2.R
import com.example.pokedexappv2.data.DataStoreUtils
import com.example.pokedexappv2.models.Pokemon
import com.example.pokedexappv2.ui.components.PokemonListItem
import com.example.pokedexappv2.ui.components.TopAppBarMenu
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@RequiresApi(35)
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    onPokemonSelected: (Pokemon) -> Unit,
    onSettingsClick: () -> Unit,
    onHelpClick: () -> Unit,
    onLogoutClick: () -> Unit,
    pokemonList: List<Pokemon>,
    isLoading: Boolean,
    onFavoriteToggle: (Pokemon) -> Unit,
    context: Context // Adicione o contexto aqui
) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredPokemon = remember(searchQuery, pokemonList) {
        pokemonList.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }
    val recentSearches = remember { mutableStateListOf<Pokemon>() }

    Scaffold(
        topBar = {
            TopAppBarMenu(
                onSettingsClick = onSettingsClick,
                onHelpClick = onHelpClick,
                onLogoutClick = { onLogoutClick() }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            // Campo de busca
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Pesquisar Pokémon") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // Exibição de carregamento
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .wrapContentWidth()
                )
            } else {
                // Recent Searches
                LazyRow(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(recentSearches) { pokemon ->
                        Button(onClick = { onPokemonSelected(pokemon) }) {
                            Text(text = pokemon.name)
                        }
                    }
                }

                // Lista filtrada de Pokémon
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(horizontal = 18.dp)
                ) {
                    items(filteredPokemon) { pokemon ->
                        PokemonListItem(
                            pokemon = pokemon,
                            onPokemonSelected = { selectedPokemon ->
                                if (!recentSearches.contains(selectedPokemon)) {
                                    recentSearches.add(0, selectedPokemon)
                                }
                                onPokemonSelected(selectedPokemon)
                            },
                            onFavoriteToggle = { pokemon ->
                                if (!pokemon.isFavorite) { // Só envia a notificação quando o Pokémon for favoritado
                                    CoroutineScope(Dispatchers.Main).launch {
                                        if(DataStoreUtils.areNotificationsEnabled(context).first()) {
                                            sendNotification(context, pokemon.name)
                                        }
                                    }
                                }
                                onFavoriteToggle(pokemon)
                            }
                        )
                    }
                }
            }
        }
    }
}


//envio
private fun sendNotification(context: Context, pokemonName: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        // Verifica se a permissão foi concedida
        if (context.checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            val builder = NotificationCompat.Builder(context, "FAVORITES_CHANNEL")
                .setSmallIcon(R.drawable.pokemon_simbol)
                .setContentTitle("Pokémon Favoritado")
                .setContentText("$pokemonName foi adicionado aos favoritos!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            with(NotificationManagerCompat.from(context)) {
                notify(pokemonName.hashCode(), builder.build()) // ID único baseado no nome
            }
        } else {
            // Log ou alternativa caso a permissão não seja concedida
            println("Permissão para enviar notificações não concedida.")
        }
    } else {
        // Para versões anteriores a Android 13, não é necessário verificar permissão
        val builder = NotificationCompat.Builder(context, "FAVORITES_CHANNEL")
            .setSmallIcon(R.drawable.pokemon_simbol)
            .setContentTitle("Pokémon Favoritado")
            .setContentText("$pokemonName foi adicionado aos favoritos!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            notify(pokemonName.hashCode(), builder.build()) // ID único baseado no nome
        }
    }
}
