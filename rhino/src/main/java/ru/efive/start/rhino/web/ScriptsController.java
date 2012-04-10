package ru.efive.start.rhino.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.efive.start.rhino.processor.RhinoProcessor;
import ru.efive.start.rhino.processor.ScriptProcessor;
import ru.efive.start.rhino.web.forms.ScriptForm;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Controller
public class ScriptsController {
    @Autowired
    private ScriptEngineManager engineManager;

    @RequestMapping(value = "/{lang}", method = RequestMethod.GET)
    public ModelAndView getScriptUI(@PathVariable("lang") String lang)  {
        ModelAndView modelAndView = new ModelAndView("enterscript");
        modelAndView.addObject( "scriptform", new ScriptForm());
        modelAndView.addObject("lang",lang);
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView startPage() {
        return new ModelAndView("redirect:/rhino");
    }


    @RequestMapping(value = "/{lang}/process", method = RequestMethod.GET)
    public ModelAndView procGet(@PathVariable("lang") String lang) {
        return new ModelAndView("redirect:/"+lang);
    }

    @RequestMapping(value = "/{lang}/process", method = RequestMethod.POST)
    public ModelAndView processNewSnatchOrder(@PathVariable("lang") String lang, @Valid @ModelAttribute("scriptform") ScriptForm form, BindingResult result) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName(lang);
        engine = mgr.getEngineByName(lang);
        ModelAndView modelAndView = new ModelAndView("enterscript");
        modelAndView.addObject("scriptform", form);
        modelAndView.addObject("lang",lang);
        if (result.hasErrors()){
            modelAndView.addObject("result","Some errors");
        } else {
            modelAndView.addObject("result",ScriptProcessor.processScript(engineManager.getEngineByName(lang), form.getScript(), form.getAlias(),form.getObject()));
        }

        return modelAndView;
    }


}
