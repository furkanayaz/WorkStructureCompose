package com.furkanayaz.workstructurecompose.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.furkanayaz.workstructurecompose.R
import com.furkanayaz.workstructurecompose.models.Person
import com.google.gson.Gson

@Composable
fun PageA(navController: NavController, name: String, lastname: String, education: String, grade: Int, average: Float) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
        Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
    ) {
        Text(text = stringResource(id = R.string.page_a), color = colorResource(id = R.color.black), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = dimensionResource(
            id = R.dimen.textSize
        ).value.sp)
        Text(text = "$name $lastname", color = colorResource(id = R.color.black), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = dimensionResource(
            id = R.dimen.textSize3
        ).value.sp)
        Text(text = education, color = colorResource(id = R.color.black), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = dimensionResource(
            id = R.dimen.textSize3
        ).value.sp)
        Text(text = "$grade year", color = colorResource(id = R.color.black), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = dimensionResource(
            id = R.dimen.textSize3
        ).value.sp)
        Text(text = "Average: $average", color = colorResource(id = R.color.black), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = dimensionResource(
            id = R.dimen.textSize3
        ).value.sp)
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(id = R.dimen.padding).value.dp,
                end = dimensionResource(id = R.dimen.padding).value.dp
            ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.black),
                contentColor = colorResource(id = R.color.white)
            ),
            onClick = {
                val person = Person(name = "FURKAN", lastname = "AYAZ", age = 22, height = 1.78f)
                val personJson = Gson().toJson(person)
                navController.navigate("pageb/$personJson") {
                    popUpTo("pagea") { inclusive = true }
                }
            }) {
            Text(text = stringResource(id = R.string.switch_page_b), fontSize = dimensionResource(id = R.dimen.textSize3).value.sp, fontWeight = FontWeight.Bold)
        }
    }
}