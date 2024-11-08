package vn.edu.iuh.fit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.services.SampleService;

@Controller
@RequestMapping("/sample")
public class SampleController {
    @Autowired
    private SampleService sampleService;

    @RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("message", sampleService.calTotal(10, 20));
        return mav;
    }
}
