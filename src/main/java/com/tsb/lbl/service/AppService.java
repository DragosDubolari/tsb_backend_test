package com.tsb.lbl.service;

import com.tsb.lbl.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;

import javax.annotation.PreDestroy;

/**
 * {@link Service} contains the main logic for listening and indexing contract events on spring-boot app startup.
 * For simplicity, all the logs are indexed to the server console, however it can be extended to store events in a database or read/route the events to
 * a web-page.
 * */

@Service
public class AppService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppService.class);
    private final Web3j web3j;
    public AppService(Web3j web3j) {
        this.web3j = web3j;
    }

    /**
     * This method is invoked by the spring-boot app. It will subscribe to the Sandbox contract and will listen to events once the app is deployed.
     * */
    @EventListener(ApplicationReadyEvent.class)
    public void listenAndIndexContractEvents() {
        //Define a filter for the Sandbox contract to index the latest events.
        EthFilter ethFilter = new EthFilter(DefaultBlockParameterName.LATEST,
                DefaultBlockParameterName.LATEST,
                AppConstants.CONTRACT_ADDRESS
        );
        LOGGER.info("Start Listening to events...");
        web3j.ethLogFlowable(ethFilter).subscribe(
                log -> LOGGER.info("Event message received " + log),
                throwable -> LOGGER.error("Error on receiving event message " + throwable.getMessage())
        );
    }

    //Shutdown client silently on app exit
    @PreDestroy
    public void shutDown() {
        LOGGER.info("Shutting down Web3j Client");
        web3j.shutdown();
    }
}
