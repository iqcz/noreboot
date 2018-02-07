package com.nashorn;

import com.nashorn.util.Constant;
import com.nashorn.util.EngineLoadUtils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * @author i324779
 *         No need to restart the applicion or server,
 *         sometimes, we need hard code some configuration,
 *         and need to change them.
 *         <p>
 *         here use below function in the model.js:
 *         function allIds() {
 *         return "1,2,3,4,5,6";
 *         }
 *         now I want to add some ids such as "7,8",
 *         just change the string ids is fine.
 */
public class HardCodeDemo {
    public static void main(String[] args)
            throws FileNotFoundException, ScriptException, NoSuchMethodException {

        ScriptEngine engine = EngineLoadUtils.loadNashornEngine();

        // user Scanner to wait user input and application keeping running.
        // you can modify the formula function,
        // and re-input numbers, can get different result.
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Please input one number:");
            while (input.hasNextInt()) {
                int number = input.nextInt();

                System.out.printf("Input number is: %d%n", number);

                engine.eval(new FileReader(Constant.JAVASCRIPT_FUNCTION_PATH));
                if (engine instanceof Invocable) {
                    Invocable in = (Invocable) engine;
                    // execute javascript function in the model.js file.
                    String result = (String) in.invokeFunction("allIds");

                    System.out.printf("The result is %s%n", result);
                }
            }
        }
    }
}
