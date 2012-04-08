package ru.efive.start.rhino.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.efive.start.rhino.processor.RhinoProcessor;
import ru.efive.start.rhino.web.forms.RhinoForm;


import javax.validation.Valid;

@Controller
public class RhinoController {
    @RequestMapping(value = "/rhino", method = RequestMethod.GET)
    public ModelAndView addSnatchOrder() {
        ModelAndView modelAndView = new ModelAndView("enterscript", "rhinoform", new RhinoForm());
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView strartPage() {
        return new ModelAndView("redirect:/rhino");
    }

    @RequestMapping(value = "/rhino/process", method = RequestMethod.POST)
    public ModelAndView processNewSnatchOrder(@Valid @ModelAttribute("rhinoform") RhinoForm form, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("enterscript");
        modelAndView.addObject("rhinoform", form);
        if (result.hasErrors()){
            modelAndView.addObject("result","Some errors");
        } else {
            modelAndView.addObject("result", RhinoProcessor.processScript(form.getScript(),form.getAlias(),form.getObject()));
        }

        return modelAndView;
    }

}
