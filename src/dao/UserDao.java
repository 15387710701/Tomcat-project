package dao;

import java.sql.*;
import java.util.ArrayList;

import vo.City;
import vo.Province;
import vo.User;
import tools.DBGet;
public class UserDao {
	User user = null;
	DBGet db = new DBGet();
	public User login(String userName,String password){
		Connection con = db.getConnection();
		String sql = "select * from t_user where userName = ? and password = ?";
		//System.out.println("success");
		try {
			
			//System.out.println(userName);
			//System.out.println(password);
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setString(1,userName);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				user = new User();
				user.setUsername(userName);
				user.setChrName(rs.getString("chrName"));
				//System.out.println(user.getChrName());
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			db.closeConnection(con);
		}
		return user;
	}
	
	public boolean check(String username){
		boolean ret = false;
		Connection con = db.getConnection();
		
		try {
			String sql = "select * from t_user where userName = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, username);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next())
				ret = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.closeConnection(con);
		}
		
		return ret;
	}
	
	public boolean insert(String username,String chrname,String password,String provinceCode,String cityCode){
		boolean ret = false;
		Connection con = db.getConnection();
		
		try {
			String sql = "insert into t_user(userName,chrName,password,provinceCode,cityCode) values(?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, chrname);
			pst.setString(3, password);
			pst.setString(4, provinceCode);
			pst.setString(5, cityCode);
			int i = pst.executeUpdate();
			if(i>0)
					ret = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.closeConnection(con);
		}
		return ret;
	}
	
	public ArrayList<Province> queryProvince() {
		ArrayList<Province> list = new ArrayList<Province>();
		Connection con = db.getConnection();
		
		try {
			String sql = "select * from t_province";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Province p = new Province();
				p.setProvinceCode(rs.getString("provinceCode"));
				p.setProvinceName(rs.getString("provinceName"));
				list.add(p);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.closeConnection(con);
		}
		return list;
	}
	
	public ArrayList<City> queryCity(String provinceCode) {
		ArrayList<City> list = new ArrayList<City>();
		Connection con = db.getConnection();
		
		try {
			String sql = "select * from t_city where provinceCode=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, provinceCode);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				City c = new City();
				c.setCityCode(rs.getString("cityCode"));
				c.setCityName(rs.getString("cityName"));
				c.setProvinceCode(rs.getString("provinceCode"));
				list.add(c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.closeConnection(con);
		}
		return list;
	}
	
	public ArrayList<User> queryUser(String username,String chrname,String province,String page,String currentPage){
		ArrayList<User> list = new ArrayList<User>();
		StringBuffer sb = new StringBuffer();
		//sb.append("select * from t_user where 1=1 ");
		
		sb.append("select userName,chrName,password,A.provinceCode,A.cityCode, ");
		sb.append("provinceName,cityName ");
		sb.append("from t_user A ");
		sb.append("inner join t_province B ");
		sb.append("on A.provinceCode = B.provinceCode ");
		sb.append("inner join t_city C ");
		sb.append("on A.cityCode = C.cityCode ");
		sb.append("where 1=1 ");
		
		if(username!=null && !"".equals(username)){
			sb.append("and userName like '%"+username+"%'");
		}
		if(chrname!=null && !"".equals(chrname)){
			sb.append("and chrName = '"+chrname+"'");
		}
		if(province!=null && !"".equals(province)){
			sb.append("and provinceCode = '"+province+"'");
		}
		//sb.append(" limit 10,130");
		//System.out.println(sb.toString());
		int start = (Integer.parseInt(currentPage)-1)*Integer.parseInt(page);
		int size = Integer.parseInt(page);
		sb.append(" limit ");
		sb.append(start);
		sb.append(", "+size);
		Connection con = db.getConnection();
		Statement stm ;
		try {
			stm =  con.createStatement();
			ResultSet rs = stm.executeQuery(sb.toString());
			while(rs.next()){
				User user = new User();
				user.setUsername(rs.getString("userName"));
				user.setChrName(rs.getString("chrName"));
				user.setProvinceCode(rs.getString("provinceCode"));
				user.setProvinceName(rs.getString("provinceName"));
				user.setCityCode(rs.getString("cityCode"));
				user.setCityName(rs.getString("cityName"));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.closeConnection(con);
		}
		return list;
	}
	
	public int total(String username,String chrname,
			String province){
		int count = 0;
		
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) total from t_user where 1=1 ");
		if(username!=null && !"".equals(username)){
			sb.append("and userName like '%"+username+"%'");
		}
		if(chrname!=null && !"".equals(chrname)){
			sb.append("and chrName = '"+chrname+"'");
		}
		if(province!=null && !"".equals(province)){
			sb.append("and provinceCode = '"+province+"'");
		}
		
		Connection con = db.getConnection();
		Statement stm ;
		try {
			stm =  con.createStatement();
			ResultSet rs = stm.executeQuery(sb.toString());
			rs.next();
			count = rs.getInt("total");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.closeConnection(con);
		}
		return count;
	}
}
