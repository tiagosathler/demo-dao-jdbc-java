package application;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);

		Scanner sc = new Scanner(System.in);

		System.out.println("SELLER CRUD:");
		System.out.println("------------");
		System.out.println();

		sellerCRUD(sc);

		System.out.println();
		System.out.println("***********");

		System.out.println("DEPARTMENT CRUD:");
		System.out.println("----------------");
		System.out.println();

		departmentCRUD(sc);

		sc.close();
	}

	private static void sellerCRUD(Scanner sc) {

		SellerDao sellerDao = DaoFactory.createSellerDao();
		Department department = new Department(2, "Electronics");

		System.out.println("=== TEST 1: seller findById ====");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);

		System.out.println("=== TEST 2: seller findByDepartmentId ====");
		List<Seller> list = sellerDao.findByDepartment(department);
		list.forEach(System.out::println);

		System.out.println("=== TEST 3: seller findAll ====");
		list = sellerDao.findAll();
		list.forEach(System.out::println);

		System.out.println("=== TEST 4: seller insert ====");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.00, department);
		sellerDao.insert(newSeller);
		System.out.println(newSeller);

		System.out.println("=== TEST 5: seller update ====");
		newSeller.setName("Grieg");
		newSeller.setBaseSalary(5000.00);
		newSeller.setEmail("grieg@ymail.com");
		sellerDao.update(newSeller);
		System.out.println(newSeller);

		System.out.println("=== TEST 6: seller delete ====");
		System.out.print("Enter seller Id for delete test: ");
		int id = sc.nextInt();

		sellerDao.deleteById(id);
		System.out.println("Delete complete!");
	}

	private static void departmentCRUD(Scanner sc) {
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

		System.out.println("=== TEST 1: department findById ====");
		Department department = departmentDao.findById(3);
		System.out.println(department);

		System.out.println("=== TEST 2: department findAll ====");
		List<Department> list = departmentDao.findAll();
		list.forEach(System.out::println);

		System.out.println("=== TEST 3: department insert ====");
		Department newDepartment = new Department(null, "Software");
		departmentDao.insert(newDepartment);
		System.out.println(newDepartment);

		System.out.println("=== TEST 4: department update ====");
		newDepartment.setName("Hardware");
		departmentDao.update(newDepartment);
		System.out.println(newDepartment);

		System.out.println("=== TEST 5: department delete ====");
		System.out.print("Enter department Id for delete test: ");
		int id = sc.nextInt();

		departmentDao.deleteById(id);
		System.out.println("Delete complete!");
	}
}
