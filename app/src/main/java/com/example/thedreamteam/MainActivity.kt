package com.example.thedreamteam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thedreamteam.ui.theme.TheDreamTeamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheDreamTeamTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DreamTeamApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DreamTeamApp(modifier: Modifier = Modifier) {
    var selectedStudent by remember { mutableStateOf<Int?>(null) }

    if (selectedStudent == null) {
        // Main Screen with buttons
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (i in 1..5) {
                Button(
                    onClick = { selectedStudent = i },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text("Student $i")
                }
            }
        }
    } else {
        // Student Screen (no image)
        StudentScreen(
            studentNumber = selectedStudent!!,
            onBack = { selectedStudent = null }
        )
    }
}

@Composable
fun StudentScreen(studentNumber: Int, onBack: () -> Unit) {
    val studentName = "Student $studentNumber"
    val studentBio = "This is a short bio of Student $studentNumber."

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = studentName,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = studentBio,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onBack) {
            Text("Back")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DreamTeamAppPreview() {
    TheDreamTeamTheme {
        DreamTeamApp()
    }
}
