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
public class GetPetsRequest extends com.amazonaws.opensdk.BaseRequest implements Serializable, Cloneable {

    private String page;

    private String type;

    /**
     * @param page
     */

    public void setPage(String page) {
        this.page = page;
    }

    /**
     * @return
     */

    public String getPage() {
        return this.page;
    }

    /**
     * @param page
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public GetPetsRequest page(String page) {
        setPage(page);
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

    public GetPetsRequest type(String type) {
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
        if (getPage() != null)
            sb.append("Page: ").append(getPage()).append(",");
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

        if (obj instanceof GetPetsRequest == false)
            return false;
        GetPetsRequest other = (GetPetsRequest) obj;
        if (other.getPage() == null ^ this.getPage() == null)
            return false;
        if (other.getPage() != null && other.getPage().equals(this.getPage()) == false)
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

        hashCode = prime * hashCode + ((getPage() == null) ? 0 : getPage().hashCode());
        hashCode = prime * hashCode + ((getType() == null) ? 0 : getType().hashCode());
        return hashCode;
    }

    @Override
    public GetPetsRequest clone() {
        return (GetPetsRequest) super.clone();
    }

    /**
     * Set the configuration for this request.
     *
     * @param sdkRequestConfig
     *        Request configuration.
     * @return This object for method chaining.
     */
    public GetPetsRequest sdkRequestConfig(com.amazonaws.opensdk.SdkRequestConfig sdkRequestConfig) {
        super.sdkRequestConfig(sdkRequestConfig);
        return this;
    }

}
