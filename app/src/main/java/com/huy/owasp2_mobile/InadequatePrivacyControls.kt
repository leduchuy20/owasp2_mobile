package com.huy.owasp2_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun InadequatePrivacyControlsApp() {
    InadequatePrivacyDemo(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    )
}


@Composable
fun InadequatePrivacyDemo(modifier: Modifier = Modifier) {
    // Giả lập thông tin người dùng bị lộ ra ngoài
    val userEmail = "user@example.com"
    val userPhone = "0987654321"

    Column(
        modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Inadequate Privacy Controls Demo", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(20.dp))
        Text("Your Email: $userEmail")
        Spacer(Modifier.height(10.dp))
        Text("Your Phone: $userPhone")
        Spacer(Modifier.height(20.dp))
        Text(
            "Lỗ hổng: Thông tin cá nhân của bạn bị hiển thị công khai mà không có bất kỳ thiết lập, cảnh báo hay lựa chọn bảo mật nào.\n" +
                    "Người dùng không kiểm soát được việc chia sẻ dữ liệu cá nhân!"
        )
    }
}