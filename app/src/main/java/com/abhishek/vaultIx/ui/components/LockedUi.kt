package com.abhishek.vaultIx.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhishek.vaultIx.util.BiometricHelper
import com.abhishek.vaultIx.util.BiometricStatus

@Composable
fun LockedUi(
    onBiometricUnlock: () -> Unit,
    onSetupBiometric: () -> Unit,
    onUsePin: () -> Unit
) {
    val context = LocalContext.current
    val biometricStatus = remember {
        BiometricHelper.getBiometricStatus(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "🔒",
            fontSize = 56.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "App Locked",
            fontSize = 22.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Authenticate to access your passwords",
            fontSize = 14.sp,
            color = androidx.compose.ui.graphics.Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        when (biometricStatus) {

            is BiometricStatus.Available -> {
                Button(
                    onClick = onBiometricUnlock
                ) {
                    Text("Unlock with Biometric")
                }
            }

            is BiometricStatus.NotEnrolled -> {
                Text(
                    text = "No Security Setup Found on this device",
                    fontSize = 14.sp,
                    color = androidx.compose.ui.graphics.Color.Gray,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onSetupBiometric
                ) {
                    Text("Set up")
                }
            }

            is BiometricStatus.NotSupported -> {
                Text(
                    text = "Unlock With Device Pattern or Pin",
                    fontSize = 14.sp,
                    color = androidx.compose.ui.graphics.Color.Gray,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onUsePin
                ) {
                    Text("Unlock")
                }
            }
        }
    }
}

@Composable
private fun TextButtonSmall(
    text: String,
    onClick: () -> Unit
) {
    androidx.compose.material3.TextButton(onClick = onClick) {
        Text(
            text = text,
            fontSize = 13.sp,
            color = androidx.compose.ui.graphics.Color.Gray
        )
    }
}
