package sound;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Piano {
    private static MidiChannel channel;
    private static final boolean[] keys = new boolean[26];
    private static int instrument = 0;
    private static Synthesizer synthesizer;
    public static void main(String[] args) throws MidiUnavailableException {
        KeyNoteMap.init();
        synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
        channel = synthesizer.getChannels()[0];
        channel.programChange(instrument);
        JFrame frame = new JFrame();
        frame.transferFocus();
        frame.addKeyListener(new KeyListener());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        System.out.println(synthesizer.getAvailableInstruments()[instrument].getName());
    }

    private static class KeyListener extends KeyAdapter{

        @Override
        public void keyTyped(KeyEvent e) {
            if(e.getKeyChar() == '<'){
                instrument--;
                if(instrument < 0) instrument = 127;
                channel.programChange(instrument);
                System.out.println(synthesizer.getAvailableInstruments()[instrument].getName());

            }
            if(e.getKeyChar() == '>'){
                instrument++;
                if(instrument > 127) instrument = 0;
                channel.programChange(instrument);
                System.out.println(synthesizer.getAvailableInstruments()[instrument].getName());
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyChar() >= 'a' && e.getKeyChar() <='z') {
                int n = e.getKeyChar() - 'a';
                if(!keys[n]) {
                    keys[n] = true;
                    channel.noteOn(KeyNoteMap.get(e.getKeyChar()), 60);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyChar() >= 'a' && e.getKeyChar() <='z') {
                int n = e.getKeyChar() - 'a';
                if(keys[n]) {
                    keys[n] = false;
                    channel.noteOff(KeyNoteMap.get(e.getKeyChar()), 60);
                }
            }
        }
    }

}
