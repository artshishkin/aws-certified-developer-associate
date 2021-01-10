/**
 * null
 */
package net.shyshkin.study.apigateway.model.transform;

import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.fasterxml.jackson.core.JsonToken;
import net.shyshkin.study.apigateway.model.NewPet;

import javax.annotation.Generated;

import static com.fasterxml.jackson.core.JsonToken.*;

/**
 * NewPet JSON Unmarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class NewPetJsonUnmarshaller implements Unmarshaller<NewPet, JsonUnmarshallerContext> {

    public NewPet unmarshall(JsonUnmarshallerContext context) throws Exception {
        NewPet newPet = new NewPet();

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
                if (context.testExpression("price", targetDepth)) {
                    context.nextToken();
                    newPet.setPrice(context.getUnmarshaller(Double.class).unmarshall(context));
                }
                if (context.testExpression("type", targetDepth)) {
                    context.nextToken();
                    newPet.setType(context.getUnmarshaller(String.class).unmarshall(context));
                }
            } else if (token == END_ARRAY || token == END_OBJECT) {
                if (context.getLastParsedParentElement() == null || context.getLastParsedParentElement().equals(currentParentElement)) {
                    if (context.getCurrentDepth() <= originalDepth)
                        break;
                }
            }
            token = context.nextToken();
        }

        return newPet;
    }

    private static NewPetJsonUnmarshaller instance;

    public static NewPetJsonUnmarshaller getInstance() {
        if (instance == null)
            instance = new NewPetJsonUnmarshaller();
        return instance;
    }
}
