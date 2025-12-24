package com.abhishek.vaultIx.ui.viewmodels

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.abhishek.vaultIx.data.local.entity.PasswordEntity
import com.abhishek.vaultIx.domain.PasswordRepository
import com.abhishek.vaultIx.ui.states.AddPasswordUiState
import com.abhishek.vaultIx.ui.states.BiometricState
import com.abhishek.vaultIx.ui.states.HomeProcessState
import com.abhishek.vaultIx.ui.states.HomeUiState
import com.abhishek.vaultIx.util.PasswordHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val passwordRepository: PasswordRepository
) : ViewModel(), DefaultLifecycleObserver {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    private val _addPasswordUiState = MutableStateFlow(AddPasswordUiState())
    val addPasswordUiState: StateFlow<AddPasswordUiState> = _addPasswordUiState

    private val _biometricState = MutableStateFlow<BiometricState>(BiometricState.Locked)
    val biometricState: StateFlow<BiometricState> = _biometricState


    init {
        observePasswords()
    }

    private fun observePasswords() {
        passwordRepository.getAllPasswords()
            .onEach { list ->
                _uiState.update {
                    it.copy(
                        passwordList = list,
                        processState = HomeProcessState.Success(list)
                    )
                }
            }
            .catch { e ->
                _uiState.update {
                    it.copy(
                        processState = HomeProcessState.Error(e.message ?: "Unknown error")
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun onPasswordClick(id: Int) {
        _uiState.update {
            it.copy(
                selectedPassword = it.passwordList.firstOrNull { p -> p.id == id }
            )
        }
    }

    fun onDismissPreviewSheet() {
        _uiState.update { it.copy(selectedPassword = null) }
    }

    fun onAddClick() {
        _addPasswordUiState.value = AddPasswordUiState(sheetVisible = true)
    }

    fun onDismissAddSheet() {
        _addPasswordUiState.value = AddPasswordUiState()
    }

    fun onEditClick(password: PasswordEntity) {
        _addPasswordUiState.value = AddPasswordUiState(
            id = password.id,
            accountName = password.accountName,
            accountId = password.accountId,
            password = password.accountPassword,
            sheetVisible = true
        )
    }

    fun onNameChange(value: String) {
        _addPasswordUiState.update { it.copy(accountName = value) }
    }

    fun onIdChange(value: String) {
        _addPasswordUiState.update { it.copy(accountId = value) }
    }

    fun onPasswordChange(value: String) {
        _addPasswordUiState.update { it.copy(password = value) }
    }

    fun onGeneratePassword() {
        _addPasswordUiState.update { it.copy(password = PasswordHelper.generatePassword(12)) }
    }

    fun onSubmit(){
        if (_addPasswordUiState.value.accountName.isEmpty()) {
            _uiState.update {
                it.copy(
                    processState = HomeProcessState.Error("Account name cannot be empty")
                )
            }
            return
        }
        if (_addPasswordUiState.value.accountId.isEmpty()) {
            _uiState.update {
                it.copy(
                    processState = HomeProcessState.Error("Account id cannot be empty")
                )
            }
            return
        }

        if (_addPasswordUiState.value.password.isEmpty()) {
            _uiState.update {
                it.copy(
                    processState = HomeProcessState.Error("Password cannot be empty")
                )
            }
            return
        }

        viewModelScope.launch {

            val state = _addPasswordUiState.value

            if (state.id == null) {
                passwordRepository.insertPassword(
                    PasswordEntity(
                        accountName = state.accountName,
                        accountId = state.accountId,
                        accountPassword = state.password
                    )
                )
            } else {
                passwordRepository.updatePassword(
                    PasswordEntity(
                        id = state.id,
                        accountName = state.accountName,
                        accountId = state.accountId,
                        accountPassword = state.password
                    )
                )
                _uiState.update { it.copy(selectedPassword = passwordRepository.getPasswordById(state.id)) }
            }

            _addPasswordUiState.value = AddPasswordUiState()
        }
    }

    fun onDelete(id: Int) = viewModelScope.launch {
        passwordRepository.deletePasswordById(id)
        _uiState.update { it.copy(selectedPassword = null) }
    }

    fun unlock(){
        _biometricState.update {
            BiometricState.Authenticated
        }
    }

    fun lock(){
        _biometricState.update {
            BiometricState.Locked
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        lock()
    }

}
