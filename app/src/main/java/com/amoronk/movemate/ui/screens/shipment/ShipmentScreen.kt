package com.amoronk.movemate.ui.screens.shipment

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amoronk.movemate.R
import com.amoronk.movemate.ui.components.StandardToolbar
import com.amoronk.movemate.ui.theme.MoniePointChallengeTheme
import com.amoronk.movemate.ui.theme.Theme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import kotlin.random.Random

@Destination
@Composable
fun ShipmentHistoryScreen(
    navigator: DestinationsNavigator
) {
    val shipments = generateRandomShipments()

    val topBarOffset = remember { Animatable(-1000f) }
    val tabRowOffset = remember { Animatable(-1000f) }
    val animateShipmentsText = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        topBarOffset.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing)
        )
        tabRowOffset.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearOutSlowInEasing
            )
        )
    }

    Scaffold(
        topBar = {
            StandardToolbar(
                title = {
                    Text(
                        text = "Shipment History",
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
                    )
                },
                showBackArrow = true,
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.offset(y = topBarOffset.value.dp),
                navigate = {
                    navigator.popBackStack()
                }
            )
        }
    ) { paddingValues ->

        val allShipments = shipments.size
        val completedShipments = shipments.count { it.status == ShipmentStatus.Completed }
        val inProgressShipments = shipments.count { it.status == ShipmentStatus.InProgress }
        val pendingShipments = shipments.count { it.status == ShipmentStatus.Pending }
        val cancelledShipments = shipments.count { it.status == ShipmentStatus.Canceled }

        val tabs = listOf(
            "All" to allShipments,
            "Completed" to completedShipments,
            "In progress" to inProgressShipments,
            "Pending" to pendingShipments,
            "Cancelled" to cancelledShipments
        )

        var selectedTabIndex by remember { mutableIntStateOf(0) }

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                edgePadding = 16.dp,
                containerColor = MaterialTheme.colorScheme.primary,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectedTabIndex])
                            .fillMaxWidth(),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            ) {
                tabs.forEachIndexed { index, (tabTitle, tabCount) ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = {
                            selectedTabIndex = index
                            if (index == 0) animateShipmentsText.value = false
                        },
                        modifier = Modifier
                            .padding(10.dp)
                            .offset(
                                x = -tabRowOffset.value.dp,
                                y = tabRowOffset.value.dp
                            )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = tabTitle,
                                style = if (selectedTabIndex == index)
                                    MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold)
                                else
                                    MaterialTheme.typography.labelLarge,
                                color = if (selectedTabIndex == index)
                                    Color.White
                                else
                                    MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
                                modifier = Modifier.padding(end = 8.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .background(
                                        color = if (selectedTabIndex == index)
                                            MaterialTheme.colorScheme.secondary
                                        else
                                            MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f),
                                        shape = RoundedCornerShape(50)
                                    )
                                    .padding(horizontal = 12.dp)
                            ) {
                                Text(
                                    text = tabCount.toString(),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = if (selectedTabIndex == index) Color.White else MaterialTheme.colorScheme.onPrimary.copy(
                                        alpha = 0.7f
                                    )
                                )
                            }
                        }
                    }
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item {
                    if (selectedTabIndex == 0) {
                        if (animateShipmentsText.value) {
                            AnimatedVisibility(
                                visible = true,
                                enter = slideInVertically(
                                    initialOffsetY = { it },
                                    animationSpec = tween(600)
                                )
                            ) {
                                Text(
                                    "Shipments",
                                    style = MaterialTheme.typography.titleLarge.copy(
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                                )
                            }
                        } else {
                            Text(
                                "Shipments",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.SemiBold
                                ),
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                        }
                    } else {
                        Text(
                            "Shipments",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }

                items(
                    when (selectedTabIndex) {
                        0 -> shipments
                        1 -> shipments.filter { it.status == ShipmentStatus.Completed }
                        2 -> shipments.filter { it.status == ShipmentStatus.InProgress }
                        3 -> shipments.filter { it.status == ShipmentStatus.Pending }
                        4 -> shipments.filter { it.status == ShipmentStatus.Canceled }
                        else -> emptyList()
                    }
                ) { shipment ->
                    AnimatedShipmentItem(shipmentData = shipment)
                }
            }
        }
    }
}

@Composable
fun AnimatedShipmentItem(shipmentData: ShipmentData) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(durationMillis = 500)
        ),
        exit = slideOutVertically(
            targetOffsetY = { it },
            animationSpec = tween(durationMillis = 300)
        )
    ) {
        ShipmentItem(shipmentData = shipmentData)
    }
}


@Composable
fun ShipmentItem(shipmentData: ShipmentData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor =if (isSystemInDarkTheme()) Color.Black else Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 50.dp)
            ) {
                Row(
                    modifier = Modifier
                        .background(
                            color = Color.Gray.copy(alpha = 0.08f),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(
                            id = when (shipmentData.status) {
                                ShipmentStatus.Loading -> R.drawable.ic_loading_icon
                                ShipmentStatus.InProgress -> R.drawable.ic_in_progress_icon
                                ShipmentStatus.Pending -> R.drawable.ic_shipment_history_icon
                                ShipmentStatus.Completed -> R.drawable.ic_complete_icon
                                ShipmentStatus.Canceled -> R.drawable.ic_cancelled_icon
                            }
                        ),
                        tint = when (shipmentData.status) {
                            ShipmentStatus.Loading -> Color(0xFF0277BD)
                            ShipmentStatus.InProgress -> Color(0xFF77CCA4)
                            ShipmentStatus.Pending -> Color(0xFFDA955D)
                            ShipmentStatus.Completed -> Color.Green
                            ShipmentStatus.Canceled -> Color.Red
                        },
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = shipmentData.status.data,
                        color = when (shipmentData.status) {
                            ShipmentStatus.Loading -> Color(0xFF0277BD)
                            ShipmentStatus.InProgress -> Color(0xFF77CCA4)
                            ShipmentStatus.Pending -> Color(0xFFDA955D)
                            ShipmentStatus.Completed -> Color.Green
                            ShipmentStatus.Canceled -> Color.Red
                        },
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    shipmentData.title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    shipmentData.description,
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.outlineVariant)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    Text(
                        text = shipmentData.amount,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = if (isSystemInDarkTheme()) Color.White else MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "• ${shipmentData.date}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = if (isSystemInDarkTheme()) Color.White else  MaterialTheme.typography.bodyMedium.color.copy(alpha = 0.7f)
                        )
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.ic_grey_box_icon),
                contentDescription = "Grey Box",
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 8.dp)
            )
        }
    }
}

fun generateRandomShipments(count: Int = 10): List<ShipmentData> {
    val statuses = ShipmentStatus.entries
    return List(count) {
        ShipmentData(
            title = "Arriving today!",
            description = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
            amount = "$1400 USD",
            date = "Sep 20, 2023",
            status = statuses[Random.nextInt(statuses.size)]
        )
    }
}

data class ShipmentData(
    val id: Long = Random.nextLong(),
    val title: String,
    val description: String,
    val date: String,
    val amount: String,
    val status: ShipmentStatus
)

enum class ShipmentStatus(val data: String) {
    Loading("loading"),
    InProgress("in-progress"),
    Pending("pending"),
    Completed("completed"),
    Canceled("canceled");
}

@Preview(showBackground = true)
@Composable
fun ShipmentViewPreview() {
    MoniePointChallengeTheme(theme = Theme.FOLLOW_SYSTEM.themeValue) {
        ShipmentHistoryScreen(navigator = EmptyDestinationsNavigator)
    }
}
