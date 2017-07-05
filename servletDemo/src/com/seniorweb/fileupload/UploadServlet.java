package com.seniorweb.fileupload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.DiskFileUpload;
import org.apache.tomcat.util.http.fileupload.FileItem;


/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		ServletContext sc=getServletContext();
		
		String uploadDir=sc.getRealPath("/upload");
		if(uploadDir==null){
			out.println("无法访问存储目录！");
			return;
		}
		File fUploadDir=new File(uploadDir);
		if(!fUploadDir.exists()){
			if(!fUploadDir.mkdir()){
				out.println("无法创建存储目录！");
				return;
			}
		}
		
		if(!DiskFileUpload.isMultipartContent(request)){
			out.println("只能处理multipart/form-data类型的数据");
			return;
		}
		DiskFileUpload fu=new DiskFileUpload();
		//最多上传200mb数据
		fu.setSizeMax(1024*1024*200);
		//超过1mb的字段数据采用临时文件缓存
		fu.setSizeThreshold(1024*1024);
		//采用默认的临时文件存储位置
		//fu.setRepositoryPath("..");
		//设置上传的普通字段的名称和文件字段的文件名所采用的字符集编码
		fu.setHeaderEncoding("utf-8");
		
		
		//得到所有表单字段对象的集合
		List fileItems=null;
		try {
			fileItems=fu.parseRequest(request);
		} catch (Exception e) {
			out.println("解析数据时出现如下问题：");
			e.printStackTrace();
			return;
		}
		
		//处理每个表单字段
		Iterator i=fileItems.iterator();
		while (i.hasNext()) {
			FileItem fi=(FileItem)i.next();
			if(fi.isFormField()){
				String content=fi.getString("utf-8");
				String fieldName=fi.getFieldName();
				request.setAttribute(fieldName, content);
			}else{
				try {
					String pathSrc=fi.getName();
					//如果用户没有在Form表单的文件字段中选择任何文件，那么忽略对该字段项的处理
					if(pathSrc.trim().equals("")){
						continue;
					}
					
					int start=pathSrc.lastIndexOf('\\');
					String fileName=pathSrc.substring(start+1);
					File pathDest=new File(uploadDir,fileName);
					
					fi.write(pathDest);
					
					String fieldName=fi.getFieldName();
					request.setAttribute(fieldName, fileName);
				} catch (Exception e) {
					out.println("存储文件时出现如下问题:");
					e.printStackTrace();
					return;
				}
				finally{//总是立即删除保存表单字段内容的临时文件
					fi.delete();
					//12
				}
			}
		}
		
		//显示处理结果
		out.println("用户:"+request.getAttribute("author")+"<br>");
		out.println("来自："+request.getAttribute("company")+"<br>");
		
		/*
		 * 将上传的文件名组合成file1.file2 这种形式显示出来，如果没有上传任何文件，则显示 无，如果只上传了第二个文件，则显示为file2
		 * */
		
		StringBuffer fileList=new StringBuffer();
		String file1=(String)request.getAttribute("file1");
		makeUpList(fileList,file1);
		String file2=(String)request.getAttribute("file2");
		makeUpList(fileList,file2);
		out.println("成功上传的文件："+(fileList.length()==0?"无":fileList.toString()));
	}
	
	private void makeUpList(StringBuffer result,String fragment){
		if(fragment!=null){
			if(result.length()!=0){
				result.append(",");
			}
			result.append(fragment);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
