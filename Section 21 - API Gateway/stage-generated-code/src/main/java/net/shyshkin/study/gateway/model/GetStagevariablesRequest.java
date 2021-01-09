/**
 * null
 */
package net.shyshkin.study.gateway.model;

import javax.annotation.Generated;
import java.io.Serializable;

/**
 * 
 * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/qozxt2izp7-2021-01-09T20:00:29Z/GetStagevariables"
 *      target="_top">AWS API Documentation</a>
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class GetStagevariablesRequest extends com.amazonaws.opensdk.BaseRequest implements Serializable, Cloneable {

    /**
     * Returns a string representation of this object. This is useful for testing and debugging. Sensitive data will be
     * redacted from this string using a placeholder value.
     *
     * @return A string representation of this object.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (obj instanceof GetStagevariablesRequest == false)
            return false;
        GetStagevariablesRequest other = (GetStagevariablesRequest) obj;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        return hashCode;
    }

    @Override
    public GetStagevariablesRequest clone() {
        return (GetStagevariablesRequest) super.clone();
    }

    /**
     * Set the configuration for this request.
     *
     * @param sdkRequestConfig
     *        Request configuration.
     * @return This object for method chaining.
     */
    public GetStagevariablesRequest sdkRequestConfig(com.amazonaws.opensdk.SdkRequestConfig sdkRequestConfig) {
        super.sdkRequestConfig(sdkRequestConfig);
        return this;
    }

}
