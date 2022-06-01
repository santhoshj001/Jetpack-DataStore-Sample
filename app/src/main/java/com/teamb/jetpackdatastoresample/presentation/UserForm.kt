package com.teamb.jetpackdatastoresample.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UserForm(viewModel: UserPreferenceViewModel = hiltViewModel()) {
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = state.firstName, onValueChange = {
                viewModel.onEvent(UserFormEvent.FirstNameChanged(it))
            },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "First Name")
            }
        )
        OutlinedTextField(
            value = state.lastName, onValueChange = {
                viewModel.onEvent(UserFormEvent.LastNameChanged(it))
            },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Last Name")
            }
        )
        OutlinedTextField(
            value = state.phoneNumber, onValueChange = {
                viewModel.onEvent(UserFormEvent.PhoneNumberChanged(it))
            },
            Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = {
                Text(text = "Phone Number")
            }

        )
        OutlinedTextField(
            value = state.email,
            onValueChange = {
                viewModel.onEvent(UserFormEvent.EmailChanged(it))
            },
            Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = {
                Text(text = "E-Mail")
            }
        )

        OutlinedButton(onClick = { viewModel.onEvent(UserFormEvent.Submit) }) {
            Text(text = "Submit")
        }
    }
}