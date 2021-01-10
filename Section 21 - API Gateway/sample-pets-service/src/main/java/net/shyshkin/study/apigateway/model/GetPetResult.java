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
public class GetPetResult extends com.amazonaws.opensdk.BaseResult implements Serializable, Cloneable {

    private Pet pet;

    /**
     * @param pet
     */

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    /**
     * @return
     */

    public Pet getPet() {
        return this.pet;
    }

    /**
     * @param pet
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public GetPetResult pet(Pet pet) {
        setPet(pet);
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
        if (getPet() != null)
            sb.append("Pet: ").append(getPet());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (obj instanceof GetPetResult == false)
            return false;
        GetPetResult other = (GetPetResult) obj;
        if (other.getPet() == null ^ this.getPet() == null)
            return false;
        if (other.getPet() != null && other.getPet().equals(this.getPet()) == false)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime * hashCode + ((getPet() == null) ? 0 : getPet().hashCode());
        return hashCode;
    }

    @Override
    public GetPetResult clone() {
        try {
            return (GetPetResult) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Got a CloneNotSupportedException from Object.clone() " + "even though we're Cloneable!", e);
        }
    }

}
