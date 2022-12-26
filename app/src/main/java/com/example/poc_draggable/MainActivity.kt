package com.example.poc_draggable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.poc_draggable.ui.calendar.Calendar
import com.example.poc_draggable.ui.task.TaskSheet
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    POC_DraggableTheme {
        TasksAndCalendarScreen()
    }
}
