package com.ibm.excel;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hsp.dao.Dao;
import com.hsp.dao.DaoImpl;
import com.hsp.util.StringUtil;

public class TestExcel {
	
	private Dao dao=new DaoImpl();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String path="E:/A4_1.xlsx";
		TestExcel t=new TestExcel();
		t.readExcel1(path);
		
		String path1="E:/A4_2.xlsx";
		t.readExcel2(path1);
		
		String title[] = {"被保险人姓名","身份证号","账户类型","银行卡号","保险金额(元)","购买时间","保单生效时间","保单失效时间"};
		String path = "E:/";  
        String fileName = "A4_1_new";  
        String fileType = "xlsx";
		
//		TestExcel t=new TestExcel();
//		t.compareChePaiHao("A4-");
		
		String path2="E:/cheChangTongJi.xlsx";
		t.readExcelCheChangTongJi(path2);
//		t.compareName("A4-");
 * 
 * 
*/		
		TestExcel t=new TestExcel();
		String str="A4-";
//		t.importData(path, path2, path3);
//		t.handleData("A4-");
		t.compareName(str);
	}
	
	public void importData(){
		String path="E:/A4_1.xlsx";
		String path2="E:/A4_2.xlsx";
		String path3="E:/cheChangTongJi.xlsx";
		
		this.readExcel1(path);
		this.readExcel2(path2);
		this.readExcelCheChangTongJi(path3);
	}
	
	public void importData(String path,String path2,String path3){
		this.readExcel1(path);
		this.readExcel2(path2);
		this.readExcelCheChangTongJi(path3);
	}
	
	public void handleData(String str){
		this.compareLouHao(str);
		this.compareChePaiHao(str);
		this.compareName(str);
	}
	
	// str= A4
		public void compareName(String str){
			List<CheWeiTaiZhangVO> list1=dao.getCheWeiTaiZhangVOs();
			List<CheLiangDangAnVO> list2=dao.getcheLiangDangAnVOs();
			List<CheChangTongJiVO> list3=dao.getChangTongJiVOs();
			
			for (int i = 0; i < list1.size(); i++) {
				CheWeiTaiZhangVO vo1=list1.get(i); 
				String vo1_cheWeiHao=vo1.getCheWeiHao();	//A4-CK-1-（-1）-A001
				vo1_cheWeiHao=vo1_cheWeiHao.substring(vo1_cheWeiHao.length()-4,vo1_cheWeiHao.length()); //A001
				String vo1_name=vo1.getName();
				String vo1_louHao=vo1.getLouHao();
				if(vo1_louHao!=null&&!"".equals(vo1_louHao)){
					vo1_louHao=vo1_louHao.trim();			//A4-4#-1-14-3
					String vo1_louHao1=StringUtil.removeHeng(vo1_louHao, str); //1#1704
					for (int j = 0; j < list2.size(); j++) {
						CheLiangDangAnVO vo2=list2.get(j);
						String vo2_suoShuCheWei=vo2.getSuoshu_chewei();		//B257
						String vo2_cheLiangFenzu=vo2.getCheliang_fenzhu();	//1#1704
						String vo2_name=vo2.getChezhu_name();
						//当车位号相同的时候 判断
						vo2_suoShuCheWei=StringUtil.handleCheWeiHao(vo2_suoShuCheWei);
						if(vo2_suoShuCheWei.equals(vo1_cheWeiHao)){
							for (int k = 0; k < list3.size(); k++) {
								CheChangTongJiVO vo3=list3.get(k);
								String vo3_louHao=vo3.getFangwu_name();
								String vo3_name=vo3.getYezhu_name();
								//当楼号相同的时候进行判断
								if(vo3_louHao.equals(vo2_suoShuCheWei)){
									vo1.setTemp_name1(vo1_name);
									vo1.setTemp_name2(vo2_name);
									vo1.setTemp_name3(vo3_name);
									
									vo2.setTemp_name1(vo1_name);
									vo2.setTemp_name2(vo2_name);
									vo2.setTemp_name3(vo3_name);
									
									vo3.setTemp_name1(vo1_name);
									vo3.setTemp_name2(vo2_name);
									vo3.setTemp_name3(vo3_name);
									
									dao.doUpdateTaiZhangName(vo1);
									dao.doUpdateDangAnName(vo2);
									dao.doUpdateCheChangTongJiName(vo3);
									break;
									
									/*if(!vo1_name.equals(vo2_name)&&!vo1_name.equals(vo3_name)
											&&!vo2_name.equals(vo3_name)){
										vo1.setTemp_name1(vo1_name);
										vo1.setTemp_name2(vo2_name);
										vo1.setTemp_name3(vo3_name);
										
										vo1.setTemp_name1(vo1_name);
										vo1.setTemp_name2(vo2_name);
										vo1.setTemp_name3(vo3_name);
										
										vo3.setTemp_name1(vo1_name);
										vo3.setTemp_name2(vo2_name);
										vo3.setTemp_name3(vo3_name);
										
									}else if(vo1_name.equals(vo2_name)&&!vo1_name.equals(vo3_name)
											&&!vo2_name.equals(vo3_name)){
										
										vo1.setTemp_name3(vo3_name);
										
										vo2.setTemp_name3(vo3_name);
										
										vo3.setTemp_name1(vo1_name);
										
									}else if (!vo1_name.equals(vo2_name)&&vo1_name.equals(vo3_name)
											&&!vo2_name.equals(vo3_name)) {
										
										vo1.setTemp_name2(vo2_name);
										
										vo2.setTemp_name1(vo1_name);
										
										vo3.setTemp_name2(vo2_name);
										
									}else if (!vo1_name.equals(vo2_name)&&!vo1_name.equals(vo3_name)
											&&vo2_name.equals(vo3_name)) {
										
										vo1.setTemp_name2(vo2_name);
										
										vo2.setTemp_name1(vo1_name);
										
										vo3.setTemp_name1(vo1_name);
										
									}*/
								}
							}
							break;
						}
					}
				}else{
					
				}
			}
		}
	
	// str= A4
	public void compareLouHao(String str){
		List<CheWeiTaiZhangVO> list1=dao.getCheWeiTaiZhangVOs();
		List<CheLiangDangAnVO> list2=dao.getcheLiangDangAnVOs();
		
		for (int i = 0; i < list1.size(); i++) {
			CheWeiTaiZhangVO vo1=list1.get(i); 
			String vo1_cheWeiHao=vo1.getCheWeiHao();	//A4-CK-1-（-1）-A001
			vo1_cheWeiHao=vo1_cheWeiHao.substring(vo1_cheWeiHao.length()-4,vo1_cheWeiHao.length()); //A001
			String vo1_louHao=vo1.getLouHao();
			if(vo1_louHao!=null&&!"".equals(vo1_louHao)){
				vo1_louHao=vo1_louHao.trim();			//A4-4#-1-14-3
				
				String vo1_louHao1=StringUtil.removeHeng(vo1_louHao, str); //1#1704
				
				String vo1_chePaiHao2=vo1.getChePaiHao2();  //辽B562Q9
				String vo1_chePaiHao3=vo1.getChePaiHao3();
				String vo1_chePaiHao4=vo1.getChePaiHao4();
				String vo1_chePaiHao5=vo1.getChePaiHao5();
				
				for (int j = 0; j < list2.size(); j++) {
					CheLiangDangAnVO vo2=list2.get(j);
					String vo2_chePaiHao=vo2.getChePaiHao();		 	//辽BC5E31
					String vo2_suoShuCheWei=vo2.getSuoshu_chewei();		//B257
					String vo2_cheLiangFenzu=vo2.getCheliang_fenzhu();	//1#1704
					//当车位号相同的时候 判断
					vo2_suoShuCheWei=StringUtil.handleCheWeiHao(vo2_suoShuCheWei);
					if(vo2_suoShuCheWei.equals(vo1_cheWeiHao)){
						//判断楼号和车辆分组是否相同
						if(!vo2_cheLiangFenzu.equals(vo1_louHao1)){
							vo2.setLouhao_temp1(vo1_louHao);
							vo2.setCheliang_fenzhu(vo1_louHao1);
							vo1.setLouhao_temp1(vo2_cheLiangFenzu);
						}
						
						dao.doUpdateDangAn(vo2);
						dao.doUpdateTaiZhang(vo1);
					}
				}
			}else{
				
			}
		}
	}
	
	public void compareChePaiHao(String str){
		List<CheWeiTaiZhangVO> list1=dao.getCheWeiTaiZhangVOs();
		List<CheLiangDangAnVO> list2=dao.getcheLiangDangAnVOs();
		
		for (int i = 0; i < list1.size(); i++) {
			CheWeiTaiZhangVO vo1=list1.get(i); 
			String vo1_cheWeiHao=vo1.getCheWeiHao();	//A4-CK-1-（-1）-A001
			vo1_cheWeiHao=vo1_cheWeiHao.substring(vo1_cheWeiHao.length()-4,vo1_cheWeiHao.length()); //A001
			String vo1_louHao=vo1.getLouHao();
			if(vo1_louHao!=null&&!"".equals(vo1_louHao)){
				vo1_louHao=vo1_louHao.trim();			//A4-4#-1-14-3
				String vo1_louHao1=StringUtil.removeHeng(vo1_louHao, str); //1#1704
				String vo1_chePaiHao2=vo1.getChePaiHao2();  //辽B562Q9
				String vo1_chePaiHao3=vo1.getChePaiHao3();
				String vo1_chePaiHao4=vo1.getChePaiHao4();
				String vo1_chePaiHao5=vo1.getChePaiHao5();
				List<String> chePaiHaoList=new ArrayList<>();
				if(vo1_chePaiHao2!=null&&!"".equals(vo1_chePaiHao2)){
					chePaiHaoList.add(vo1_chePaiHao2);
				}
				
				if(vo1_chePaiHao3!=null&&!"".equals(vo1_chePaiHao3)){
					chePaiHaoList.add(vo1_chePaiHao3);
				}
				
				if(vo1_chePaiHao4!=null&&!"".equals(vo1_chePaiHao4)){
					chePaiHaoList.add(vo1_chePaiHao4);
				}
				
				if(vo1_chePaiHao5!=null&&!"".equals(vo1_chePaiHao5)){
					chePaiHaoList.add(vo1_chePaiHao5);
				}
				
				for (int j = 0; j < list2.size(); j++) {
					CheLiangDangAnVO vo2=list2.get(j);
					String vo2_chePaiHao=vo2.getChePaiHao();		 	//辽BC5E31
					
					String vo2_suoShuCheWei=vo2.getSuoshu_chewei();		//B257
					String vo2_cheLiangFenzu=vo2.getCheliang_fenzhu();	//1#1704
					//当车位号相同的时候 判断
					vo2_suoShuCheWei=StringUtil.handleCheWeiHao(vo2_suoShuCheWei);
					if(vo2_suoShuCheWei.equals(vo1_cheWeiHao)){
						//判断车牌号是否相同
						if(!vo2_chePaiHao.equals(vo1_chePaiHao2)){
							
						}
						boolean flag=false;
						for (int k = 0; k < chePaiHaoList.size(); k++) {
							if(vo2_chePaiHao.equals(chePaiHaoList.get(k))){
								flag=true;
								break;
							}
						}
						
						//如果都不相同 则吧第一个车牌号赋值给vo2
						if(flag==false){
							vo2.setChepai_hao1(vo1_chePaiHao2);
							vo1.setChepai_hao1(vo2_chePaiHao);
							dao.doUpdateDangAnChePaiHao(vo2);
							dao.doUpdateTaiZhangChePaiHao(vo1);
						}
					}
				}
			}else{
				
			}
		}
	}
	
	public void readExcelCheChangTongJi(String path){
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
						CheChangTongJiVO vo=new CheChangTongJiVO();
						XSSFCell one = xssfRow.getCell(4);
						vo.setFangwu_name(getValue(one));
						one = xssfRow.getCell(5);
						vo.setYezhu_name(getValue(one));
						dao.doInsert(vo);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void readExcel1(String path){
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
						CheWeiTaiZhangVO vo=new CheWeiTaiZhangVO();
						XSSFCell one = xssfRow.getCell(0);
						vo.setXuHao(getValue(one));
						one = xssfRow.getCell(1);
						vo.setCheWeiHao(getValue(one));
						one = xssfRow.getCell(2);
						vo.setLouHao(getValue(one));
						one = xssfRow.getCell(3);
						vo.setName(getValue(one));
						one = xssfRow.getCell(4);
						vo.setPhoneNum(getValue(one));
						one = xssfRow.getCell(5);
						vo.setStartDate(getValue1(one));
						one = xssfRow.getCell(6);
						vo.setEndDate(getValue1(one));
						one = xssfRow.getCell(7);
						vo.setChePaiHao1(getValue(one));
						one = xssfRow.getCell(8);
						vo.setChePaiHao2(getValue(one));
						one = xssfRow.getCell(9);
						vo.setChePaiHao3(getValue(one));
						one = xssfRow.getCell(10);
						vo.setChePaiHao4(getValue(one));
						one = xssfRow.getCell(11);
						vo.setChePaiHao5(getValue(one));
						dao.doInsert(vo);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readExcel2(String path){
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
				for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow != null) {
						System.out.println("第"+(rowNum+1)+"行是");
						CheLiangDangAnVO vo=new CheLiangDangAnVO();
						XSSFCell one = xssfRow.getCell(0);
						vo.setChePaiHao(getValue(one));
						one = xssfRow.getCell(1);
						vo.setCheLiangLeiXing(getValue(one));
						one = xssfRow.getCell(2);
						vo.setYou_xiao_start_date(getValue1(one));
						one = xssfRow.getCell(3);
						vo.setYou_xiao_end_date(getValue1(one));
						one = xssfRow.getCell(4);
						vo.setCheliang_yuliang(getValue(one));
						one = xssfRow.getCell(5);
						vo.setCheliang_leibie(getValue1(one));
						one = xssfRow.getCell(6);
						vo.setCheliang_xinghao(getValue1(one));
						one = xssfRow.getCell(7);
						vo.setCheliang_yanse(getValue(one));
						one = xssfRow.getCell(8);
						vo.setChezhu_name(getValue(one));
						one = xssfRow.getCell(9);
						vo.setShenfen_zheng(getValue(one));
						one = xssfRow.getCell(10);
						vo.setLianxi_dianhua(getValue(one));
						one = xssfRow.getCell(11);
						vo.setLianxi_dizhi(getValue(one));
						
						one = xssfRow.getCell(12);
						vo.setSuoshu_chewei(getValue(one));
						one = xssfRow.getCell(13);
						vo.setCheliang_fenzhu(getValue(one));
						one = xssfRow.getCell(14);
						vo.setChechang_quanxian(getValue(one));
						one = xssfRow.getCell(15);
						vo.setZhuche_date(getValue1(one));
						one = xssfRow.getCell(16);
						vo.setBei_zhu(getValue(one));
						one = xssfRow.getCell(17);
						vo.setShifou_jinyong(getValue(one));
						one = xssfRow.getCell(18);
						vo.setShifou_zhuxiao(getValue(one));
						
						dao.doInsert(vo);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writer(String path, String fileName,String fileType,String titleRow[]){  
		Workbook wb = null; 
		Sheet sheet =null;
		try {
	        String excelPath = path+File.separator+fileName+"."+fileType;
	        File file = new File(excelPath);
	        //创建工作文档对象   
	        if (!file.exists()) {
	            if (fileType.equals("xls")) {
	                wb = new HSSFWorkbook();
	            } else if(fileType.equals("xlsx")) {
	                wb = new XSSFWorkbook();
	            } else {
	                throw new Exception("文件格式不正确");
	            }
	            
	        } else {
	            if (fileType.equals("xls")) {  
	                wb = new HSSFWorkbook();  
	            } else if(fileType.equals("xlsx")) { 
	                wb = new XSSFWorkbook();  
	            } else {  
	                throw new Exception("文件格式不正确");
	            }  
	        }
	         //创建sheet对象   
	        if (sheet==null) {
	            sheet = (Sheet) wb.createSheet("sheet1");  
	        }
	        //添加表头  
	        Row row = sheet.createRow(0);
	        Cell cell = row.createCell(0);
	        
	        row = sheet.createRow(1);    //创建第二行    
	        for(int i = 0;i < 12;i++){  
	            cell = row.createCell(i);  
	            cell.setCellValue(titleRow[i]);  
	            sheet.setColumnWidth(i, 20 * 256); 
	        }  
	        row.setHeight((short) 540); 

	        List list=new ArrayList<>();
	        //循环写入行数据   
	        for (int i = 0; i < list.size(); i++) {  
	            row = (Row) sheet.createRow(i+2);  
	            row.setHeight((short) 500); 
//	            row.createCell(0).setCellValue(( list.get(i)).getInsuraceUser());
//	            row.createCell(1).setCellValue(( list.get(i)).getIdCard());
//	            row.createCell(2).setCellValue(( list.get(i)).getType());
//	            row.createCell(3).setCellValue(( list.get(i)).getBankCardId());
//	            row.createCell(4).setCellValue(( list.get(i)).getMoney());
//	            row.createCell(5).setCellValue(( list.get(i)).getBuyTime());
//	            row.createCell(6).setCellValue(( list.get(i)).getInsStartTime());
//	            row.createCell(7).setCellValue(( list.get(i)).getInsEndTime());
	        }  
	        
	        //创建文件流   
	        OutputStream stream = new FileOutputStream(excelPath);  
	        //写入数据   
	        wb.write(stream);  
	        //关闭文件流   
	        stream.close();  
		} catch (Exception e) {
			// TODO: handle exception
		}
    }  

	// 转换数据格式
	private String getValue(XSSFCell xssfRow) {
		if(null!=xssfRow&&!"".equals(xssfRow)){
			if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
				return String.valueOf(xssfRow.getBooleanCellValue());
			} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
				DecimalFormat df = new DecimalFormat("#");
				return df.format(xssfRow.getNumericCellValue())+"";
			}else{
				return String.valueOf(xssfRow.getStringCellValue().trim());
			}
		}else{
			return "";
		}
	}
	
	// 转换数据格式
	private String getValue1(XSSFCell xssfRow) {
		if(null!=xssfRow&&!"".equals(xssfRow)){
			if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
				return String.valueOf(xssfRow.getBooleanCellValue());
			} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
				DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
				Date date=xssfRow.getDateCellValue();
				return df.format(date);
			}else{
				return String.valueOf(xssfRow.getStringCellValue());
			}
		}else{
			return "";
		}
		
	}
	
	// 转换数据格式
	private String getValue2(XSSFCell xssfRow) {
		if(null!=xssfRow&&!"".equals(xssfRow)){
			if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
				return String.valueOf(xssfRow.getBooleanCellValue());
			} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
				DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				Date date=xssfRow.getDateCellValue();
				return df.format(date);
			}else{
				return String.valueOf(xssfRow.getStringCellValue());
			}
		}else{
			return "";
		}
		
	}

}
