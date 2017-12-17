package com.javalec.spring_pjt_board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import javax.sql.DataSource;

//import java.security.Timestamp;

import java.util.ArrayList;

//import javax.activation.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.javalec.spring_pjt_board.dto.BDto;

public class BDao {

	DataSource dataSource;
	int rn=0;
	
	
	public BDto contentView(String strlD) {
		
		upHit(strlD); 
		BDto dto =null;
		
		Connection connection= null;
		PreparedStatement preparedStatement= null;
		ResultSet resultSet=null;
		
		try {
			connection=dataSource.getConnection();
			String query="select *from mvc_table where bid=?";
			preparedStatement= connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strlD));
			
			resultSet= preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int bid=resultSet.getInt("bid");
				String bname= resultSet.getString("bname");
				String btitle=resultSet.getString("btitle");
				String bcontent= resultSet.getString("bcontent");
				Timestamp bdate= resultSet.getTimestamp("bdate");
			int bhit=resultSet.getInt("bhit");
			int bgroup=resultSet.getInt("bgroup");
			int bstep=resultSet.getInt("bstep");
			int bindent=resultSet.getInt("bindent");
			
			
			dto= new BDto(bid, bname,btitle,bcontent,bdate,bhit,bgroup,bstep,bindent);
			}
		}catch(Exception e) {
			
		}finally {
			try {
				if(resultSet !=null) resultSet.close();
				if(preparedStatement !=null) preparedStatement.close();
				if(connection !=null) preparedStatement.close();
			}catch (Exception e2) {
				e2.printStackTrace();
				 
			}
		}
		  
		
		
		
		return dto;
		  
	}
	
	public void  write(String bname, String btitle,String bcontent) {
		Connection connection = null;//데이터베이스 접근해서 글 입
	PreparedStatement preparedStatement = null;
	
	
	
	
	//select * from (select max(seq)+1 from tbl_board)

	
	try {
		//(select *from (select max(bid)+1 from mvc_table) next)
		connection =(dataSource).getConnection();
		String query="INSERT INTO mvc_table (bid,bname,btitle,bcontent,bhit,bgroup,bstep,bindent) VALUES (0,?,?,?,0,0,0,0);";
		preparedStatement=connection.prepareStatement(query);
		//System.out.println("들어"); 
		
		preparedStatement.setString(1, bname);
	preparedStatement.setString(2, btitle);
	preparedStatement.setString(3, bcontent);
	
	//System.out.println(""); 
	rn=preparedStatement.executeUpdate();//실제로 데이터이스에 들어갈 수 있
	//System.out.println("들어"); 
	
	}catch(Exception e) {
		e.printStackTrace();
	
	}finally{
		
//	return rn;
		try {
			if(preparedStatement != null) preparedStatement.close();
			if(connection !=null) connection.close();
			
		}catch(Exception e2) {
		
		}
		
	
		}	
		
	
	
	
	
	}
	

	public BDao() {
		
		try {
		
		Context context= new InitialContext();
		dataSource= (DataSource) context.lookup("java:comp/env/jdbc/mysql");
		}catch (NamingException e) {
			e.printStackTrace();
			
		}
	}
	
	public ArrayList<BDto> list() {
		
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		
		try {
		connection= dataSource.getConnection();
		String query="select bid, bname, btitle,bcontent,bdate,bhit, bgroup,bstep,bindent from mvc_table order by bgroup desc, bstep asc";
		
		preparedStatement=connection.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next())
		{
			int bid= resultSet.getInt("bid");
			String bname= resultSet.getString("bname");
			String btitle= resultSet.getString("btitle");
			String bcontent= resultSet.getString("bcontent");
		Timestamp bdate= resultSet.getTimestamp("bdate");
		int bhit=resultSet.getInt("bhit");
		int bgroup=resultSet.getInt("bgroup");
		int bstep=resultSet.getInt("bstep");
		int bindent=resultSet.getInt("bindent");
		
		BDto dto= new BDto(bid,bname, btitle, bcontent,bdate, bhit, bgroup, bstep, bindent);
		dtos.add(dto);//쿼리로 가져온걸 여기에 담아
		
		}
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
		try {
			
			if(resultSet!=null) resultSet.close();
			if(preparedStatement!=null) resultSet.close();
			if(connection!=null) resultSet.close();
			
		}catch (Exception e2) {
			
		}
		}
		
		return dtos;
		
	}
	
	public void modify(String bid,String bname,String btitle,String bcontent) {
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		
		try {
			connection=dataSource.getConnection();
			String query="update mvc_table set bname = ?, btitle = ?, bcontent = ? where bid = ?";
			
			preparedStatement=connection.prepareStatement(query);
			
			preparedStatement.setString(1, bname);
			preparedStatement.setString(2, btitle);
			preparedStatement.setString(3, bcontent);
			preparedStatement.setInt(4, Integer.parseInt(bid));
			
			int rn=preparedStatement.executeUpdate();
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
				
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		
	}
	
	public void delete(String strID) {//글삭제 메소
			Connection connection =null;
			PreparedStatement preparedStatement=null;
			
			try {
				connection=dataSource.getConnection();
				String query="delete from mvc_table where bid = ?";
				preparedStatement= connection.prepareStatement(query);
				preparedStatement.setInt(1,Integer.parseInt(strID));
				int rn=preparedStatement.executeUpdate();
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {

					if(preparedStatement!=null) preparedStatement.close();
					if(connection!=null) connection.close();
					
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			
				
			}	
	
			
	}
	
	
	
	public BDto reply_view(String strID) {
		
		BDto dto=null;
		
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		
		try {
				
			connection=dataSource.getConnection();
			String query="select *from mvc_table where bid=?";
			preparedStatement=connection.prepareStatement(query);
			
			preparedStatement.setInt(1,Integer.parseInt(strID));
			resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int bid=resultSet.getInt("bid");
				
				String bname=resultSet.getString("bname");
				String btitle=resultSet.getString("btitle");
				String bcontent=resultSet.getString("bcontent");
				Timestamp bdate=resultSet.getTimestamp("bdate");
				int bhit= resultSet.getInt("bhit");
				int bgroup=resultSet.getInt("bgroup");
				int bstep=resultSet.getInt("bstep");
				int bindent=resultSet.getInt("bindent");
				
				dto=new BDto(bid,bname, btitle,bcontent,bdate,bhit,bgroup,bstep, bindent);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				
				if(resultSet!=null)resultSet.close();
				if(preparedStatement !=null) preparedStatement.close();
				if(connection !=null) preparedStatement.close();
				
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		return null;
		
	}
	
	public void reply(String bid, String bname,String btitle,String bcontent,String bgroup,String bstep,String bindent) {
		
		
		
		replyShape(bgroup,bstep);//답변 들여쓰

		Connection connection=null;
		PreparedStatement preparedStatement=null;
		

		try {
		connection=dataSource.getConnection();
		String query="insert into mvc_table (bid,btitle,bcontent,bgroup,bstep,bindent) values (select max(bid)+1 from mvc_table,?,?,?,?,?,?)";
		preparedStatement=connection.prepareStatement(query);
		
			preparedStatement.setString(1,bname);
			preparedStatement.setString(2,btitle);
			preparedStatement.setString(3,bcontent);
			preparedStatement.setInt(4,Integer.parseInt(bgroup)) ;
			preparedStatement.setInt(5,Integer.parseInt(bstep)+1) ;
			preparedStatement.setInt(6,Integer.parseInt(bindent)+1) ;
			
			
			int rn = preparedStatement.executeUpdate();
					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	

	private void replyShape( String strGroup, String strStep) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "update mvc_table set bstep = bstep + 1 where bgroup = ? and bstep > ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strGroup));
			preparedStatement.setInt(2, Integer.parseInt(strStep));
			
			int rn = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle ex ception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	
	private void upHit( String bId) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bId);
			
			int rn = preparedStatement.executeUpdate();
					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
}
