package com.example.sportappforhuntit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportappforhuntit.data.models.match.Match
import com.example.sportappforhuntit.data.models.team.Team
import com.example.sportappforhuntit.data.network.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ApiRepository): ViewModel() {
  private val _allMatches =  MutableLiveData<List<Match>>()
  val allMatches: LiveData<List<Match>>
    get() = _allMatches

  private val _team =  MutableLiveData<List<Team>>()
  val team: LiveData<List<Team>>
    get() = _team

  private val _match =  MutableLiveData<List<Match>>()
  val match: LiveData<List<Match>>
    get() = _match

  fun getAllMatches(){
    viewModelScope.launch {
      repository.getAllMatches().let {
        if (it.isSuccessful){
          _allMatches.postValue(it.body())
        } else{
          Log.d("checkData", "Failed to load matches: ${it.errorBody()}")
        }
      }
    }
  }

  fun getCurrentTeamInfo(slug: String){
    viewModelScope.launch {
      repository.getCurrentTeam(slug).let {
        if (it.isSuccessful){
          _team.postValue(it.body())
        } else{
          Log.d("checkData", "Failed to load matches: ${it.errorBody()}")
        }
      }
    }
  }

  fun getCurrentMatch(slug: String){
    viewModelScope.launch {
      repository.getCurrentMatch(slug).let {
        if (it.isSuccessful){
          _match.postValue(it.body())
        } else{
          Log.d("checkData", "Failed to load match: ${it.errorBody()}")
        }
      }
    }
  }
}