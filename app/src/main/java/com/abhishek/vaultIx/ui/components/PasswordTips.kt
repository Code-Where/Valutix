package com.abhishek.vaultIx.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhishek.vaultIx.ui.theme.green
import com.abhishek.vaultIx.ui.theme.red
import com.abhishek.vaultIx.ui.theme.sfProDisplayFont

@Composable
fun PasswordTips(
    password: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {

        val lengthOk = password.length >= 8
        val longLengthOk = password.length >= 12
        val upperOk = password.any { it.isUpperCase() }
        val lowerOk = password.any { it.isLowerCase() }
        val numberOk = password.any { it.isDigit() }
        val specialOk = password.any { !it.isLetterOrDigit() }


        if (!lengthOk){
            PasswordTipItem(
                text = "At least 8 characters",
            )
        }
        else if (!upperOk) {
            PasswordTipItem(
                text = "Contains uppercase letter",
            )
        }
        else if (!lowerOk) {
            PasswordTipItem(
                text = "Contains lowercase letter"
            )
        }
        else if (!numberOk) {
            PasswordTipItem(
                text = "Contains number"
            )
        }
        else if (!specialOk) {
            PasswordTipItem(
                text = "Contains special character"
            )
        }
        else if (!longLengthOk) {
            PasswordTipItem(
                text = "At least 12 characters for stronger password"
            )
        }
        else {
            PasswordTipItem(
                text = "Password is strong",
                satisfied = true
            )
        }
    }
}


@Composable
fun PasswordTipItem(
    text: String,
    satisfied: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            if(satisfied) Icons.Default.CheckCircle
            else Icons.Default.RadioButtonUnchecked,
            contentDescription = null,
            tint = if(satisfied) green else red,
            modifier = Modifier.size(16.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            fontFamily = sfProDisplayFont,
            fontSize = 13.sp,
            color = if(satisfied) green else red
        )
    }
}
