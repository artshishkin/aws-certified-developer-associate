/**
 * null
 */
package net.shyshkin.study.gateway;

import com.amazonaws.SdkBaseException;
import com.amazonaws.annotation.ThreadSafe;
import com.amazonaws.client.AwsSyncClientParams;
import com.amazonaws.client.ClientExecutionParams;
import com.amazonaws.client.ClientHandler;
import com.amazonaws.client.ClientHandlerParams;
import com.amazonaws.http.HttpResponseHandler;
import com.amazonaws.opensdk.protect.client.SdkClientHandler;
import com.amazonaws.protocol.json.JsonClientMetadata;
import com.amazonaws.protocol.json.JsonErrorResponseMetadata;
import com.amazonaws.protocol.json.JsonErrorShapeMetadata;
import com.amazonaws.protocol.json.JsonOperationMetadata;
import net.shyshkin.study.gateway.model.*;
import net.shyshkin.study.gateway.model.transform.*;

import javax.annotation.Generated;
import java.util.Arrays;

/**
 * Client for accessing MyFirstSdkGeneration. All service calls made using this client are blocking, and will not return
 * until the service call completes.
 * <p>
 * 
 */
@ThreadSafe
@Generated("com.amazonaws:aws-java-sdk-code-generator")
class MyFirstSdkGenerationClient implements MyFirstSdkGeneration {

    private final ClientHandler clientHandler;

    private static final com.amazonaws.opensdk.protect.protocol.ApiGatewayProtocolFactoryImpl protocolFactory = new com.amazonaws.opensdk.protect.protocol.ApiGatewayProtocolFactoryImpl(
            new JsonClientMetadata().withProtocolVersion("1.1").withSupportsCbor(false).withSupportsIon(false).withContentTypeOverride("application/json")
                    .withBaseServiceExceptionClass(net.shyshkin.study.gateway.model.MyFirstSdkGenerationException.class));

    /**
     * Constructs a new client to invoke service methods on MyFirstSdkGeneration using the specified parameters.
     *
     * <p>
     * All service calls made using this new client object are blocking, and will not return until the service call
     * completes.
     *
     * @param clientParams
     *        Object providing client parameters.
     */
    MyFirstSdkGenerationClient(AwsSyncClientParams clientParams) {
        this.clientHandler = new SdkClientHandler(new ClientHandlerParams().withClientParams(clientParams));
    }

    /**
     * @param getApiRootRequest
     * @return Result of the GetApiRoot operation returned by the service.
     * @sample MyFirstSdkGeneration.GetApiRoot
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/qozxt2izp7-2021-01-09T20:00:29Z/GetApiRoot"
     *      target="_top">AWS API Documentation</a>
     */
    @Override
    public GetApiRootResult getApiRoot(GetApiRootRequest getApiRootRequest) {
        HttpResponseHandler<GetApiRootResult> responseHandler = protocolFactory.createResponseHandler(new JsonOperationMetadata().withPayloadJson(true)
                .withHasStreamingSuccessResponse(false), new GetApiRootResultJsonUnmarshaller());

        HttpResponseHandler<SdkBaseException> errorResponseHandler = createErrorResponseHandler();

        return clientHandler.execute(new ClientExecutionParams<GetApiRootRequest, GetApiRootResult>()
                .withMarshaller(new GetApiRootRequestProtocolMarshaller(protocolFactory)).withResponseHandler(responseHandler)
                .withErrorResponseHandler(errorResponseHandler).withInput(getApiRootRequest));
    }

    /**
     * @param getHousesRequest
     * @return Result of the GetHouses operation returned by the service.
     * @sample MyFirstSdkGeneration.GetHouses
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/qozxt2izp7-2021-01-09T20:00:29Z/GetHouses" target="_top">AWS
     *      API Documentation</a>
     */
    @Override
    public GetHousesResult getHouses(GetHousesRequest getHousesRequest) {
        HttpResponseHandler<GetHousesResult> responseHandler = protocolFactory.createResponseHandler(new JsonOperationMetadata().withPayloadJson(true)
                .withHasStreamingSuccessResponse(false), new GetHousesResultJsonUnmarshaller());

        HttpResponseHandler<SdkBaseException> errorResponseHandler = createErrorResponseHandler();

        return clientHandler.execute(new ClientExecutionParams<GetHousesRequest, GetHousesResult>()
                .withMarshaller(new GetHousesRequestProtocolMarshaller(protocolFactory)).withResponseHandler(responseHandler)
                .withErrorResponseHandler(errorResponseHandler).withInput(getHousesRequest));
    }

    /**
     * @param getStagevariablesRequest
     * @return Result of the GetStagevariables operation returned by the service.
     * @sample MyFirstSdkGeneration.GetStagevariables
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/qozxt2izp7-2021-01-09T20:00:29Z/GetStagevariables"
     *      target="_top">AWS API Documentation</a>
     */
    @Override
    public GetStagevariablesResult getStagevariables(GetStagevariablesRequest getStagevariablesRequest) {
        HttpResponseHandler<GetStagevariablesResult> responseHandler = protocolFactory.createResponseHandler(new JsonOperationMetadata().withPayloadJson(true)
                .withHasStreamingSuccessResponse(false), new GetStagevariablesResultJsonUnmarshaller());

        HttpResponseHandler<SdkBaseException> errorResponseHandler = createErrorResponseHandler();

        return clientHandler.execute(new ClientExecutionParams<GetStagevariablesRequest, GetStagevariablesResult>()
                .withMarshaller(new GetStagevariablesRequestProtocolMarshaller(protocolFactory)).withResponseHandler(responseHandler)
                .withErrorResponseHandler(errorResponseHandler).withInput(getStagevariablesRequest));
    }

    /**
     * Create the error response handler for the operation.
     * 
     * @param errorShapeMetadata
     *        Error metadata for the given operation
     * @return Configured error response handler to pass to HTTP layer
     */
    private HttpResponseHandler<SdkBaseException> createErrorResponseHandler(JsonErrorShapeMetadata... errorShapeMetadata) {
        return protocolFactory.createErrorResponseHandler(new JsonErrorResponseMetadata().withErrorShapes(Arrays.asList(errorShapeMetadata)));
    }

    @Override
    public void shutdown() {
        clientHandler.shutdown();
    }

}
