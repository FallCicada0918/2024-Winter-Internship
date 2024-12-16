package com.example.hotal.controller;

import com.example.hotal.entity.Game;
import com.example.hotal.mapper.GameMapper;
import com.example.hotal.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GameController {
    @Autowired
    private GameService gameService;
    @Autowired
    private GameMapper gameMapper;

    @GetMapping("/games")
    public String getAllGames(Model model) {
        List<Game> games = gameService.getAllGames();
        model.addAttribute("games", games);
        return "games";
    }

    public byte[] convertFileToByteArray(File file) {
        byte[] fileData = null;
        try (FileInputStream fis = new FileInputStream(file)) {
            fileData = new byte[(int) file.length()];
            fis.read(fileData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileData;
    }

    @GetMapping("/gameInsert")
    public String insertGameTest() {
        List<Game> Games = new ArrayList<>();
        File img1 = new File("D:\\2exec\\hotal\\src\\main\\resources\\static\\pic\\B.png");
        byte[] img1Data = convertFileToByteArray(img1);
        Game gAme = new Game(3,"博德之门3", img1Data,"召集你的队伍，返回被遗忘的国度，开启一段充满友谊与背叛、牺牲与生存，以及至高无上力量诱惑的传奇故事。\n" +
                "神秘的力量正在你体内苏醒，而这一切都来源于夺心魔种在你大脑里的寄生虫。反抗吧，将黑暗的力量反掌为用。你也可以选择接受这种腐化，变成终极的邪恶。\n" +
                "故事将在D&D（龙与地下城）的世界中展开");
        Games.add(gAme);
        gameMapper.insertGames(Games);
        return "games";
    }

}