package com.example.calctonystark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DraggableTextLowLevel(Color.Blue)
            DraggableTextLowLevel(Color.Black)
            DraggableTextLowLevel(Color.Red)
            DraggableTextLowLevel(Color.Black)


        }
    }
}

@Preview(showBackground = false)
@Composable
fun NewText(){
    Text(text = "Калькулятор")
}

@Composable
fun CalcButton(name: String){
    Button(onClick = {}, shape = RoundedCornerShape(1.dp)) {
        Text(name, fontSize = 40.sp)
    }
}

@Composable
private fun DraggableTextLowLevel(col: Color) {
    Box(modifier = Modifier.fillMaxSize()) {
        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }

        Box(
            Modifier
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .background(col)
                .size(100.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    }
                }
                .onFocusChanged {  }
        ){
            val count = remember { mutableStateOf(0) }
            // content that you want to make clickable
            Text(
                fontSize=50.sp,
                textAlign = TextAlign.Center,
                text = count.value.toString(),
                color=Color.Red,
                modifier = Modifier.clickable { count.value += 1 }
            )
        }
    }
}

data class Item(val title: String)

@Composable
fun CreateNewObject() {
    // создание списка объектов. Мы будем использовать mutableStateOf, чтобы отслеживать изменения списка.
    var items by remember { mutableStateOf(listOf<Item>()) }

    // функция для добавления нового объекта в список
    fun addItem() {
        val newItem = Item("Новый объект ${items.size + 1}")
        items = items + newItem
    }

    // интерфейс
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // кнопка для добавления нового объекта
        Button(
            onClick = { addItem() },
            modifier = Modifier.padding(16.dp)
        ) {
            MaterialTheme {
                TextStyle(color = Color.White)
            }
            Text("Добавить новый объект")
        }

        // отображаем список объектов в колонке
        for (item in items) {
            Box(modifier = Modifier.fillMaxSize()) {
                var offsetX by remember { mutableStateOf(0f) }
                var offsetY by remember { mutableStateOf(0f) }

                Box(
                    Modifier
                        .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                        .background(Color.Gray)
                        .size(100.dp)
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->
                                change.consume()
                                offsetX += dragAmount.x
                                offsetY += dragAmount.y
                            }
                        }
                        .onFocusChanged {  }
                ){
                    val count = remember { mutableStateOf(0) }
                    // content that you want to make clickable
                    Text(
                        fontSize=50.sp,
                        textAlign = TextAlign.Center,
                        text = count.value.toString(),
                        color=Color.Red,
                        modifier = Modifier.clickable { count.value += 1 }
                    )
                }
            }
        }
    }
}


