package ru.efive.start.rhino.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.efive.start.rhino.processor.RhinoProcessor;
import ru.efive.start.rhino.web.forms.ScriptForm;


import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Controller
public class ScriptsController {
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
        ModelAndView modelAndView = new ModelAndView("enterscript");
        modelAndView.addObject("scriptform", form);
        modelAndView.addObject("lang",lang);
        if (result.hasErrors()){
            modelAndView.addObject("result","Some errors");
        } else {
            try {
                Class cl =  Class.forName("ru.efive.start.rhino.processor." + lang.substring(0, 1).toUpperCase() + lang.substring(1) + "Processor");
                Method method = cl.getDeclaredMethod("processScript",String.class,String.class,String.class);
                modelAndView.addObject("result", method.invoke(null,form.getScript(), form.getAlias(), form.getObject()));
            } catch (ClassNotFoundException e) {
                modelAndView.addObject("result",e);
            } catch (NoSuchMethodException e) {
                modelAndView.addObject("result", e);
            } catch (InvocationTargetException e) {
                modelAndView.addObject("result", e);
            } catch (IllegalAccessException e) {
                modelAndView.addObject("result", e);
            }
        }

        return modelAndView;
    }


}
