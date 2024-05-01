package com.example.mygallery.presentation.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mygallery.R
import com.example.mygallery.presentation.component.EmptyUi
import com.example.mygallery.utils.EmptyStateModel

@Composable
fun ErrorScreen(emptyParams: EmptyStateModel?, onBack: () -> Unit) {
    EmptyUi(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
            .background(Color.White),
        animationResource = R.raw.jeky_error,
        title = emptyParams?.title.orEmpty(),
        description = emptyParams?.description.orEmpty(),
        buttonText = emptyParams?.buttonText ?: "Okay"
    ) {
        onBack.invoke()
    }
}