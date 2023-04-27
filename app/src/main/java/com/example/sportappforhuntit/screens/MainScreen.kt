package com.example.sportappforhuntit.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.sportappforhuntit.MainViewModel
import com.example.sportappforhuntit.R
import com.example.sportappforhuntit.data.models.match.Match
import com.example.sportappforhuntit.navigation.Screens
import com.example.sportappforhuntit.ui.theme.*
import com.example.sportappforhuntit.ui.theme.TextColor
import java.time.Clock
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    val allGames = viewModel.allMatches.observeAsState(listOf()).value

// Текущее время по UTC (Сделать пагинацию по дням)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val current = LocalDateTime.now(Clock.systemUTC()).format(formatter)

    Surface(modifier = Modifier.fillMaxSize(), color = GreyBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(0.9f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(allGames) { item ->
                    MatchesItem(item = item, navController = navController, viewModel = viewModel)
                }
            }
            Box(
                modifier = Modifier
                    .weight(0.1f)
                    .align(alignment = Alignment.End)
            ) {
                ExtendedFloatingActionButton(
                    modifier = Modifier
                        .padding(all = 8.dp),
                    onClick = {
                        navController.navigate(Screens.Web.route)
                    },
                    text = {
                        Text(
                            text = "liquipedia", color = TextColor,
                            fontSize = 16.sp,
                            maxLines = 1,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    icon = {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable.link),
                            tint = TextColor,
                            contentDescription = "More"
                        )
                    }
                )
            }

        }
    }
}

@Composable
fun MatchesItem(item: Match, navController: NavController, viewModel: MainViewModel) {
    Row(modifier = Modifier
        .fillMaxSize()
        .background(GreyCard)
        .clickable {
            viewModel.getCurrentMatch(item.slug)
            navController.navigate(Screens.Details.route)
        }
    ) {
        Column(modifier = Modifier.padding(start = 4.dp)) {
// Название лиги формат матча
            Row() {
                Box(modifier = Modifier.size(24.dp)) {
                    Image(
                        modifier = Modifier
                            .size(16.dp)
                            .align(Alignment.Center),
                        painter = rememberAsyncImagePainter(
                            item.league.image_url ?: R.drawable.trophy
                        ),
                        contentDescription = null
                    )
                }
                Text(
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(start = 8.dp),
                    text = "${item.league.name} | bo${item.number_of_games}",
                    color = TextColor,
                    fontSize = 12.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                )
            }

            Row(modifier = Modifier.padding(bottom = 4.dp)) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
// Назвиние и логотип первого оппонента
                    Row() {
                        Box(modifier = Modifier.size(24.dp)) {
                            Image(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .align(Alignment.Center),
                                painter = rememberAsyncImagePainter(
                                    if (item.opponents.size == 2) {
                                        if (item.opponents[0].opponent.image_url != null) {
                                            item.opponents[0].opponent.image_url
                                        } else R.drawable.question
                                    } else R.drawable.question
                                ),
                                contentDescription = null
                            )
                        }
                        Text(
                            modifier =
                            Modifier
                                .align(CenterVertically)
                                .padding(start = 8.dp),
                            text = if (item.opponents.size == 2) item.opponents[0].opponent.name else "unknown",
                            color = TextColor, fontSize = 16.sp,
                        )
                    }
// Назвиние и логотип второго оппонента
                    Row() {
                        Box(modifier = Modifier.size(24.dp)) {
                            Image(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .align(Alignment.Center),
                                painter = rememberAsyncImagePainter(
                                    if (item.opponents.size == 2) {
                                        if (item.opponents[1].opponent.image_url != null) {
                                            item.opponents[1].opponent.image_url
                                        } else
                                            R.drawable.question
                                    } else
                                        R.drawable.question
                                ),
                                contentDescription = null
                            )
                        }
                        Text(
                            modifier = Modifier
                                .align(CenterVertically)
                                .padding(start = 8.dp),
                            text = if (item.opponents.size == 2) item.opponents[1].opponent.name else "unknown",
                            color = TextColor, fontSize = 16.sp,
                        )
                    }
                }
// Время начала матча
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(CenterVertically)
                        .padding(end = 8.dp)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        text = "${
                            item.begin_at.substringAfter("T").substringBeforeLast(":")
                        } (UTC)",
                        color = TextColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                    )
                }
            }
        }
    }
}