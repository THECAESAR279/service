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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ServiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   servicetype()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun servicetype() {

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
            text = "Service Type",
            fontSize = 26.sp,
            fontWeight = FontWeight.Light,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = horizontalPadding)
        )

        Spacer(modifier = Modifier.size(30.dp))

        var expanded by remember { mutableStateOf(false) }
        val suggestions = listOf("Barber", "Dentist", "mechanic", "Others...")
        var selectedText by remember { mutableStateOf("Choose...") }

        var textfieldSize by remember { mutableStateOf(Size.Zero)}

        val icon = if (expanded)
            Icons.Filled.KeyboardArrowUp //it requires androidx.compose.material:material-icons-extended
        else
            Icons.Filled.KeyboardArrowDown


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding * 1.8f)
        ) {
            OutlinedTextField(
                readOnly=true,
                value = selectedText,
                onValueChange = { selectedText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        //This value is used to assign to the DropDown the same width
                        textfieldSize = coordinates.size.toSize()
                    },
                trailingIcon = {
                    Icon(icon,"contentDescription",
                        Modifier.clickable { expanded = !expanded })
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = ColorSecondaryVariant,
                    unfocusedBorderColor = ColorSecondaryVariant,
                    textColor = Color.Gray
                ),
                shape = RoundedCornerShape(12.dp)

            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                    .border(
                        BorderStroke(width = 1.dp, color = ColorSecondaryVariant),
                        shape = RoundedCornerShape(12.dp)
                    )

            ) {
                suggestions.forEach { label ->
                    DropdownMenuItem(onClick = {
                        selectedText = label
                    }) {
                        Text(text = label,color=Color.Gray)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.size(350.dp))
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
fun DefaultPreview() {
    ServiceTheme {
       servicetype()
    }
}