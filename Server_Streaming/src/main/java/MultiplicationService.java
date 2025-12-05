import io.grpc.stub.StreamObserver;
import stub.MultiplicationGrpc;
import stub.MultiplicationOuterClass;

public class MultiplicationService extends MultiplicationGrpc.MultiplicationImplBase{
    @Override
    public void getMultiplicationTable(MultiplicationOuterClass.MultiplicationRequest request,

                                       StreamObserver<MultiplicationOuterClass.MultiplicationResponse> responseObserver) {

        int nombre = request.getNombre();
        int limite = request.getLimite();
        for (int i = 1; i <= limite; i++) {
            String result = nombre + " x " + i + " = " + (nombre * i);
            MultiplicationOuterClass.MultiplicationResponse response = MultiplicationOuterClass.MultiplicationResponse.newBuilder()
                    .setResultat(result)
                    .build();
// Envoyer le message au client
            responseObserver.onNext(response);
        }
// Terminer le streaming
        responseObserver.onCompleted();
    }
}