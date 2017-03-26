package com.pb.deposits.ClientSideApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class ApacheHttpClientPost {

    // http://localhost:8080/RESTfulExample/json/product/post
    public static void main(String[] args) {

        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(
                    "http://localhost:8080/account");

            StringEntity input = new StringEntity(
                    "{\"id\":\"01234-567888\",\"amount\":\"10000\",\"bankName\":\"Credo\",\"country\":\"Poland\",\"profitability\":\"25\",\"timeConstraints\":\"365\",\"typeDeposit\":\"Cumulative\",\"depositor\":\"http://localhost:8080/Depositor/kya@bk.ru\"}");
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

            httpClient.getConnectionManager().shutdown();


        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}