package com.pb.deposits.ClientSideApp.lib;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class AccountRemover implements Command{

    private String id;

    public AccountRemover(String id) {
        this.id = id;
    }

    @Override
    public void execute() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpDelete delRequest = new HttpDelete("http://localhost:8080/account/" + id);

            HttpResponse response = httpClient.execute(delRequest);

            if (response.getStatusLine().getStatusCode() != 204) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            System.out.println("Success .... \n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
