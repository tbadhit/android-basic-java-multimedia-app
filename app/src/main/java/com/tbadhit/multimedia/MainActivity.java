package com.tbadhit.multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.tbadhit.multimedia.databinding.ActivityMainBinding;

import java.io.IOException;

// Bikin layout
// Download assets
// Bikin folder raw
// masukan asset (Andoid) yang di download tadi ke dalam raw
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 1.
        // enable & disable button
        binding.btnPlay.setEnabled(true);
        binding.btnPause.setEnabled(false);
        binding.btnStop.setEnabled(false);
        binding.btnResume.setEnabled(false);

        // 2
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                        .build());
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.musikk);
        try {
            // setDataSource nya pilih yang (context, Uri uri)
            mediaPlayer.setDataSource(this, uri);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

        // 3.
        binding.btnPlay.setOnClickListener(v -> {
            try {
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

            // enable & disable button
            binding.btnPlay.setEnabled(false);
            binding.btnPause.setEnabled(true);
            binding.btnStop.setEnabled(true);
            binding.btnResume.setEnabled(false);

        });

        // 4.
        binding.btnPause.setOnClickListener(v ->  {
            if (mediaPlayer.isPlaying()) mediaPlayer.pause();

            // enable & disable button
            binding.btnPlay.setEnabled(false);
            binding.btnPause.setEnabled(false);
            binding.btnStop.setEnabled(false);
            binding.btnResume.setEnabled(true);
        });

        // 5.
        binding.btnStop.setOnClickListener(v ->  {
            if (mediaPlayer.isPlaying()) mediaPlayer.stop();

            // enable & disable button
            binding.btnPlay.setEnabled(true);
            binding.btnPause.setEnabled(false);
            binding.btnStop.setEnabled(false);
            binding.btnResume.setEnabled(false);
        });

        // 6.
        binding.btnResume.setOnClickListener(v -> {
            mediaPlayer.start();

            // enable & disable button
            binding.btnPlay.setEnabled(false);
            binding.btnPause.setEnabled(true);
            binding.btnStop.setEnabled(true);
            binding.btnResume.setEnabled(false);
        });
        // Setelah bikin kode di atas bikin activity baru namanya "VidioActivity" ->

        // 7.
        binding.btnVidio.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VidioActivity.class);
            startActivity(intent);
        });
        // setelah bikin no 7. masuk ke "AndroidManifest.xml" tambahkan scaleOrientation (biar vidionya landscape)->
        // setelah bikin kode di atas bikin new activity kasi nama "VideoStreamingActivity" ->

        // 8.
        binding.btnVidioStreaming.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VideoStreamingActivity.class);
            startActivity(intent);
        });
        // Selesai
    }
}