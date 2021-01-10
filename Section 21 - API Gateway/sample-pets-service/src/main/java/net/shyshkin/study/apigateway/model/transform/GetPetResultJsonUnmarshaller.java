/**
 * null
 */
package net.shyshkin.study.apigateway.model.transform;

import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.fasterxml.jackson.core.JsonToken;
import net.shyshkin.study.apigateway.model.GetPetResult;

import javax.annotation.Generated;

import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

/**
 * GetPetResult JSON Unmarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class GetPetResultJsonUnmarshaller implements Unmarshaller<GetPetResult, JsonUnmarshallerContext> {

    public GetPetResult unmarshall(JsonUnmarshallerContext context) throws Exception {
        GetPetResult getPetResult = new GetPetResult();

        int originalDepth = context.getCurrentDepth();
        String currentParentElement = context.getCurrentParentElement();
        int targetDepth = originalDepth + 1;

        JsonToken token = context.getCurrentToken();
        if (token == null)
            token = context.nextToken();
        if (token == VALUE_NULL) {
            return getPetResult;
        }

        while (true) {
            if (token == null)
                break;

            getPetResult.setPet(PetJsonUnmarshaller.getInstance().unmarshall(context));
            token = context.nextToken();
        }

        return getPetResult;
    }

    private static GetPetResultJsonUnmarshaller instance;

    public static GetPetResultJsonUnmarshaller getInstance() {
        if (instance == null)
            instance = new GetPetResultJsonUnmarshaller();
        return instance;
    }
}
