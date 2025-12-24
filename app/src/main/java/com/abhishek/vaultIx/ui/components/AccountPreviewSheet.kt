package com.abhishek.vaultIx.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhishek.vaultIx.data.local.entity.PasswordEntity
import com.abhishek.vaultIx.ui.theme.bgColor
import com.abhishek.vaultIx.ui.theme.black
import com.abhishek.vaultIx.ui.theme.blackBtn
import com.abhishek.vaultIx.ui.theme.blue
import com.abhishek.vaultIx.ui.theme.grey
import com.abhishek.vaultIx.ui.theme.red
import com.abhishek.vaultIx.ui.theme.sfProDisplayFont
import com.abhishek.vaultIx.ui.theme.white
import com.abhishek.vaultIx.util.ValueType

@Composable
fun AccountPreviewSheet(
    modifier: Modifier = Modifier,
    passwordEntity: PasswordEntity,
    onEdit: (id: Int) -> Unit,
    onDelete: (id: Int) -> Unit
) {
    var visiblity by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .background(color = bgColor)
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        Text(
            "Account Details",
            color = blue,
            fontFamily = sfProDisplayFont,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        AccountPreviewItem(
            title = "Account Type",
            value = passwordEntity.accountName
        )
        AccountPreviewItem(
            title = "Username/Email",
            value = passwordEntity.accountId
        )
        AccountPreviewItem(
            title = "Password",
            value = passwordEntity.accountPassword,
            valueType = ValueType.PASSWORD,
            valueVisible = visiblity
        ){
            visiblity = it
        }

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Button(
                onClick = { onEdit(passwordEntity.id!!) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = blackBtn,
                    contentColor = white
                ),
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier
                    .weight(0.4f)
            ) {
                Text(
                    "Edit",
                    fontFamily = sfProDisplayFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Spacer(
                modifier = Modifier
                    .weight(0.1f)
            )
            Button(
                onClick = { onDelete(passwordEntity.id!!) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = red,
                    contentColor = white
                ),
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier
                    .weight(0.4f)
            ) {
                Text(
                    "Delete",
                    fontFamily = sfProDisplayFont,                                  
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun AccountPreviewItem(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    valueType: ValueType = ValueType.TEXT,
    valueVisible: Boolean = true,
    onVisibilityChange : (Boolean) -> Unit = {}
) {
    Row(
        modifier = modifier
    ) {
        Column (
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ){
            Text(
                text = title,
                fontFamily = sfProDisplayFont,
                color = grey,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
            Text(
                text = if (!valueVisible && valueType == ValueType.PASSWORD) "••••••••" else value,
                fontFamily = sfProDisplayFont,
                color = black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
        if (valueType == ValueType.PASSWORD){
            val visibilityIcon = if (valueVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
            IconButton(
                onClick = {onVisibilityChange(!valueVisible)}
            ) {
                Icon(
                    imageVector = visibilityIcon,
                    contentDescription = "Password Visibility",
                    tint = grey
                )
            }
        }
    }
    
}

