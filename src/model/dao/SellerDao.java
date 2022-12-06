package model.dao;

import java.util.List;

import model.entities.Seller;

public interface SellerDao extends GenericDao<Seller>{
	List<Seller> findByDepartmentId(Integer id);
}
