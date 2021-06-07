package com.example.memesandfunnysquares

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        random_image.setOnClickListener {
            colorTrick(random_image)
        }
    }

    private fun colorTrick(v: View) {
        var luckyHorFlag = false
        var luckyVerFlag = false
        var verCycle = 1
        var horCycle = 1
        val listOfViews =
            mapOf(
                scr_1 to 1,
                scr_2 to 2,
                scr_3 to 3,
                scr_4 to 4,
                scr_5 to 5,
                scr_6 to 6,
                scr_7 to 7,
                scr_8 to 8,
                scr_9 to 9
            )
        val mapOfPoints = mutableMapOf<Int, Int>()
        for (it in listOfViews.keys) {
            mapOfPoints[listOfViews.getValue(it)] = randomColor(it)
            Log.d("CHECKER", "$mapOfPoints")
        }
        while (horCycle < 9) {
            if ((mapOfPoints.getValue(horCycle) == mapOfPoints.getValue(horCycle + 1)) &&
                (mapOfPoints.getValue(horCycle) == mapOfPoints.getValue(horCycle + 2))
            ) {
                luckyHorFlag = true
                break
            } else {
                luckyHorFlag = false
                when (horCycle) {
                    1 -> horCycle += 3
                    4 -> horCycle += 3
                    else -> break
                }
            }
        }
        while (verCycle < 7) {
            if ((mapOfPoints.getValue(verCycle) == mapOfPoints.getValue(verCycle + 3) &&
                        (mapOfPoints.getValue(verCycle) == mapOfPoints.getValue(verCycle + 6)))
            ) {
                luckyVerFlag = true
                break
            } else {
                luckyVerFlag = false
                when (verCycle) {
                    1 -> verCycle++
                    2 -> verCycle++
                    else -> break
                }
            }
        }
        if (luckyVerFlag || luckyHorFlag) {
            score++
            score_id.text = getString(R.string.userscore, score)
            Toast.makeText(this, "Три подряд!!!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun randomColor(screen: View): Int {
        var result = 0
        val mapOfColors =
            mapOf(
                1 to android.R.color.holo_orange_dark,
                2 to android.R.color.darker_gray,
                3 to android.R.color.holo_green_dark,
                4 to android.R.color.holo_blue_bright,
                5 to android.R.color.holo_green_light,
                6 to android.R.color.holo_purple,
                7 to android.R.color.holo_orange_light,
                8 to android.R.color.holo_blue_dark,
                9 to android.R.color.holo_red_dark
            )
        val num = Random.nextInt(1, 10)
        for (it in mapOfColors)
            if (it.key == num) {
                screen.setBackgroundResource(it.value)
                result = it.value
            }
        return result
    }
}