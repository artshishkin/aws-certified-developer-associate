import json
import boto3
import os

profile = os.environ['PROFILE']
ssm=boto3.client("ssm",region_name="eu-north-1")

def lambda_handler(event, context):
    db_url=ssm.get_parameters(Names=["/my-app/"+profile+"/db-url"])
    print(db_url)
    db_password=ssm.get_parameters(Names=["/my-app/"+profile+"/db-password"],WithDecryption=True)
    print(db_password)
    return "Worked"