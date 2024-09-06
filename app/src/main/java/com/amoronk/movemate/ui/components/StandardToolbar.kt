package com.amoronk.movemate.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.amoronk.movemate.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardToolbar(
    modifier: Modifier = Modifier,
    navigate: () -> Unit = {},
    showBackArrow: Boolean = false,
    iconColor: Color = Color.White,
    containerColor: Color = Color.Transparent,
    navActions: @Composable RowScope.() -> Unit = {},
    title: @Composable () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = title,
        navigationIcon = {
            if (showBackArrow) {
                IconButton(
                    onClick = {
                        navigate()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back_icon),
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        },
        actions = navActions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor
        )
    )
}
