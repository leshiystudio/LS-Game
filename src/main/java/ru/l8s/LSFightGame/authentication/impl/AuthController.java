package ru.l8s.LSFightGame.authentication.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.l8s.LSFightGame.DB.impl.CharacterDaoImpl;
import ru.l8s.LSFightGame.DB.impl.UserDaoImpl;
import ru.l8s.LSFightGame.gamecore.impl.Character;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LS on 17.05.2016.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    @Qualifier("userService")
    UserDaoImpl userService;

    @Autowired
    @Qualifier("characterService")
    CharacterDaoImpl characterDao;

    @RequestMapping(method = RequestMethod.POST, value = "/existUser")
    public String existUser(HttpServletRequest request) {
        String username = request.getParameter("username");
        boolean existUser = userService.exist(username);
        if(existUser){
            return "{\"exist\":true}";
        }else{
            return "{\"exist\":false}";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/reg")
    public @ResponseBody User regUser(@RequestBody User newUser) {
        boolean existUser = userService.exist(newUser.getUsername());
        if(existUser){
            return new User();
        }

        int id = userService.insert(newUser);
        Character ch = new Character();
        ch.setUser_id(id);
        ch.setName(newUser.getUsername());
        ch.setHealth(100);
        ch.setArmor(100);
        ch.setDexterity(0);
        ch.setSpeed(0);
        ch.setPower(0);

        //System.out.println(ch.getUser_id()+" "+ch.getName()+" "+ch.getHealth()+" "+ch.getDexterity()+" "+ch.getSpeed()+" "+ch.getPower());

        characterDao.insert(ch);
        newUser.setId(id);

        return newUser;
    }
}
