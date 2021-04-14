package com.tbadhit.multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;

import com.tbadhit.multimedia.databinding.ActivityVidioBinding;

// Bikin layout
// Bikin binding
// (Codenya berjalan syncronus)
public class VidioActivity extends AppCompatActivity {

    private ActivityVidioBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVidioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 1.
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.android_developer);

        // untuk frame dari vidionya
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(binding.videoView);
        binding.videoView.setMediaController(mediaController);

        // Tampilkan vidio
        binding.videoView.setVideoURI(uri);
        binding.videoView.requestFocus();
        binding.videoView.start();

        // biar kembali ke activity sebelumnya
        binding.videoView.setOnCompletionListener(mp -> {
            finish();
        });
        // setelah bikin kode di atas kembali ke main activity bikin navigasinya (karna td lupa) ->
    }
}