package sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by MSI on 29.11.2015.
 */
public class LevelDownloader implements Model{
    String text;
    String instruction;
    String path;
    public LevelDownloader(String path)
    {
        this.path=path;
        downloadLVL();

    }
    public void downloadLVL()
    {BufferedReader reader;
        HashMap<String, String> level = new HashMap<String, String>();

        try {
            reader = new BufferedReader(new FileReader("src\\lvls\\"+path));
            reader.readLine();
            text =reader.readLine();
            reader.readLine();
            instruction=reader.readLine();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getInstruction() {
        return instruction;
    }
}
