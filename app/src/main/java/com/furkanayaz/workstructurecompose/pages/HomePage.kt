package com.furkanayaz.workstructurecompose.pages

import com.furkanayaz.workstructurecompose.R
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.furkanayaz.workstructurecompose.models.Person
import com.furkanayaz.workstructurecompose.ui.theme.WorkStructureComposeTheme
import com.google.gson.Gson

class HomePage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkStructureComposeTheme {
                SwitchPages()
            }
        }
    }
}

@Composable
fun SwitchPages() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "homepage") {
        composable(route = "homepage") {
            HomePage(navController = navController)
        }
        composable(route = "pagea/{name}/{lastname}/{education}/{grade}/{average}", arguments = listOf(
            navArgument(name = "name") { type = NavType.StringType },
            navArgument(name = "lastname") { type = NavType.StringType },
            navArgument(name = "education") { type = NavType.StringType },
            navArgument(name = "grade") { type = NavType.IntType },
            navArgument(name = "average") { type = NavType.FloatType }
        )) {
            val name = it.arguments?.getString("name", "empty")!!
            val lastname = it.arguments?.getString("lastname", "empty")!!
            val education = it.arguments?.getString("education", "empty")!!
            val grade = it.arguments?.getInt("grade", 0)!!
            val average = it.arguments?.getFloat("average", 0.0f)!!
            PageA(navController = navController, name = name, lastname = lastname, education = education, grade = grade, average = average)
        }
        composable(route = "pageb/{person}", arguments = listOf(
            navArgument(name = "person") { type = NavType.StringType }
        )) {
            val personJson = it.arguments?.getString("person") // String türünde JSON formatında gelmektedir.
            val person = Gson().fromJson(personJson, Person::class.java)
            PageB(navController = navController, person = person)
        }
    }
}

@Composable
fun HomePage(navController: NavController) {
    val counter = remember {
        mutableStateOf(0)
    }
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
        Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
    ) {
        Text(text = stringResource(id = R.string.homepage), color = colorResource(id = R.color.black), fontWeight = FontWeight.Bold, fontSize = dimensionResource(
            id = R.dimen.textSize
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
                navController.navigate("pagea/FURKAN/AYAZ/Management Information Systems/4/3.33f") {
                    popUpTo("homepage") { inclusive = true }
                }
            }) {
            Text(text = stringResource(id = R.string.switch_page_a), fontSize = dimensionResource(id = R.dimen.textSize3).value.sp, fontWeight = FontWeight.Bold)
        }
        Text(text = "Counter: ${counter.value}", color = colorResource(id = R.color.black), fontWeight = FontWeight.Bold, fontSize = dimensionResource(
            id = R.dimen.textSize2
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
                counter.value += 1
            }) {
            Text(text = stringResource(id = R.string.increase_counter), fontSize = dimensionResource(id = R.dimen.textSize3).value.sp, fontWeight = FontWeight.Bold)
        }
    }

    LaunchedEffect(key1 = true) {
        Log.e("LifeCycle", "This page only works when once the page is created")
    }

    SideEffect {
        Log.e("LifeCycle", "This page only works when once the page is created and infinitely refreshed (set state).")
    }

    DisposableEffect(Unit) {
        onDispose {
            Log.e("LifeCycle", "This page was destroyed from backstack")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WorkStructureComposeTheme {
        SwitchPages()
    }
}