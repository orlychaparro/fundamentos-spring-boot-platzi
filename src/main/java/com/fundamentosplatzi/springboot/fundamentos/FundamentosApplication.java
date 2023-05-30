package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.*;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private OtroBean otroBean;
	private BeanSumarDosNros beanSumarDosNros;
	private MiBeanConDependencia miBeanConDependencia;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;

	public FundamentosApplication(@Qualifier("componentTwoImplement")
								  ComponentDependency componentDependency,
								  MyBean myBean,
								  MyOperation myOperation,
								  MyBeanWithDependency myBeanWithDependency,
								  BeanSumarDosNros beanSumarDosNros,
								  OtroBean otroBean,
								  MiBeanConDependencia miBeanConDependencia,
								  MyBeanWithProperties myBeanWithProperties,
								  UserPojo userPojo,
								  UserRepository userRepository
								  ){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.otroBean = otroBean;
		this.beanSumarDosNros = beanSumarDosNros;
		this.miBeanConDependencia = miBeanConDependencia;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;



	}
	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) {
		//ejemplosAnteriores();
		SaveUsersInDataBase();



	}

	private  void  SaveUsersInDataBase(){
		User user1 = new User("John", "john@doamin.com", LocalDate.of(2021,03,20));
		User user2 = new User("Julie", "julie@doamin.com", LocalDate.of(2021,05,21));
		User user3 = new User("Daniela", "daniela@doamin.com", LocalDate.of(2021,05,21));
		User user4 = new User("user4", "user4@doamin.com", LocalDate.of(2021,04,21));
		User user5 = new User("user5", "user5@doamin.com", LocalDate.of(2021,02,21));
		User user6 = new User("user6", "user6@doamin.com", LocalDate.of(2021,04,21));
		User user7 = new User("user7", "user7@doamin.com", LocalDate.of(2021,03,21));
		User user8 = new User("user8", "user8@doamin.com", LocalDate.of(2021,11,21));
		User user9 = new User("user9", "user9@doamin.com", LocalDate.of(2021,04,21));
		User user10 = new User("user10", "user10@doamin.com", LocalDate.of(2021,05,21));
		User user11 = new User("user11", "user11@doamin.com", LocalDate.of(2021,02,21));
		User user12 = new User("user12", "user12@doamin.com", LocalDate.of(2021,03,21));

		List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,
				                        user7,user8,user9,user10,user11,user12);

		list.stream().forEach(userRepository::save);




	}

	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		otroBean.diceHola();
		otroBean.imprime();
		miBeanConDependencia.imprimeOperacionesMatematicas();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + "- " + userPojo.getPassword());
		System.out.println(userPojo.getAge());

		try {
			//error
			int value = 10/0;
			LOGGER.debug("Mi valor es : " + value);
		}catch (Exception e){
			LOGGER.error("Esto es un error al dividir entero por cero. " + e.getMessage());
		}
	}
}
