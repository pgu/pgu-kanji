import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.HashSet;

public class ParseJouyou {

    public static void main(String[] args) throws IOException {

//        BufferedReader in = new BufferedReader(new FileReader("pgu-kanji/src/src_kyoiku.html"));
//        BufferedReader in = new BufferedReader(new FileReader("pgu-kanji/src/src_joyo.html"));
        BufferedReader in = new BufferedReader(new FileReader("pgu-kanji/src/joyo2.txt"));

        StringBuilder sb  = new StringBuilder();

//        sb.append("var joyo = [ ");
//        sb.append("private static final String[] joyo = new String[] { ");
        sb.append("private static final String joyo = \"");

        int counter = 0;
        String line = null;
        while ((line = in.readLine()) != null) {
            if (line.startsWith(">>>")) {
//                sb.append("\"");
                sb.append(line.substring(3, 4));
//                sb.append("\"");

//                if (counter != 2135) { // idx
//                    sb.append(", ");
//                }
//                sb.append(line);

//                if ((counter +1) % 30 == 0) {
//                    sb.append(" //\n");
//                }
                counter++;
            }
        }

        sb.append("\";");
//        sb.append("};");

        String html = sb.toString();

//        System.out.print(StringUtils.countMatches(html, "<tr><td class=\"webkit-line-number\">"));
//        System.out.print(html);
//        System.out.println(StringUtils.countMatches(html, " "));
        System.out.println(counter);

//        PrintWriter writer = new PrintWriter("pgu-kanji/src/joyo.js", "UTF-8");
//        PrintWriter writer = new PrintWriter("pgu-kanji/src/joyo.java.txt", "UTF-8");
        PrintWriter writer = new PrintWriter("pgu-kanji/src/joyo.string.txt", "UTF-8");
        writer.println(sb.toString());
        writer.close();

        System.out.println("Done!");
        // 2136
    }

}
