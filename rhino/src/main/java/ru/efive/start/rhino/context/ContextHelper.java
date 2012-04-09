package ru.efive.start.rhino.context;

public class ContextHelper {
    public Class getClass(String name) throws ClassNotFoundException {
        return Class.forName(name);
    }
}
