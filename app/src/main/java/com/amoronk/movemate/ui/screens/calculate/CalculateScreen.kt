package com.amoronk.movemate.ui.screens.calculate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.amoronk.movemate.ui.screens.calculate.dialog.TotalEstimate
import com.amoronk.movemate.ui.theme.MoniePointChallengeTheme
import com.amoronk.movemate.ui.theme.Theme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination
@Composable
fun CalculateScreen(
    navigator: DestinationsNavigator
) {
    var selectedItems by remember { mutableStateOf(setOf<CategoryTag>()) }
    var senderLocation by remember { mutableStateOf("") }
    var receiverLocation by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var showTotalEstimateDialog by remember { mutableStateOf(false) }

    var showAnimations by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        showAnimations = true
    }

    Scaffold(
        topBar = {
            StandardToolbar(
                title = {
                    Text(
                        text = "Calculate",
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
                    )
                },
                showBackArrow = true,
                containerColor = MaterialTheme.colorScheme.primary,
                navigate = {
                    navigator.popBackStack()
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        ) {
            item {
                Spacer(modifier = Modifier.height(24.dp))

                AnimatedVisibility(
                    visible = showAnimations,
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(600)
                    )
                ) {
                    Text(
                        text = "Destination",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                AnimatedVisibility(
                    visible = showAnimations,
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(600)
                    )
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = if (isSystemInDarkTheme()) Color.Black else Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {

                        Column(modifier = Modifier.padding(16.dp)) {

                            Spacer(modifier = Modifier.height(16.dp))

                            val backgroundColor = Color(0xFFF8F8F8)

                            OutlinedTextField(
                                value = senderLocation,
                                onValueChange = { senderLocation = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(backgroundColor, shape = RoundedCornerShape(15.dp))
                                    .height(56.dp),
                                leadingIcon = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            painterResource(id = R.drawable.ic_send_icon),
                                            contentDescription = "Sender location icon",
                                            tint = MaterialTheme.colorScheme.outlineVariant,
                                            modifier = Modifier.padding(start = 12.dp, end = 8.dp)
                                        )

                                        VerticalDivider(
                                            color = Color.LightGray,
                                            modifier = Modifier
                                                .height(24.dp)
                                                .width(1.dp)
                                        )
                                    }
                                },
                                placeholder = { Text("Sender location", color = Color.Gray) },
                                colors = OutlinedTextFieldDefaults.colors(
                                    disabledBorderColor = Color.Transparent,
                                    disabledContainerColor = backgroundColor,
                                    focusedBorderColor = Color.Transparent,
                                    unfocusedBorderColor = Color.Transparent
                                ),
                                singleLine = true,
                                textStyle = LocalTextStyle.current.copy(
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            OutlinedTextField(
                                value = receiverLocation,
                                onValueChange = { receiverLocation = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(backgroundColor, shape = RoundedCornerShape(15.dp))
                                    .height(56.dp),
                                leadingIcon = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            painterResource(id = R.drawable.ic_receive_icon),
                                            contentDescription = "Receiver location icon",
                                            tint = MaterialTheme.colorScheme.outlineVariant,
                                            modifier = Modifier.padding(start = 12.dp, end = 8.dp)
                                        )
                                        VerticalDivider(
                                            color = Color.LightGray,
                                            modifier = Modifier
                                                .height(24.dp)
                                                .width(1.dp)
                                        )
                                    }
                                },
                                placeholder = { Text("Receiver location", color = Color.Gray) },
                                colors = OutlinedTextFieldDefaults.colors(
                                    disabledBorderColor = Color.Transparent,
                                    disabledContainerColor = backgroundColor,
                                    focusedBorderColor = Color.Transparent,
                                    unfocusedBorderColor = Color.Transparent
                                ),
                                singleLine = true,
                                textStyle = LocalTextStyle.current.copy(
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            OutlinedTextField(
                                value = weight,
                                onValueChange = { weight = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(backgroundColor, shape = RoundedCornerShape(15.dp))
                                    .height(56.dp),
                                leadingIcon = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            painterResource(id = R.drawable.ic_scale_icon),
                                            contentDescription = "Weight icon",
                                            tint = MaterialTheme.colorScheme.outlineVariant,
                                            modifier = Modifier.padding(start = 12.dp, end = 8.dp)
                                        )
                                        VerticalDivider(
                                            color = Color.LightGray,
                                            modifier = Modifier
                                                .height(24.dp)
                                                .width(1.dp)
                                        )
                                    }
                                },
                                placeholder = { Text("Approx weight", color = Color.Gray) },
                                colors = OutlinedTextFieldDefaults.colors(
                                    disabledBorderColor = Color.Transparent,
                                    disabledContainerColor = backgroundColor,
                                    focusedBorderColor = Color.Transparent,
                                    unfocusedBorderColor = Color.Transparent
                                ),
                                singleLine = true,
                                textStyle = LocalTextStyle.current.copy(
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                AnimatedVisibility(
                    visible = showAnimations,
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(600)
                    )
                ) {
                    Column {
                        Text(
                            text = "Packaging",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 17.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "What are you sending?",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.outlineVariant
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        DropdownMenuField()
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                AnimatedVisibility(
                    visible = showAnimations,
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(600)
                    )
                ) {
                    Column {
                        Text(
                            text = "Categories",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 17.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "What are you sending?",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.outlineVariant
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

            item {
                AnimatedVisibility(
                    visible = showAnimations,
                    enter = slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(600)
                    )
                ) {
                    CategoryGrid(
                        items = listOf(
                            CategoryTag("Documents"),
                            CategoryTag("Glass"),
                            CategoryTag("Liquid"),
                            CategoryTag("Food"),
                            CategoryTag("Electronic"),
                            CategoryTag("Product"),
                            CategoryTag("Others")
                        ),
                        selectedItems = selectedItems,
                        onToggleItem = { tag ->
                            selectedItems = if (selectedItems.contains(tag)) {
                                selectedItems - tag
                            } else {
                                selectedItems + tag
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                AnimatedVisibility(
                    visible = showAnimations,
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(600)
                    )
                ) {
                    Button(
                        onClick = { showTotalEstimateDialog = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        colors = ButtonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    ) {
                        Text(
                            text = "Calculate",
                            modifier = Modifier.padding(5.dp),
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp)
                        )
                    }
                }
            }
        }
    }

    TotalEstimate(
        showDialog = showTotalEstimateDialog,
        onDismissRequest = { showTotalEstimateDialog = false }) {
        navigator.popBackStack()
        showTotalEstimateDialog = false
    }
}

@Composable
fun DropdownMenuField() {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Box") }

    Box {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonColors(
                containerColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
                contentColor = MaterialTheme.colorScheme.outlineVariant,
                disabledContainerColor = Color.White,
                disabledContentColor = MaterialTheme.colorScheme.outlineVariant
            ),
            shape = RoundedCornerShape(12.dp),
            border = null
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.weight(1f)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_grey_box_icon),
                        contentDescription = "Grey Box",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 8.dp, bottom = 4.dp)
                    )

                    VerticalDivider(
                        color = Color.LightGray,
                        modifier = Modifier
                            .height(24.dp)
                            .width(1.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        selectedOption,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    )
                }
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    tint = MaterialTheme.colorScheme.outlineVariant,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Box") },
                onClick = {
                    selectedOption = "Box"
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Envelope") },
                onClick = {
                    selectedOption = "Envelope"
                    expanded = false
                }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryGrid(
    items: List<CategoryTag>,
    selectedItems: Set<CategoryTag>,
    onToggleItem: (CategoryTag) -> Unit
) {
    FlowRow(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        items.forEach { item ->
            CategoryTagBubble(
                item = item,
                isSelected = selectedItems.contains(item),
                onToggleItem = onToggleItem
            )
        }
    }
}

@Composable
fun CategoryTagBubble(
    item: CategoryTag,
    isSelected: Boolean,
    onToggleItem: (CategoryTag) -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = if (isSelected) Color(0xFF132333) else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.12f
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onToggleItem(item) }
            .padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (isSelected) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_done_icon),
                    modifier = Modifier.size(16.dp),
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            Text(
                text = item.category,
                fontSize = 14.sp,
                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

data class CategoryTag(val category: String)

@Preview(showBackground = true)
@Composable
fun PreviewShipmentScreen() {
    MoniePointChallengeTheme(theme = Theme.FOLLOW_SYSTEM.themeValue) {
        CalculateScreen(navigator = EmptyDestinationsNavigator)
    }
}
