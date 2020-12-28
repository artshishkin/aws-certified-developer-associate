##  Creating Java Lambda

[AWS Lambda With Java](https://www.baeldung.com/java-aws-lambda)

1.  Custom MethodHandler
    -  `net.shyshkin.study.lambda.LambdaMethodHandler.handleRequest(String input, Context context)`
2.  RequestHandler Interface
    -  see `LambdaRequestHandler` class
3.  RequestStreamHandler Interface
    -  see `LambdaRequestStreamHandler` class

Build deployment file:
-  ~~mvn clean package shade:shade~~            
-  `mvn clean package`            

###  Here are the steps required to create our lambda:

1.  “Select blueprint” and then select “Blank Function”
2.  “Configure triggers” (in our case we don't have any triggers or events)
3.  “Configure function”:
    -  Name: Provide MethodHandlerLambda,
    -  Description: Anything that describes our lambda function
    -  Runtime: Select `java11`
    -  Code Entry Type and Function Package: Select “Upload a .ZIP and Jar file” and click on “Upload” button. Select the file which contains lambda code.
    -  Under Lambda function handler and role:
        -  Handler name: Provide lambda function handler name `net.shyshkin.study.lambda.MethodHandlerLambda::handleRequest`
        -  Role name: If any other AWS resources are used in lambda function, then provide access by creating/using existing role and also define the policy template.
    -  Under Advanced settings:
        -  Memory: Provide memory that will be used by our lambda function.
        -  Timeout: Select a time for execution of lambda function for each request.
4.  Once you are done with all inputs, click “Next” which will show you to review the configuration.
6.  Once a review is completed, click on “Create Function”.

###  Invoke the Function

Once the AWS lambda function is created, we'll test it by passing in some data:
-  Click on your lambda function from lists and then click on “Test” button
-  A popup window will appear which contains dummy value for sending data. Override the data with “Art”
-  Click on “Save and test” button
On the screen, you can see the Execution result section with successfully returned output as:

"Hello World - Art"

##### [Creating Java Lambda](https://github.com/artshishkin/aws-certified-developer-associate/blob/main/java-lambda-hello-world/README.md)