import java.io.*;
import java.util.*;
public class ConfigParser {
    private Map<String, String> me = new HashMap<String, String>();
    private String nameOfFile;

    public ConfigParser(String nameOfFile) throws Exception {
        this.nameOfFile = nameOfFile;
        try {
            File file = new File(this.nameOfFile);    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            StringBuffer sb = new StringBuffer();    //constructs a string buffer with no characters
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                } else {
//                        System.out.println(line);
                    sb.append(line + ",");
                }

//                    System.out.println(Arrays.toString(nn));
            }

        //change the stringbuffer to string to make for easy split into an array
            String str = sb.toString();
        //stores the split array into another array to ease the iteration and create hashmaps
            String[] nn = str.split("(=)|(,)");
        //creates the hashmaps
            for (int i = 0; i < nn.length - 2; i++) {
//                        System.out.println(nn[i]);
                if (nn[i].contains("application") && i + 6 < nn.length) {
                    for (int j = i + 1; j < i + 7; j += 2) {
//                                System.out.println(nn[j]);
                        me.put("application." + nn[j], nn[j + 1]);
                    }
                    i += 6;
                } else if (nn[i].contains("application") && i + 6 > nn.length) {
                    i++;
                    me.put("application1." + nn[i], nn[i + 1]);
                } else {
                    me.put(nn[i], nn[i + 1]);
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    public void conf(String name){
        System.out.println(me.get(name));
    }

    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            ConfigParser config = new ConfigParser("config.txt");
            config.conf("application.name");
        } else if (args[0].equals("dev")) {
            ConfigParser config = new ConfigParser("config.txt.dev");
            config.conf("application.name");
        } else if (args[0].equals("staging")) {
            ConfigParser config = new ConfigParser("config.txt.staging");
            config.conf("application.port");
        }

    }
}

