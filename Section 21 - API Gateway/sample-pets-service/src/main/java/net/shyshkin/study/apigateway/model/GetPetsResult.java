/**
 * null
 */
package net.shyshkin.study.apigateway.model;

import javax.annotation.Generated;
import java.io.Serializable;

/**
 * 
 * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/woe2mm08tl-2021-01-10T09:37:54Z/GetPets" target="_top">AWS API
 *      Documentation</a>
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class GetPetsResult extends com.amazonaws.opensdk.BaseResult implements Serializable, Cloneable {

    private java.util.List<Pet> pets;

    /**
     * @return
     */

    public java.util.List<Pet> getPets() {
        return pets;
    }

    /**
     * @param pets
     */

    public void setPets(java.util.Collection<Pet> pets) {
        if (pets == null) {
            this.pets = null;
            return;
        }

        this.pets = new java.util.ArrayList<Pet>(pets);
    }

    /**
     * <p>
     * <b>NOTE:</b> This method appends the values to the existing list (if any). Use
     * {@link #setPets(java.util.Collection)} or {@link #withPets(java.util.Collection)} if you want to override the
     * existing values.
     * </p>
     * 
     * @param pets
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public GetPetsResult pets(Pet... pets) {
        if (this.pets == null) {
            setPets(new java.util.ArrayList<Pet>(pets.length));
        }
        for (Pet ele : pets) {
            this.pets.add(ele);
        }
        return this;
    }

    /**
     * @param pets
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public GetPetsResult pets(java.util.Collection<Pet> pets) {
        setPets(pets);
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
        if (getPets() != null)
            sb.append("Pets: ").append(getPets());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (obj instanceof GetPetsResult == false)
            return false;
        GetPetsResult other = (GetPetsResult) obj;
        if (other.getPets() == null ^ this.getPets() == null)
            return false;
        if (other.getPets() != null && other.getPets().equals(this.getPets()) == false)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime * hashCode + ((getPets() == null) ? 0 : getPets().hashCode());
        return hashCode;
    }

    @Override
    public GetPetsResult clone() {
        try {
            return (GetPetsResult) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Got a CloneNotSupportedException from Object.clone() " + "even though we're Cloneable!", e);
        }
    }

}
