package ru.efive.start.rhino.processor;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RhinoProcessor {
    private final static Logger logger = LoggerFactory.getLogger(RhinoProcessor.class);

    public static Object processScript(String script) throws Exception {
        Context cx = Context.enter();
        try
        {
            Scriptable scope = cx.initStandardObjects();

            Object obj = cx.evaluateString( scope, script, "TestScript", 1, null );
            logger.info( "Object: " + obj );
            Context.exit();
            return obj;

        }
        catch( Exception e )
        {
            logger.warn("Cant eval script",e);
            throw e;
        }

    }
}
