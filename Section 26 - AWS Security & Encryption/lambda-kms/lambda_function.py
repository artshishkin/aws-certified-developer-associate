import json
import boto3
import os

from base64 import b64decode

ENCRYPTED = os.environ['DB_PASSWORD']
# Decrypt code should run once and variables stored outside of the function
# handler so that these are decrypted once per container
DECRYPTED = boto3.client('kms').decrypt(
    CiphertextBlob=b64decode(ENCRYPTED),
    EncryptionContext={'LambdaFunctionName': os.environ['AWS_LAMBDA_FUNCTION_NAME']}
)['Plaintext'].decode('utf-8')

def lambda_handler(event, context):
    print(ENCRYPTED)
    print(DECRYPTED)
    # TODO implement
    return {
        'statusCode': 200,
        'body': json.dumps(DECRYPTED)
    }
