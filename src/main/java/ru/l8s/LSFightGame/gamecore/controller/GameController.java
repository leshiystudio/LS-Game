package ru.l8s.LSFightGame.gamecore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.l8s.LSFightGame.DB.impl.CharacterDaoImpl;
import ru.l8s.LSFightGame.DB.impl.UserDaoImpl;
import ru.l8s.LSFightGame.authentication.impl.User;
import ru.l8s.LSFightGame.gamecore.impl.*;
import ru.l8s.LSFightGame.gamecore.impl.Character;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by LS on 22.04.2016.
 */
@RestController
@RequestMapping("/api")
public class GameController {
    @Autowired
    UserDaoImpl userService;

    @Autowired
    CharacterDaoImpl characterDao;

    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.POST, value = "/fight")
    public @ResponseBody  HashMap<String,Character> fightTo(HttpServletRequest request) {
        Logger logger = Logger.getGlobal();
        logger.info("строка");
        HashMap<String,Character> characterHashMap  = new HashMap<>();
        if(request.getUserPrincipal() != null) {
            User user = userService.get(request.getUserPrincipal().getName());
            Character userCharacter = characterDao.get(user,1);
            Character enemyCharacter;
            try {
                Integer idEnemyCharacter = Integer.parseInt(request.getParameter("id"));
                enemyCharacter =  characterDao.get(idEnemyCharacter);
                System.out.println(userCharacter.getName()+" fight with "+enemyCharacter.getName());
            }catch (NumberFormatException e){

                characterHashMap.put("false",new Character());
                return characterHashMap;
            }

            System.out.println("FIGHT!");
            Character winner, losser;
            if(userCharacter.fightTo(enemyCharacter)){
                winner = userCharacter;
                losser = enemyCharacter;
            }else{
                winner = enemyCharacter;
                losser = userCharacter;
            }

            System.out.println("win: "+winner.getName()+" loss: "+losser.getName());
            characterHashMap.put("winner",winner);
            characterDao.setWin(winner);
            characterDao.setLoss(losser);

            characterDao.saveFight(winner,losser);

            characterDao.updateOnlyHealthAndArmor(userCharacter);
            characterDao.updateOnlyHealthAndArmor(enemyCharacter);

            characterHashMap.put("user",userCharacter);
            characterHashMap.put("enemy",enemyCharacter);

            return characterHashMap;

        }else{
            characterHashMap.put("false",new Character());
            return characterHashMap;
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/characters/{page}")
    public @ResponseBody ResponsePageCharacters getCharacters(HttpServletRequest request, @PathVariable("page") int page){
        int start;
        int countPage;
        int backPage, nextPage;
        if(page<1) {
            page=1;
        }
        int countOnPage=4;
        int countAll = characterDao.getCount()-1;
        //System.out.println("allChars: "+countAll+ " / "+countOnPage );
        //System.out.println(Math.ceil((double) countAll / countOnPage));
        countPage = (int)Math.ceil((double)countAll / countOnPage);
        start = countOnPage * page - countOnPage;
        //start=(start>countPage)?countPage:start;
        User user = userService.get(request.getUserPrincipal().getName());
        countOnPage=(start+countOnPage>countAll)?countAll-start:countOnPage;
        List<Character> characterList = characterDao.getAll(start,countOnPage,user.getId());

        backPage = page-1;
        backPage = backPage<1?countPage:backPage;
        nextPage = page+1;
        nextPage = nextPage>countPage?1:nextPage;

        return new ResponsePageCharacters(characterList,backPage,nextPage,countPage);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/fights/{page}")
    public @ResponseBody Response<List<FIghtEvent>> getFights(HttpServletRequest request, @PathVariable("page") int page){
        int start;
        int countPage;
        int backPage, nextPage;
        if(page<1) {
            page=1;
        }
        int countOnPage=4;
        int countAll = characterDao.getCount()-1;
        //System.out.println("allChars: "+countAll+ " / "+countOnPage );
        //System.out.println(Math.ceil((double) countAll / countOnPage));
        countPage = (int)Math.ceil((double)countAll / countOnPage);
        start = countOnPage * page - countOnPage;
        //start=(start>countPage)?countPage:start;
        countOnPage=(start+countOnPage>countAll)?countAll-start:countOnPage;
        List<FIghtEvent> allFights = characterDao.getAllFights(start,countOnPage);

        backPage = page-1;
        backPage = backPage<1?countPage:backPage;
        nextPage = page+1;
        nextPage = nextPage>countPage?1:nextPage;

        return new Response<>(allFights,0,"успешно");
    }


    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.POST, value = "/mycharacter")
    public @ResponseBody Response<Character> getMyCharacter(HttpServletRequest request){
        if(request.getUserPrincipal() != null) {
            User user = userService.get(request.getUserPrincipal().getName());
            int start;
            try {
                start = Integer.parseInt(request.getParameter("start"));
            }catch (NumberFormatException e){
                System.out.println("NumberFormatException!");
                start=0;
            }
            int count = 4;
            Character character = characterDao.get(user,1);
            return new Response<>(character,1,"успешно");
        }else {
            return new Response<>(new Character(),0,"вы должны авторизоваться");
        }
    }

    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.POST, value = "/saveMycharacter")
    public @ResponseBody  Character saveMyCharacter(@RequestBody Character character,HttpServletRequest request){
        //String name = (request.getUserPrincipal() == null) ? null : request.getUserPrincipal().getName();
        //System.out.println(name);
        Character oldCharacter = characterDao.get(character.getId());
        //System.out.println("compare "+oldCharacter.getName().compareToIgnoreCase(character.getName()));
        if( oldCharacter.getName().compareToIgnoreCase(character.getName()) != 0 ){
            return oldCharacter;
        }
        int allSkillPoints = Character.allSkillPoints;
        int dexterity = character.getDexterity();
        int speed = character.getSpeed();
        int power = character.getPower();
        System.out.println(character.getId()+" skills "+dexterity+" "+speed+" "+power);
        if( dexterity<0 || speed<0 || power<0 ){
            return oldCharacter;
        }
        if((dexterity+speed+power) > allSkillPoints){
            return oldCharacter;//getMyCharacter(request);
        }
        Character newCharacter = new Character(-1,-1,speed,power,dexterity);
        newCharacter.setId(character.getId());
        newCharacter.setName(character.getName());

        characterDao.updateOnlySkills(newCharacter);
        Character rescharacter = characterDao.get(character.getId());
        return rescharacter ;
    }


    //ajax - contentType:"application/json",
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

    //    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    @Secured(value = {"ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String add(HttpServletRequest request) {
        String username=request.getParameter("username");
        final User u = new User(username,"123456");
        return new Integer( userService.insert(u) ).toString();
    }

    @Secured(value = {"ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.POST, value = "/rem")
    public String rem(HttpServletRequest request) {
        String id=request.getParameter("id");
        userService.delete(Integer.parseInt(id));
        return "1";
    }

    @Secured(value = {"ROLE_ADMIN"})
    @RequestMapping(value = "/333", method = RequestMethod.POST )
    public @ResponseBody Result saveProfileJson(HttpServletRequest request){
        System.out.println(request.getUserPrincipal().getName());
        final Result result = new Result();
        result.setAddition(234);
        result.setSubtraction(444);
        result.setMultiplication(555);
        return result;
    }

}


