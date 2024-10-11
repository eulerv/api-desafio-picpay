package com.github.eulerv.picpaydesafiobackend.authorization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import com.github.eulerv.picpaydesafiobackend.exception.UnableToRequestAuthorization;
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

    public void authorize(Transaction transaction) {
        LOG.info("...Authorizing transaction: {}", transaction);
        try {
            var response = restClient.get()
                    .retrieve()
                    .toEntity(Authorization.class);
            if (response.getBody() == null) {
                throw new UnableToRequestAuthorization("A resposta da API de autorização não contém corpo.");
            } else if (!response.getBody().isAuthorized()) {
                throw new UnauthorizedTransactionException("Transação não autorizada pela API de autorização.");
            }
            LOG.info("Transação autorizada com sucesso. Valor: {}", response.getBody().isAuthorized());

        } catch (RestClientResponseException e) {
            throw new UnableToRequestAuthorization(
                    "Erro (fail) na resposta HTTP da API de autorização(serviço propositalmente volátil para simular um cenário real, tente novamente ^^ ).",
                    e);
        } catch (RestClientException e) {
            throw new UnableToRequestAuthorization(
                    "Erro ao conectar com a API de autorização, se possível me relate o problema para ser corrigido o mais breve possível!",
                    e);
        } catch (Exception e) {
            throw new UnableToRequestAuthorization("Erro inesperado ao solicitar autorização.", e);
        }
    }
}