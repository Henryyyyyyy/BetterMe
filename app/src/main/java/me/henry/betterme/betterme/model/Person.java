package me.henry.betterme.betterme.model;

/**
 * Created by zj on 2017/3/23.
 * me.henry.betterme.betterme.model
 */

public class Person {
    public String name;
    public int age;
    public double height;
    public double weight;

    private Person(Builder builder) {
        this.name=builder.name;
        this.age=builder.age;
        this.height=builder.height;
        this.weight=builder.weight;
    }
    public Person() {

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public static class Builder{
        private String name;
        private int age;
        private double height;
        private double weight;
        public Builder(){

        }
        public Builder name(String name){
            this.name=name;
            return this;
        }
        public Builder age(int age){
            this.age=age;
            return this;
        }
        public Builder height(double height){
            this.height=height;
            return this;
        }

        public Builder weight(double weight){
            this.weight=weight;
            return this;
        }

        public Person build(){
            return new Person(this);
        }
    }
}