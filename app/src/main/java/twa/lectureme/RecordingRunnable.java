package twa.lectureme;

import android.app.Application;
import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by anton on 9/16/17.
 */

public class RecordingRunnable implements Runnable {

    private static final int SAMPLE_RATE = 22050;

    private AudioRecord recorder;

    //private MediaRecorder mRecorder;
    private boolean canceled = false;

    private Timer t;
    short[] audioBuffer;

    //RequestHandler requester;
    BinaryRequestHandler requester;

    LinkedList<short[]> section;

    private Context c;

    public RecordingRunnable(BinaryRequestHandler requester, Context con)
    {
        c = con;

        section = new LinkedList<>();

        this.requester = requester;
        /*final String mFileName = con.getFilesDir().getAbsolutePath()+"/audiorecordtest.mp4";

        final Context c = con;
        final RequestHandler r = requester;
        section = new LinkedList<>();
        this.requester = requester;
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mRecorder.setOutputFile(mFileName);
        try {
            mRecorder.prepare();
            mRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("LOL","CORRUPTED IO EXCEPTION");
        }
        t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

            String file = mFileName;
            int i = 1;
            @Override
            public void run() {
                Log.d("LOL","CORRUPTED");
                mRecorder.reset();
                r.handleRequest(file+String.valueOf(i),c);
                mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
                mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                mRecorder.setOutputFile(file+String.valueOf(i));
                i = (i+1)%2;
                try {
                    mRecorder.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mRecorder.start();
            }
        },5000,5000);*/
    }

    //@Override
    //public void run() {
        //android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_AUDIO);
        /*int bufferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT);
        Log.d("Buffer", String.valueOf(bufferSize));
        if (bufferSize == AudioRecord.ERROR || bufferSize == AudioRecord.ERROR_BAD_VALUE) {
            bufferSize = SAMPLE_RATE * 2;
        }
        recorder = new AudioRecord(MediaRecorder.AudioSource.DEFAULT,SAMPLE_RATE,AudioFormat.CHANNEL_IN_DEFAULT,AudioFormat.ENCODING_PCM_8BIT, bufferSize);
        if (recorder.getState() != AudioRecord.STATE_INITIALIZED) {
            Log.e("RECORDER_ERROR", "Audio Record can't initialize!");
            return;
        }
        audioBuffer = new short[bufferSize/2];
        recorder.startRecording();
        int read = 0;
        while (!canceled)
        {
            read += recorder.read(audioBuffer,0,audioBuffer.length);

            section.add(audioBuffer.clone());
            if(read >(5*SAMPLE_RATE))
            {
                requester.handleRequest(section,read);
                read = 0;
                section.clear();
            }
        }*/





    //}
    public void cancel(){

    }

    @Override
    public void run() {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_AUDIO);
        int bufferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT);
        Log.d("Buffer", String.valueOf(bufferSize));
        if (bufferSize == AudioRecord.ERROR || bufferSize == AudioRecord.ERROR_BAD_VALUE) {
            bufferSize = SAMPLE_RATE * 2;
        }
        recorder = new AudioRecord(MediaRecorder.AudioSource.MIC,SAMPLE_RATE,AudioFormat.CHANNEL_IN_DEFAULT,AudioFormat.ENCODING_PCM_16BIT, bufferSize);
        if (recorder.getState() != AudioRecord.STATE_INITIALIZED) {
            Log.e("RECORDER_ERROR", "Audio Record can't initialize!");
            return;
        }
        audioBuffer = new short[bufferSize/2];
        recorder.startRecording();
        int read = 0;
        while (!canceled) {
            read += recorder.read(audioBuffer, 0, audioBuffer.length);

            section.add(audioBuffer.clone());
            if (read > (1 * SAMPLE_RATE)) {
                requester.handleRequest((List<short[]>)section.clone(), read, c);
                read = 0;
                section.clear();
            }
        }
    }
}
