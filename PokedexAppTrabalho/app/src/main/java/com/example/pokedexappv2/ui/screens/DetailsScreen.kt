package com.example.pokedexappv2.ui.screens

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.pokedexappv2.R
import com.example.pokedexappv2.models.Pokemon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(pokemon: Pokemon) {
    val context = LocalContext.current
    var mediaPlayer: MediaPlayer? by remember { mutableStateOf(null) }

    // Carregar o som do Pokémon no momento adequado com LaunchedEffect
    LaunchedEffect(pokemon.id) {
        // Libera o recurso anterior, se houver
        mediaPlayer?.stop()
        mediaPlayer?.release()

        // Cria o novo MediaPlayer para o Pokémon atual
        mediaPlayer = MediaPlayer.create(context, getPokemonSoundResource(pokemon.id))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = pokemon.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = pokemon.imageUrl,
                        imageLoader = coil.ImageLoader(context).newBuilder()
                            .components {
                                add(coil.decode.ImageDecoderDecoder.Factory()) // Suporte para GIFs
                            }
                            .build()
                    ),
                    contentDescription = "${pokemon.name} image",
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Informações Gerais",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Tipo: ${pokemon.type}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Região: ${pokemon.region}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Habilidade: ${pokemon.abilities}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Peso: ${pokemon.weight}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Altura: ${pokemon.height}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Descrição do Pokémon
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Descrição",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = pokemon.description,
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botão para ouvir o som do Pokémon
            IconButton(onClick = {
                mediaPlayer?.start() // Reproduz o som quando o ícone é clicado
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_play_sound), // Ícone de som
                    contentDescription = "Ouvir som"
                )
            }
        }
    }

    // Liberação do MediaPlayer quando a função composable sai de escopo
    DisposableEffect(pokemon.id) {
        onDispose {
            mediaPlayer?.stop()
            mediaPlayer?.release()
        }
    }
}

fun getPokemonSoundResource(pokemonId: Int): Int {
    return when (pokemonId) {
        25 -> R.raw.pikachu_sound  // Som do Pikachu
        94 -> R.raw.gengar_sound  // Som do Gengar
        1 -> R.raw.bulbasaur_sound  // Som do Bulbasaur
        7 -> R.raw.squirtle_sound  // Som do Squirtle
        133 -> R.raw.eevee_sound  // Som do Eevee
        149 -> R.raw.dragonite_sound  // Som do Dragonite
        448 -> R.raw.lucario_sound  // Som do Lucario
        143 -> R.raw.snorlax_sound  // Som do Snorlax
        4 -> R.raw.charmander_sound  // Som do Charmander
        150 -> R.raw.mewtwo_sound  // Som do Mewtwo

        else -> R.raw.default_pokemon_sound  // Som padrão
    }
}
