package com.example.sportappforhuntit.data.network
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {
  suspend fun getAllMatches() = apiService.getAllMatches()
  suspend fun getCurrentTeam(slug: String) = apiService.getTeamInfo(slug)
  suspend fun getCurrentMatch(slug: String) = apiService.getOneMatches(slug)
}