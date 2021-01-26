package com.jester.crypto.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jester.crypto.clients.BitcoinPriceIndexClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
class BitcoinPriceIndexControllerTest {

    @Mock BitcoinPriceIndexClient bitcoinPriceIndexClient;
    @InjectMocks BitcoinPriceIndexController bitcoinPriceIndexController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void itReturnsBitcoinPriceIndex() throws JsonProcessingException {
        when(bitcoinPriceIndexClient.getExchange()).thenReturn("23423.323");
        bitcoinPriceIndexController.bitcoinPriceIndex();
        verify(bitcoinPriceIndexClient, times(1)).getExchange();
    }
}