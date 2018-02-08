package com.nashorn;

import static com.nashorn.util.Constant.JAVASCRIPT_FUNCTION_PATH;

import com.nashorn.util.EngineLoadUtils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * @author i324779
 *         No need to restart the applicion or server,
 *         give a file path to parse the file content.
 *         <p>
 *         here use below function in the model.js:
 *         function getFilePath() {
 *         return "/Users/i324779/myFramework/noreboot/noreboot/src/main/resources/model.js";
 *         }
 *         If you want to read diffrent content of file,
 *         just change the file path is fine.
 */
public class FileParserDemo {
    public static void main(String[] args)
            throws FileNotFoundException, ScriptException, NoSuchMethodException {

        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Please input one number:");
            while (input.hasNextInt()) {
                int number = input.nextInt();

                String filePath = getFilePath();
                System.out.printf("The path of file is %s%n", filePath);

                Path path = Paths.get(filePath);
                List<String> contentOfFile = Files.readAllLines(path);

                System.out.println(contentOfFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * load function in model.js file to set up event start date.
     *
     * @return sql conditions.
     * @throws FileNotFoundException
     * @throws ScriptException
     * @throws NoSuchMethodException
     */
    private static String getFilePath()
            throws FileNotFoundException, ScriptException, NoSuchMethodException {
        String filePath = "";
        ScriptEngine engine = EngineLoadUtils.loadNashornEngine();

        engine.eval(new FileReader(JAVASCRIPT_FUNCTION_PATH));
        if (engine instanceof Invocable) {
            Invocable in = (Invocable) engine;
            // execute javascript function in the model.js file.
            filePath = (String) in.invokeFunction("getFilePath");
        }

        return filePath;
    } // end method getFilePath
}
