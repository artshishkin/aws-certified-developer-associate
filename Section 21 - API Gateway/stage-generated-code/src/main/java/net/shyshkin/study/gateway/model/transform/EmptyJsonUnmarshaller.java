/**
 * null
 */
package net.shyshkin.study.gateway.model.transform;

import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.fasterxml.jackson.core.JsonToken;
import net.shyshkin.study.gateway.model.Empty;

import javax.annotation.Generated;

import static com.fasterxml.jackson.core.JsonToken.*;

/**
 * Empty JSON Unmarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class EmptyJsonUnmarshaller implements Unmarshaller<Empty, JsonUnmarshallerContext> {

    public Empty unmarshall(JsonUnmarshallerContext context) throws Exception {
        Empty empty = new Empty();

        int originalDepth = context.getCurrentDepth();
        String currentParentElement = context.getCurrentParentElement();
        int targetDepth = originalDepth + 1;

        JsonToken token = context.getCurrentToken();
        if (token == null)
            token = context.nextToken();
        if (token == VALUE_NULL) {
            return null;
        }

        while (true) {
            if (token == null)
                break;

            if (token == FIELD_NAME || token == START_OBJECT) {
            } else if (token == END_ARRAY || token == END_OBJECT) {
                if (context.getLastParsedParentElement() == null || context.getLastParsedParentElement().equals(currentParentElement)) {
                    if (context.getCurrentDepth() <= originalDepth)
                        break;
                }
            }
            token = context.nextToken();
        }

        return empty;
    }

    private static EmptyJsonUnmarshaller instance;

    public static EmptyJsonUnmarshaller getInstance() {
        if (instance == null)
            instance = new EmptyJsonUnmarshaller();
        return instance;
    }
}
