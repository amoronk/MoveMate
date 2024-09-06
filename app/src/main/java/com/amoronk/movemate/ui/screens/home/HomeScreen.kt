package com.amoronk.movemate.ui.screens.home

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amoronk.movemate.R
import com.amoronk.movemate.ui.components.SearchBar
import com.amoronk.movemate.ui.components.StandardToolbar
import com.amoronk.movemate.ui.theme.MoniePointChallengeTheme
import com.amoronk.movemate.ui.theme.Theme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    val transitionState = remember { MutableTransitionState(false).apply { targetState = true } }

    Scaffold(
        topBar = {
            AnimatedVisibility(
                visibleState = transitionState,
                enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
                exit = fadeOut()
            ) {
                StandardToolbar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_default_error_image),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(50.dp)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_location_icon),
                                        tint = MaterialTheme.colorScheme.outlineVariant,
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp)
                                    )

                                    Spacer(modifier = Modifier.width(4.dp))

                                    Text(
                                        text = "Your location",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = Color.White.copy(alpha = 0.8f)
                                        )
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Lagos, Nigeria",
                                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
                                    )

                                    Spacer(modifier = Modifier.width(4.dp))

                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowDown,
                                        tint = Color.White,
                                        contentDescription = null,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.primary,
                    navActions = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            IconButton(
                                onClick = { /* Handle bell icon click */ },
                                modifier = Modifier
                                    .background(
                                        color = if (isSystemInDarkTheme()) Color.Black else Color.White,
                                        shape = CircleShape
                                    )
                                    .size(30.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_notification_icon),
                                    contentDescription = "Notification",
                                    modifier = Modifier.size(20.dp),
                                    tint = if (isSystemInDarkTheme()) Color.White else MaterialTheme.colorScheme.tertiary
                                )
                            }
                        }
                    }
                )
            }
        },
        bottomBar = { BottomNavBar(navigator, "home_screen") }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            item {
                AnimatedVisibility(
                    visibleState = transitionState,
                    enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
                    exit = fadeOut()
                ) {
                    SearchBar(
                        isClickable = true,
                        onSearchClick = { navigator.navigate("search_screen") }
                    )
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Tracking",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    AnimatedVisibility(
                        visibleState = transitionState,
                        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                        exit = fadeOut()
                    ) {
                        TrackingCard()
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    AnimatedVisibility(
                        visibleState = transitionState,
                        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                        exit = fadeOut()
                    ) {
                        AvailableVehiclesSection()
                    }
                }
            }
        }
    }
}



@Composable
fun TrackingCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = if (isSystemInDarkTheme()) Color.Black else Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .background(color = if (isSystemInDarkTheme()) Color.Black else Color.White)
        ) {

            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = "Shipment Number",
                            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.outlineVariant)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "NEJ20089934122231",
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.ic_forklift_icon),
                        contentDescription = "ForkLift",
                        modifier = Modifier
                            .size(40.dp)
                            .rotate(-14f)
                            .padding(end = 8.dp)
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))

                HorizontalDivider(
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.1f),
                    thickness = 1.2.dp
                )

                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFEE7D5)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_sender_box_icon),
                                contentDescription = "Sender",
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Column {
                            Text(
                                text = "Sender",
                                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.outlineVariant)
                            )
                            Text(
                                text = "Atlanta, 5243", style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    Column {
                        Text(
                            text = "Time",
                            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.outlineVariant)
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_ellipse_icon),
                                contentDescription = "Ellipse",
                                modifier = Modifier.size(6.dp),
                                tint = Color.Green
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = "2 day - 3 days", style = MaterialTheme.typography.bodyMedium
                            )

                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(25.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFD5F0DF)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_receiver_box_icon),
                                contentDescription = "Sender",
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Column {
                            Text(
                                text = "Receiver",
                                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.outlineVariant)
                            )

                            Text(
                                text = "Chicago, 6342", style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                    Column {
                        Text(
                            text = "Status",
                            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.outlineVariant)
                        )

                        Text(
                            text = "Waiting to collect",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.1f),
                thickness = 1.2.dp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "+ Add Stop",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                modifier = Modifier
                    .clickable { /* Handle add stop click */ }
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun AvailableVehiclesSection() {
    Column {
        Text(
            text = "Available vehicles",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            val vehicleData = listOf(
                VehicleData("Ocean freight", "International", R.drawable.ic_ocean_freight, 1),
                VehicleData("Cargo freight", "Reliable", R.drawable.ic_cargo_freight, 2),
                VehicleData("Air freight", "International", R.drawable.ic_air_freight, 3)
            )

            items(vehicleData, key = {
                it.id
            }) { data ->
                VehicleCard(data.title, data.description, data.image)

                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

data class VehicleData(
    val title: String,
    val description: String,
    val image: Int,
    val id: Int
)

@Composable
fun VehicleCard(title: String, subtitle: String, imageRes: Int) {

    val transitionState = remember { MutableTransitionState(false).apply { targetState = true } }

    Card(
        modifier = Modifier
            .height(220.dp)
            .width(150.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(modifier = Modifier.align(Alignment.BottomEnd)) {
                AnimatedVisibility(
                    visibleState = transitionState,
                    enter = slideInHorizontally(initialOffsetX = { -it }) + fadeIn(),
                    exit = fadeOut()
                ) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(180.dp)

                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(modifier = Modifier.padding(6.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp)
                )

                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.outlineVariant)
                )
            }
        }
    }
}


@Composable
fun BottomNavBar(navigator: DestinationsNavigator, selectedRoute: String) {

    val colors = NavigationBarItemColors(
        selectedIconColor = MaterialTheme.colorScheme.primary,
        selectedTextColor = MaterialTheme.colorScheme.primary,
        unselectedIconColor = MaterialTheme.colorScheme.outlineVariant,
        unselectedTextColor = MaterialTheme.colorScheme.outlineVariant,
        disabledIconColor = MaterialTheme.colorScheme.outlineVariant,
        disabledTextColor = MaterialTheme.colorScheme.outlineVariant,
        selectedIndicatorColor = Color.Transparent
    )

    NavigationBar(
        containerColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
        contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    ) {
        val items = listOf(
            BottomNavItem("home_screen", "Home", R.drawable.icon_home_icon),
            BottomNavItem("calculate_screen", "Calculate", R.drawable.ic_calculate_icon),
            BottomNavItem(
                "shipment_history_screen",
                "Shipment",
                R.drawable.ic_shipment_history_icon
            ),
            BottomNavItem("profile", "Profile", R.drawable.ic_profile_icon)
        )

        items.forEach { item ->
            val isSelected = item.route == selectedRoute
            val indicatorColor =
                if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent

            NavigationBarItem(
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .height(4.dp)
                                .fillMaxWidth()
                                .background(indicatorColor)
                                .align(Alignment.CenterHorizontally)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.label,
                            tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
                        )
                    }
                },
                label = {
                    Text(
                        item.label,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
                    )
                },
                selected = isSelected,
                onClick = { if (item.route != "profile") navigator.navigate(item.route) },
                alwaysShowLabel = true,
                colors = colors
            )
        }
    }
}

data class BottomNavItem(val route: String, val label: String, @DrawableRes val icon: Int)

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MoniePointChallengeTheme(theme = Theme.FOLLOW_SYSTEM.themeValue) {
        HomeScreen(navigator = EmptyDestinationsNavigator)
    }
}
