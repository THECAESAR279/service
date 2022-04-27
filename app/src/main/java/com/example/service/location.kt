package com.example.service

import ColorSecondaryVariant
import NextButton
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.service.ui.theme.ServiceTheme

class location : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ServiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ServiceLocation()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ServiceLocation() {

    Column(modifier= Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
        .padding(bottom = 16.dp, top = 40.dp)
    ) {
        val horizontalPadding = 30.dp
        val keyboardController = LocalSoftwareKeyboardController.current
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(horizontal = horizontalPadding)
                .width(24.dp)
                .align(Alignment.Start)
                .clickable {}
        )
        Spacer(modifier = Modifier.size(85.dp))
        Text(
            text = "Service Location",
            fontSize = 26.sp,
            fontWeight = FontWeight.Light,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = horizontalPadding)
        )

        Spacer(modifier = Modifier.size(85.dp))

        var selectedText by remember { mutableStateOf("") }

        OutlinedTextField(
            value = selectedText,
            placeholder = {Text(text = "Address Description")},
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding * 1.8f)
                .height(116.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp,
                        bottomStart = 12.dp,
                        bottomEnd = 12.dp
                    )
                )
                .border(
                    1.dp,
                    ColorSecondaryVariant,
                    RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp,
                        bottomStart = 12.dp,
                        bottomEnd = 12.dp
                    )
                )
                .background(Color(red = 1f, green = 1f, blue = 1f, alpha = 1f)),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = ColorSecondaryVariant,
                unfocusedBorderColor = ColorSecondaryVariant,
                textColor = Color.Gray
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = false,
            maxLines = 1000,


            )

        Spacer(modifier = Modifier.size(300.dp))
        NextButton(
            enabled = true,
            text = "Next",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding * 1.8f)
        )

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ServiceTheme {
        ServiceLocation()
    }
}