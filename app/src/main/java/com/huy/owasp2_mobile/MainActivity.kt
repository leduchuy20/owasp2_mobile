package com.huy.owasp2_mobile
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.huy.owasp2_mobile.improper_credentail_usage.ImproperCredentialUsageApp
import com.huy.owasp2_mobile.insecure_data_storage.InsecureDataStorageApp
import com.huy.owasp2_mobile.ui.theme.Owasp2_mobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            M1ImproperCredentialScreen(context = applicationContext)
//
//            Owasp2_mobileTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
            //OWASP
//            ImproperCredentialUsageApp()
//            InsecureDataStorageApp()

//            ImproperCredentialUsageApp()
//            InsecureAuthenticationApp()
//            InsufficientCryptographyApp()
            InsecureCommunicationApp()
//            InadequatePrivacyControlsApp()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Owasp2_mobileTheme {
        Greeting("Android")
    }
}