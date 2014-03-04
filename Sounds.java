package breakout; /**
 * Created by Adam on 26/02/14.
 */

import sun.audio.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Sounds {
    private static void fileGrabber(){

    }

    public static void music(){
        System.out.println("loading music");
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;

        ContinuousAudioDataStream loop = null;

        try
        {
            InputStream test = new FileInputStream("");
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);

        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }
        catch(IOException error)
        {
            System.out.print(error.toString());
        }
        MGP.start(loop);
    }

}
