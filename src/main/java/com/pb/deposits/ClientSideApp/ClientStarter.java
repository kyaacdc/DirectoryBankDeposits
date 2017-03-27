package com.pb.deposits.ClientSideApp;

import com.pb.deposits.ClientSideApp.services.Service;

public class ClientStarter {

    public static void main(String[] args) {
        System.out.println("Welcome into Bank Deposits Directory CLI");
        new Service().runDepositClient();
    }
}
