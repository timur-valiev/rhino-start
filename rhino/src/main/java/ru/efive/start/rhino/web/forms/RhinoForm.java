package ru.efive.start.rhino.web.forms;

import org.hibernate.validator.constraints.NotBlank;

public class RhinoForm {
    @NotBlank(message = "Blanck script!")
    public String script;

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}
