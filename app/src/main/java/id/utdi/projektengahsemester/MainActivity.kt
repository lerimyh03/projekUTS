package id.utdi.projektengahsemester


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import id.utdi.projektengahsemester.ui.theme.Pink40
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme{
                // Surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieCard()
                }
            }
        }
    }
}

data class Movie(val imageResource: Int, val moreInfo: String, val description: String)

@Composable
fun MovieCard() {
    var currentMovieIndex by remember { mutableStateOf(0) }
    var isDescriptionVisible by remember { mutableStateOf(false) }

    // List of movies to display
    val movies = listOf(
        Movie(R.drawable.film1, "Informasi tambahan Film 1", "Deskripsi Film 1"),
        Movie(R.drawable.film2, "Informasi tambahan Film 2", "Deskripsi Film 2"),
        Movie(R.drawable.film3, "Informasi tambahan Film 3", "Deskripsi Film 3"),
        // Add more movies here
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(Color.White)
    ) {
        // Column 1: Image
        MovieImage(imageResource = movies[currentMovieIndex].imageResource) {
            // Toggle description visibility on image click
            isDescriptionVisible = !isDescriptionVisible
        }

        // Spacer
        Spacer(modifier = Modifier.height(16.dp))

        // Column 3: Action Buttons (Previous, Next, and More Info)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    // Action for the Previous button
                    currentMovieIndex = (currentMovieIndex - 1).coerceAtLeast(0)
                    isDescriptionVisible = false
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Previous")
            }

            Button(
                onClick = {
                    // Action for the Next button
                    currentMovieIndex = (currentMovieIndex + 1).coerceAtMost(movies.size - 1)
                    isDescriptionVisible = false
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Next")
            }

            // Button for More Info
            Button(
                onClick = {
                    // Action for the More Info button
                    isDescriptionVisible = !isDescriptionVisible
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = if (isDescriptionVisible) "Hide Info" else "More Info")
            }
        }

        // Column 2: Movie Description (Centered horizontally)
        if (isDescriptionVisible) {
            Text(
                text = movies[currentMovieIndex].description,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun MovieImage(imageResource: Int, onClick: () -> Unit) {
    Image(
        painter = painterResource(id = imageResource),
        contentDescription = null,
        modifier = Modifier
            .size(600.dp)
            .padding(1.dp)
            .clickable { onClick() } // Handle click event
    )
}

@Preview(showBackground = true)
@Composable
fun MovieCardPreview() {
    MaterialTheme {
        MovieCard()
    }
}