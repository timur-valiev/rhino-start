package ru.efive.start.rhino.processor;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.efive.start.rhino.context.ApplicationContextProvider;

public class RhinoProcessor {
    private final static Logger logger = LoggerFactory.getLogger(RhinoProcessor.class);

    public static String processScript(String script, String alias, String json){
        Context cx = Context.enter();
        try {
            Scriptable scope = cx.initStandardObjects();


            Object contextOut = Context.javaToJS(ApplicationContextProvider.getApplicationContext(), scope);
            ScriptableObject.putProperty(scope, "context", contextOut);

            Object jsOut = Context.javaToJS(System.out, scope);
            ScriptableObject.putProperty(scope, "out", jsOut);

            if (!alias.isEmpty()){
                script = "var "+alias+" = " + json +";\n" + script;
            }

            Object obj = cx.evaluateString( scope, script, "TestScript", 1, null );

            logger.info( "Object: " + obj );
            return obj.toString();

        } catch( Exception e ) {
            logger.warn("Cant eval script",e);
            return "Cant eval script"+e.toString();
        } catch (Error error) {
            logger.error("Some error while script processed:", error);
            return "Some error while script processed :"+ error.toString();
        } finally {
            Context.exit();
        }
    }
}
