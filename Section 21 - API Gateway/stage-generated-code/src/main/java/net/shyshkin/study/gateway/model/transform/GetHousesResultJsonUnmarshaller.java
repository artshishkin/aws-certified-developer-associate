/**
 * null
 */
package net.shyshkin.study.gateway.model.transform;

import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.fasterxml.jackson.core.JsonToken;
import net.shyshkin.study.gateway.model.GetHousesResult;

import javax.annotation.Generated;

import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

/**
 * GetHousesResult JSON Unmarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class GetHousesResultJsonUnmarshaller implements Unmarshaller<GetHousesResult, JsonUnmarshallerContext> {

    public GetHousesResult unmarshall(JsonUnmarshallerContext context) throws Exception {
        GetHousesResult getHousesResult = new GetHousesResult();

        int originalDepth = context.getCurrentDepth();
        String currentParentElement = context.getCurrentParentElement();
        int targetDepth = originalDepth + 1;

        JsonToken token = context.getCurrentToken();
        if (token == null)
            token = context.nextToken();
        if (token == VALUE_NULL) {
            return getHousesResult;
        }

        while (true) {
            if (token == null)
                break;

            getHousesResult.setEmpty(EmptyJsonUnmarshaller.getInstance().unmarshall(context));
            token = context.nextToken();
        }

        return getHousesResult;
    }

    private static GetHousesResultJsonUnmarshaller instance;

    public static GetHousesResultJsonUnmarshaller getInstance() {
        if (instance == null)
            instance = new GetHousesResultJsonUnmarshaller();
        return instance;
    }
}
