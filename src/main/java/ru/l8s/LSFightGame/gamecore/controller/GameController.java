package ru.l8s.LSFightGame.gamecore.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.l8s.LSFightGame.gamecore.impl.Request;
import ru.l8s.LSFightGame.gamecore.impl.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LS on 22.04.2016.
 */
@RestController
@RequestMapping("/api")
public class GameController {
    @RequestMapping(method = RequestMethod.POST, value = "/888")
    public @ResponseBody String test(@RequestBody Request request) {
        Integer v = request.getLeft();
        System.out.println("JSON GO! "+v.toString());
        final Result result = new Result();
        result.setAddition(234);
        result.setSubtraction(request.getLeft() - request.getRight());
        result.setMultiplication(request.getLeft() * request.getRight());
        return v.toString();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/1", consumes = "application/json")
    public @ResponseBody String test2(HttpServletRequest request) {
        String attack = request.getParameter("attack");
        return attack;
    }

    @RequestMapping(value = "/333", method = RequestMethod.POST, consumes="application/json")
    public @ResponseBody Result saveProfileJson(HttpServletRequest request){
        System.out.println(request.getParameter("profileCheckedValues"));
        final Result result = new Result();
        result.setAddition(234);
        result.setSubtraction(444);
        result.setMultiplication(555);
        return result;
    }
    public static void main(String[] args) {
        System.out.println("HelloWorld");
    }
}


