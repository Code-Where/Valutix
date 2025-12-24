package com.abhishek.vaultIx

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.abhishek.vaultIx.ui.screens.HomeScreen
import com.abhishek.vaultIx.ui.screens.SplashScreen
import com.abhishek.vaultIx.ui.theme.PasswordManagerTheme
import com.abhishek.vaultIx.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PasswordManagerTheme {
                var isSplashScreen by rememberSaveable { mutableStateOf(true) }

                val viewModel: HomeViewModel = hiltViewModel()
                lifecycle.addObserver(viewModel)

                if (isSplashScreen){
                    SplashScreen{
                        CoroutineScope(Dispatchers.Main).launch {
                            delay(2000)
                            isSplashScreen = false
                        }
                    }
                }
                else{
                    HomeScreen(activity = this)
                }
            }
        }
    }
}

