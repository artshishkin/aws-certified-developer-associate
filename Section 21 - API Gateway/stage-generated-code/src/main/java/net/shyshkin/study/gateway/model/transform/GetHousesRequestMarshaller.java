/**
 * null
 */
package net.shyshkin.study.gateway.model.transform;

import com.amazonaws.SdkClientException;
import com.amazonaws.annotation.SdkInternalApi;
import com.amazonaws.protocol.ProtocolMarshaller;
import net.shyshkin.study.gateway.model.GetHousesRequest;

import javax.annotation.Generated;

/**
 * GetHousesRequestMarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
@SdkInternalApi
public class GetHousesRequestMarshaller {

    private static final GetHousesRequestMarshaller instance = new GetHousesRequestMarshaller();

    public static GetHousesRequestMarshaller getInstance() {
        return instance;
    }

    /**
     * Marshall the given parameter object.
     */
    public void marshall(GetHousesRequest getHousesRequest, ProtocolMarshaller protocolMarshaller) {

        if (getHousesRequest == null) {
            throw new SdkClientException("Invalid argument passed to marshall(...)");
        }

        try {
        } catch (Exception e) {
            throw new SdkClientException("Unable to marshall request to JSON: " + e.getMessage(), e);
        }
    }

}
