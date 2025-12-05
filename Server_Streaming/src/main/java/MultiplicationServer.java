import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class MultiplicationServer {
    public static void main(String[] args) throws IOException,
            InterruptedException {
        Server server = ServerBuilder.forPort(50051)
                .addService((BindableService) new MultiplicationService())
                .build();
        System.out.println("Serveur gRPC démarré sur le port 9092...");
        server.start();
        server.awaitTermination();
    }
}