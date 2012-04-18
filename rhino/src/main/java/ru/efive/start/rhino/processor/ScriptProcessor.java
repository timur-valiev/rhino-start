package ru.efive.start.rhino.processor;

import org.mozilla.javascript.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.efive.start.rhino.context.ApplicationContextProvider;
import ru.efive.start.rhino.context.ContextHelper;
import ru.efive.start.rhino.facade.UserFacade;

import javax.script.*;

@Service
public class ScriptProcessor {
    private final static Logger logger = LoggerFactory.getLogger(ScriptProcessor.class);

    @Autowired
    private UserFacade userFacade;

    @Transactional(rollbackFor = Exception.class)
    public String processScript(ScriptEngine engine, String script, String alias, String json) throws ScriptException {
        if (engine == null)
            return "engine not find!";
        try {
            long time = System.nanoTime();
            for (int i=0;i<new Integer(alias);i++){
            ScriptContext newContext = new SimpleScriptContext();
            Bindings engineScope = newContext.getBindings(ScriptContext.ENGINE_SCOPE);
            engineScope.put("scope", ApplicationContextProvider.getApplicationContext());
            engineScope.put("helper", new ContextHelper());
            engineScope.put("users",ApplicationContextProvider.getApplicationContext().getBean(Class.forName("ru.efive.start.rhino.facade.UserFacade")));
            engineScope.put("orders",ApplicationContextProvider.getApplicationContext().getBean(Class.forName("ru.efive.start.rhino.facade.SnatchOrderFacade")));

            Object res =  engine.eval(script, newContext);
            if (i%1000==0)
                System.out.println(Integer.toString(i) +" "+ (res==null?"null":res.toString()));
            //if (res != null)
            //    return res.toString();
            }
            time = System.nanoTime() - time;
            return Long.toString(time / 1000000000l)+"."+Long.toString(time % 1000000000l); //"Script finished but nothing returned"
        } catch (ScriptException e) {
        //}catch (Exception e) {
            logger.warn("Cant eval script",e);
            throw e;
            //return "Cant eval script"+e.toString();
        }  catch (Error error) {
            logger.error("Some error while script processed:", error);
            return "Some error while script processed :"+ error.toString();
        } catch (ClassNotFoundException e) {
            logger.error("Some error while script processed:", e);
            return "Some error while script processed :"+ e;
        }
    }
}
