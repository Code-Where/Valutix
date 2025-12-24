package com.abhishek.vaultIx.ui.screens

import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.abhishek.vaultIx.R
import com.abhishek.vaultIx.data.local.entity.PasswordEntity
import com.abhishek.vaultIx.ui.components.AccountPreviewSheet
import com.abhishek.vaultIx.ui.components.AddAccountSheet
import com.abhishek.vaultIx.ui.components.EmptyListUi
import com.abhishek.vaultIx.ui.components.LockedUi
import com.abhishek.vaultIx.ui.components.PasswordItem
import com.abhishek.vaultIx.ui.states.AddPasswordUiState
import com.abhishek.vaultIx.ui.states.BiometricState
import com.abhishek.vaultIx.ui.states.HomeProcessState
import com.abhishek.vaultIx.ui.theme.bgColor
import com.abhishek.vaultIx.ui.theme.black
import com.abhishek.vaultIx.ui.theme.blue
import com.abhishek.vaultIx.ui.viewmodels.HomeViewModel
import com.abhishek.vaultIx.util.BiometricHelper


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    activity: FragmentActivity,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val addPasswordUiState by viewModel.addPasswordUiState.collectAsState()
    val biometricState by viewModel.biometricState.collectAsState()

    val context = LocalContext.current

    val addPassSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val passPrevSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    LaunchedEffect(uiState.processState) {
        when(uiState.processState) {
            is HomeProcessState.Error -> {
                Toast.makeText(context, (uiState.processState as HomeProcessState.Error).message, Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    if (biometricState is BiometricState.Authenticated) {
        Scaffold(
            topBar = { TopBarHome() },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = viewModel::onAddClick,
                    containerColor = blue,
                    contentColor = Color.White,
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Password")
                }
            },
            modifier = Modifier.fillMaxSize()
        )
        { innerPadding ->
            if (uiState.passwordList.isEmpty()) {
                EmptyListUi(Modifier.fillMaxSize().padding(innerPadding)) {
                    viewModel.onAddClick()
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = modifier
                        .padding(innerPadding)
                        .padding(horizontal = 10.dp, vertical = 30.dp)
                ) {
                    items(uiState.passwordList, key = { it.id!! }) { password ->
                        PasswordItem(
                            id = password.id!!,
                            accountType = password.accountName,
                            onItemClick = viewModel::onPasswordClick
                        )
                    }
                }
            }

            if (uiState.selectedPassword != null) {
                AccountPrevBottomSheetHome(
                    bottomSheetState = passPrevSheetState,
                    passwordEntity = uiState.selectedPassword!!,
                    onEdit = { viewModel.onEditClick(uiState.selectedPassword!!) },
                    onDelete = viewModel::onDelete,
                    onDismiss = viewModel::onDismissPreviewSheet
                )
            }

            if (addPasswordUiState.sheetVisible) {
                AccountAddBottomSheetHome(
                    addPasswordUiState = addPasswordUiState,
                    onGeneratePassword = viewModel::onGeneratePassword,
                    onNameChange = viewModel::onNameChange,
                    onIdChange = viewModel::onIdChange,
                    onPasswordChange = viewModel::onPasswordChange,
                    bottomSheetState = addPassSheetState,
                    onDismiss = viewModel::onDismissAddSheet,
                    onSubmit = viewModel::onSubmit
                )
            }
        }
    }
    else {
        LockedUi(
            onBiometricUnlock = {
                BiometricHelper.authenticate(
                    activity = activity,
                    onSuccess = viewModel::unlock,
                    onError = {
                        viewModel.lock()
                    }
                )
            },
            onSetupBiometric = {
                context.startActivity(Intent(Settings.ACTION_SECURITY_SETTINGS))
            },
            onUsePin = {
                viewModel.unlock()
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHome() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = black
            )
        },
        colors = TopAppBarColors(
            bgColor,
            scrolledContainerColor = bgColor,
            navigationIconContentColor = black,
            titleContentColor = black,
            actionIconContentColor = black
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountPrevBottomSheetHome(
    bottomSheetState: SheetState,
    passwordEntity: PasswordEntity,
    onEdit: (id: Int) -> Unit = {},
    onDelete: (id: Int) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    ModalBottomSheet(
        sheetState = bottomSheetState,
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        AccountPreviewSheet(
            passwordEntity = passwordEntity,
            onEdit = onEdit,
            onDelete = onDelete
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountAddBottomSheetHome(
    addPasswordUiState: AddPasswordUiState,
    onGeneratePassword: () -> Unit,
    onNameChange: (String) -> Unit,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    bottomSheetState: SheetState,
    onDismiss: () -> Unit = {},
    onSubmit: () -> Unit,
) {
    ModalBottomSheet(
        sheetState = bottomSheetState,
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        AddAccountSheet(
            addPasswordUiState = addPasswordUiState,
            onGeneratePassword = onGeneratePassword,
            onNameChange = onNameChange,
            onIdChange = onIdChange,
            onPasswordChange = onPasswordChange,
            onSubmit = onSubmit
        )
    }
}
