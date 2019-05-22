package com.example.mediarecorder;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import static com.example.mediarecorder.MainActivity.FOLDER_AUDIO;

public class ServiceRecorder extends Service {
    private static String OUTPUTFILE = "";
    String nameAudio = "";
    MediaRecorder mediaRecorder;


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        DateFormat sdf = DateFormat.getDateInstance();
        DateFormat sdf2 = DateFormat.getTimeInstance();
        Date date = new Date();
        nameAudio = sdf.format(date) + "_" + sdf2.format(date) + ".mp3";
        File file = new File(FOLDER_AUDIO, nameAudio);
        try {
            if (file.createNewFile()) {
                System.out.println("Se creó el archivo :" + nameAudio);
            } else {
                System.out.println("No se creó el archivo :" + nameAudio);
            }
        } catch (IOException e) {
            System.out.println("Error creando archivo: " + e.getMessage());
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Servicio de grabación iniciado",Toast.LENGTH_SHORT).show();
        try {
            OUTPUTFILE = FOLDER_AUDIO + "/" + nameAudio;
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mediaRecorder.setOutputFile(OUTPUTFILE);
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            Toast.makeText(this, "Servicio Detenido", Toast.LENGTH_SHORT).show();
        }
    }


}
