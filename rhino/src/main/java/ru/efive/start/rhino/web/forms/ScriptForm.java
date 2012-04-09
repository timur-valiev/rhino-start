package ru.efive.start.rhino.web.forms;

import org.hibernate.validator.constraints.NotBlank;

public class ScriptForm {
    @NotBlank(message = "Blanck script!")
    public String script;

    public String alias;

    public String object;

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
