/**
 * null
 */
package net.shyshkin.study.apigateway.model;

import javax.annotation.Generated;
import java.io.Serializable;

/**
 * 
 * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/woe2mm08tl-2021-01-10T09:37:54Z/CreatePet" target="_top">AWS API
 *      Documentation</a>
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class CreatePetRequest extends com.amazonaws.opensdk.BaseRequest implements Serializable, Cloneable {

    private NewPet newPet;

    /**
     * @param newPet
     */

    public void setNewPet(NewPet newPet) {
        this.newPet = newPet;
    }

    /**
     * @return
     */

    public NewPet getNewPet() {
        return this.newPet;
    }

    /**
     * @param newPet
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public CreatePetRequest newPet(NewPet newPet) {
        setNewPet(newPet);
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
        if (getNewPet() != null)
            sb.append("NewPet: ").append(getNewPet());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (obj instanceof CreatePetRequest == false)
            return false;
        CreatePetRequest other = (CreatePetRequest) obj;
        if (other.getNewPet() == null ^ this.getNewPet() == null)
            return false;
        if (other.getNewPet() != null && other.getNewPet().equals(this.getNewPet()) == false)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime * hashCode + ((getNewPet() == null) ? 0 : getNewPet().hashCode());
        return hashCode;
    }

    @Override
    public CreatePetRequest clone() {
        return (CreatePetRequest) super.clone();
    }

    /**
     * Set the configuration for this request.
     *
     * @param sdkRequestConfig
     *        Request configuration.
     * @return This object for method chaining.
     */
    public CreatePetRequest sdkRequestConfig(com.amazonaws.opensdk.SdkRequestConfig sdkRequestConfig) {
        super.sdkRequestConfig(sdkRequestConfig);
        return this;
    }

}
