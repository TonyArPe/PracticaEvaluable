package com.example.practicaevaluable

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MusicPlayerActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private var currentSongIndex = 0
    private val songs = arrayOf(R.raw.song1, R.raw.song2, R.raw.song3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)

        mediaPlayer = MediaPlayer.create(this, songs[currentSongIndex])

        val playButton = findViewById<Button>(R.id.playButton)
        val pauseButton = findViewById<Button>(R.id.pauseButton)
        val nextButton = findViewById<Button>(R.id.nextButton)
        val previousButton = findViewById<Button>(R.id.previousButton)
        val backButton = findViewById<Button>(R.id.backButton)

        playButton.setOnClickListener {
            mediaPlayer.start()
        }

        pauseButton.setOnClickListener {
            mediaPlayer.pause()
        }

        nextButton.setOnClickListener {
            currentSongIndex = (currentSongIndex + 1) % songs.size
            mediaPlayer.reset()
            mediaPlayer = MediaPlayer.create(this, songs[currentSongIndex])
            mediaPlayer.start()
        }

        previousButton.setOnClickListener {
            currentSongIndex = (currentSongIndex - 1 + songs.size) % songs.size
            mediaPlayer.reset()
            mediaPlayer = MediaPlayer.create(this, songs[currentSongIndex])
            mediaPlayer.start()
        }

        backButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.release()
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        mediaPlayer.release()
    }
}
