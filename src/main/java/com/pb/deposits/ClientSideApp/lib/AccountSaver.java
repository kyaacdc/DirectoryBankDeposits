package com.pb.deposits.ClientSideApp.lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class AccountSaver implements Command{

    private String id;
    private int amount;
    private String bankName;
    private String country;
    private int profitability;
    private int timeConstraints;
    private String typeDeposit;
    private String depositor;

    public AccountSaver(String id, int amount, String bankName, String country, int profitability,
                        int timeConstraints, String typeDeposit, String depositor) {
        this.id = id;
        this.amount = amount;
        this.bankName = bankName;
        this.country = country;
        this.profitability = profitability;
        this.timeConstraints = timeConstraints;
        this.typeDeposit = typeDeposit;
        this.depositor = depositor;
    }

    @Override
    public void execute() {

        if(isAccountExist(id)){
            System.err.println("ERROR Deposit with this ID is exist");
        } else if (amount >= 100000){
            System.err.println("ERROR This amount of deposit is not permit in our bank");
        } else if (profitability < 0 || profitability > 30){
            System.err.println("ERROR This profitability of deposit is not permit in our bank." +
                    " Percent of profitability should be from 0 to 30 percents");
        } else if (timeConstraints <= 0){
            System.err.println("ERROR TimeConstraints of deposit should be > 0. ");
        } else {

            if(!isDepositorExist(depositor))
                createDepositor(depositor);

            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpPost postRequest = new HttpPost(
                        "http://localhost:8080/account");

                StringEntity input = new StringEntity(
                        "{\"id\":\"" + id + "\",\"amount\":\"" + amount
                                + "\",\"bankName\":\"" + bankName
                                + "\",\"country\":\"" + country
                                + "\",\"profitability\":\"" + profitability
                                + "\",\"timeConstraints\":\"" + timeConstraints
                                + "\",\"typeDeposit\":\"" + typeDeposit
                                + "\",\"depositor\":\"http://localhost:8080/depositor/" + depositor
                                + "\"}");

                input.setContentType("application/json");
                postRequest.setEntity(input);

                HttpResponse response = httpClient.execute(postRequest);

                if (response.getStatusLine().getStatusCode() != 201) {
                    System.err.println(response.getStatusLine().getStatusCode());
                    System.err.println("Input ERROR.");
                    System.err.println("Id should consist of digits 0-9 and -,(,) symbols");
                    System.err.println("Name of country should consist of letters A-Z, a-z symbols");
                    System.err.println("Email of depositor should consist of letters A-Z, a-z, digits 0-9 and symbols -@,(,)");
                    return;
                }

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (response.getEntity().getContent())));

                String output;
                System.out.println("Output from Server OK \n");
                while ((output = br.readLine()) != null) {

                    System.out.println(output);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isAccountExist(String id){
        try (CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpGet request = new HttpGet("http://localhost:8080/account/" + id);

            HttpResponse response = httpClient.execute(request);

            return response.getStatusLine().getStatusCode() != 404;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean isDepositorExist(String depositor){
        try (CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpGet request = new HttpGet("http://localhost:8080/depositor/" + depositor);

            HttpResponse response = httpClient.execute(request);

            return response.getStatusLine().getStatusCode() != 404;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void createDepositor(String depositor){
        Command command = new DepositorSaver(depositor, depositor.split("@")[0]);
        command.execute();
    }
}