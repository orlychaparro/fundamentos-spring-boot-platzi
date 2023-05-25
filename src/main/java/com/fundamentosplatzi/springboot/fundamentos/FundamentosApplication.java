package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.*;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

	public FundamentosApplication(@Qualifier("componentTwoImplement")
								  ComponentDependency componentDependency,
								  MyBean myBean,
								  MyOperation myOperation,
								  MyBeanWithDependency myBeanWithDependency,
								  BeanSumarDosNros beanSumarDosNros,
								  OtroBean otroBean,
								  MiBeanConDependencia miBeanConDependencia,
								  MyBeanWithProperties myBeanWithProperties,
								  UserPojo userPojo
								  ){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.otroBean = otroBean;
		this.beanSumarDosNros = beanSumarDosNros;
		this.miBeanConDependencia = miBeanConDependencia;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;



	}
	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) {
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
