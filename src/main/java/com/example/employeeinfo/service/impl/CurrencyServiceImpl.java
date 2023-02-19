package com.example.employeeinfo.service.impl;

import com.example.employeeinfo.dto.ResponseData;
import com.example.employeeinfo.service.CurrencyService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Override
    public ResponseData<String> getCurrencyData() {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity <String> entity = new HttpEntity<String>(headers);
            RestTemplate restTemplate = new RestTemplate();

            try {
                String body = Objects.requireNonNull(restTemplate.exchange("https://nbu.uz/uz/exchange-rates/json/", HttpMethod.GET, entity, Object.class).getBody()).toString();
                ResponseData<String> responseData = new ResponseData<>();
                responseData.setData(body);
                return responseData;

            }catch (Exception e){

                ResponseData<String> responseData = new ResponseData<>();

                responseData.setSuccess(false);
                responseData.setMessage("information not found");

                return responseData;
            }
    }
}
