package com.example.demo.model;



public class AddMoneyRequest {
    
    private String email;
    
    private Double amount;

   
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}