package sound;

import javax.sound.midi.*;
import java.io.*;

public class TextToSound {
    public static void main(String[] args) throws IOException, InvalidMidiDataException, MidiUnavailableException {
        FileInputStream stream = new FileInputStream("music.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(stream));
        StringBuilder in = new StringBuilder();
        while(r.ready()){
            in.append(r.readLine());
        }
        int count = 1;
        Sequence s = new Sequence(Sequence.PPQ, 4);
        Track t = s.createTrack();
        KeyNoteMap.init();
        for(char c : in.toString().toCharArray()){
            if(c >= 'a' && c <= 'z') {
                t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, KeyNoteMap.get(c), 60), count));
            }
            count++;
        }
        t.add(new MidiEvent(new MetaMessage(), count * 5));
        Sequencer sequencer = MidiSystem.getSequencer();
        sequencer.addMetaEventListener((m) -> {
            sequencer.stop();
            sequencer.close();
        });
        sequencer.setSequence(s);
        sequencer.open();
        sequencer.start();

    }
}
