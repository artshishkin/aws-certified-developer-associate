/**
 * null
 */
package net.shyshkin.study.gateway.model.transform;

import com.amazonaws.Request;
import com.amazonaws.SdkClientException;
import com.amazonaws.annotation.SdkInternalApi;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.protocol.OperationInfo;
import com.amazonaws.protocol.Protocol;
import com.amazonaws.protocol.ProtocolRequestMarshaller;
import com.amazonaws.transform.Marshaller;
import net.shyshkin.study.gateway.model.GetStagevariablesRequest;

import javax.annotation.Generated;

/**
 * GetStagevariablesRequest Marshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
@SdkInternalApi
public class GetStagevariablesRequestProtocolMarshaller implements Marshaller<Request<GetStagevariablesRequest>, GetStagevariablesRequest> {

    private static final OperationInfo SDK_OPERATION_BINDING = OperationInfo.builder().protocol(Protocol.API_GATEWAY).requestUri("/dev/stagevariables")
            .httpMethodName(HttpMethodName.GET).hasExplicitPayloadMember(false).hasPayloadMembers(false).serviceName("MyFirstSdkGeneration").build();

    private final com.amazonaws.opensdk.protect.protocol.ApiGatewayProtocolFactoryImpl protocolFactory;

    public GetStagevariablesRequestProtocolMarshaller(com.amazonaws.opensdk.protect.protocol.ApiGatewayProtocolFactoryImpl protocolFactory) {
        this.protocolFactory = protocolFactory;
    }

    public Request<GetStagevariablesRequest> marshall(GetStagevariablesRequest getStagevariablesRequest) {

        if (getStagevariablesRequest == null) {
            throw new SdkClientException("Invalid argument passed to marshall(...)");
        }

        try {
            final ProtocolRequestMarshaller<GetStagevariablesRequest> protocolMarshaller = protocolFactory.createProtocolMarshaller(SDK_OPERATION_BINDING,
                    getStagevariablesRequest);

            protocolMarshaller.startMarshalling();
            GetStagevariablesRequestMarshaller.getInstance().marshall(getStagevariablesRequest, protocolMarshaller);
            return protocolMarshaller.finishMarshalling();
        } catch (Exception e) {
            throw new SdkClientException("Unable to marshall request to JSON: " + e.getMessage(), e);
        }
    }

}
