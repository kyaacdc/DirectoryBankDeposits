package com.pb.deposits.ClientSideApp;

import com.pb.deposits.ClientSideApp.lib.*;

import java.util.Scanner;

public class ClientStarter {

    public static void main(String[] args) {

        System.out.println("Welcome into Bank Deposits Directory");

        Command command;

        ClientStarter client = new ClientStarter();

        client.printHelp();
        Scanner sc = new Scanner(System.in);
        String commLine = "";

        while (! (commLine = sc.nextLine().toLowerCase().trim()).equals("/q")) {

            String[] split = commLine.split(" ");

            if(commLine.equals("/h"))
                client.printCommands();
            else if(split[0].equals("delete") && split.length == 2) {
                command = new AccountRemover((split[1]));
                System.out.print("Remove account - ");
                command.execute();
            }
            else if(split[0].equals("addcustomer") && split.length == 3) {
                command = new DepositorSaver(split[1], split[2]);
                System.out.print("Add customer - ");
                command.execute();
            }
            else if(split[0].equals("add") && split.length == 9) {
                command = new AccountSaver(split[1], Integer.parseInt(split[2]), split[3], split[4],
                        Integer.parseInt(split[5]), Integer.parseInt(split[6]), split[7], split[8]);
                System.out.print("Add deposit - ");
                command.execute();
            } else if(split[0].equals("list")) {
                command = new AccountGetter();
                command.execute();
            } else if(split[0].equals("sum")) {
                command = new AccountGetter("/account/search/getSumAllAccounts", "");
                System.out.print("Amount of all deposits = ");
                command.execute();
            } else if(split[0].equals("count")) {
                command = new AccountGetter("/account/search/countBy", "");
                System.out.print("Count all deposits = ");
                command.execute();
            } else if(split[0].equals("info") && split[1].equals("account") && split.length == 3) {
                command = new AccountGetter("/account/" + split[2], "");
                System.out.print("Info by account number - ");
                command.execute();
            } else if(split[0].equals("info") && split[1].equals("depositor") && split.length == 3) {
                command = new AccountGetter("/account/search/findByDepositor", "?depositor=" + split[2]);
                System.out.print("List deposits of customer - ");
                command.execute();
            } else if(split[0].equals("show") && split[1].equals("type") && split.length == 3) {
                command = new AccountGetter("/account/search/findByTypeDeposit", "?typeDeposit=" + split[2]);
                System.out.print("List deposits by type - ");
                command.execute();
            }else if(split[0].equals("show") && split[1].equals("bank") && split.length == 3) {
                command = new AccountGetter("/account/search/findByBankNameIgnoreCase", "?bankName=" + split[2]);
                System.out.print("List deposits by bank name - ");
                command.execute();
            }else {
                System.out.println("Input correct command");
            }
            client.printHelp();
        }
        System.out.println("Good Bye!");
    }

    private void printCommands(){
        System.out.println("Commands Manual. (case ignored)");
        System.out.println("LIST - for show list of all deposits");
        System.out.println("SUM - for view general amount of all deposits in bank");
        System.out.println("COUNT - view count of deposits in bank");
        System.out.println("INFO ACCOUNT <account_id> - show full info about this deposit. Example - info account 0000-1111...");
        System.out.println("INFO DEPOSITOR <depositor_email> - list of deposits by customer. Example - info depositor kya@bk.ru");
        System.out.println("SHOW TYPE <type> - list all deposits by type. Type: PosteRestante, Urgent, Rated, Cumulative, Savings, Metallic");
        System.out.println("SHOW BANK <name> - for show list of all deposits by bank name. Example - show bank privat");
        System.out.println("ADDCUSTOMER <customer info> - for add new customer. Example - addCustomer <email> <name>");
        System.out.println("ADD <deposit info> - for add new deposit. Example - add <id> <amount> <bank_name> <country> <profitabitity> <timeConstraints> <typeDeposit> <DepositorsEmail>");
        System.out.println("DELETE <account id> - for remove deposit by ID");
    }

    private void printHelp(){
        System.out.println("Please, input command below (ignore case)");
        System.out.println("(/q - QUIT, /h - HELP)");
    }
}
