package com.tsb.lbl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.websocket.WebSocketService;

import java.net.ConnectException;

import static com.tsb.lbl.AppConstants.INFURA_WS_ADDRESS;
/**
 * {@link Configuration} class which takes the responsibility to build the {@link Web3j} bean and configure WS connectivity to the Infura network.
 * */
@Configuration
public class Web3Launcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(Web3Launcher.class);
    @Bean
    public Web3j init() {
        LOGGER.info("Initializing Web3j Websocket");
        WebSocketService webSocketService = new WebSocketService(INFURA_WS_ADDRESS, true);
        try {
            webSocketService.connect();
        } catch (ConnectException e) {
            LOGGER.error("Web3j WS failed during init " + e.getMessage());
            throw new RuntimeException(e);
        }
        return Web3j.build(webSocketService);
    }
}
