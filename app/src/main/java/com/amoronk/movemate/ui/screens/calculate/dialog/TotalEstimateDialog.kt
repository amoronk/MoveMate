package com.amoronk.movemate.ui.screens.calculate.dialog

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.amoronk.movemate.R
import kotlinx.coroutines.delay

@Composable
fun TotalEstimate(
    showDialog: Boolean = false,
    onDismissRequest: () -> Unit,
    onGoBackHome: () -> Unit
) {
    if (!showDialog) return

    val boxSlideOffset = remember { Animatable(initialValue = 1000f) }
    val textFadeAlpha = remember { Animatable(initialValue = 0f) }

    var amount by remember { mutableIntStateOf(1200) }
    val targetAmount = 1460
    var animationStarted by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        boxSlideOffset.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
        )
        textFadeAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
        )
        animationStarted = true
    }

    LaunchedEffect(animationStarted) {
        if (animationStarted) {
            for (i in amount..targetAmount) {
                amount = i
                delay(5)
            }
        }
    }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_movemate_icon),
                    contentDescription = "subscription icon",
                    modifier = Modifier
                        .height(80.dp)
                        .width(200.dp)
                        .offset(y = boxSlideOffset.value.dp)
                )

                Spacer(modifier = Modifier.height(40.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_grey_box_icon),
                    contentDescription = "subscription icon",
                    modifier = Modifier
                        .size(100.dp)
                        .offset(y = boxSlideOffset.value.dp)
                )

                Column {
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Total Estimated Amount",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .alpha(textFadeAlpha.value)
                            .offset(y = boxSlideOffset.value.dp)
                    )

                    Text(
                        text = "$ $amount",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFF77CCA4),
                            fontSize = 20.sp
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .alpha(textFadeAlpha.value)
                            .offset(y = boxSlideOffset.value.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "This amount is estimated and will vary if you change location or weight",
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.outlineVariant),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .padding(horizontal = 16.dp)
                            .alpha(textFadeAlpha.value)
                            .offset(y = boxSlideOffset.value.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { onGoBackHome() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .alpha(textFadeAlpha.value)
                            .offset(y = boxSlideOffset.value.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    ) {
                        Text(
                            text = "Back to Home",
                            modifier = Modifier.padding(5.dp),
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}
