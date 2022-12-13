/**
 * Created by LS on 12.05.2016.
 */
var lsGame;
var lsGameCLASS = function(){
};

lsGameCLASS.prototype = {
    id: null,
    name: "",
    data: {
        health: 100,
        armor: 100,
        speed: 0,
        power: 0,
        dexterity: 0
    },
    currSkillPoints: null,
    allSkillPoints: 10,
    $health: null,
    $armor: null,
    $dexterity: null,
    $speed: null,
    $power: null,
    $inputDexterity: null,
    $inputSpeed: null,
    $inputPower: null,
    $skillPoints: null,

    currPage:1,
    init: function () {
        var self = this;
        self.$health = $("#health");
        self.$armor = $("#armor");
        self.$dexterity = $("#dexterity");
        self.$speed = $("#speed");
        self.$power = $("#power");

        self.$skillPoints = $("#skillPoints");
        self.$inputDexterity = $("#inputDexterity");
        self.$inputSpeed = $("#inputSpeed");
        self.$inputPower = $("#inputPower");

        self.$skillPoints.popover({
            trigger: "manual",
            delay: {show: 500, hide: 100}
        });

        self.$inputDexterity.change(function (e) {
            self.checkChForm();
        });
        self.$inputSpeed.change(function (e) {
            self.checkChForm();
        });
        self.$inputPower.change(function (e) {
            self.checkChForm();
        });

        this.initMyCharacters();
    },
    setProgress: function ($target, val, setNumber) {
        var min = Number($target.attr("aria-valuemin"));
        var max = Number($target.attr("aria-valuemax"));
        if ((Number(val) < min) || (Number(val) > max)) {
            console.warn("IllegalArgument val=" + val + ", max=" + max + ", min=" + min);
            return false;
        }
        var proc = (val / max) * 100;
        $target.css('width', proc + '%');
        if(setNumber!=false)
            $target.attr('aria-valuenow', val).html(val);
        return true;
    },
    getProgress: function ($target) {
        return $target.attr("aria-valuenow");
        //return $target.parent().next().val();
    },

    setHealth: function (val) {
        this.data.health = val;
        this.setProgress(this.$health, val);
    },
    getHealth: function () {
        return this.data.health = this.getProgress(this.$health);
    },
    setArmor: function (val) {
        this.data.armor = val;
        this.setProgress(this.$armor, val);
    },
    getArmor: function () {
        return this.data.armor = this.getProgress(this.$armor);
    },
    setDexterity: function (val) {
        var self = this;
        var $target = self.$dexterity;
        self.data.dexterity = val;

        self.setProgress($target, val);
        $target.parent().next().val(val);
    },
    getDexterity: function () {
        var self = this;
        var $target = self.$dexterity;
        return self.getProgress($target);
    },
    setSpeed: function (val) {
        var self = this;
        var $target = self.$speed;
        self.data.speed = val;
        self.setProgress($target, val);
        $target.parent().next().val(val);
    },

    getSpeed: function () {
        var self = this;
        var $target = self.$speed;
        return self.getProgress($target);
    },

    setPower: function (val) {
        var self = this;
        var $target = self.$power;
        self.data.power = val;
        self.setProgress($target, val);
        $target.parent().next().val(val);
    },
    getPower: function () {
        var self = this;
        var $target = self.$power;
        return self.getProgress($target);
    },

    setChAll: function (data) {
        var self = this;
        self.setHealth(data.health);
        self.setArmor(data.armor);
        self.currSkillPoints = self.allSkillPoints;

        self.currSkillPoints -= data.dexterity;
        self.currSkillPoints -= data.speed;
        self.currSkillPoints -= data.power;

        if (self.currSkillPoints < 0 || data.dexterity < 0 || data.speed < 0 || data.power < 0) {
            /*
             console.error("no more skill points! " +
             "\ncurrSkillPoints "+self.currSkillPoints+
             "\ndexterity "+data.dexterity+
             "\nspeed "+data.speed+
             "\npower "+data.power
             );
             */
            self.setChAll(self.data);
            self.$skillPoints.popover("show");
            setTimeout(function () {
                self.$skillPoints.popover("hide");
            }, 3000)
            return false;
        }

        self.$skillPoints.html(self.currSkillPoints + " из " + self.allSkillPoints);
        self.setDexterity(data.dexterity);
        self.setSpeed(data.speed);
        self.setPower(data.power);
        return true;
    },

    initMyCharacters: function () {
        var self = this;
        $.ajax({
            url: "/api/mycharacter",
            type: "POST",
            dataType: "json",
            data: {
                start: 0
            },
            complete: function (inputData) {
                var error = inputData.responseJSON.error;
                var data = inputData.responseJSON.data;
                var newData = {};
                self.id = data.id;
                self.name = data.name;
                newData.health = data.health;
                newData.armor = data.armor;
                newData.speed = data.speed;
                newData.power = data.power;
                newData.dexterity = data.dexterity;
                self.allSkillPoints = data.allSkillPoints;
                self.currSkillPoints = data.allSkillPoints;
                self.setChAll(newData);
            }
        })
    },

    toggleOnOffEditCharacter: function () {
        var self = this;
        var $target = $(".form-control");
        if ($target.css("visibility") == "hidden") {
            $target.css("visibility", "");
        } else {
            self.setChAll(self.data);
            $target.css("visibility", "hidden");
        }
    },

    checkChForm: function () {
        var self = this;
        var data = {
            health: self.getHealth(),
            armor: self.getArmor(),
            dexterity: self.$inputDexterity.val(),
            speed: self.$inputSpeed.val(),
            power: self.$inputPower.val()
        };
        return self.setChAll(data);
    },
    saveCh: function () {
        var self = this;
        var sendData = {
            "id": self.id,
            "name": self.name,
            "health": self.data.health,
            "armor": self.data.armor,
            "speed": self.data.speed,
            "power": self.data.power,
            "dexterity": self.data.dexterity,
            "allSkillPoints": self.allSkillPoints
        };
        $.ajax({
            url: "/api/saveMycharacter",
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(sendData),
            complete: function (data) {
                console.log(data);
                self.setChAll(data.responseJSON);
                self.toggleOnOffEditCharacter();
            }
        });
    },
    getPageEnemy: function (page) {
        var self=this;
        $.ajax({
            url: "/api/characters/" + page,
            dataType: "JSON",
            type: "POST",
            //data: {page: page},
            complete: function (inputData) {
                var data = inputData.responseJSON;
                var backPage = data.backPage;
                var nextPage = data.nextPage;
                var chars = data.characterList;
                //console.log(chars);

                var $oldCarts = $(".cart:not(#pattern-prev-enemy)");
                var $nextPageBtns = $(".next > a");
                var $backPageBtns = $(".previous > a");

                $.each(chars, function (i, char) {
                    var $target = $("#pattern-prev-enemy").clone();
                    $target = self.setParamCart($target,char);
                    $("#enemy-viewer").append($target);
                    $target.removeClass("hidden");


                });
                $oldCarts.hide();
                $oldCarts.remove();

                self.currPage = page;

                $nextPageBtns.attr("page", nextPage);
                $backPageBtns.attr("page", backPage);
            }
        })
    },
    setParamCart:function($target,char){
        var self=this;
        $target.removeAttr("id");

        //console.log(i+" "+char);
        //console.log(char.name);
        //console.log(char.health);
        //console.log(char.armor);
        //console.log(char.speed);
        //console.log(char.power);
        //console.log(char.dexterity);

        if(char.health<1) {
            $target.children().addClass("gray");
        }
        $target.find(".username").html(char.name);
        self.setProgress($target.find(".progress.health .progress-bar"),char.health,false);
        self.setProgress($target.find(".progress.armor .progress-bar"),char.armor,false);
        self.setProgress($target.find(".form-characteristics .progress.dexterity .progress-bar"),char.dexterity,false);
        self.setProgress($target.find(".form-characteristics .progress.speed .progress-bar"),char.speed,false);
        self.setProgress($target.find(".form-characteristics .progress.power .progress-bar"),char.power,false);

        $target.find(".win").html(char.countWin);
        $target.find(".loss").html(char.countLoss);

        if(char.health>0 && $target.find(".btn").length>0) {
            $target.find(".btn").click(function (e) {
                console.info("fightTo enemy with id=" + char.id);

                $.ajax({
                    url: "/api/fight",
                    type: "POST",
                    data: {id: char.id},
                    complete: function (inData) {
                        console.log(inData);
                        var data = inData.responseJSON;
                        if (data.winner.id == self.id) {
                            alert("Победа!)");
                        } else {
                            alert("Поражение!(");
                        }
                        self.setChAll(data.user);
                        self.getPageEnemy(self.currPage);
                    }
                });
                return false;
            });
        }else{
            $target.find(".btn").remove();
        }

        return $target;
    },
    getHistory:function(){
        $.ajax({
            url:"/api/fights/1",
            type:"POST",
            data:{},
            complete:function(inData){
                if(inData.error != 0){
                    var data = inData.responseJSON.data;

                    $.each(data, function(i,val){
                        var $fightHistory = $("#fight-history").clone();
                        $fightHistory.removeClass("hidden");
                        $fightHistory.removeAttr("id");

                        //console.log(new Date());

                        $fightHistory.find(".date").html(new Date(val.date).toLocaleFormat()+" :");
                        $fightHistory.find(".char:eq(0)").html(val.winner.name);
                        $fightHistory.find(".char:eq(1)").html(val.loser.name);

                        $winner = $fightHistory.find(".cart:eq(0)");
                        $loser = $fightHistory.find(".cart:eq(1)");

                        $winner.find(".thumbnail").addClass("success");
                        $loser.find(".thumbnail").addClass("gray");

                        lsGame.setParamCart($winner,val.winner);
                        lsGame.setParamCart($loser,val.loser);

                        $("#history").append($fightHistory);
                    });
                }
            }
        });
    }
};

$(function() {
    lsGame = new lsGameCLASS();
});