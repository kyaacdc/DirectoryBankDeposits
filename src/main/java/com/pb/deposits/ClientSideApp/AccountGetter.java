package com.pb.deposits.ClientSideApp;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class AccountGetter implements Command{
    private String request = "";
    private String criteria = "";

    public AccountGetter() {
    }

    public AccountGetter(String request, String criteria) {
        this.request = request;
        this.criteria = criteria;
    }

    @Override
    public void execute() {

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

            HttpGet getRequest;
            // specify the host, protocol, and port
            HttpHost target = new HttpHost("localhost", 8080, "http");

            if(request.equals("") && criteria.equals(""))
                getRequest = new HttpGet("/account");
            else if (!request.equals("") && criteria.equals(""))
                getRequest = new HttpGet(request);
            else
                getRequest = new HttpGet(request + criteria);

            System.out.println("executing request to " + target);

            HttpResponse httpResponse = httpclient.execute(target, getRequest);
            HttpEntity entity = httpResponse.getEntity();

            System.out.println("----------------------------------------");
            System.out.println(httpResponse.getStatusLine());
            Header[] headers = httpResponse.getAllHeaders();
            for (int i = 0; i < headers.length; i++) {
                System.out.println(headers[i]);
            }
            System.out.println("----------------------------------------");

            if (entity != null) {
                System.out.println(EntityUtils.toString(entity));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
