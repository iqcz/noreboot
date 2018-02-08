package com.nashorn;

import com.nashorn.util.EngineLoadUtils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static com.nashorn.util.Constant.JAVASCRIPT_FUNCTION_PATH;

/**
 * @author i324779
 *         No need to restart the applicion or server,
 *         when festival is coming,
 *         you can hold a event to sales promotion, voting activity etc,
 *         need to set up a date for them.
 *         so for this case,
 *         you can use javascript function to return a date string.
 *         <p>
 *         here use below function in the model.js:
 *         function setUpEventStartDate() {
 *              return "20180214";
 *         }
 *         now I want to change another date,
 *         just change the string date is fine.
 */
public class EventDateDemo {
    public static void main(String[] args)
            throws FileNotFoundException, ScriptException, NoSuchMethodException {

        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Please input one number:");
            while (input.hasNextInt()) {
                int number = input.nextInt();

                String eventStartDate = changeEventStartDate();
                System.out.printf("The Event start date is %s.", eventStartDate);
            }
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
    private static String changeEventStartDate()
            throws FileNotFoundException, ScriptException, NoSuchMethodException {
        String result = "";
        ScriptEngine engine = EngineLoadUtils.loadNashornEngine();

        engine.eval(new FileReader(JAVASCRIPT_FUNCTION_PATH));
        if (engine instanceof Invocable) {
            Invocable in = (Invocable) engine;
            // execute javascript function in the model.js file.
            result = (String) in.invokeFunction("setUpEventStartDate");
        }

        return result;
    } // end method changeEventStartDate
}
