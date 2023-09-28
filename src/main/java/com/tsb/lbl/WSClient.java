package com.tsb.lbl;

import okhttp3.*;
import okio.ByteString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
@Component
public class WSClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(WSClient.class);
    private static final String INFURA_API_KEY = "<MASKED_FOR_SECURITY_REASON>";
    private static final String INFURA_WS_ADDRESS = "wss://mainnet.infura.io/ws/v3/" + INFURA_API_KEY;

    private static final String NFT_COLLECTION_ADDRESS = "<MASKED_FOR_SECURITY_REASON>";

    public void listenToEvents() throws InterruptedException {
        Request request = new Request.Builder().url(INFURA_WS_ADDRESS).build();
        WebSocketListener webSocketListener = new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                // Subscribe to NFT collection events
                String subscriptionRequest = "{\"jsonrpc\":\"2.0\",\"method\":\"eth_subscribe\",\"params\":[\"logs\",{\"address\":\"" + NFT_COLLECTION_ADDRESS + "\"}],\"id\":1}";
                webSocket.send(subscriptionRequest);
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                LOGGER.info("Received message: " + text);
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                LOGGER.info("Received byes: " + bytes);
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                LOGGER.info("Closing WebSocket. Code: " + code + ", Reason: " + reason);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                LOGGER.info("Closed for " + reason + " " + code);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                LOGGER.error("Failed " + t.getMessage());
            }
        };
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newWebSocket(request, webSocketListener);
        CountDownLatch latch = new CountDownLatch(1);
        latch.await();
    }
}
