package com.MIDIMusicPlayer.v1;

import javax.sound.midi.*;

public class MiniMiniMusicApp {
    public static void main(String[] args) {
        MiniMiniMusicApp mini = new MiniMiniMusicApp();
        mini.play();
    }

    public void play() {
        try {
            Sequencer player = MidiSystem.getSequencer();
            player.open();//取得Sequencer并将其打开

            Sequence seq = new Sequence(Sequence.PPQ, 4);//先不用管参数的意义

            Track track = seq.createTrack();//要求取得Track

            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, 44, 100);
            MidiEvent noteOn = new MidiEvent(a, 1);
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, 44, 100);
            MidiEvent noteOff = new MidiEvent(b, 16);
            track.add(noteOff);//对Track加入几个MIDIEvent，要注意的是setMessage()的参数，以及MIDIEvent的constructor，下一页会讨论

            player.setSequence(seq);//将sequence送到sequencer上

            player.start();//开始播放
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
