package ru.l8s.LSFightGame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.l8s.LSFightGame.DB.impl.UserDaoImpl;
import ru.l8s.LSFightGame.authentication.impl.User;
import ru.l8s.LSFightGame.gamecore.impl.Character;

@Controller
public class MainController {

    /*First method on start application*/
    /*Попадаем сюда на старте приложения (см. параметры аннотации и настройки пути после деплоя) */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo", new pageInfo());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/check-user")
    public ModelAndView checkUser(@ModelAttribute("userJSP") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("secondPage");
        modelAndView.addObject("pageInfo", new pageInfo());
        modelAndView.addObject("userJSP", user);
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(String error) {
        ModelAndView modelAndView = new ModelAndView();
        if (error != null)
            modelAndView.addObject("error", "Your username and password is invalid.");
        modelAndView.addObject("pageInfo", new pageInfo());
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo", new pageInfo());
        modelAndView.addObject("allSkillPoints", Character.allSkillPoints);
        modelAndView.setViewName("profile/index");
        return modelAndView;
    }
    @RequestMapping(value = "/error403", method = RequestMethod.GET)
    public ModelAndView error403() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo", new pageInfo());
        modelAndView.setViewName("error403");
        return modelAndView;
    }
}



