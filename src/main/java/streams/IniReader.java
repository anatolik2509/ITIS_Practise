package streams;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public class IniReader extends InputStream {
    private DataInputStream inputStream;

    public IniReader(InputStream inputStream) {
        this.inputStream = new DataInputStream(inputStream);
    }

    public Map.Entry<String, String> readEntry() throws IOException{
        char c;
        String k = "";
        String v = "";
        while ((c = inputStream.readChar()) != '\n'){
            while(c != '='){
                if(c != ' '){
                    k += c;
                }
                c = inputStream.readChar();
            }
            c = inputStream.readChar();
            if(c != ' '){
                k += c;
            }
        }
        return new Pair(k, v);
    }

    @Override
    public int read() throws IOException {
        return inputStream.read();
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return inputStream.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return inputStream.skip(n);
    }

    @Override
    public int available() throws IOException {
        return inputStream.available();
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }

    @Override
    public void mark(int readlimit) {
        inputStream.mark(readlimit);
    }

    @Override
    public void reset() throws IOException {
        inputStream.reset();
    }

    @Override
    public boolean markSupported() {
        return inputStream.markSupported();
    }

    private class Pair<K, V> implements Map.Entry{

        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public Object setValue(Object o) {
            V r = value;
            value = (V)o;
            return r;
        }
    }
}
