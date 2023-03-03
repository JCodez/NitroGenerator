package nitro.generator.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.io.File.separator;

public class Utils {

    public static String localPath = System.getProperty("user.home");
    public static final String configPath = localPath + separator + "Nitro-generator";
    static File configFolder = new File(configPath);


    public static void create(){
        if(!configFolder.exists()){
            configFolder.mkdir();
        }
    }
    public static boolean isNew(){
        if(configFolder.exists()) return false;
        else return true;
    }
    public static void write(String subpath, String text) throws IOException {
        FileWriter writer = new FileWriter(configPath + separator + subpath);
        writer.write(text);
        writer.close();

    }
    public static void exit(){
        System.exit(0);
    }
}
