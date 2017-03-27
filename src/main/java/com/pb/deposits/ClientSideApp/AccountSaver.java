package com.pb.deposits.ClientSideApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class AccountSaver implements Command{

    private String id;
    private String amount;
    private String bankName;
    private String country;
    private String profitability;
    private String timeConstraints;
    private String typeDeposit;
    private String depositor;

    public AccountSaver(String id, String amount, String bankName, String country, String profitability,
                        String timeConstraints, String typeDeposit, String depositor) {
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

        try (CloseableHttpClient httpClient = HttpClients.createDefault()){

            HttpPost postRequest = new HttpPost(
                    "http://localhost:8080/account");

            StringEntity input = new StringEntity(
                    "{\"id\":\"" + id + "\",\"amount\":\"" + amount
                            + "\",\"bankName\":\"" + bankName
                            + "\",\"country\":\"" + country
                            + "\",\"profitability\":\"" + profitability
                            + "\",\"timeConstraints\":\"" + timeConstraints
                            + "\",\"typeDeposit\":\"" + typeDeposit
                            + "\",\"depositor\":\"" + depositor
                            + "\"}");
            input.setContentType("application/json");
            postRequest.setEntity(input);

            HttpResponse response = httpClient.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 201) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (response.getEntity().getContent())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {

                System.out.println(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}