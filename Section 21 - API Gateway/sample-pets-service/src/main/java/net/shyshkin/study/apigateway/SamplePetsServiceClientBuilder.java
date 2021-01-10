/**
 * null
 */
package net.shyshkin.study.apigateway;

import com.amazonaws.Protocol;
import com.amazonaws.annotation.NotThreadSafe;
import com.amazonaws.client.AwsSyncClientParams;
import com.amazonaws.opensdk.internal.config.ApiGatewayClientConfigurationFactory;
import com.amazonaws.opensdk.protect.client.SdkSyncClientBuilder;
import com.amazonaws.util.RuntimeHttpUtils;

import javax.annotation.Generated;
import java.net.URI;

/**
 * Fluent builder for {@link net.shyshkin.study.apigateway.SamplePetsService}.
 * 
 * @see net.shyshkin.study.apigateway.SamplePetsService#builder
 **/
@NotThreadSafe
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public final class SamplePetsServiceClientBuilder extends SdkSyncClientBuilder<SamplePetsServiceClientBuilder, SamplePetsService> {

    private static final URI DEFAULT_ENDPOINT = RuntimeHttpUtils.toUri("woe2mm08tl.execute-api.eu-north-1.amazonaws.com", Protocol.HTTPS);
    private static final String DEFAULT_REGION = "eu-north-1";

    /**
     * Package private constructor - builder should be created via {@link SamplePetsService#builder()}
     */
    SamplePetsServiceClientBuilder() {
        super(new ApiGatewayClientConfigurationFactory());
    }

    /**
     * Construct a synchronous implementation of SamplePetsService using the current builder configuration.
     *
     * @param params
     *        Current builder configuration represented as a parameter object.
     * @return Fully configured implementation of SamplePetsService.
     */
    @Override
    protected SamplePetsService build(AwsSyncClientParams params) {
        return new SamplePetsServiceClient(params);
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
