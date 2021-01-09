/**
 * null
 */
package net.shyshkin.study.gateway.model.transform;

import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.fasterxml.jackson.core.JsonToken;
import net.shyshkin.study.gateway.model.GetStagevariablesResult;

import javax.annotation.Generated;

import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

/**
 * GetStagevariablesResult JSON Unmarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class GetStagevariablesResultJsonUnmarshaller implements Unmarshaller<GetStagevariablesResult, JsonUnmarshallerContext> {

    public GetStagevariablesResult unmarshall(JsonUnmarshallerContext context) throws Exception {
        GetStagevariablesResult getStagevariablesResult = new GetStagevariablesResult();

        int originalDepth = context.getCurrentDepth();
        String currentParentElement = context.getCurrentParentElement();
        int targetDepth = originalDepth + 1;

        JsonToken token = context.getCurrentToken();
        if (token == null)
            token = context.nextToken();
        if (token == VALUE_NULL) {
            return getStagevariablesResult;
        }

        while (true) {
            if (token == null)
                break;

            getStagevariablesResult.setEmpty(EmptyJsonUnmarshaller.getInstance().unmarshall(context));
            token = context.nextToken();
        }

        return getStagevariablesResult;
    }

    private static GetStagevariablesResultJsonUnmarshaller instance;

    public static GetStagevariablesResultJsonUnmarshaller getInstance() {
        if (instance == null)
            instance = new GetStagevariablesResultJsonUnmarshaller();
        return instance;
    }
}
