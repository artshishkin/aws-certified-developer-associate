/**
 * null
 */
package net.shyshkin.study.gateway.model.transform;

import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.fasterxml.jackson.core.JsonToken;
import net.shyshkin.study.gateway.model.GetApiRootResult;

import javax.annotation.Generated;

import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

/**
 * GetApiRootResult JSON Unmarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class GetApiRootResultJsonUnmarshaller implements Unmarshaller<GetApiRootResult, JsonUnmarshallerContext> {

    public GetApiRootResult unmarshall(JsonUnmarshallerContext context) throws Exception {
        GetApiRootResult getApiRootResult = new GetApiRootResult();

        int originalDepth = context.getCurrentDepth();
        String currentParentElement = context.getCurrentParentElement();
        int targetDepth = originalDepth + 1;

        JsonToken token = context.getCurrentToken();
        if (token == null)
            token = context.nextToken();
        if (token == VALUE_NULL) {
            return getApiRootResult;
        }

        while (true) {
            if (token == null)
                break;

            getApiRootResult.setEmpty(EmptyJsonUnmarshaller.getInstance().unmarshall(context));
            token = context.nextToken();
        }

        return getApiRootResult;
    }

    private static GetApiRootResultJsonUnmarshaller instance;

    public static GetApiRootResultJsonUnmarshaller getInstance() {
        if (instance == null)
            instance = new GetApiRootResultJsonUnmarshaller();
        return instance;
    }
}
