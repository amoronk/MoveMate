package com.amoronk.movemate.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amoronk.movemate.R

@Composable
fun SearchBar(
    isClickable: Boolean = false,
    onSearchClick: () -> Unit = {},
    searchQuery: String = "",
    onValueChange: (String) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(16.dp)
            .let {
                if (isClickable) it.clickable { onSearchClick() } else it
            },
        contentAlignment = Alignment.Center
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onValueChange,
            enabled = isClickable.not(),
            shape = RoundedCornerShape(30.dp),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search_icon),
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            trailingIcon = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    IconButton(
                        onClick = { /* Handle QR scanner click */ },
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.secondary,
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_qr_scanner_icon),
                            contentDescription = "QR",
                            tint = Color.White
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                if (isClickable) Text(text = "Enter the receipt number ...")
            },
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp
            ),
            colors = TextFieldDefaults.colors()
        )
    }
}
