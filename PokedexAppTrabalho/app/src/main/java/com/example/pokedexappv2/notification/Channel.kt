package com.example.pokedexappv2.notification
//
//object NotificationHelper {
//    private const val CHANNEL_ID = "favorite_channel"
//    private const val CHANNEL_NAME = "Favoritos"
//    private const val CHANNEL_DESCRIPTION = "Notificações para Pokémon adicionados aos favoritos"
//
//    fun createNotificationChannel(context: Context) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                CHANNEL_ID,
//                CHANNEL_NAME,
//                NotificationManager.IMPORTANCE_DEFAULT
//            ).apply {
//                description = CHANNEL_DESCRIPTION
//            }
//            val notificationManager = context.getSystemService(NotificationManager::class.java)
//            notificationManager?.createNotificationChannel(channel)
//        }
//    }
//
//    fun getChannelId(): String = CHANNEL_ID
//}