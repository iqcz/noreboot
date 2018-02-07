package com.nashorn;

import com.nashorn.util.Constant;
import com.nashorn.util.EngineLoadUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.Scanner;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 * @author i324779
 *         No need to restart the applicion or server,
 *         wo can change the element in the formula,
 *         and get different result.
 *         <p>
 *         here use below function in the model.js:
 *         function formula(var1, var2) {
 *         return var1 + var2 + factor + 5;
 *         }
 *         if the constant 5 need be be change frequently,
 *         here is a good solution.
 */
public class FormulaModificationDemo {

    public static void main(String[] args)
            throws FileNotFoundException, ScriptException, NoSuchMethodException {

        ScriptEngine engine = EngineLoadUtils.loadNashornEngine();
        // here set a element in the formula, no need modify.
        engine.put("factor", 0);

        // user Scanner to wait user input and application keeping running.
        // you can modify the formula function,
        // and re-input numbers, can get different result.
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Please input two numbers:");
            while (input.hasNextInt()) {
                int first = input.nextInt();
                int second = input.nextInt();
                System.out.printf("Input number are: %d, %d%n", first, second);

                engine.eval(new FileReader(Constant.JAVASCRIPT_FUNCTION_PATH));
                if (engine instanceof Invocable) {
                    Invocable in = (Invocable) engine;
                    // execute javascript function in the model.js file.
                    Double result = (Double) in.invokeFunction("formula", first, second);

                    System.out.printf("The result is %d%n", result.intValue());
                }
            }
        }
    }
}