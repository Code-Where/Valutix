package com.abhishek.vaultIx.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhishek.vaultIx.ui.states.AddPasswordUiState
import com.abhishek.vaultIx.ui.theme.bgColor
import com.abhishek.vaultIx.ui.theme.blackBtn
import com.abhishek.vaultIx.ui.theme.blue
import com.abhishek.vaultIx.ui.theme.green
import com.abhishek.vaultIx.ui.theme.grey
import com.abhishek.vaultIx.ui.theme.orange
import com.abhishek.vaultIx.ui.theme.red
import com.abhishek.vaultIx.ui.theme.sfProDisplayFont
import com.abhishek.vaultIx.ui.theme.white
import com.abhishek.vaultIx.ui.theme.yellow
import com.abhishek.vaultIx.util.PasswordHelper
import com.abhishek.vaultIx.util.PasswordStrength

@Composable
fun AddAccountSheet(
    modifier: Modifier = Modifier,
    addPasswordUiState: AddPasswordUiState,
    onGeneratePassword: () -> Unit,
    onNameChange: (String) -> Unit,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit
) {
    val textFieldsColors = TextFieldDefaults.colors(
        focusedContainerColor = white,
        unfocusedContainerColor = white,
        focusedIndicatorColor = white,
        unfocusedIndicatorColor = white,
        cursorColor = blackBtn,
        focusedTextColor = blackBtn,
        unfocusedTextColor = blackBtn,
        focusedLabelColor = blackBtn,
        unfocusedLabelColor = blackBtn,
    )

    val passwordStrength = PasswordHelper.calculatePasswordStrength(addPasswordUiState.password)

    val (passwordColor, text) = when(passwordStrength) {
        PasswordStrength.WEAK -> red to "Weak"
        PasswordStrength.MEDIUM -> yellow to "Medium"
        PasswordStrength.STRONG -> orange to "Strong"
        PasswordStrength.VERY_STRONG -> green to "Very Strong"
    }

    Column(
        modifier = modifier
            .background(color = bgColor)
            .padding(horizontal = 20.dp, vertical = 30.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        TextField(
            value = addPasswordUiState.accountName,
            onValueChange = onNameChange,
            label = { Text(text = "Account Name") },
            colors = textFieldsColors,
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    1.dp,
                    grey,
                    RoundedCornerShape(10.dp)
                )

        )
        TextField(
            value = addPasswordUiState.accountId,
            onValueChange = onIdChange,
            label = { Text(text = "Username/Email") },
            colors = textFieldsColors,
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    1.dp,
                    grey,
                    RoundedCornerShape(10.dp)
                )

        )
        Column (verticalArrangement = Arrangement.spacedBy(4.dp)){
            TextField(
                value = addPasswordUiState.password,
                onValueChange = onPasswordChange,
                label = { Text(text = "Password") },
                colors = textFieldsColors,
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                trailingIcon = {
                    Row {
                        Text(
                            text = "Generate",
                            fontSize = 12.sp,
                            color = white,
                            fontFamily = sfProDisplayFont,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clickable {
                                    onGeneratePassword()
                                }
                                .background(blue, RoundedCornerShape(50.dp))
                                .padding(vertical = 2.dp, horizontal = 7.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        1.dp,
                        color = passwordColor,
                        RoundedCornerShape(10.dp)
                    )
            )

            Text(
                text = text,
                fontSize = 14.sp,
                color = passwordColor,
                fontFamily = sfProDisplayFont,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 5.dp)
            )
        }


        PasswordTips(password = addPasswordUiState.password, modifier = Modifier.fillMaxWidth(0.8f))

        Button(
            onClick = onSubmit,
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = blackBtn,
                contentColor = white
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                if(addPasswordUiState.id == null) "Add New Account" else "Update Account",
                fontFamily = sfProDisplayFont,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        }
    }
}

