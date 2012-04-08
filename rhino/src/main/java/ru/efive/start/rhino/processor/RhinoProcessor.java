package ru.efive.start.rhino.processor;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RhinoProcessor {
    private final static Logger logger = LoggerFactory.getLogger(RhinoProcessor.class);

    public static String processScript(String script, String alias, String json){
        Context cx = Context.enter();
        try
        {
            Scriptable scope = cx.initStandardObjects();
            if (!alias.isEmpty()){
                script = "var "+alias+" = " + json +";\n" + script;
            }
            Object obj = cx.evaluateString( scope, script, "TestScript", 1, null );
            logger.info( "Object: " + obj );
            return obj.toString();
        }
        catch( Exception e )
        {
            logger.warn("Cant eval script",e);
            return "Cant eval script"+e.toString();
        }  catch (Error error){
            logger.error("Some error while script processed:", error);
            return "Some error while script processed :"+ error.toString();
        } finally {
            Context.exit();
        }
    }
}
