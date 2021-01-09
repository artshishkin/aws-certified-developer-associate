/**
 * null
 */
package net.shyshkin.study.gateway.model.transform;

import com.amazonaws.SdkClientException;
import com.amazonaws.annotation.SdkInternalApi;
import com.amazonaws.protocol.ProtocolMarshaller;
import net.shyshkin.study.gateway.model.GetStagevariablesRequest;

import javax.annotation.Generated;

/**
 * GetStagevariablesRequestMarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
@SdkInternalApi
public class GetStagevariablesRequestMarshaller {

    private static final GetStagevariablesRequestMarshaller instance = new GetStagevariablesRequestMarshaller();

    public static GetStagevariablesRequestMarshaller getInstance() {
        return instance;
    }

    /**
     * Marshall the given parameter object.
     */
    public void marshall(GetStagevariablesRequest getStagevariablesRequest, ProtocolMarshaller protocolMarshaller) {

        if (getStagevariablesRequest == null) {
            throw new SdkClientException("Invalid argument passed to marshall(...)");
        }

        try {
        } catch (Exception e) {
            throw new SdkClientException("Unable to marshall request to JSON: " + e.getMessage(), e);
        }
    }

}
