###  Working with DynamoDB locally

####  Docker image

-  Start `dynamodb-local` docker image
    -  `docker run -p 8000:8000 amazon/dynamodb-local`

####  Create Table

-  List existing tables      
    -  `aws dynamodb list-tables --endpoint-url http://localhost:8000`
    -  None
-  [Create a Table](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/getting-started-step-1.html)

```shell script
aws dynamodb create-table `
    --table-name Music `
    --attribute-definitions `
        AttributeName=Artist,AttributeType=S `
        AttributeName=SongTitle,AttributeType=S `
    --key-schema `
        AttributeName=Artist,KeyType=HASH `
        AttributeName=SongTitle,KeyType=RANGE `
--provisioned-throughput `
        ReadCapacityUnits=10,WriteCapacityUnits=5 `
--endpoint-url http://localhost:8000
```  
-  The answer is
```json
{
    "TableDescription": {
        "AttributeDefinitions": [
            {
                "AttributeName": "Artist",
                "AttributeType": "S"
            },
            {
                "AttributeName": "SongTitle",
                "AttributeType": "S"
            }
        ],
        "TableName": "Music",
        "KeySchema": [
            {
                "AttributeName": "Artist",
                "KeyType": "HASH"
            },
            {
                "AttributeName": "SongTitle",
                "KeyType": "RANGE"
            }
        ],
        "TableStatus": "ACTIVE",
        "CreationDateTime": "2021-01-02T14:51:05.988000+02:00",
        "ProvisionedThroughput": {
            "LastIncreaseDateTime": "1970-01-01T02:00:00+02:00",
            "LastDecreaseDateTime": "1970-01-01T02:00:00+02:00",
            "NumberOfDecreasesToday": 0,
            "ReadCapacityUnits": 10,
            "WriteCapacityUnits": 5
        },
        "TableSizeBytes": 0,
        "ItemCount": 0,
        "TableArn": "arn:aws:dynamodb:ddblocal:000000000000:table/Music"
    }
}
```
The same result we can display by typing
-  `aws dynamodb describe-table --table-name Music --endpoint-url http://localhost:8000`
-  filtering rows with `TableStatus` in PowerShell (analog grep in Unix)
-  `aws dynamodb describe-table --table-name Music --endpoint-url http://localhost:8000 | findstr TableStatus`



