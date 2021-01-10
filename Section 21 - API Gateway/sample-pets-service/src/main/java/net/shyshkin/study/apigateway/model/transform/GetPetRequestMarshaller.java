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
import net.shyshkin.study.apigateway.model.GetPetRequest;

import javax.annotation.Generated;

/**
 * GetPetRequestMarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
@SdkInternalApi
public class GetPetRequestMarshaller {

    private static final MarshallingInfo<String> PETID_BINDING = MarshallingInfo.builder(MarshallingType.STRING).marshallLocation(MarshallLocation.PATH)
            .marshallLocationName("petId").build();

    private static final GetPetRequestMarshaller instance = new GetPetRequestMarshaller();

    public static GetPetRequestMarshaller getInstance() {
        return instance;
    }

    /**
     * Marshall the given parameter object.
     */
    public void marshall(GetPetRequest getPetRequest, ProtocolMarshaller protocolMarshaller) {

        if (getPetRequest == null) {
            throw new SdkClientException("Invalid argument passed to marshall(...)");
        }

        try {
            protocolMarshaller.marshall(getPetRequest.getPetId(), PETID_BINDING);
        } catch (Exception e) {
            throw new SdkClientException("Unable to marshall request to JSON: " + e.getMessage(), e);
        }
    }

}
