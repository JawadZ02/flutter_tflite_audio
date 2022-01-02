package flutter.tflite_audio;

import android.media.AudioManager;
import android.media.AudioTrack;

public class AudioPlayer {

    private AudioTrack audioTrack;

    //set up audioTreack
    public AudioPlayer(int sampleRate, int channel, int sampBit) {

        int minBufSize = AudioTrack.getMinBufferSize(sampleRate, channel, sampBit);
        audioTrack = new AudioTrack(
                AudioManager.STREAM_MUSIC,
                sampleRate, channel, sampBit, minBufSize, AudioTrack.MODE_STREAM
        );
        // AudioTrack  at=new AudioTrack(AudioManager.STREAM_MUSIC, sampleRate, AudioFormat.CHANNEL_OUT_MONO,
        // AudioFormat.ENCODING_PCM_16BIT, sampleRate*totalSeconds /* 1 second buffer */,
        // AudioTrack.MODE_STREAM);
    }

    public void release() {
        if (audioTrack != null) {
            audioTrack.stop();
            audioTrack.release();
            audioTrack = null;
        }
    }

    public void play(short[] data, int offset, int length) {
        if (data == null || data.length == 0) {
            return;
        }
        audioTrack.write(data, offset, length);
        audioTrack.play();
    }
}
