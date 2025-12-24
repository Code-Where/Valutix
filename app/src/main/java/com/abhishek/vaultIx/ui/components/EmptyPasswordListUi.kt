package com.abhishek.vaultIx.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhishek.vaultIx.ui.theme.black
import com.abhishek.vaultIx.ui.theme.blue
import com.abhishek.vaultIx.ui.theme.grey
import com.abhishek.vaultIx.ui.theme.sfProDisplayFont
import com.abhishek.vaultIx.ui.theme.white

@Composable
fun EmptyListUi(modifier: Modifier = Modifier, onAddClick: () -> Unit) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            imageVector = Icons.Outlined.Lock,
            contentDescription = "Lock Icon in Empty List",
            tint = blue,
            modifier = Modifier.size(96.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Your vault is empty",
            fontFamily = sfProDisplayFont,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Start by adding your first password.\nYour data stays safe on this device.",
            fontFamily = sfProDisplayFont,
            fontSize = 15.sp,
            color = grey,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = onAddClick,
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = blue,
                contentColor = white
            ),
            modifier = Modifier
                .height(48.dp)
                .padding(horizontal = 24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add First Password"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Add Password",
                fontFamily = sfProDisplayFont,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

