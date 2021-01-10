/**
 * null
 */
package net.shyshkin.study.apigateway.model;

import javax.annotation.Generated;
import java.io.Serializable;

/**
 * 
 * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/woe2mm08tl-2021-01-10T09:37:54Z/GetPet" target="_top">AWS API
 *      Documentation</a>
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class GetPetRequest extends com.amazonaws.opensdk.BaseRequest implements Serializable, Cloneable {

    private String petId;

    /**
     * @param petId
     */

    public void setPetId(String petId) {
        this.petId = petId;
    }

    /**
     * @return
     */

    public String getPetId() {
        return this.petId;
    }

    /**
     * @param petId
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public GetPetRequest petId(String petId) {
        setPetId(petId);
        return this;
    }

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
        if (getPetId() != null)
            sb.append("PetId: ").append(getPetId());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (obj instanceof GetPetRequest == false)
            return false;
        GetPetRequest other = (GetPetRequest) obj;
        if (other.getPetId() == null ^ this.getPetId() == null)
            return false;
        if (other.getPetId() != null && other.getPetId().equals(this.getPetId()) == false)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime * hashCode + ((getPetId() == null) ? 0 : getPetId().hashCode());
        return hashCode;
    }

    @Override
    public GetPetRequest clone() {
        return (GetPetRequest) super.clone();
    }

    /**
     * Set the configuration for this request.
     *
     * @param sdkRequestConfig
     *        Request configuration.
     * @return This object for method chaining.
     */
    public GetPetRequest sdkRequestConfig(com.amazonaws.opensdk.SdkRequestConfig sdkRequestConfig) {
        super.sdkRequestConfig(sdkRequestConfig);
        return this;
    }

}
