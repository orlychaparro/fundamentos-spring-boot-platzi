package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.*;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private OtroBean otroBean;
	private BeanSumarDosNros beanSumarDosNros;
	private MiBeanConDependencia miBeanConDependencia;
	private MyBeanWithProperties myBeanWithProperties;

	public FundamentosApplication(@Qualifier("componentTwoImplement")
								  ComponentDependency componentDependency,
								  MyBean myBean,
								  MyOperation myOperation,
								  MyBeanWithDependency myBeanWithDependency,
								  BeanSumarDosNros beanSumarDosNros,
								  OtroBean otroBean,
								  MiBeanConDependencia miBeanConDependencia,
								  MyBeanWithProperties myBeanWithProperties
								  ){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.otroBean = otroBean;
		this.beanSumarDosNros = beanSumarDosNros;
		this.miBeanConDependencia = miBeanConDependencia;
		this.myBeanWithProperties = myBeanWithProperties;



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

	}
}
