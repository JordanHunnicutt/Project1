package com.web.repository;

import java.sql.CallableStatement;
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
import com.web.models.Reimbursement;
import com.web.models.ReimbursementStatus;
import com.web.models.ReimbursementType;

public class ReimbursementDao implements DaoContract<Reimbursement, Integer>{

	private ConnectionInterface ci = new PTConnUtil().getInstance();
	private static final Logger logger = LogManager.getLogger(ReimbursementDao.class);
	
	@Override
	public List<Reimbursement> findAll() {
		List<Reimbursement> reimbursements = new ArrayList<>();
		try (Connection conn = ci.getConnection()){
			String sql = "select * from ers_reimbursement where reimb_id > ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, 0);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				reimbursements.add(new Reimbursement(rs.getInt(1),rs.getDouble(2),rs.getTimestamp(3),rs.getTimestamp(4),rs.getString(5),rs.getBytes(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10)));
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.info("Failed to find all reimbursement requests" +e);
			return reimbursements;
		}
		logger.info("Selected all reimbursement requests");
		return reimbursements;
	}

	@Override
	public Reimbursement findById(Integer i) {
		Reimbursement r = null;
		try (Connection conn = ci.getConnection()){
			String sql = "select * from ers_reimbursement where reimb_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				r = new Reimbursement(rs.getInt(1),rs.getDouble(2),rs.getTimestamp(3),rs.getTimestamp(4),rs.getString(5),rs.getBytes(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10));
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.info("Failed to find reimbursement request by Id" +e);
			return r;
		}
		logger.info("Selected one reimbursement request by Id");
		return r;
	}

	public List<Reimbursement> findByAuthor(Integer i){
		List<Reimbursement> reimbursements = new ArrayList<>();
		try (Connection conn = ci.getConnection()){
			String sql = "select * from ers_reimbursement where reimb_author = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				reimbursements.add(new Reimbursement(rs.getInt(1),rs.getDouble(2),rs.getTimestamp(3),rs.getTimestamp(4),rs.getString(5),rs.getBytes(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10)));
			}
		} catch (SQLException e) {
			logger.info("Failed to find reimbursements by author"+e);
			return reimbursements;
		}
		logger.info("Found reimbursements by author");
		return reimbursements;
	}
	
	public List<Reimbursement> findByPending(){
		List<Reimbursement> reimbursements = new ArrayList<>();
		try (Connection conn = ci.getConnection()){
			String sql = "select * from ers_reimbursement where reimb_status_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				reimbursements.add(new Reimbursement(rs.getInt(1),rs.getDouble(2),rs.getTimestamp(3),rs.getTimestamp(4),rs.getString(5),rs.getBytes(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10)));
			}
		} catch (SQLException e) {
			logger.info("Failed to find reimbursements by pending"+e);
			return reimbursements;
		}
		logger.info("Found reimbursements by pending");
		return reimbursements;
	}
	
	
	@Override
	public Reimbursement findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int create(Reimbursement t) {
		int result = 0;
		try (Connection conn = ci.getConnection()){
			String sql = "insert into ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, t.getAmount());
			ps.setTimestamp(2, t.getSubmitDate());
			ps.setTimestamp(3, t.getResolveDate());
			ps.setString(4, t.getDescription());
			ps.setBytes(5, t.getReceipt());
			ps.setInt(6, t.getAuthorId());
			ps.setInt(7, t.getResolverId());
			ps.setInt(8, t.getStatusId());
			ps.setInt(9, t.getTypeId());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.info("Failed to add a new reimbursement request"+e);
			return result;
		}
		logger.info("Created a new reimbursement request");
		return result;
	}

	@Override
	public int update(Reimbursement t) {
		int result = 0;
		try (Connection conn = ci.getConnection()){
			String sql = "update ers_reimbursement set (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) = (?,?,?,?,?,?,?,?,?) where reimb_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, t.getAmount());
			ps.setTimestamp(2, t.getSubmitDate());
			ps.setTimestamp(3, t.getResolveDate());
			ps.setString(4, t.getDescription());
			ps.setBytes(5, t.getReceipt());
			ps.setInt(6, t.getAuthorId());
			ps.setInt(7, t.getResolverId());
			ps.setInt(8, t.getStatusId());
			ps.setInt(9, t.getTypeId());
			ps.setInt(10, t.getReimbursementId());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.info("Failed to update reimbursement request"+e);
			return result;
		}
		logger.info("Updated reimbursement request");
		return result;
	}

	@Override
	public int delete(Integer i) {
		int result = 0;
		try (Connection conn = ci.getConnection()){
			String sql = "delete from ers_reimbursement where reimb_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.info("Failed to delete reimbursement request" +e);
			return result;
		}
		logger.info("Deleted one reimbursement request");
		return result;
	}
	
	public List<ReimbursementStatus> findAllStatuses(){
		List<ReimbursementStatus> statuses = new ArrayList<>();
		try (Connection conn = ci.getConnection()){
			String sql = "select * from ers_reimbursement_status where reimb_status_id > ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, 0);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				statuses.add(new ReimbursementStatus(rs.getInt(1),rs.getString(2)));
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.info("Failed to find all reimbursement statuses" +e);
			return statuses;
		}
		logger.info("Selected all reimbursement statuses");
		return statuses;
	}
	
	public List<ReimbursementType> findAllTypes(){
		List<ReimbursementType> types = new ArrayList<>();
		try (Connection conn = ci.getConnection()){
			String sql = "select * from ers_reimbursement_type where reimb_type_id > ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, 0);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				types.add(new ReimbursementType(rs.getInt(1),rs.getString(2)));
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.info("Failed to find all reimbursement types" +e);
			return types;
		}
		logger.info("Selected all reimbursement types");
		return types;
	}
	
	public boolean createType(String newType) {
		try(Connection conn = ci.getConnection()){
			String sql = "call make_new_type(?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, newType);
			int i = cs.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.info("Failed to create a new reimbursement type"+e);
			return false;
		}
		logger.info("New reimbursement type created");
		return true;
	}
	
}
