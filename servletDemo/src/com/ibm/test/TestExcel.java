package com.ibm.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestExcel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path="E:/A4_1.xlsx";
		TestExcel t=new TestExcel();
		t.readExcel(path);
	}
	
	public void readExcel(String path){
		File file=new File(path);
		InputStream is=null;
		XSSFWorkbook xssfWorkbook=null;
		try {
			is = new FileInputStream(file);
			xssfWorkbook= new XSSFWorkbook(is);
			// 获取每一个工作薄
			for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
				if (xssfSheet == null) {
					continue;
				}
				// 获取当前工作薄的每一行
				for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow != null) {
						System.out.println("第"+(rowNum+1)+"行是");
						for(int i=0;i<xssfRow.getLastCellNum();i++){
							XSSFCell one = xssfRow.getCell(i);
							String str=this.getValue(one);
							System.out.print(str+" : ");
						}
						System.out.println();
//						XSSFCell one = xssfRow.getCell(0);
//						// 读取第一列数据
//						XSSFCell two = xssfRow.getCell(1);
//						// 读取第二列数据
//						XSSFCell three = xssfRow.getCell(2);
						// 读取第三列数据

					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 转换数据格式
	private String getValue(XSSFCell xssfRow) {
		if(null!=xssfRow&&!"".equals(xssfRow)){
			if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
				return String.valueOf(xssfRow.getBooleanCellValue());
			} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
				return String.valueOf(xssfRow.getNumericCellValue());
			} else {
				return String.valueOf(xssfRow.getStringCellValue());
			}
		}else{
			return "";
		}
		
	}

}
