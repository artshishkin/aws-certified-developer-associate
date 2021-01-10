/**
 * null
 */
package net.shyshkin.study.apigateway.model.transform;

import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import net.shyshkin.study.apigateway.model.GetApiRootResult;

import javax.annotation.Generated;

/**
 * GetApiRootResult JSON Unmarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class GetApiRootResultJsonUnmarshaller implements Unmarshaller<GetApiRootResult, JsonUnmarshallerContext> {

    public GetApiRootResult unmarshall(JsonUnmarshallerContext context) throws Exception {
        GetApiRootResult getApiRootResult = new GetApiRootResult();

        return getApiRootResult;
    }

    private static GetApiRootResultJsonUnmarshaller instance;

    public static GetApiRootResultJsonUnmarshaller getInstance() {
        if (instance == null)
            instance = new GetApiRootResultJsonUnmarshaller();
        return instance;
    }
}
