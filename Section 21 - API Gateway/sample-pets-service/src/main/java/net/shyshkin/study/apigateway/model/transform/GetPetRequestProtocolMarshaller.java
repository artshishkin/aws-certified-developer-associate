/**
 * null
 */
package net.shyshkin.study.apigateway.model.transform;

import com.amazonaws.Request;
import com.amazonaws.SdkClientException;
import com.amazonaws.annotation.SdkInternalApi;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.protocol.OperationInfo;
import com.amazonaws.protocol.Protocol;
import com.amazonaws.protocol.ProtocolRequestMarshaller;
import com.amazonaws.transform.Marshaller;
import net.shyshkin.study.apigateway.model.GetPetRequest;

import javax.annotation.Generated;

/**
 * GetPetRequest Marshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
@SdkInternalApi
public class GetPetRequestProtocolMarshaller implements Marshaller<Request<GetPetRequest>, GetPetRequest> {

    private static final OperationInfo SDK_OPERATION_BINDING = OperationInfo.builder().protocol(Protocol.API_GATEWAY).requestUri("/v1/pets/{petId}")
            .httpMethodName(HttpMethodName.GET).hasExplicitPayloadMember(false).hasPayloadMembers(false).serviceName("SamplePetsService").build();

    private final com.amazonaws.opensdk.protect.protocol.ApiGatewayProtocolFactoryImpl protocolFactory;

    public GetPetRequestProtocolMarshaller(com.amazonaws.opensdk.protect.protocol.ApiGatewayProtocolFactoryImpl protocolFactory) {
        this.protocolFactory = protocolFactory;
    }

    public Request<GetPetRequest> marshall(GetPetRequest getPetRequest) {

        if (getPetRequest == null) {
            throw new SdkClientException("Invalid argument passed to marshall(...)");
        }

        try {
            final ProtocolRequestMarshaller<GetPetRequest> protocolMarshaller = protocolFactory.createProtocolMarshaller(SDK_OPERATION_BINDING, getPetRequest);

            protocolMarshaller.startMarshalling();
            GetPetRequestMarshaller.getInstance().marshall(getPetRequest, protocolMarshaller);
            return protocolMarshaller.finishMarshalling();
        } catch (Exception e) {
            throw new SdkClientException("Unable to marshall request to JSON: " + e.getMessage(), e);
        }
    }

}
