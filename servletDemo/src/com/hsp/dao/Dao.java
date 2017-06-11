package com.hsp.dao;

import java.util.List;

import com.hsp.entity.Product;
import com.hsp.entity.User;
import com.ibm.excel.CheChangTongJiVO;
import com.ibm.excel.CheLiangDangAnVO;
import com.ibm.excel.CheWeiTaiZhangVO;

public interface Dao {
	User getUser(User user);
	
	List<User> getUser();
	
	int ifExit(User user);
	
	List<User> getUser(int pageNum,int pageSize);
	
	List<Product> getProd();
	
	Product getProd(int id);
	
	List<Product> getProd(int[] id);
	
	void doInsert(CheWeiTaiZhangVO vo);
	
	void doInsert(CheLiangDangAnVO vo);
	
	List<CheWeiTaiZhangVO> getCheWeiTaiZhangVOs();
	
	List<CheLiangDangAnVO> getcheLiangDangAnVOs();
	
	void doUpdateDangAn(CheLiangDangAnVO vo);
	
	void doUpdateTaiZhang(CheWeiTaiZhangVO vo);
	
	void doUpdateTaiZhangChePaiHao(CheWeiTaiZhangVO vo);
	
	void doUpdateDangAnChePaiHao(CheLiangDangAnVO vo);
	
	List<CheChangTongJiVO> getChangTongJiVOs();
	
	void doInsert(CheChangTongJiVO vo);
	
	void doUpdateDangAnName(CheLiangDangAnVO vo);
	
	void doUpdateTaiZhangName(CheWeiTaiZhangVO vo);
	
	void doUpdateCheChangTongJiName(CheChangTongJiVO vo);
	
}
