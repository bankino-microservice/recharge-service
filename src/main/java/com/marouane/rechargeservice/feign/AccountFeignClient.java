package com.marouane.rechargeservice.feign;

import com.marouane.rechargeservice.model.dto.feign.AccountResponseWrapper;
import com.marouane.rechargeservice.model.dto.feign.AccountFeignPostDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ACCOUNT-SERVICE", url = "${account-service.url}")
public interface AccountFeignClient {
    @GetMapping("/api/accounts/{accountId}")
    AccountResponseWrapper getAccountById(@PathVariable("accountId") Long accountId);

    @PostMapping("/api/accounts/update")
    void updateAccountBalance(@RequestBody AccountFeignPostDTO accountFeignPostDTO);
}
