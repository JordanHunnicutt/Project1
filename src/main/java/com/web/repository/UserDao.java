package com.web.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.web.config.ConnectionInterface;
import com.web.config.PTConnUtil;
import com.web.models.User;
import com.web.models.UserRole;

public class UserDao implements DaoContract<User, Integer>{

	private ConnectionInterface ci = new PTConnUtil().getInstance();
	private static final Logger logger = LogManager.getLogger(UserDao.class);
	
	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		try (Connection conn = ci.getConnection()){
			String sql = "select * from ers_users where ers_users_id > ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, 0);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				users.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7)));
			}
			ps.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.info("Failed to find all users" +e);
			return users;
		}
		logger.info("Selected all users");
		return users;
	}

	@Override
	public User findById(Integer i) {
		User u = null;
		try (Connection conn = ci.getConnection()){
			String sql = "select * from ers_users where ers_users_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
			}
			ps.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.info("Failed to find user by Id" +e);
			return u;
		}
		logger.info("Selected one user by Id");
		return u;
	}

	@Override
	public User findByName(String name) {
		User u = null;
		try (Connection conn = ci.getConnection()){
			String sql = "select * from ers_users where ers_username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
			}
			ps.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.info("Failed to find user by username" +e);
			return u;
		}
		logger.info("Selected one user by username");
		return u;
	}

	public User findByNameAndPass(String name, String pass) {
		User u = null;
		try (Connection conn = ci.getConnection()){
			String sql = "select * from ers_users where ers_username = ? and ers_password = md5(?||?||?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, name);
			ps.setString(3, pass);
			ps.setString(4, "superSuddenDeath");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
			}
			ps.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.info("Failed to find user by username and password" +e);
			return u;
		}
		logger.info("Selected one user by username and password");
		return u;
	}
	
	@Override
	public int create(User t) {
		int result = 0;
		try (Connection conn = ci.getConnection()){
			String sql = "insert into ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) values (?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getPassword());
			ps.setString(3, t.getFirstName());
			ps.setString(4, t.getLastName());
			ps.setString(5, t.getEmail());
			ps.setInt(6, t.getRoleId());
			result = ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.info("Failed to add a new user"+e);
			return result;
		} catch (NullPointerException e) {
			return result;
		}
		logger.info("Created a new user");
		return result;
	}

	@Override
	public int update(User t) {
		int result = 0;
		try (Connection conn = ci.getConnection()){
			String sql = "update ers_users set (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) = (?,?,?,?,?,?) where ers_users_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getPassword());
			ps.setString(3, t.getFirstName());
			ps.setString(4, t.getLastName());
			ps.setString(5, t.getEmail());
			ps.setInt(6, t.getRoleId());
			ps.setInt(7, t.getUserId());
			result = ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.info("Failed to update user"+e);
			return result;
		} catch (NullPointerException e) {
			return result;
		}
		logger.info("Updated a user");
		return result;
	}

	@Override
	public int delete(Integer i) {
		int result = 0;
		try (Connection conn = ci.getConnection()){
			String sql = "delete from ers_users where ers_users_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			result = ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.info("Failed to delete user" +e);
			return result;
		} catch (NullPointerException e) {
			return result;
		}
		logger.info("Deleted one user");
		return result;
	}

	public List<UserRole> findAllRoles(){
		List<UserRole> roles = new ArrayList<>();
		try (Connection conn = ci.getConnection()){
			String sql = "select * from ers_user_roles where ers_user_role_id > ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, 0);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				roles.add(new UserRole(rs.getInt(1),rs.getString(2)));
			}
			ps.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.info("Failed to find all roles" +e);
			return roles;
		}
		logger.info("Selected all roles");
		return roles;
	}
	
}
