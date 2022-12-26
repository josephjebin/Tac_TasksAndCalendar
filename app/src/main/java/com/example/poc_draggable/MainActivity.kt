package com.example.poc_draggable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.poc_draggable.data.Datasource
import com.example.poc_draggable.data.Task
import com.example.poc_draggable.ui.theme.POC_DraggableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            POC_DraggableTheme {
                TasksAndCalendarScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TasksAndCalendarScreen() {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(
        sheetContent = {
            TaskSheet()
        },
        scaffoldState = bottomSheetScaffoldState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) { padding ->  // We need to pass scaffold's inner padding to content. That's why we use Box.
        Box(modifier = Modifier.padding(padding)) {
            Calendar()
        }
    }
}

@Composable
fun Calendar() {
    LazyVerticalGrid(columns = GridCells.Fixed(1)) {
        items(items = get24Hours(), key = { hour -> hour }) { hour ->
            CalendarRow(hour)
        }
    }
}

@Composable
fun CalendarRow(hour: String) {
    Row(modifier = Modifier.border(BorderStroke(2.dp, Color.DarkGray))) {
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .width(60.dp)
        ) {
            Text(text = hour)
        }

        Column() {
            val rowModifier: Modifier =
                Modifier
                    .fillMaxWidth()
                    .border(BorderStroke(2.dp, Color.LightGray))
                    .padding(start = 8.dp)
            Row(modifier = rowModifier) {
                Text(text = "0")
            }
            Row(modifier = rowModifier) {
                Text(text = "15")
            }
            Row(modifier = rowModifier) {
                Text(text = "30")
            }
            Row(modifier = rowModifier) {
                Text(text = "45")
            }
        }
    }
}

@Composable
fun TaskSheet() {
    Column() {
        val datasource = Datasource()
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(items = datasource.listOfTasks, key = { task ->
                task.id
            }) { task ->
                TaskRow(task)
            }
        }
    }
}

@Composable
fun TaskRow(task: Task) {
    Row() {
        Button(onClick = { /*TODO*/ }) {

        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(text = task.text)


    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    POC_DraggableTheme {
        TasksAndCalendarScreen()
    }
}

private fun get24Hours(): List<String> {
    val hours = mutableListOf<String>()
    for (i in 0..23) hours.add("$i")
    return hours
}