/**
 * null
 */
package net.shyshkin.study.apigateway.model.transform;

import com.amazonaws.SdkClientException;
import com.amazonaws.annotation.SdkInternalApi;
import com.amazonaws.protocol.*;
import net.shyshkin.study.apigateway.model.NewPetResponse;

import javax.annotation.Generated;

/**
 * NewPetResponseMarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
@SdkInternalApi
public class NewPetResponseMarshaller {

    private static final MarshallingInfo<String> MESSAGE_BINDING = MarshallingInfo.builder(MarshallingType.STRING).marshallLocation(MarshallLocation.PAYLOAD)
            .marshallLocationName("message").build();
    private static final MarshallingInfo<StructuredPojo> PET_BINDING = MarshallingInfo.builder(MarshallingType.STRUCTURED)
            .marshallLocation(MarshallLocation.PAYLOAD).marshallLocationName("pet").build();

    private static final NewPetResponseMarshaller instance = new NewPetResponseMarshaller();

    public static NewPetResponseMarshaller getInstance() {
        return instance;
    }

    /**
     * Marshall the given parameter object.
     */
    public void marshall(NewPetResponse newPetResponse, ProtocolMarshaller protocolMarshaller) {

        if (newPetResponse == null) {
            throw new SdkClientException("Invalid argument passed to marshall(...)");
        }

        try {
            protocolMarshaller.marshall(newPetResponse.getMessage(), MESSAGE_BINDING);
            protocolMarshaller.marshall(newPetResponse.getPet(), PET_BINDING);
        } catch (Exception e) {
            throw new SdkClientException("Unable to marshall request to JSON: " + e.getMessage(), e);
        }
    }

}
