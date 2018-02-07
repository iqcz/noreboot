package com.nashorn.util;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 *
 * @author i324779
 * @date 07/02/2018
 */
public final class EngineLoadUtils {

    /**
     * Because of being a tool class, no need to create objecst.
     * so set the default constructor method as private.
     */
    private EngineLoadUtils() {}

    /**
     *
     * @return
     */
    public static ScriptEngine loadNashornEngine() {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(Constant.LANGUAGE);
        Bindings bind = engine.createBindings();
        engine.setBindings(bind, ScriptContext.ENGINE_SCOPE);

        return engine;
    } // end method loadNashornEngine
}
