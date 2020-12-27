import custom_func as cf
def lambda_handler(event, context):
    cf.cust_fun()
    return {
        'statusCode': 200,
        'body': 'Hello from Lambda Layers!'
    }