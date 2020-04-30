package sound;

import javax.sound.midi.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.Channel;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException, IOException, InterruptedException {
        Sequence s = new Sequence(Sequence.PPQ, 10, 1);
        Track t = s.getTracks()[0];
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        /*
        for(int i = 0; i < 10; i++){
            t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 60, 60),
                    10 * i));

        }
        t.add(new MidiEvent(new ShortMessage(ShortMessage.STOP, 60, 60),
                100));
        t.add(new MidiEvent(new ShortMessage(MetaMessage.META, 60, 60),
                            100));
        Sequencer sequencer = MidiSystem.getSequencer();
        sequencer.setSequence(s);
        sequencer.addMetaEventListener((m) -> {
            sequencer.stopRecording();
            sequencer.close();
        });
        sequencer.addControllerEventListener((c) -> {
            if(c.getCommand() == ShortMessage.STOP){
                sequencer.stopRecording();
                sequencer.close();
            }
        }, new int[10]);
        sequencer.open();
        sequencer.startRecording();
        MidiSystem.write(s, MidiSystem.getMidiFileTypes(s)[0], new FileOutputStream(new File("test.midi")));

        while(sequencer.getTickPosition() < sequencer.getTickLength()){
            System.out.println(sequencer.getTickPosition() + " " + sequencer.getTickLength());
            TimeUnit.SECONDS.sleep(1);
        }
        */
        synthesizer.open();
        MidiChannel[] channels = synthesizer.getChannels();
        channels[0].programChange(1);
        channels[0].noteOn(131,60);
        Thread.sleep(1000);
        channels[0].noteOff(131);
    }
}
