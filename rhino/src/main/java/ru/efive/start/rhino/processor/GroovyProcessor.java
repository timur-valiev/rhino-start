package ru.efive.start.rhino.processor;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.efive.start.rhino.context.ApplicationContextProvider;

public class GroovyProcessor {
    private final static Logger logger = LoggerFactory.getLogger(GroovyProcessor.class);

    public static String processScript(String script, String alias, String json){
        Binding binding = new Binding();
        binding.setVariable("context", ApplicationContextProvider.getApplicationContext());
        GroovyShell shell = new GroovyShell(binding);

        Object value = shell.evaluate(script);
        String res = value.toString();
        return res;
    }
}
