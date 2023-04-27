package com.example.sportappforhuntit.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportappforhuntit.MainViewModel
import com.example.sportappforhuntit.data.models.match.Match
import com.example.sportappforhuntit.ui.theme.ButtonColor
import com.example.sportappforhuntit.ui.theme.GreyBackground
import com.example.sportappforhuntit.ui.theme.TextColor

// Сделать ResponseState. Данные не успевают отобразиться
@Composable
fun DetailsScreen(viewModel: MainViewModel) {
    val context = LocalContext.current
    val allMatches = viewModel.match.observeAsState(listOf()).value
    Surface(modifier = Modifier.fillMaxSize(), color = GreyBackground) {
        LazyColumn(
            modifier = Modifier.padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(allMatches) { item ->
                MatchInfo(item = item, context)
            }
        }
    }
}

@Composable
fun MatchInfo(item: Match, context: Context){
    Column() {
        Text(color = TextColor, fontSize = 16.sp, text = "Begin: ${item.begin_at}")
        Text(color = TextColor, fontSize = 16.sp, text = "Game: ${item.videogame.name}")
        Text(color = TextColor, fontSize = 16.sp, text = "League name: ${item.league.name}")
        Text(color = TextColor, fontSize = 16.sp, text = "Serie: ${item.serie.full_name}")
        Text(color = TextColor, fontSize = 16.sp, text = "Tournament: ${item.tournament.name}")
        Row() {
            Text(color = TextColor, fontSize = 16.sp, text = "League link: " )
            ClickableText(
                text = AnnotatedString("${item.league.url}"),
                maxLines = 1,
                style = TextStyle(color = ButtonColor, fontSize = 16.sp, textDecoration = TextDecoration.Underline),
                onClick = {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.league.url ?: "https://http.cat/404"))
                    context.startActivity(browserIntent)
                })
        }

        Text(color = TextColor, fontSize = 16.sp, text = "Type: Best of ${item.number_of_games}")
        Text(color = TextColor, fontSize = 16.sp, text = "Opponent 1: ${item.opponents[0].opponent.name}")
        Text(color = TextColor, fontSize = 16.sp, text = "Opponent 2: ${item.opponents[1].opponent.name}")
        Row() {
            Text(color = TextColor, fontSize = 16.sp, text = "Stream link: ")
            ClickableText(
                text = AnnotatedString("${item.streams_list[0].raw_url}"),
                maxLines = 1,
                style = TextStyle(color = ButtonColor, fontSize = 16.sp, textDecoration = TextDecoration.Underline),
                onClick = {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.streams_list[0].raw_url ?: "https://http.cat/404"))
                    context.startActivity(browserIntent)
                })
        }

    }
}

