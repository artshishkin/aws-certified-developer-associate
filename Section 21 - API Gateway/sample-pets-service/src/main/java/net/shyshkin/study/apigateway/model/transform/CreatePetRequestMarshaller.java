/**
 * null
 */
package net.shyshkin.study.apigateway.model.transform;

import com.amazonaws.SdkClientException;
import com.amazonaws.annotation.SdkInternalApi;
import com.amazonaws.protocol.*;
import net.shyshkin.study.apigateway.model.CreatePetRequest;

import javax.annotation.Generated;

/**
 * CreatePetRequestMarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
@SdkInternalApi
public class CreatePetRequestMarshaller {

    private static final MarshallingInfo<StructuredPojo> NEWPET_BINDING = MarshallingInfo.builder(MarshallingType.STRUCTURED)
            .marshallLocation(MarshallLocation.PAYLOAD).isExplicitPayloadMember(true).build();

    private static final CreatePetRequestMarshaller instance = new CreatePetRequestMarshaller();

    public static CreatePetRequestMarshaller getInstance() {
        return instance;
    }

    /**
     * Marshall the given parameter object.
     */
    public void marshall(CreatePetRequest createPetRequest, ProtocolMarshaller protocolMarshaller) {

        if (createPetRequest == null) {
            throw new SdkClientException("Invalid argument passed to marshall(...)");
        }

        try {
            protocolMarshaller.marshall(createPetRequest.getNewPet(), NEWPET_BINDING);
        } catch (Exception e) {
            throw new SdkClientException("Unable to marshall request to JSON: " + e.getMessage(), e);
        }
    }

}
