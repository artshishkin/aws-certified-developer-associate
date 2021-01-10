/**
 * null
 */
package net.shyshkin.study.apigateway.model.transform;

import com.amazonaws.SdkClientException;
import com.amazonaws.annotation.SdkInternalApi;
import com.amazonaws.protocol.MarshallLocation;
import com.amazonaws.protocol.MarshallingInfo;
import com.amazonaws.protocol.MarshallingType;
import com.amazonaws.protocol.ProtocolMarshaller;
import net.shyshkin.study.apigateway.model.GetPetsRequest;

import javax.annotation.Generated;

/**
 * GetPetsRequestMarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
@SdkInternalApi
public class GetPetsRequestMarshaller {

    private static final MarshallingInfo<String> PAGE_BINDING = MarshallingInfo.builder(MarshallingType.STRING).marshallLocation(MarshallLocation.QUERY_PARAM)
            .marshallLocationName("page").build();
    private static final MarshallingInfo<String> TYPE_BINDING = MarshallingInfo.builder(MarshallingType.STRING).marshallLocation(MarshallLocation.QUERY_PARAM)
            .marshallLocationName("type").build();

    private static final GetPetsRequestMarshaller instance = new GetPetsRequestMarshaller();

    public static GetPetsRequestMarshaller getInstance() {
        return instance;
    }

    /**
     * Marshall the given parameter object.
     */
    public void marshall(GetPetsRequest getPetsRequest, ProtocolMarshaller protocolMarshaller) {

        if (getPetsRequest == null) {
            throw new SdkClientException("Invalid argument passed to marshall(...)");
        }

        try {
            protocolMarshaller.marshall(getPetsRequest.getPage(), PAGE_BINDING);
            protocolMarshaller.marshall(getPetsRequest.getType(), TYPE_BINDING);
        } catch (Exception e) {
            throw new SdkClientException("Unable to marshall request to JSON: " + e.getMessage(), e);
        }
    }

}
