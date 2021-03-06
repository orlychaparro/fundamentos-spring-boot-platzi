package com.fundamentosplatzi.springboot.fundamentos;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.custom.MyCustomDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
	
	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	
	private ComponentDependency componentDependency;
	private MyBean myBean;
	
	
	private MyBeanWithDependency myBeanWithDependency;
	private MyCustomDependency myCustomDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;
	
	public FundamentosApplication(@Qualifier("componetTwoImplement") ComponentDependency componentDependency, MyBean myBean,
			                                  MyBeanWithDependency myBeanWithDependency, MyCustomDependency myCustomDependency, 
			                                  MyBeanWithProperties myBeanWithProperties,UserPojo userPojo,
			                                  UserRepository userRepository,
			                                  UserService userService) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myCustomDependency = myCustomDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}
	
	@Override
	public void run(String... args) {
		// TODO Auto-generated method stub
		//ejemplosAnteriores();	
		saveUsersInDatabase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();

	}
	
	private void saveWithErrorTransactional() {
		User test1 = new User("test1Transactional1", "test1Transactional1@domain.com", LocalDate.now());
		User test2 = new User("test2Transactional2", "test2Transactional2@domain.com", LocalDate.now());
		User test3 = new User("test3Transactional3", "test1Transactional1@domain.com", LocalDate.now());
		User test4 = new User("test4Transactional4", "test4Transactional4@domain.com", LocalDate.now());
		
		List<User> users = Arrays.asList(test1,test2,test3,test4);
		
		try {
		
			userService.saveTransactional(users);
		
		}catch(Exception e) {
			LOGGER.error("Esta es una exception del metodo transaccional " + e);
		}
		
		userService.getAllUsers().stream()
			.forEach(user -> 
					LOGGER.info("Este es el usuario dentro del metodo transaccional" + user));
		
	}
	
	
	
	private void getInformationJpqlFromUser() {
		//userRepository.findByUserEmail("juan2@gmail.com");
	/*
		
		LOGGER.info("El usuario con el metodo findByUserEmail : " + 
				userRepository.findByUserEmail("Juan2@gmail.com")
			   .orElseThrow(()-> new RuntimeException("No se encontro el usuario")));
		
	userRepository.findandSort("Juan", Sort.by("id").descending())
				  .stream()
				  .forEach(user-> LOGGER.info("Usuario con metodo sort " + user));
	
	userRepository.findByName("JuanJose")
		.stream()
		.forEach(user -> LOGGER.info("Usuario con query Method = " + user));
	
	
	LOGGER.info("Usuario con query Method findByEmailAndName = " + userRepository.findByEmailAndName("juan1@gmail.com","Juan1")
		.orElseThrow(()-> new RuntimeException("Usuario x no encontrado")));
	
	
	userRepository.findByNameLike("%ose%")
		.stream()
		.forEach(user -> LOGGER.info("Usuario finByNameLike " + user));
	
	userRepository.findByNameOrEmail("Pedro","Juan7@gmail.com")
	.stream()
	.forEach(user -> LOGGER.info("Usuario indByNameOrEmail " + user));
	
	
	LocalDate.of(1975, 02, 01),LocalDate.of(1975, 02, 01)
	
*/
	userRepository.findByBirthdateBetween(LocalDate.of(1975, 02, 2), LocalDate.of(1975, 02, 4))
		.stream()
		.forEach(user -> LOGGER.info("Usuario findByBirthdateBetween Intervalo de fechas" + user));
	
	userRepository.findByNameLikeOrderByIdDesc("%Ped%")
	.stream()
	.forEach(user -> LOGGER.info("Usuario findByNameLikeOrderByIdDesc like y ordenado" + user));
	
	userRepository.findByNameContainingOrderByIdDesc("edro")
	.stream()
	.forEach(user -> LOGGER.info("Usuario findByNameContainingOrderByIdDesc containing" + user));
		
	
	LOGGER.info("El usuario by named parameter es " + userRepository.getAllByBirthdateAndEmail(LocalDate.of(1975, 02, 3), "Juan3@gmail.com")
	.orElseThrow(()-> new RuntimeException("No se encontro Usuario a partir del named parameter")));
	
	}
	
	private void saveUsersInDatabase() {
		User user1 = new User("Juan1", "juan1@gmail.com", LocalDate.of(1975,02,1));
		User user2 = new User("JuanJose", "Juan2@gmail.com", LocalDate.of(1975,02,2));
		User user3 = new User("Juan3", "Juan3@gmail.com", LocalDate.of(1975,02,3));
		User user4 = new User("Pedro", "ppena@gmail.com", LocalDate.of(1975,02,4));
		User user5 = new User("JuanJose", "Juan5@gmail.com", LocalDate.of(1975,02,5));
		User user6 = new User("Juan6", "Juan6@gmail.com", LocalDate.of(1975,02,6));
		User user7 = new User("Juan7", "Juan7@gmail.com", LocalDate.of(1975,02,7));
		User user8 = new User("JuanJose", "Juan8@gmail.com", LocalDate.of(1975,02,8));
		User user9 = new User("Juan9", "Juan9@gmail.com", LocalDate.of(1975,02,9));
		User user10 = new User("Juan10", "Juan10@gmail.com", LocalDate.of(1975,02,10));
		User user11 = new User("Juan11", "Juan11@gmail.com", LocalDate.of(1975,02,11));
		User user12 = new User("Juan12", "Juan12@gmail.com", LocalDate.of(1975,02,12));
		
		List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10,user11,user12);
		list.stream().forEach(userRepository::save);

	}
	
	
	private void ejemplosAnteriores() {
		
		// Se corto el contenido usado en las clases de inyeccion de dependencias }
		// y se ego aca
		
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		myCustomDependency.ImprimeCustomDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println("email: "+ userPojo.getEmail() + ", pass: " + userPojo.getPassword() + " ,edad: "+ userPojo.getAge());
		try {
			//error
			//int value = 10/0;
			
		}catch (Exception e){
			LOGGER.error("Error:" + e);	
			LOGGER.error("Error:" + e.getStackTrace());	
		}
	}

}
