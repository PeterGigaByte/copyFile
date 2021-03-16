import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) throws IOException {
        final File fileSRC = new File("path");
        final File fileDST = new File("path");

        Timer timer = new Timer();

        timer.schedule( new TimerTask() {
            public void run() {
                try {
                    copyFileUsingChannel(fileSRC, fileDST);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 900*1000);
    }

    private static void copyFileUsingChannel(File source, File dest) throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } finally {
            sourceChannel.close();
            destChannel.close();
        }
    }
}