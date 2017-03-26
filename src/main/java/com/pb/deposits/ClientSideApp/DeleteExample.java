package com.pb.deposits.ClientSideApp;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;

public class DeleteExample {

    public static void main(String[] args){

        try (CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpDelete delRequest = new HttpDelete(
                    "http://localhost:8080/account/01234-567888");

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