/**
 * null
 */
package net.shyshkin.study.apigateway.model;

import com.amazonaws.protocol.ProtocolMarshaller;
import com.amazonaws.protocol.StructuredPojo;

import javax.annotation.Generated;
import java.io.Serializable;

/**
 * 
 * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/woe2mm08tl-2021-01-10T09:37:54Z/Pet" target="_top">AWS API
 *      Documentation</a>
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class Pet implements Serializable, Cloneable, StructuredPojo {

    private Integer id;

    private Double price;

    private String type;

    /**
     * @param id
     */

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return
     */

    public Integer getId() {
        return this.id;
    }

    /**
     * @param id
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public Pet id(Integer id) {
        setId(id);
        return this;
    }

    /**
     * @param price
     */

    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return
     */

    public Double getPrice() {
        return this.price;
    }

    /**
     * @param price
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public Pet price(Double price) {
        setPrice(price);
        return this;
    }

    /**
     * @param type
     */

    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return
     */

    public String getType() {
        return this.type;
    }

    /**
     * @param type
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public Pet type(String type) {
        setType(type);
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
        if (getId() != null)
            sb.append("Id: ").append(getId()).append(",");
        if (getPrice() != null)
            sb.append("Price: ").append(getPrice()).append(",");
        if (getType() != null)
            sb.append("Type: ").append(getType());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (obj instanceof Pet == false)
            return false;
        Pet other = (Pet) obj;
        if (other.getId() == null ^ this.getId() == null)
            return false;
        if (other.getId() != null && other.getId().equals(this.getId()) == false)
            return false;
        if (other.getPrice() == null ^ this.getPrice() == null)
            return false;
        if (other.getPrice() != null && other.getPrice().equals(this.getPrice()) == false)
            return false;
        if (other.getType() == null ^ this.getType() == null)
            return false;
        if (other.getType() != null && other.getType().equals(this.getType()) == false)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime * hashCode + ((getId() == null) ? 0 : getId().hashCode());
        hashCode = prime * hashCode + ((getPrice() == null) ? 0 : getPrice().hashCode());
        hashCode = prime * hashCode + ((getType() == null) ? 0 : getType().hashCode());
        return hashCode;
    }

    @Override
    public Pet clone() {
        try {
            return (Pet) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Got a CloneNotSupportedException from Object.clone() " + "even though we're Cloneable!", e);
        }
    }

    @com.amazonaws.annotation.SdkInternalApi
    @Override
    public void marshall(ProtocolMarshaller protocolMarshaller) {
        net.shyshkin.study.apigateway.model.transform.PetMarshaller.getInstance().marshall(this, protocolMarshaller);
    }
}
