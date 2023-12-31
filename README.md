# Simple Blockchain Events Listener

### This is a lightweight spring-boot web-app to show how to use Web3j (Java based Web3 library) in order to listen on the latest events for a deployed blockchain contract.

### It makes use of Web3 Websockets to keep a live connection between the Simple Blockchain Events Listener App and Infura Ethereum Node. By default the application listens to a [Sandbox contract](https://etherscan.io/address/0x3845badade8e6dff049820680d1f14bd3903a5d0)

#### In order to run the application, please change your **contract address** (or use the default one) and add your **Infura API key** for authorization in the AppConstants.
#### After changes are made, Run the spring-boot app
> ./mvnw spring-boot:run

### Latest events will be indexed on the server console for the specified contract
![Screenshot](https://github.com/DragosDubolari/tsb_backend_test/blob/main/src/main/resources/screens/log_events_example.jpg)