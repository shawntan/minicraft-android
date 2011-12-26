package com.mojang.ld22;

import java.io.Serializable;

import com.mojang.ld22.entity.Player;
import com.mojang.ld22.level.Level;

public class State implements Serializable {
	int playerDeadTime;
	int wonTimer;
	int gameTime;
	boolean hasWon;
	int currentLevel;
	Player player;
	Level[] levels;
}
