/**
 * null
 */
package net.shyshkin.study.gateway.model;

import com.amazonaws.annotation.SdkInternalApi;
import com.amazonaws.opensdk.SdkErrorHttpMetadata;
import com.amazonaws.opensdk.internal.BaseException;

import javax.annotation.Generated;

/**
 * Base exception for all service exceptions thrown by MyFirstAPI
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class MyFirstSdkGenerationException extends com.amazonaws.SdkBaseException implements BaseException {

    private static final long serialVersionUID = 1L;

    private SdkErrorHttpMetadata sdkHttpMetadata;

    private String message;

    /**
     * Constructs a new MyFirstSdkGenerationException with the specified error message.
     *
     * @param message
     *        Describes the error encountered.
     */
    public MyFirstSdkGenerationException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public MyFirstSdkGenerationException sdkHttpMetadata(SdkErrorHttpMetadata sdkHttpMetadata) {
        this.sdkHttpMetadata = sdkHttpMetadata;
        return this;
    }

    @Override
    public SdkErrorHttpMetadata sdkHttpMetadata() {
        return sdkHttpMetadata;
    }

    @SdkInternalApi
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
