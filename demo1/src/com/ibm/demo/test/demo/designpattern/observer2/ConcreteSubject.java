package com.ibm.demo.test.demo.designpattern.observer2;

import java.util.List;
import java.util.Observable;

public class ConcreteSubject extends Observable{
	
	private int state;
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void set(int s){
		state=s; //目标对象的状态发生改变
		
		setChanged(); //表示目标对象已经做了更改
		
		notifyObservers(state);	//通知所有的观察者
	}
}
