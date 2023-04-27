package com.example.sportappforhuntit.data.network

import com.example.sportappforhuntit.data.models.match.Match
import com.example.sportappforhuntit.data.models.team.Team
import com.example.sportappforhuntit.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
  @GET("/csgo/matches/upcoming")
  suspend fun getAllMatches(
    @Query("sort") sort: String = "begin_at",
    @Query("page") page: String = "1",
    @Query("per_page") per_page: String = "50",
    @Query("token") token: String = Constants.Token.TOKEN
  ): Response<List<Match>>

  @GET("/csgo/teams")
  suspend fun getTeamInfo(
    @Query("search[slug]") slug: String,
    @Query("page") page: String = "1",
    @Query("per_page") per_page: String = "1",
    @Query("token") token: String = Constants.Token.TOKEN,
  ): Response<List<Team>>

  // Получение конкретного матча по id недоступен (платная подписка) поэтому для данной реализации
  // Специально было сделано неточное допущение, которое позволяет найти матч из списка по др. критериям
  // Возвращается список. Подразумеваем что первое найденное - необходимое
  @GET("/csgo/matches/upcoming")
  suspend fun getOneMatches(
    @Query("search[slug]") slug: String,
    @Query("search[status]") status: String = "not_started",
    @Query("page") page: String = "1",
    @Query("per_page") per_page: String = "1",
    @Query("sort") sort: String = "begin_at",
    @Query("token") token: String = Constants.Token.TOKEN
  ): Response<List<Match>>
}