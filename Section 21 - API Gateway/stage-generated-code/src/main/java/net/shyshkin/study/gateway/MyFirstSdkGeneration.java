/**
 * null
 */
package net.shyshkin.study.gateway;

import net.shyshkin.study.gateway.model.*;

import javax.annotation.Generated;

/**
 * Interface for accessing MyFirstSdkGeneration.
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public interface MyFirstSdkGeneration {

    /**
     * @param getApiRootRequest
     * @return Result of the GetApiRoot operation returned by the service.
     * @sample MyFirstSdkGeneration.GetApiRoot
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/qozxt2izp7-2021-01-09T20:00:29Z/GetApiRoot"
     *      target="_top">AWS API Documentation</a>
     */
    GetApiRootResult getApiRoot(GetApiRootRequest getApiRootRequest);

    /**
     * @param getHousesRequest
     * @return Result of the GetHouses operation returned by the service.
     * @sample MyFirstSdkGeneration.GetHouses
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/qozxt2izp7-2021-01-09T20:00:29Z/GetHouses" target="_top">AWS
     *      API Documentation</a>
     */
    GetHousesResult getHouses(GetHousesRequest getHousesRequest);

    /**
     * @param getStagevariablesRequest
     * @return Result of the GetStagevariables operation returned by the service.
     * @sample MyFirstSdkGeneration.GetStagevariables
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/qozxt2izp7-2021-01-09T20:00:29Z/GetStagevariables"
     *      target="_top">AWS API Documentation</a>
     */
    GetStagevariablesResult getStagevariables(GetStagevariablesRequest getStagevariablesRequest);

    /**
     * @return Create new instance of builder with all defaults set.
     */
    public static MyFirstSdkGenerationClientBuilder builder() {
        return new MyFirstSdkGenerationClientBuilder();
    }

    /**
     * Shuts down this client object, releasing any resources that might be held open. This is an optional method, and
     * callers are not expected to call it, but can if they want to explicitly release any open resources. Once a client
     * has been shutdown, it should not be used to make any more requests.
     */
    void shutdown();

}
