package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SellerDao sellerDao = DaoFactory.createSellerDao();
		Department department = new Department(4, "Books");

		System.out.println("=== TEST 1: seller findById ====");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);

		System.out.println("=== TEST 2: seller findByDepartmentId ====");
		List<Seller> list = sellerDao.findByDepartmentId(2);
		list.forEach(System.out::println);

		System.out.println("=== TEST 3: seller findAll ====");
		list = sellerDao.findAll();
		list.forEach(System.out::println);
		
		System.out.println("=== TEST 4: seller insert ====");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.00, department);
		sellerDao.insert(newSeller);
		System.out.println(newSeller);
	}

}
