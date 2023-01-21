package com.furkanayaz.workstructurecompose.pages

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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

@Composable
fun PageB(navController: NavController, person: Person) {
    val currentActivity = (LocalContext.current as Activity)

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
        Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
    ) {
        Text(text = stringResource(id = R.string.page_b), color = colorResource(id = R.color.black), fontWeight = FontWeight.Bold, fontSize = dimensionResource(
            id = R.dimen.textSize
        ).value.sp)
        Text(text = "${person.name} ${person.lastname}", color = colorResource(id = R.color.black), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = dimensionResource(
            id = R.dimen.textSize3
        ).value.sp)
        Text(text = "Age: ${person.age}", color = colorResource(id = R.color.black), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = dimensionResource(
            id = R.dimen.textSize3
        ).value.sp)
        Text(text = "Height: ${person.height}", color = colorResource(id = R.color.black), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = dimensionResource(
            id = R.dimen.textSize3
        ).value.sp)
        Button(
            modifier = Modifier
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
                navController.navigate("homepage") {
                    popUpTo("pageb") { inclusive = true }
                }
            }) {
            Text(text = stringResource(id = R.string.switch_homepage), fontSize = dimensionResource(id = R.dimen.textSize3).value.sp, fontWeight = FontWeight.Bold)
        }
    }

    BackHandler(onBack = {
                         currentActivity.finish()
    }, enabled = true)

}