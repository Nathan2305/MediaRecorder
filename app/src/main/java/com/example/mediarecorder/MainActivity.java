package com.example.mediarecorder;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static final String FOLDER_AUDIO = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AppForThem/Audios";

    Button startAudio, stopAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startAudio = findViewById(R.id.startAudio);
        stopAudio = findViewById(R.id.stopAudio);
        File file=new File(FOLDER_AUDIO);
        if (!file.exists()){
            if (file.mkdirs()){  //crea directios incluyendo la carpeta padre
                System.out.println("Se creó el directorio "+FOLDER_AUDIO);
            }else{
                System.out.println("No se creó el directorio "+FOLDER_AUDIO);
            }
        }
        startAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getApplicationContext(), ServiceRecorder.class));
            }
        });
        stopAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getApplicationContext(), ServiceRecorder.class));
            }
        });
    }
}
