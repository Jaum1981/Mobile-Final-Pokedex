package com.example.pokedexappv2.models

import com.example.pokedexappv2.R

data class Pokemon(
    val id: Int,
    val name: String,
    val type: String,
    val region: String,
    val abilities: String,
    val weight: String,
    val height: String,
    val description: String,
    val imageRes: Int,
    var isFavorite: Boolean = false
)

val pokemonList = mutableListOf(
    Pokemon(
        id = 25,
        name = "Pikachu",
        type = "Elétrico",
        region = "Kanto",
        abilities = "Static, Lightning Rod",
        weight = "6.0 kg",
        height = "0.4 m",
        description = "Pikachu é um Pokémon do tipo Elétrico. É conhecido por armazenar eletricidade nas bochechas.",
        imageRes = R.drawable.pikachu_o,
        isFavorite = true,
    ),
    Pokemon(
        id = 94,
        name = "Gengar",
        type = "Fantasma/Venenoso",
        region = "Kanto",
        abilities = "Cursed Body",
        weight = "40.5 kg",
        height = "1.5 m",
        description = "Gengar é um Pokémon travesso que adora pregar sustos e se esconde nas sombras para atacar.",
        imageRes = R.drawable.gengar_nao_ironicamente_
    ),
    Pokemon(
        id = 1,
        name = "Bulbasaur",
        type = "Planta/Venenoso",
        region = "Kanto",
        abilities = "Overgrow, Chlorophyll",
        weight = "6.9 kg",
        height = "0.7 m",
        description = "Bulbasaur possui uma semente em suas costas que cresce ao absorver energia solar.",
        imageRes = R.drawable.bulbasaur
    ),
    Pokemon(
        id = 7,
        name = "Squirtle",
        type = "Água",
        region = "Kanto",
        abilities = "Torrent, Rain Dish",
        weight = "9.0 kg",
        height = "0.5 m",
        description = "Squirtle é conhecido por seu casco que o protege e por jatos de água poderosos.",
        imageRes = R.drawable.squirtle
    ),
    Pokemon(
        id = 133,
        name = "Eevee",
        type = "Normal",
        region = "Kanto",
        abilities = "Run Away, Adaptability",
        weight = "6.5 kg",
        height = "0.3 m",
        description = "Eevee é um Pokémon muito versátil, capaz de evoluir para várias formas diferentes.",
        imageRes = R.drawable.eevee
    ),
    Pokemon(
        id = 149,
        name = "Dragonite",
        type = "Dragão/Voador",
        region = "Kanto",
        abilities = "Inner Focus, Multiscale",
        weight = "210.0 kg",
        height = "2.2 m",
        description = "Dragonite é um Pokémon majestoso que voa a grandes altitudes e protege os mares.",
        imageRes = R.drawable.dragonite
    ),
    Pokemon(
        id = 448,
        name = "Lucario",
        type = "Lutador/Metálico",
        region = "Sinnoh",
        abilities = "Steadfast, Inner Focus, Justified",
        weight = "54.0 kg",
        height = "1.2 m",
        description = "Lucario é um Pokémon que pode sentir a aura dos seres vivos e usá-la em combate.",
        imageRes = R.drawable.lucario
    ),
    Pokemon(
        id = 143,
        name = "Snorlax",
        type = "Normal",
        region = "Kanto",
        abilities = "Immunity, Thick Fat",
        weight = "460.0 kg",
        height = "2.1 m",
        description = "Snorlax passa a maior parte do tempo dormindo e só acorda para comer.",
        imageRes = R.drawable.snorlax
    ),
    Pokemon(
        id = 778,
        name = "Mimikyu",
        type = "Fantasma/Fada",
        region = "Alola",
        abilities = "Disguise",
        weight = "0.7 kg",
        height = "0.2 m",
        description = "Mimikyu se disfarça para parecer com Pikachu, na esperança de fazer amigos. Sua verdadeira aparência é desconhecida e dizem que quem a vê fica terrivelmente amaldiçoado.",
        imageRes = R.drawable.mimikyu
    ),
    Pokemon(
        id = 4,
        name = "Charmander",
        type = "Fogo",
        region = "Kanto",
        abilities = "Blaze, Solar Power",
        weight = "8.5 kg",
        height = "0.6 m",
        description = "Charmander tem uma chama na ponta de sua cauda que reflete seu humor e saúde.",
        imageRes = R.drawable.charmander
    ),
    Pokemon(
        id = 150,
        name = "Mewtwo",
        type = "Psíquico",
        region = "Kanto",
        abilities = "Pressure, Unnerve",
        weight = "122.0 kg",
        height = "2.0 m",
        description = "Mewtwo é um Pokémon criado por engenharia genética para ser incrivelmente poderoso.",
        imageRes = R.drawable.mewtwo
    ),
    Pokemon(
        id = 245,
        name = "Suicune",
        type = "Água",
        region = "Johto",
        abilities = "Pressure, Inner Focus",
        weight = "187.0 kg",
        height = "2.0 m",
        description = "Suicune purifica a água onde quer que vá e é conhecido como o vento do norte.",
        imageRes = R.drawable.suicune
    ),
    Pokemon(
        id = 282,
        name = "Gardevoir",
        type = "Psíquico/Fada",
        region = "Hoenn",
        abilities = "Synchronize, Trace, Telepathy",
        weight = "48.4 kg",
        height = "1.6 m",
        description = "Gardevoir é um Pokémon gracioso que protege seu treinador com poderes psíquicos incríveis.",
        imageRes = R.drawable.gardevoir
    ),
    Pokemon(
        id = 131,
        name = "Lapras",
        type = "Água/Gelo",
        region = "Kanto",
        abilities = "Water Absorb, Shell Armor, Hydration",
        weight = "220.0 kg",
        height = "2.5 m",
        description = "Lapras é um Pokémon gentil que transporta pessoas pelo mar em suas costas.",
        imageRes = R.drawable.lapras
    ),
    Pokemon(
        id = 196,
        name = "Espeon",
        type = "Psíquico",
        region = "Johto",
        abilities = "Synchronize, Magic Bounce",
        weight = "26.5 kg",
        height = "0.9 m",
        description = "Espeon possui um sexto sentido que o ajuda a prever os movimentos de seus oponentes.",
        imageRes = R.drawable.espeon
    ),
    Pokemon(
        id = 197,
        name = "Umbreon",
        type = "Sombrio",
        region = "Johto",
        abilities = "Synchronize, Inner Focus",
        weight = "27.0 kg",
        height = "1.0 m",
        description = "Umbreon é um Pokémon sombrio que se camufla na escuridão para surpreender seus inimigos.",
        imageRes = R.drawable.umbreon
    ),
    Pokemon(
        id = 448,
        name = "Togekiss",
        type = "Fada/Voador",
        region = "Sinnoh",
        abilities = "Hustle, Serene Grace, Super Luck",
        weight = "38.0 kg",
        height = "1.5 m",
        description = "Togekiss é um Pokémon que traz alegria e harmonia por onde passa.",
        imageRes = R.drawable.togekiss
    ),
    Pokemon(
        id = 143,
        name = "Metagross",
        type = "Metálico/Psíquico",
        region = "Hoenn",
        abilities = "Clear Body, Light Metal",
        weight = "550.0 kg",
        height = "1.6 m",
        description = "Metagross é um Pokémon incrivelmente inteligente com quatro cérebros interligados.",
        imageRes = R.drawable.metagross
    ),
    Pokemon(
        id = 359,
        name = "Absol",
        type = "Sombrio",
        region = "Hoenn",
        abilities = "Pressure, Super Luck, Justified",
        weight = "47.0 kg",
        height = "1.2 m",
        description = "Absol é conhecido como o Pokémon Desastre, prevendo calamidades iminentes.",
        imageRes = R.drawable.absol
    ),
    Pokemon(
        id = 151,
        name = "Mew",
        type = "Psíquico",
        region = "Kanto",
        abilities = "Synchronize",
        weight = "4.0 kg",
        height = "0.4 m",
        description = "Mew é um Pokémon raro que contém o DNA de todos os outros Pokémon.",
        imageRes = R.drawable.mew
    ),
    Pokemon(
        id = 212,
        name = "Scizor",
        type = "Metálico/Inseto",
        region = "Johto",
        abilities = "Swarm, Technician, Light Metal",
        weight = "118.0 kg",
        height = "1.8 m",
        description = "Scizor possui garras afiadas e um corpo metálico extremamente resistente.",
        imageRes = R.drawable.scizor
    ),
    Pokemon(
        id = 373,
        name = "Salamence",
        type = "Dragão/Voador",
        region = "Hoenn",
        abilities = "Intimidate, Moxie",
        weight = "102.6 kg",
        height = "1.5 m",
        description = "Salamence é o resultado dos sonhos de voar de um Bagon finalmente se tornando realidade.",
        imageRes = R.drawable.salamence
    ),
    Pokemon(
        id = 250,
        name = "Ho-Oh",
        type = "Fogo/Voador",
        region = "Johto",
        abilities = "Pressure, Regenerator",
        weight = "199.0 kg",
        height = "3.8 m",
        description = "Ho-Oh é um Pokémon lendário que traz felicidade e arco-íris por onde passa.",
        imageRes = R.drawable.ho_oh
    )


)
