package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
		ps = null;
		rs = null;
	}

	@Override
	public void insert(Seller obj) {

	}

	@Override
	public void update(Seller obj) {

	}

	@Override
	public void deleteById(Integer id) {

	}

	@Override
	public Seller findById(Integer id) {
		try {
			ps = conn.prepareStatement(""
					+ "SELECT s.*, d.Name as DepName "
					+ "FROM seller AS s "
					+ "INNER JOIN department AS d "
					+ "ON s.DepartmentId = d.Id " + "WHERE s.id = ?;");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				Department department = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, department);

				return seller;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException("Something was wrong: " + e.getMessage());
		} finally {
			DB.closePreparedStatment(ps);
			DB.closeResultSet(rs);
		}
	}
	
	public List<Seller> findByDepartmentId(Integer id) {
		List<Seller> list = new ArrayList<>();

		try {
			ps = conn.prepareStatement(""
					+ "SELECT s.*, d.Name as DepName "
					+ "FROM seller As s "
					+ "INNER JOIN department As d "
					+ "ON s.DepartmentId = d.Id "
					+ "WHERE s.DepartmentId = ? "
					+ "ORDER BY Name;");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			Department department = null;

			if (rs.next()) {
				department = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, department);
				list.add(seller);
			} else {
				return null;
			}

			while (rs.next()) {
				Seller seller = instantiateSeller(rs, department);
				list.add(seller);
			}

			return list;

		} catch (SQLException e) {
			throw new DbException("Something was wrong: " + e.getMessage());
		}
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setId(rs.getInt("DepartmentId"));
		department.setName(rs.getString("DepName"));
		return department;
	}

	private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setDepartment(department);
		return seller;
	}

	@Override
	public List<Seller> findAll() {
		return null;
	}
}
