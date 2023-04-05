package com.saiful.roomtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.saiful.roomtest.ui.theme.RoomTestTheme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            ClickDB::class.java,
            "click_db"
        ).build()
    }

    private val viewModel by viewModels<ClickViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return ClickViewModel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val count by remember { mutableStateOf(0) }
//        viewModel.click.observe(this){it ->
//            Log.d("TAG", "onCreate: ${it.size}")
//        }
//        viewModel.click.observe(this){it->
//            Log.d("TAG", "onCreate: ${it.size} - observe")
//        }
//        val clickCount by viewModel.click.observe
//        Log.d("TAG", "onCreate: ${viewModel.click.collectAsState()}")
        setContent {
            RoomTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RoomTestApp(
                        addClick = viewModel::updateClick,
                        viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun RoomTestApp(addClick: (Click) -> Unit, viewModel: ClickViewModel) {

    val count = viewModel.click.observeAsState().value?.size
//    Log.d("TAG", "RoomTestApp: ${count}p----")

//    val count by viewModel.click
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$count",
            fontSize = 40.sp
        )

        Button(onClick = { addClick(Click(0, 1)) }) {
            Text(
                text = "Click"
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun RoomTestPreview() {
//    RoomTestTheme {
//        RoomTestApp(addClick = viewModel::updateClick)
//    }
//}