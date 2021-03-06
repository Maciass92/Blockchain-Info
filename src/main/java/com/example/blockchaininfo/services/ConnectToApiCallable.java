package com.example.blockchaininfo.services;

import com.example.blockchaininfo.pojos.ReturnedPoolData;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.util.concurrent.Callable;

@Slf4j
public class ConnectToApiCallable implements Callable<ReturnedPoolData> {

    private String url;
    private String poolName;
    private String poolType;

    public ConnectToApiCallable(String url, String poolName, String poolType) {
        this.url = url;
        this.poolName = poolName;
        this.poolType = poolType;
    }

    @Override
    public ReturnedPoolData call(){

            HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build());
            clientHttpRequestFactory.setConnectTimeout(4000);
            RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

            ReturnedPoolData stringAndDate = new ReturnedPoolData();
            stringAndDate.setJsonString(restTemplate.getForObject(url, String.class));
            stringAndDate.setDateTime(OffsetDateTime.now());
            stringAndDate.setPoolName(this.poolName);
            stringAndDate.setPoolType(this.poolType);

        return stringAndDate;
    }

}
