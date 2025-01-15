# Pokedex App 

## Sobre o Projeto

O **Pokedex App V2** é um aplicativo mobile desenvolvido na disciplina **Desenvolvimento Mobile 2024.2**. O aplicativo utiliza o **Jetpack Compose** para criação de interfaces modernas e responsivas, permitindo ao usuário visualizar, pesquisar e gerenciar uma lista de Pokémon da primeira geração, incluindo a funcionalidade de favoritar os Pokémon de sua escolha.

## Funcionalidades

- Visualizar uma lista completa de Pokémon.
- Pesquisar Pokémon por nome.
- Favoritar e desfavoritar Pokémon.
- Visualizar a lista de Pokémon favoritos.
- Detalhes individuais de cada Pokémon.
- Suporte a temas configuráveis e reset de favoritos.
- Navegação intuitiva utilizando **Jetpack Navigation**.

## Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação principal.
- **Jetpack Compose**: Framework para desenvolvimento de UI declarativa.
- **Material3**: Design System para estilização moderna.
- **Arquitetura MVVM**: Para separação de responsabilidades.
- **State Management**: Utilização de estados para reatividade no app.

## Organização do Projeto

O projeto segue uma estrutura modular para facilitar a manutenção e escalabilidade:

- **`ui.screens`**: Contém as telas principais, como HomeScreen e FavoriteScreen.
- **`ui.components`**: Componentes reutilizáveis, como a BottomNavigationBar e PokemonListItem.
- **`models`**: Modelos de dados, como `Pokemon`.
- **`navigation`**: Configuração do NavGraph para navegação entre telas.

## Configuração e Execução

1. Clone este repositório
   ```bash
   git clone https://github.com/Jaum1981/Mobile-Final-Pokedex
   ```
