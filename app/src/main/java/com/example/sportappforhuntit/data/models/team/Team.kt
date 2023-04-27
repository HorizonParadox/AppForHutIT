package com.example.sportappforhuntit.data.models.team

import com.example.sportappforhuntit.data.models.player.Player

data class Team(
    val acronym: Any,
    val current_videogame: CurrentVideogame,
    val id: Int,
    val image_url: Any,
    val location: String,
    val modified_at: String,
    val name: String,
    val players: List<Player>,
    val slug: String
)