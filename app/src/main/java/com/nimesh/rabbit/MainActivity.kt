package com.nimesh.rabbit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.nimesh.rabbit.ui.theme.RabbitAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalCoilApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RabbitAppTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    val viewModel: MainViewModel = hiltViewModel()
                    val rabbit = viewModel.state.value.rabbit
                    val isLoading = viewModel.state.value.isLoading

                    rabbit?.let {
                        Image(
                            painter = rememberImagePainter(
                                data = rabbit.imageUrl,
                                builder = { crossfade(true) }
                            ),
                            contentDescription = "Rabbit"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = rabbit.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = rabbit.description,)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Button(
                        onClick = { viewModel.getRandomRabbit() },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = "Next Rabbit!")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    if (isLoading){
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}