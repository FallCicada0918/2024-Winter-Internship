package com.example.hotal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private Integer game_id;
    private String game_name;
    private byte[] game_image;
    private String game_description;
    public String getGameImageBase64() {
        return Base64.getEncoder().encodeToString(this.game_image);
    }
}
