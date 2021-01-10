/**
 * null
 */
package net.shyshkin.study.apigateway;

import net.shyshkin.study.apigateway.model.*;

import javax.annotation.Generated;

/**
 * Interface for accessing SamplePetsService.
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public interface SamplePetsService {

    /**
     * @param createPetRequest
     * @return Result of the CreatePet operation returned by the service.
     * @sample SamplePetsService.CreatePet
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/woe2mm08tl-2021-01-10T09:37:54Z/CreatePet" target="_top">AWS
     *      API Documentation</a>
     */
    CreatePetResult createPet(CreatePetRequest createPetRequest);

    /**
     * @param getApiRootRequest
     * @return Result of the GetApiRoot operation returned by the service.
     * @sample SamplePetsService.GetApiRoot
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/woe2mm08tl-2021-01-10T09:37:54Z/GetApiRoot"
     *      target="_top">AWS API Documentation</a>
     */
    GetApiRootResult getApiRoot(GetApiRootRequest getApiRootRequest);

    /**
     * @param getPetRequest
     * @return Result of the GetPet operation returned by the service.
     * @sample SamplePetsService.GetPet
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/woe2mm08tl-2021-01-10T09:37:54Z/GetPet" target="_top">AWS
     *      API Documentation</a>
     */
    GetPetResult getPet(GetPetRequest getPetRequest);

    /**
     * @param getPetsRequest
     * @return Result of the GetPets operation returned by the service.
     * @sample SamplePetsService.GetPets
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/woe2mm08tl-2021-01-10T09:37:54Z/GetPets" target="_top">AWS
     *      API Documentation</a>
     */
    GetPetsResult getPets(GetPetsRequest getPetsRequest);

    /**
     * @return Create new instance of builder with all defaults set.
     */
    public static SamplePetsServiceClientBuilder builder() {
        return new SamplePetsServiceClientBuilder();
    }

    /**
     * Shuts down this client object, releasing any resources that might be held open. This is an optional method, and
     * callers are not expected to call it, but can if they want to explicitly release any open resources. Once a client
     * has been shutdown, it should not be used to make any more requests.
     */
    void shutdown();

}
