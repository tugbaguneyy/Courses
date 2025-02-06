package com.example.courses

import DataSource
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courses.model.Course
import com.example.courses.ui.theme.CoursesTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize().statusBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CourseGrid(
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun CourseCard(course:Course, modifier: Modifier=Modifier){
    Card(modifier=modifier) {
        //resim,kur ismi,ikon,kurs id bir satır içindedir
        Row {
            Image(
                painter = painterResource(id=course.imageRes),
                contentDescription = null,
                modifier=Modifier.size(68.dp).aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            //kurs adı,ikon ve kurs id bir sütun elemanıdır
            Column(modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.Center)
            {
                Text(
                    text = stringResource(id=course.name),
                    style = MaterialTheme.typography.bodyMedium
                )
                //ikon ve kurs id aynı sütünda bulunann elemanlardır
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null
                    )
                    Text(
                        text = course.availableCourses.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CourseGrid(modifier: Modifier=Modifier){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(DataSource.courses) { course->
            CourseCard((course))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoursesAppPreview() {
    CoursesTheme {
        CourseCard(course = Course(R.string.photography,32,R.drawable.photography))
    }
}

@Preview(showBackground = true)
@Composable
fun CourseGridPreview() {
    CoursesTheme {
        CourseGrid()
    }
}