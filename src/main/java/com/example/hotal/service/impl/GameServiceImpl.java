package com.example.hotal.service.impl;

import com.example.hotal.entity.Game;
import com.example.hotal.mapper.GameMapper;
import com.example.hotal.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameMapper gameMapper;

    @Override
    public List<Game> getAllGames() {
        return gameMapper.findAllGames();
    }
}

