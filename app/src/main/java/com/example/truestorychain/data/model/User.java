package com.example.truestorychain.data.model;


public class User {
    private String id;
    private String username;
    private String walletAddress;

    public User(String id, String username, String walletAddress) {
        this.id = id;
        this.username = username;
        this.walletAddress = walletAddress;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getWalletAddress() {
        return walletAddress;
    }
}