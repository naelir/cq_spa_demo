package cq_spa_demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Converter {
    private static class QBufferedReader extends BufferedReader {
        private Decoder decoder;

        public QBufferedReader(Reader in, Decoder decoder) {
            super(in);
            this.decoder = decoder;
        }
        
        @Override
        public String readLine() throws IOException {
            String line = super.readLine();
            if (line != null) {
                return new String(this.decoder.decode(line), Charset.forName("UTF-8"));
            } else {
                return null;
            }
        }
        
    }
    
    private static class QBufferedWriter extends BufferedWriter {

        private Encoder encoder;

        public QBufferedWriter(Writer out, Encoder encoder) {
            super(out);
            this.encoder = encoder;
        }
        
        @Override
        public Writer append(CharSequence csq) throws IOException {
            String line = encoder.encodeToString(csq.toString().getBytes());
            return super.append(line);
        }
        
    }

    public static BufferedReader decoder(String path, String encoding) throws UnsupportedEncodingException, IOException {
        Decoder decoder = Base64.getDecoder();
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis, encoding);
        return new QBufferedReader(isr, decoder);
    }
    
    public static BufferedWriter encoder(String path, String encoding) throws UnsupportedEncodingException, IOException {
        Encoder encoder = Base64.getEncoder();
        FileOutputStream fis = new FileOutputStream(path);
        OutputStreamWriter isr = new OutputStreamWriter(fis, encoding);
        return new QBufferedWriter(isr, encoder);
    }    
    
    public static BufferedReader reader(String path, String encoding) throws UnsupportedEncodingException, IOException {
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis, encoding);
        return new BufferedReader(isr);
    }
    
    public static BufferedWriter writer(String path, String encoding) throws FileNotFoundException, UnsupportedEncodingException {
        FileOutputStream fos = new FileOutputStream(path, true);
        OutputStreamWriter or = new OutputStreamWriter(fos, encoding);
        return new BufferedWriter(or);
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        try (BufferedReader in = Converter.reader("selectables.txt", "utf-8");
                BufferedWriter en = Converter.encoder("selectables-e.txt", "utf-8");   
        ) {
            for (String inputLine = in.readLine(); inputLine != null; inputLine = in.readLine()) {
                en.append(inputLine);
                en.write("\n");
            }
        }
        
        try (BufferedReader in = Converter.decoder("selectables-e.txt", "utf-8");
                BufferedWriter en = Converter.writer("selectables-x.txt", "utf-8");   
        ) {
            for (String inputLine = in.readLine(); inputLine != null; inputLine = in.readLine()) {
                en.append(inputLine);
                en.write("\n");
            }
        }
    }
    
}
