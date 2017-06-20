package com.example.y.androidinsertblobdatainandroid;



public class Employee {
    int id;
    String employeeName;
    String employeeAge;
    String employeeSex;
    //Blob employeeImage;

    byte[] employeeimageInByte;

    public Employee(){

    }

    /**
     * Employee constructor
     * @param id
     * @param employeeName
     * @param employeeAge
     * @param employeeSex
     * @param employeeimageInByte
     */
    public Employee(int id,String employeeName,String employeeAge,String employeeSex,byte employeeimageInByte[]){
        this.id = id;
        this.employeeName = employeeName;
        this.employeeAge = employeeAge;
        this.employeeSex = employeeSex;
        this.employeeimageInByte = employeeimageInByte;
    }
    public Employee(String employeeName,String employeeAge,String employeeSex,byte employeeimageInByte[]){
        this.employeeName = employeeName;
        this.employeeAge = employeeAge;
        this.employeeSex = employeeSex;
        this.employeeimageInByte = employeeimageInByte;
    }
//Getter and setter method
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(String employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getEmployeeSex() {
        return employeeSex;
    }

    public void setEmployeeSex(String employeeSex) {
        this.employeeSex = employeeSex;
    }

    public byte[] getEmployeeimageInByte() {
        return employeeimageInByte;
    }

    public void setEmployeeimageInByte(byte[] employeeimageInByte) {
        this.employeeimageInByte = employeeimageInByte;
    }

}
