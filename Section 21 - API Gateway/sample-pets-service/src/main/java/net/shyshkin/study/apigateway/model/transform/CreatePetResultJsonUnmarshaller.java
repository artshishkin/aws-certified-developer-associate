/**
 * null
 */
package net.shyshkin.study.apigateway.model.transform;

import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.fasterxml.jackson.core.JsonToken;
import net.shyshkin.study.apigateway.model.CreatePetResult;

import javax.annotation.Generated;

import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

/**
 * CreatePetResult JSON Unmarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class CreatePetResultJsonUnmarshaller implements Unmarshaller<CreatePetResult, JsonUnmarshallerContext> {

    public CreatePetResult unmarshall(JsonUnmarshallerContext context) throws Exception {
        CreatePetResult createPetResult = new CreatePetResult();

        int originalDepth = context.getCurrentDepth();
        String currentParentElement = context.getCurrentParentElement();
        int targetDepth = originalDepth + 1;

        JsonToken token = context.getCurrentToken();
        if (token == null)
            token = context.nextToken();
        if (token == VALUE_NULL) {
            return createPetResult;
        }

        while (true) {
            if (token == null)
                break;

            createPetResult.setNewPetResponse(NewPetResponseJsonUnmarshaller.getInstance().unmarshall(context));
            token = context.nextToken();
        }

        return createPetResult;
    }

    private static CreatePetResultJsonUnmarshaller instance;

    public static CreatePetResultJsonUnmarshaller getInstance() {
        if (instance == null)
            instance = new CreatePetResultJsonUnmarshaller();
        return instance;
    }
}
