package com.newbie.main;

public class GameExe {
	private static boolean last = true, start = false, pause = false, dead = false, win = false, restart = false;

	public static boolean isPlaying() {
		return start && !pause && !dead && !win;
	}
	
	public static boolean theyWin() {
		return start && !pause && !dead && win;
	}
	
	public static boolean theyDie() {
		return start && !pause && dead && !win;
	}
	
	public static boolean isPaused() {
		return start && pause && !dead && !win;
	}
	
	public static boolean yetStart() {
		return !start && !pause && !dead && !win;
	}
	
	public static boolean isLast() {
		return last;
	}

	public static void setLast(boolean last) {
		GameExe.last = last;
	}

	public static void setStart(boolean start) {
		GameExe.start = start;
	}

	public static void setPause(boolean pause) {
		GameExe.pause = pause;
	}

	public static void setDead(boolean dead) {
		GameExe.dead = dead;
	}

	public static void setWin(boolean win) {
		GameExe.win = win;
	}

	public static void setRestart(boolean restart) {
		GameExe.restart = restart;
	}

	public static boolean getRestart() {
		return restart;
	}
}
