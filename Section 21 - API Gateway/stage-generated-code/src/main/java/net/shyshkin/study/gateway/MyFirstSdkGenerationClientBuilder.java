/**
 * null
 */
package net.shyshkin.study.gateway;

import com.amazonaws.Protocol;
import com.amazonaws.annotation.NotThreadSafe;
import com.amazonaws.client.AwsSyncClientParams;
import com.amazonaws.opensdk.internal.config.ApiGatewayClientConfigurationFactory;
import com.amazonaws.opensdk.protect.client.SdkSyncClientBuilder;
import com.amazonaws.util.RuntimeHttpUtils;

import javax.annotation.Generated;
import java.net.URI;

/**
 * Fluent builder for {@link net.shyshkin.study.gateway.MyFirstSdkGeneration}.
 * 
 * @see net.shyshkin.study.gateway.MyFirstSdkGeneration#builder
 **/
@NotThreadSafe
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public final class MyFirstSdkGenerationClientBuilder extends SdkSyncClientBuilder<MyFirstSdkGenerationClientBuilder, MyFirstSdkGeneration> {

    private static final URI DEFAULT_ENDPOINT = RuntimeHttpUtils.toUri("qozxt2izp7.execute-api.eu-north-1.amazonaws.com", Protocol.HTTPS);
    private static final String DEFAULT_REGION = "eu-north-1";

    /**
     * Package private constructor - builder should be created via {@link MyFirstSdkGeneration#builder()}
     */
    MyFirstSdkGenerationClientBuilder() {
        super(new ApiGatewayClientConfigurationFactory());
    }

    /**
     * Construct a synchronous implementation of MyFirstSdkGeneration using the current builder configuration.
     *
     * @param params
     *        Current builder configuration represented as a parameter object.
     * @return Fully configured implementation of MyFirstSdkGeneration.
     */
    @Override
    protected MyFirstSdkGeneration build(AwsSyncClientParams params) {
        return new MyFirstSdkGenerationClient(params);
    }

    @Override
    protected URI defaultEndpoint() {
        return DEFAULT_ENDPOINT;
    }

    @Override
    protected String defaultRegion() {
        return DEFAULT_REGION;
    }

}
