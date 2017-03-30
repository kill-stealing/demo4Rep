package com.ibm.sorm;

import java.io.File;

public class CopyOfTest {
	public static void main(String[] args) {
		String srcPath=DataSource.getConf().getSrcPath()+"\\";
		String packagePath=DataSource.getConf().getPoPackage().replace(".", "/");
		File file=new File(srcPath+packagePath);
		if(!file.exists()){
			file.mkdirs();
		}
		//File file=new File("C:Users\\IBM_ADMIN\\workspace\\SormTest\\src\\com\\bjsxt\\po\\Nase_posts");
	}
}	
