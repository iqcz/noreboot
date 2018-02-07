package com.nashorn;

import com.nashorn.util.Constant;
import com.nashorn.util.EngineLoadUtils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

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
 *         now I want to add some sql query conditions,
 *         just change the string sql is fine.
 */
public class SQLAppend {

    /**
     * load function in model.js file to append sql conditions.
     * @return sql conditions.
     * @throws FileNotFoundException
     * @throws ScriptException
     * @throws NoSuchMethodException
     */
    public static String appendConditions()
            throws FileNotFoundException, ScriptException, NoSuchMethodException {
        String sqlConditions = "";
        ScriptEngine engine = EngineLoadUtils.loadNashornEngine();

        engine.eval(new FileReader(Constant.JAVASCRIPT_FUNCTION_PATH));
        if (engine instanceof Invocable) {
            Invocable in = (Invocable) engine;
            // execute javascript function in the model.js file.
            sqlConditions = (String) in.invokeFunction("appendConditions");
        }

        return sqlConditions;
    } // end method appendConditions
}
