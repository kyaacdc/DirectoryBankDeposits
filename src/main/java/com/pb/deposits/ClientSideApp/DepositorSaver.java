package com.pb.deposits.ClientSideApp;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DepositorSaver implements Command {

    private String email;
    private String name;

    public DepositorSaver(String email, String name) {
        this.email = email;
        this.name = name;
    }

    @Override
    public void execute() {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpPost postRequest = new HttpPost(
                    "http://localhost:8080/depositor");

            StringEntity input = new StringEntity(
                    "{\"email\":\"" + email + "\",\"name\":\"" + name + "\"}");

            input.setContentType("application/json");
            postRequest.setEntity(input);

            HttpResponse response = httpClient.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 201) {
                System.err.println(response.getStatusLine().getStatusCode());
                System.err.println("Input ERROR.");
                System.err.println("Email of depositor should consist of letters A-Z, a-z, digits 0-9 and symbols -@,(,)");
                System.err.println("Name of country should consist of letters A-Z, a-z symbols");
                return;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (response.getEntity().getContent())));

            String output;
            System.out.println("Output from Server OK \n");
            while ((output = br.readLine()) != null) {

                System.out.println(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}