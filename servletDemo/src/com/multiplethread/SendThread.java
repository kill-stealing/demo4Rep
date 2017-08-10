package com.multiplethread;

public class SendThread extends Thread{
	@Override
	public void run() {
		SendPushJob send=new SendPushJob();
		try {
			send.doJob();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
