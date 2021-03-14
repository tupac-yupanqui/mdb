package de.wko.mdb.format;

import ws.schild.jave.*;

import java.io.File;

public class Converter {

    private static Converter theInstance = null;

    public static Converter getInstance() {
        if (theInstance==null) {
            theInstance = new Converter();
        }
        return theInstance;
    }

    public String convert(String path, String sourceFileName) throws AudioFormatException {
            String res = null;
            try {
                String targetFileName = sourceFileName.replace(".wma",".mp3");

                File source = new File(path+sourceFileName);
                File target = new File(path+targetFileName);

                AudioAttributes audio = new AudioAttributes();
                audio.setCodec("libmp3lame");
                audio.setBitRate(256000);
                audio.setChannels(2);
                audio.setSamplingRate(44100);

                //Encoding attributes
                EncodingAttributes attrs = new EncodingAttributes();
                attrs.setFormat("mp3");
                attrs.setAudioAttributes(audio);

                //Encode
                Encoder encoder = new Encoder();
                encoder.encode(new MultimediaObject(source), target, attrs, new ConvertProgressListener());

                System.out.println(" ready");
                return targetFileName;

            } catch (Exception ex) {
                throw new AudioFormatException("Convert error: "+ex.getMessage());
            }
    }

    public class ConvertProgressListener implements EncoderProgressListener {

        public ConvertProgressListener( ) {
        }

        public void message(String m) {
        }

        public void progress(int p) {

            //Find %100 progress
            double progress = p / 1000.00;
            System.out.print("*");
        }

        public void sourceInfo(MultimediaInfo m) {
        }
    }

}
