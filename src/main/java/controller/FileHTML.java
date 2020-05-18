package controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHTML {

    public File createFile(File x) {
        File file;
        int i = 0;
        do {
            String path = "FileCheckOut\\Template" + i++;
            String fullPath = path + ".html";
            file = new File(fullPath);
            try {
                if (file.createNewFile()) {
                    Files.copy(x.toPath(), file.toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (file.exists());
        return file;
    }
    public void replaceContent(StringBuffer text,File fileX){
        try {
            Stream<String> stream = Files.lines(fileX.toPath());
            List<String> list = stream.map(line -> line.replace("1999",text)).collect(Collectors.toList());
            Files.write(fileX.toPath(), list);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        File htmlFile = new File("Template.html");
        Charset charset = StandardCharsets.UTF_8;
        File fileX = new FileHTML().createFile(new File("Template.html"));


    }
}
