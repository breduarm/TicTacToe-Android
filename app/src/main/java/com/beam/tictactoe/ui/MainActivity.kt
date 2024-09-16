package com.beam.tictactoe.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.beam.tictactoe.R
import com.beam.tictactoe.databinding.ActivityMainBinding
import com.beam.tictactoe.ui.board.BoardFragment
import com.beam.tictactoe.ui.games.GamesFragment
import com.beam.tictactoe.ui.scoreboard.ScoreboardFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

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