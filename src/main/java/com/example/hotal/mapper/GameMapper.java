package com.example.hotal.mapper;

import com.example.hotal.entity.Game;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GameMapper {
    @Select("SELECT * FROM game")
    List<Game> findAllGames();

    //测试用插入游戏Game，可删
    @Insert("<script>" +
            "INSERT INTO game (game_name, game_image, game_description) " +
            "VALUES " +
            "<foreach collection='list' item='item' index='index' separator=','>" +
            "(#{item.game_name}, #{item.game_image}, #{item.game_description})" +
            "</foreach>" +
            "</script>")
    void insertGames(List<Game> Games);

}