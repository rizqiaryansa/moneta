package com.rizqiaryansa.currencyrate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rizqiaryansa.currencyrate.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        initKoin()

        setContent {
            App()
        }
    }

    private fun initKoin() {
        if (GlobalContext.getOrNull() == null) {
            initKoin {
                androidContext(applicationContext)
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}