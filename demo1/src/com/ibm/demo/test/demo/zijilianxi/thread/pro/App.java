package com.ibm.demo.test.demo.zijilianxi.thread.pro;

public class App {

	public static void main(String[] args) {
		Movie m=new Movie();
		Player player=new Player(m);
		Watcher watcher=new Watcher(m);
		new Thread(player).start();
		new Thread(watcher).start();
	}

}
