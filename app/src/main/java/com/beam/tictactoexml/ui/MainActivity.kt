package com.beam.tictactoexml.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.beam.tictactoexml.R
import com.beam.tictactoexml.databinding.ActivityMainBinding
import com.beam.tictactoexml.ui.board.BoardFragment
import com.beam.tictactoexml.ui.games.GamesFragment
import com.beam.tictactoexml.ui.scoreboard.ScoreboardFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            enableEdgeToEdge()

            bottomNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_board -> {
                        supportFragmentManager.commit {
                            replace(R.id.app_container, BoardFragment())
                        }
                        true
                    }

                    R.id.navigation_scoreboard -> {
                        supportFragmentManager.commit {
                            replace(R.id.app_container, ScoreboardFragment())
                        }
                        true
                    }

                    R.id.navigation_other_games -> {
                        supportFragmentManager.commit {
                            replace(R.id.app_container, GamesFragment())
                        }
                        true
                    }

                    else -> false
                }
            }

            bottomNavigation.selectedItemId = R.id.navigation_board
        }
    }
}