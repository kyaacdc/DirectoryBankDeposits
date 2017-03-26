package com.pb.deposits.ClientSideApp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ClientStarter {

    public static void main(String[] args) {
        System.out.println("Welcome into Bank Deposits Directory");

        Command command = null;
        ClientStarter client = new ClientStarter();

        client.printCommands();
        Scanner sc = new Scanner(System.in);
        String commLine = "";

        while (! (commLine = sc.nextLine().toLowerCase().trim()).equals("/q")) {

            String[] split = commLine.split(" ");

            if(commLine.equals("/h"))
                client.printCommands();
            else if(split[0].equals("list")) {
                command = new AccountGetter();
                command.execute();
            } else if(split[0].equals("sum")) {
                command = new AccountGetter("", "/account/search/getSumAllAccounts");
                System.out.print("Amount of all deposits = ");
                command.execute();
            } else if(split[0].equals("count")) {
                command = new AccountGetter("", "/account/search/countBy");
                System.out.print("Count all deposits = ");
                command.execute();
            } else if(split[0].equals("info") && split[1].equals("account") && split.length == 3) {
                command = new AccountGetter("", "/account/" + split[2]);
                System.out.print("Info by account number = ");
                command.execute();
            } else {
                System.out.println("Input correct command");
                    client.printCommands();
            }
            client.printHelp();
        }
        System.out.println("Good Bye!");
    }

    private void printCommands(){
        System.out.println("Please, input command below (ignore case)");
        System.out.println("LIST - for show list of all deposits");
        System.out.println("SUM - for view general amount of all deposits in bank");
        System.out.println("COUNT - view count of deposits in bank");
        System.out.println("INFO ACCOUNT <account_id> - show full info about this deposit");
        System.out.println("INFO DEPOSITOR <depositor_email> - list of deposits by customer");
        System.out.println("SHOW TYPE <type> - list all deposits by type. Type: PosteRestante, Urgent, Rated, Cumulative, Savings, Metallic");
        System.out.println("SHOW BANK <name> - for show list of all deposits by bank name");
        System.out.println("ADD <deposit info> - for add new deposit info");
        System.out.println("DELETE <account id> - for remove deposit by ID");
    }

    private void printHelp(){
        System.out.println("Input /q for QUIT");
        System.out.println("Input /h for show Commands");
    }
}
