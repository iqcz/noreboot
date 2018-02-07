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
 *         sometimes, use sql statement to query records,
 *         need many conditions, such as is_delete column, is_visual column,
 *         state etc, when not sure you need add which columns,
 *         you can append some of them in javascript function and return them.
 *         <p>
 *         here use below function in the model.js:
 *         function appendConditions() {
 *              return " or city = 'Perry'";
 *          }
 *         now I want to add some ids such as "7,8",
 *         just change the string ids is fine.
 */
public class SQLAppendDemo {
    public static void main(String[] args)
            throws FileNotFoundException, ScriptException, NoSuchMethodException {
    }

    public static String appendConditons()
            throws FileNotFoundException, ScriptException, NoSuchMethodException {
        String result = "";
        ScriptEngine engine = EngineLoadUtils.loadNashornEngine();

        engine.eval(new FileReader(Constant.JAVASCRIPT_FUNCTION_PATH));
        if (engine instanceof Invocable) {
            Invocable in = (Invocable) engine;
            // execute javascript function in the model.js file.
            result = (String) in.invokeFunction("appendConditions");
        }

        return result;
    }
}
