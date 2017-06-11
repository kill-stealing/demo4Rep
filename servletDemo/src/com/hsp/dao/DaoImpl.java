package com.hsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hsp.entity.Product;
import com.hsp.entity.User;
import com.hsp.util.CloseUtil;
import com.hsp.util.GetDataSource;
import com.ibm.excel.CheChangTongJiVO;
import com.ibm.excel.CheLiangDangAnVO;
import com.ibm.excel.CheWeiTaiZhangVO;

public class DaoImpl implements Dao {

	@Override
	public User getUser(User user) {
		User user1 = new User();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = GetDataSource.getInstance().getConn();
			String sql = "select * from atest_user where user_name=? and pwd=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPwd());
			rs = ps.executeQuery();
			while (rs.next()) {
				user1.setUserId(rs.getInt("user_id"));
				user1.setUserName(rs.getString("user_name"));
				user1.setPwd(rs.getString("pwd"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			CloseUtil.close(rs, ps, conn);
		}
		return user1;

	}

	@Override
	public int ifExit(User user) {
		int flag = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = GetDataSource.getInstance().getConn();
			String sql = "select count(*) count from atest_user where user_name=? and pwd=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPwd());
			rs = ps.executeQuery();
			while (rs.next()) {
				flag = rs.getInt("count");
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			CloseUtil.close(rs, ps, conn);
		}
		return flag;
	}

	public static void main(String[] args) {
		DaoImpl dao = new DaoImpl();
		User u = new User("aaa", "bbb");
		int i = dao.ifExit();
		System.out.println(i);
		List<User> list = dao.getUser();
		System.out.println(list.toString());
		dao.doInsert();

	}

	@Override
	public List<User> getUser() {
		List<User> list = new ArrayList<User>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = GetDataSource.getInstance().getConn();
			String sql = "select * from atest_user";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPwd(rs.getString("pwd"));
				list.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			CloseUtil.close(rs, ps, conn);
		}
		return list;
	}

	@Override
	public List<User> getUser(int pageNum, int pageSize) {
		List<User> list = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = GetDataSource.getInstance().getConn();
			String sql = "select * from atest_user limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (pageNum - 1) * pageSize);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPwd(rs.getString("pwd"));
				list.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			CloseUtil.close(rs, ps, conn);
		}

		return list;
	}

	public int ifExit() {
		int flag = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = GetDataSource.getInstance().getConn();
			String sql = "select count(*) count from atest_user where user_name=? and pwd=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "我是你爸爸");
			ps.setString(2, "123456");
			rs = ps.executeQuery();
			while (rs.next()) {
				flag = rs.getInt("count");
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			CloseUtil.close(rs, ps, conn);
		}
		return flag;
	}

	public int doInsert() {
		int i = 0;
		try {
			Connection conn = GetDataSource.getInstance().getConn();
			String sql = "insert into atest_user (user_id,user_name,pwd) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = null;
			ps.setInt(1, 222);
			ps.setString(2, "我是你爸爸1");
			ps.setString(3, "123456");
			i = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return i;
	}

	@Override
	public List<Product> getProd() {
		List<Product> list = new ArrayList<Product>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = GetDataSource.getInstance().getConn();
			String sql = "select * from atest_prod";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product prod = new Product();
				prod.setId(rs.getInt("id"));
				prod.setProdName(rs.getString("prod_name"));
				prod.setPrice(rs.getInt("price"));
				list.add(prod);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			CloseUtil.close(rs, ps, conn);
		}
		return list;
	}

	@Override
	public List<Product> getProd(int[] id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		List<Product> list = new ArrayList<Product>();
		try {
			conn = GetDataSource.getInstance().getConn();
			StringBuilder sql = new StringBuilder(
					"select * from atest_prod where id in ( ");
			for (int i = 0; i < id.length; i++) {
				if (i == 0) {
					sql.append(id[i]);
				} else {
					sql.append("," + id[i]);
				}
			}
			sql.append(" ) ");
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				Product prod = new Product();
				prod.setId(rs.getInt("id"));
				prod.setProdName(rs.getString("prod_name"));
				prod.setPrice(rs.getInt("price"));
				list.add(prod);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			CloseUtil.close(rs, ps, conn);
		}
		return list;
	}

	@Override
	public Product getProd(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		Product prod = null;
		try {
			conn = GetDataSource.getInstance().getConn();
			String sql = "select * from atest_prod where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				prod = new Product();
				prod.setId(rs.getInt("id"));
				prod.setProdName(rs.getString("prod_name"));
				prod.setPrice(rs.getInt("price"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			CloseUtil.close(rs, ps, conn);
		}
		return prod;
	}

	@Override
	public void doInsert(CheWeiTaiZhangVO vo) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = GetDataSource.getInstance().getConn();
			String sql = "insert into che_wei_tai_zhang1 (xu_hao,che_wei_hao,lou_hao,name," +
					"phone_num,start_date,end_date,che_pai_hao1,che_pai_hao2,che_pai_hao3," +
					"che_pai_hao4,che_pai_hao5) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getXuHao());
			ps.setString(2, vo.getCheWeiHao());
			ps.setString(3, vo.getLouHao());
			ps.setString(4, vo.getName());
			ps.setString(5, vo.getPhoneNum());
			ps.setString(6, vo.getStartDate());
			ps.setString(7, vo.getEndDate());
			ps.setString(8, vo.getChePaiHao1());
			ps.setString(9, vo.getChePaiHao2());
			ps.setString(10, vo.getChePaiHao3());
			ps.setString(11, vo.getChePaiHao4());
			ps.setString(12, vo.getChePaiHao5());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			CloseUtil.close(rs, ps, conn);
		}
	}

	@Override
	public void doInsert(CheLiangDangAnVO vo) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = GetDataSource.getInstance().getConn();
			String sql = "insert into che_liang_dang_an1 (che_pai_hao,cheliang_leixing,you_xiao_start_date," +
					"you_xiao_end_date,cheliang_yuliang,cheliang_leibie,cheliang_xinghao,cheliang_yanse," +
					"chezhu_name,shenfen_zheng,lianxi_dianhua,lianxi_dizhi,suoshu_chewei,cheliang_fenzhu," +
					"chechang_quanxian,zhuche_date,bei_zhu,shifou_jinyong,shifou_zhuxiao) values" +
					"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getChePaiHao());
			ps.setString(2, vo.getCheLiangLeiXing());
			ps.setString(3, vo.getYou_xiao_start_date());
			ps.setString(4, vo.getYou_xiao_end_date());
			ps.setString(5, vo.getCheliang_yuliang());
			ps.setString(6, vo.getCheliang_leibie());
			ps.setString(7, vo.getCheliang_xinghao());
			ps.setString(8, vo.getCheliang_yanse());
			ps.setString(9, vo.getChezhu_name());
			ps.setString(10, vo.getShenfen_zheng());
			ps.setString(11, vo.getLianxi_dianhua());
			ps.setString(12, vo.getLianxi_dizhi());
			
			ps.setString(13, vo.getSuoshu_chewei());
			ps.setString(14, vo.getCheliang_fenzhu());
			ps.setString(15, vo.getChechang_quanxian());
			ps.setString(16, vo.getZhuche_date());
			
			ps.setString(17, vo.getBei_zhu());
			ps.setString(18, vo.getShifou_jinyong());
			ps.setString(19, vo.getShifou_zhuxiao());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			CloseUtil.close(rs, ps, conn);
		}
	}

	@Override
	public List<CheWeiTaiZhangVO> getCheWeiTaiZhangVOs() {
		List<CheWeiTaiZhangVO> list=new ArrayList<CheWeiTaiZhangVO>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		CheWeiTaiZhangVO vo = null;
		try {
			conn = GetDataSource.getInstance().getConn();
			String sql = "select * from che_wei_tai_zhang1";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				vo = new CheWeiTaiZhangVO();
				vo.setId(rs.getInt("id"));
				vo.setXuHao(rs.getString("xu_hao"));
				vo.setCheWeiHao(rs.getString("che_wei_hao"));
				vo.setLouHao(rs.getString("lou_hao"));
				vo.setName(rs.getString("name"));
				vo.setChePaiHao1(rs.getString("che_pai_hao1"));
				vo.setChePaiHao2(rs.getString("che_pai_hao2"));
				vo.setChePaiHao3(rs.getString("che_pai_hao3"));
				vo.setChePaiHao4(rs.getString("che_pai_hao4"));
				vo.setChePaiHao5(rs.getString("che_pai_hao5"));
				list.add(vo);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			CloseUtil.close(rs, ps, conn);
		}
		return list;
	}

	@Override
	public List<CheLiangDangAnVO> getcheLiangDangAnVOs() {
		List<CheLiangDangAnVO> list=new ArrayList<CheLiangDangAnVO>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		CheLiangDangAnVO vo = null;
		try {
			conn = GetDataSource.getInstance().getConn();
			String sql = "select * from che_liang_dang_an1";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				vo = new CheLiangDangAnVO();
				vo.setId(rs.getInt("id"));
				vo.setChePaiHao(rs.getString("che_pai_hao"));
				vo.setChezhu_name(rs.getString("chezhu_name"));
				vo.setChePaiHao(rs.getString("che_pai_hao"));
				vo.setSuoshu_chewei(rs.getString("suoshu_chewei"));
				vo.setCheliang_fenzhu(rs.getString("cheliang_fenzhu"));
				list.add(vo);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			CloseUtil.close(rs, ps, conn);
		}
		return list;
	}

	@Override
	public void doUpdateDangAn(CheLiangDangAnVO vo) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = GetDataSource.getInstance().getConn();
			String sql = "update che_liang_dang_an1 set louhao_temp1=? where id=?";
			ps = conn.prepareStatement(sql);
//			ps.setString(1, vo.getCheliang_fenzhu());
			ps.setString(1, vo.getLouhao_temp1());
			ps.setInt(2, vo.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			CloseUtil.close(rs, ps, conn);
		}
	}
	
	public void doUpdateDangAnChePaiHao(CheLiangDangAnVO vo) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = GetDataSource.getInstance().getConn();
			String sql = "update che_liang_dang_an1 set chepai_hao1=? where id=?";
			ps = conn.prepareStatement(sql);
//			ps.setString(1, vo.getCheliang_fenzhu());
			ps.setString(1, vo.getChepai_hao1());
			ps.setInt(2, vo.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			CloseUtil.close(rs, ps, conn);
		}
	}

	@Override
	public void doUpdateTaiZhang(CheWeiTaiZhangVO vo) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = GetDataSource.getInstance().getConn();
			String sql = "update che_wei_tai_zhang1 set louhao_temp1=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getLouhao_temp1());
			ps.setInt(2, vo.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			CloseUtil.close(rs, ps, conn);
		}
	}
	
	public void doUpdateTaiZhangChePaiHao(CheWeiTaiZhangVO vo) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = GetDataSource.getInstance().getConn();
			String sql = "update che_wei_tai_zhang1 set chepai_hao1=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getChepai_hao1());
			ps.setInt(2, vo.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			CloseUtil.close(rs, ps, conn);
		}
	}

	@Override
	public List<CheChangTongJiVO> getChangTongJiVOs() {
		List<CheChangTongJiVO> list=new ArrayList<CheChangTongJiVO>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		CheChangTongJiVO vo = null;
		try {
			conn = GetDataSource.getInstance().getConn();
			String sql = "select * from che_chang_tong_ji";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				vo = new CheChangTongJiVO();
				vo.setId(rs.getInt("id"));
				vo.setFangwu_name(rs.getString("fangwu_name"));
				vo.setYezhu_name(rs.getString("yezhu_name"));
				vo.setTemp_name1(rs.getString("temp_name1"));
				vo.setTemp_name2(rs.getString("temp_name2"));
				vo.setTemp_name3(rs.getString("temp_name3"));
				list.add(vo);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			CloseUtil.close(rs, ps, conn);
		}
		return list;
	}

	@Override
	public void doInsert(CheChangTongJiVO vo) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = GetDataSource.getInstance().getConn();
			String sql = "insert into che_chang_tong_ji (fangwu_name,yezhu_name) values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getFangwu_name());
			ps.setString(2, vo.getYezhu_name());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			CloseUtil.close(rs, ps, conn);
		}
	}

	@Override
	public void doUpdateDangAnName(CheLiangDangAnVO vo) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = GetDataSource.getInstance().getConn();
			String sql = "update che_liang_dang_an1 set temp_name1=?,temp_name2=?,temp_name3=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getTemp_name1());
			ps.setString(2, vo.getTemp_name2());
			ps.setString(3, vo.getTemp_name3());
			ps.setInt(4, vo.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			CloseUtil.close(rs, ps, conn);
		}
	}

	@Override
	public void doUpdateTaiZhangName(CheWeiTaiZhangVO vo) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = GetDataSource.getInstance().getConn();
			String sql = "update che_wei_tai_zhang1 set temp_name1=?,temp_name2=?,temp_name3=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getTemp_name1());
			ps.setString(2, vo.getTemp_name2());
			ps.setString(3, vo.getTemp_name3());
			ps.setInt(4, vo.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			CloseUtil.close(rs, ps, conn);
		}
	}

	@Override
	public void doUpdateCheChangTongJiName(CheChangTongJiVO vo) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = GetDataSource.getInstance().getConn();
			String sql = "update che_chang_tong_ji set temp_name1=?,temp_name2=?,temp_name3=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getTemp_name1());
			ps.setString(2, vo.getTemp_name2());
			ps.setString(3, vo.getTemp_name3());
			ps.setInt(4, vo.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			CloseUtil.close(rs, ps, conn);
		}
	}

}
