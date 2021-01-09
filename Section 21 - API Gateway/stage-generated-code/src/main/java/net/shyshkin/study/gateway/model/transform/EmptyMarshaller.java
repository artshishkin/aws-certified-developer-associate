/**
 * null
 */
package net.shyshkin.study.gateway.model.transform;

import com.amazonaws.SdkClientException;
import com.amazonaws.annotation.SdkInternalApi;
import com.amazonaws.protocol.ProtocolMarshaller;
import net.shyshkin.study.gateway.model.Empty;

import javax.annotation.Generated;

/**
 * EmptyMarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
@SdkInternalApi
public class EmptyMarshaller {

    private static final EmptyMarshaller instance = new EmptyMarshaller();

    public static EmptyMarshaller getInstance() {
        return instance;
    }

    /**
     * Marshall the given parameter object.
     */
    public void marshall(Empty empty, ProtocolMarshaller protocolMarshaller) {

        if (empty == null) {
            throw new SdkClientException("Invalid argument passed to marshall(...)");
        }

        try {
        } catch (Exception e) {
            throw new SdkClientException("Unable to marshall request to JSON: " + e.getMessage(), e);
        }
    }

}
