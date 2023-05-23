package com.fundamentosplatzi.springboot.fundamentos.bean;

public class OtroBeanImplement implements OtroBean {
    @Override
    public void imprime() {
        System.out.println("Metodo imprimir desde otro Bean");

    }

    @Override
    public void diceHola() {
        System.out.println("Metodo dice Hola desde OtroBean");

    }
}
