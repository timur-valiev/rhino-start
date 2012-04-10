package ru.efive.start.rhino.processor;

import org.python.core.PyCode;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import ru.efive.start.rhino.context.ApplicationContextProvider;
import ru.efive.start.rhino.context.ContextHelper;

public class JythonProcessor {
    private final static Logger logger = LoggerFactory.getLogger(JythonProcessor.class);

    public static String processScript(String script, String alias, String json){
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.set("context", ApplicationContextProvider.getApplicationContext());
        interpreter.set("helper",new ContextHelper());
        PyCode code = interpreter.compile(script);
        interpreter.exec(code);
        PyObject object = interpreter.get("result");
        return  object.toString();
    }
}
