package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println("=== TEST 1: seller findById ====");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);

		System.out.println("=== TEST 2: seller findByDepartmentId ====");
		List<Seller> list = sellerDao.findByDepartmentId(2);
		list.forEach(System.out::println);

		System.out.println("=== TEST 3: seller findAll ====");
		list = sellerDao.findAll();
		list.forEach(System.out::println);
	}

}
