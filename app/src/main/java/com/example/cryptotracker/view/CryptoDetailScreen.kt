package com.example.cryptotracker.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CryptoDetailScreen(
    id:String,
    price:String,
    navController: NavController
) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary),
            contentAlignment = Alignment.Center
        ){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text(text = id,
              style = MaterialTheme.typography.h3,
              modifier = Modifier.padding(2.dp),
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colors.primary,
              textAlign = TextAlign.Center
              )
                Text(text = price,
                     style = MaterialTheme.typography.h3,
                     modifier = Modifier.padding(2.dp),
                     fontWeight = FontWeight.Bold,
                     color = MaterialTheme.colors.primary,
                     textAlign = TextAlign.Center
                )
            }
        }

}