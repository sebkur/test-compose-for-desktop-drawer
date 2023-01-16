package de.mobanisto.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.singleWindowApplication
import kotlin.math.roundToInt

fun main() {
    singleWindowApplication {
        val enabled = remember { mutableStateOf(false) }
        val minWidth = 0;
        val maxWidth = 200
        val fraction = remember { mutableStateOf(0.1f) }
        val width = derivedStateOf { (minWidth + (fraction.value * (maxWidth - minWidth))).roundToInt() }
        Box(Modifier.fillMaxSize(1f).background(Color.LightGray)) {
            Column(Modifier.padding(horizontal = 80.dp)) {
                TextButton(onClick = { enabled.value = !enabled.value }) {
                    Text("toggle")
                }
                Text(if (enabled.value) "enabled" else "disabled", Modifier.padding(8.dp))
                Slider(fraction.value, { v -> fraction.value = v })
            }
            // Red drawer on the left, always visible.
            Surface(
                modifier = Modifier.fillMaxHeight().requiredWidth(width.value.dp),
                color = Color.Red
            ) { }
            // Cyan drawer on the right, expandable.
            Surface(
                modifier = Modifier
                    .fillMaxHeight()
                    .requiredWidth(width.value.dp)
                    .align(Alignment.CenterEnd)
                    .offset { IntOffset(if (enabled.value) 0 else width.value, 0) },
                color = Color.Cyan.copy(alpha = 0.5f)
            ) {

            }
        }
    }
}
