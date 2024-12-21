import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HelpScreen() {
    val faqList = listOf(
        "Como faço para alterar o tema do aplicativo?",
        "Como posso adicionar um Pokémon aos favoritos?",
        "O que devo fazer se o aplicativo não estiver funcionando corretamente?",
        "Como posso buscar por um Pokémon específico?"
    )

    var message by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Ajuda e Suporte",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Perguntas Frequentes (FAQ)",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        faqList.forEach { faq ->
            Text(text = "• $faq", style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Enviar uma mensagem para o suporte:",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Substituído por OutlinedTextField
        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Digite sua mensagem") },
            placeholder = { Text("Escreva aqui sua dúvida ou problema") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp), // Altura ajustada para simular um campo maior
            maxLines = 4
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (message.isNotEmpty()) {
                    println("Mensagem enviada: $message")
                }
            }
        ) {
            Text("Enviar Mensagem")
        }
    }
}
