package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MiBeanConDependenciaImplement implements MiBeanConDependencia{

    private BeanSumarDosNros beanSumarDosNros;

    public MiBeanConDependenciaImplement(BeanSumarDosNros beanSumarDosNros) {
        this.beanSumarDosNros = beanSumarDosNros;
    }

    @Override
    public void imprimeOperacionesMatematicas() {
        int numeroA = 10;
        int numeroB = 20;
        System.out.println("Mi metodo Sumar dos numeros A y B");
        System.out.println(beanSumarDosNros.SumarAmasB(numeroA,numeroB));






    }
}
