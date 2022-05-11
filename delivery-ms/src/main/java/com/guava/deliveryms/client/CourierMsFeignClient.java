package com.guava.deliveryms.client;


import com.guava.deliveryms.dto.feign.CourierStatusReqDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "courier-ms", url = "${feignclient.courier-ms}")
public interface CourierMsFeignClient {


    @PutMapping("/couriers/status")
    void updateCourierStatus(@RequestBody CourierStatusReqDto request);


}
