package com.abhishek.vaultIx.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhishek.vaultIx.ui.theme.black
import com.abhishek.vaultIx.ui.theme.grey
import com.abhishek.vaultIx.ui.theme.sfProDisplayFont
import com.abhishek.vaultIx.ui.theme.white

@Composable
fun PasswordItem(
    modifier: Modifier = Modifier,
    id: Int,
    accountType: String,
    onItemClick: (id: Int) -> Unit
) {
    Card (
        shape = RoundedCornerShape(50.dp),
        colors = CardColors(
            containerColor = white,
            contentColor = white,
            disabledContainerColor = white,
            disabledContentColor = white
        ),
        border = BorderStroke(1.dp, grey),
        modifier = Modifier
            .clickable(
                onClick = { onItemClick(id) }
            )
    ){
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = accountType,
                    fontFamily = sfProDisplayFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    color = black
                )

                Text(
                    text = "•••••••",
                    fontFamily = sfProDisplayFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    color = grey,
                    modifier = Modifier
                        .padding(start = 12.dp)
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = black
            )
        }

    }
}
