package ru.efive.start.rhino.processor;

import org.mozilla.javascript.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.efive.start.rhino.context.ApplicationContextProvider;
import ru.efive.start.rhino.context.ContextHelper;

import javax.script.*;

public class ScriptProcessor {
    private final static Logger logger = LoggerFactory.getLogger(ScriptProcessor.class);

    public static String processScript(ScriptEngine engine, String script, String alias, String json){
        if (engine == null)
            return "engine not find!";
        try {
            ScriptContext newContext = new SimpleScriptContext();
            Bindings engineScope = newContext.getBindings(ScriptContext.ENGINE_SCOPE);

            engineScope.put("scope", ApplicationContextProvider.getApplicationContext());
            engineScope.put("helper", new ContextHelper());
            Object res =  engine.eval(script, newContext);
            if (res != null)
                return res.toString();
            return "Script finished but nothing returned";
        } catch (ScriptException e) {
            logger.warn("Cant eval script",e);
            return "Cant eval script"+e.toString();
        }  catch (Error error) {
            logger.error("Some error while script processed:", error);
            return "Some error while script processed :"+ error.toString();
        }
    }
}
