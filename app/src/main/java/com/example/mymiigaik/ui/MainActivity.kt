package com.example.mymiigaik.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding

import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_news, R.id.navigation_schedule, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val list:List<myIntList> = (1..10).map { myIntList(week = "Week ${it}") }
        val rezult = getMyIntFrommyIntList(list) //это и есть viewMdoel
        rezult.forEach {
            Log.d("ПРивет","${it}")
        }


    }


    fun getMyIntFrommyIntList(list:List<myIntList>): List<IIntModel>{
        val rezult: MutableList<IIntModel> = mutableListOf()

        list.forEach {
            rezult.add(dayModel(it.week))
            it.list.forEach {
                rezult.add(intModel(it.i))
            }
        }



        return rezult

    }


 /*   fun getMyIntFrommyIntList2(list:List<myIntList>): List<Int>{
       return list.flatMap { it.list.map { it.i } }

    }*/



    data class myLesson(
        val i:Int = Random.nextInt(30),
        val day:String
    )



    data class myIntList(
        val list: List<myLesson> = (1..10).map { myLesson(day = "Day ${it}") },
        val week: String
    )


    interface NoLessons{

    }



    interface IIntModel{
    }



    data class intModel(
        val int:Int
    ):IIntModel

    data class dayModel(
        val day:String
    ):IIntModel


    data class weekModel(
        val week:String
    ):IIntModel


}