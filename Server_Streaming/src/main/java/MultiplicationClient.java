import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import stub.MultiplicationGrpc;
import stub.MultiplicationOuterClass;

public class MultiplicationClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        MultiplicationGrpc.MultiplicationStub stub = MultiplicationGrpc.newStub(channel);
        MultiplicationOuterClass.MultiplicationRequest request = MultiplicationOuterClass.MultiplicationRequest.newBuilder()
                .setNombre(5)
                .setLimite(10)
                .build();
        stub.getMultiplicationTable(request, new io.grpc.stub.StreamObserver<MultiplicationOuterClass.MultiplicationResponse>() {
            @Override
            public void onNext(MultiplicationOuterClass.MultiplicationResponse response) {
                System.out.println(response.getResultat());
            }
            @Override
            public void onError(Throwable t) {
                System.err.println("Erreur : " + t.getMessage());
            }
            @Override
            public void onCompleted() {
                System.out.println("Streaming terminé.");
            }
        });
// Garder le client en vie pour recevoir les réponses
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channel.shutdown();
    }
}