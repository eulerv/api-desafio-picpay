package com.github.eulerv.picpaydesafiobackend.authorization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.github.eulerv.picpaydesafiobackend.transaction.Transaction;

@Service
public class AuthorizerService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorizerService.class);
    private RestClient restClient;

    public AuthorizerService(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("https://util.devi.tools/api/v2/authorize").build();
        // this.restClient =
        // builder.baseUrl("https://run.mocky.io/v3/11d69aaa-2f99-4f1c-987c-5e08fcd58ff2").build();
    }

    @SuppressWarnings("null")
    public void authorize(Transaction transaction) {
        LOG.info("...Authorizing transaction: {}", transaction);
        var response = restClient.get()
                .retrieve()
                .toEntity(Authorization.class);

        if (response.getStatusCode().isError() || !response.getBody().isAuthorized()) {
            throw new UnauthorizedTransactionException("Unauthorized Transactionn" + response.getStatusCode());
        }
        LOG.info("Transaction isAuthorized. Value: {}", response.getBody().isAuthorized());
    }
}