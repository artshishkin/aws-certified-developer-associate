/**
 * null
 */
package net.shyshkin.study.apigateway.model.transform;

import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.fasterxml.jackson.core.JsonToken;
import net.shyshkin.study.apigateway.model.NewPetResponse;

import javax.annotation.Generated;

import static com.fasterxml.jackson.core.JsonToken.*;

/**
 * NewPetResponse JSON Unmarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class NewPetResponseJsonUnmarshaller implements Unmarshaller<NewPetResponse, JsonUnmarshallerContext> {

    public NewPetResponse unmarshall(JsonUnmarshallerContext context) throws Exception {
        NewPetResponse newPetResponse = new NewPetResponse();

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
                if (context.testExpression("message", targetDepth)) {
                    context.nextToken();
                    newPetResponse.setMessage(context.getUnmarshaller(String.class).unmarshall(context));
                }
                if (context.testExpression("pet", targetDepth)) {
                    context.nextToken();
                    newPetResponse.setPet(PetJsonUnmarshaller.getInstance().unmarshall(context));
                }
            } else if (token == END_ARRAY || token == END_OBJECT) {
                if (context.getLastParsedParentElement() == null || context.getLastParsedParentElement().equals(currentParentElement)) {
                    if (context.getCurrentDepth() <= originalDepth)
                        break;
                }
            }
            token = context.nextToken();
        }

        return newPetResponse;
    }

    private static NewPetResponseJsonUnmarshaller instance;

    public static NewPetResponseJsonUnmarshaller getInstance() {
        if (instance == null)
            instance = new NewPetResponseJsonUnmarshaller();
        return instance;
    }
}
