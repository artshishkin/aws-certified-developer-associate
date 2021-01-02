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

####  [Write Data to a Table Using the AWS CLI](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/getting-started-step-2.html)

Put new items
```shell script
aws dynamodb put-item `
  --table-name Music  `
  --item `
    '{\"Artist\": {\"S\": \"No One You Know\"}, \"SongTitle\": {\"S\": \"Call Me Today\"}, \"AlbumTitle\": {\"S\": \"Somewhat Famous\"}, \"Awards\": {\"N\": \"1\"}}' `
  --endpoint-url http://localhost:8000

aws dynamodb put-item `
    --table-name Music `
    --item `
    '{\"Artist\": {\"S\": \"Acme Band\"}, \"SongTitle\": {\"S\": \"Happy Day\"}, \"AlbumTitle\": {\"S\": \"Songs About Life\"}, \"Awards\": {\"N\": \"10\"} }' `
  --endpoint-url http://localhost:8000
```
Scan written values
```shell script
aws dynamodb scan --table-name Music  --endpoint-url http://localhost:8000
```

####  [Read Data from a Table](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/getting-started-step-3.html)

```shell script
aws dynamodb get-item --consistent-read `
    --table-name Music `
    --key '{ \"Artist\": {\"S\": \"Acme Band\"}, \"SongTitle\": {\"S\": \"Happy Day\"}}' `
    --endpoint-url http://localhost:8000
```
The result is
```json
{
    "Item": {
        "Artist": {
            "S": "Acme Band"
        },
        "AlbumTitle": {
            "S": "Songs About Life"
        },
        "Awards": {
            "N": "10"
        },
        "SongTitle": {
            "S": "Happy Day"
        }
    }
}
```

####  [Update Data in a Table](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/getting-started-step-4.html)

```shell script
aws dynamodb update-item `
    --table-name Music `
    --key '{ \"Artist\": {\"S\": \"Acme Band\"}, \"SongTitle\": {\"S\": \"Happy Day\"}}' `
    --update-expression "SET AlbumTitle = :newval" `
    --expression-attribute-values '{ \":newval\":{ \"S\": \"Updated Album Title\"}}' `
    --return-values ALL_NEW `
    --endpoint-url http://localhost:8000
```
Return value is 
```json
{
    "Attributes": {
        "Artist": {
            "S": "Acme Band"
        },
        "Awards": {
            "N": "10"
        },
        "AlbumTitle": {
            "S": "Updated Album Title"
        },
        "SongTitle": {
            "S": "Happy Day"
        }
    }
}
```

####  [Query Data in a Table](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/getting-started-step-5.html)

```shell script
aws dynamodb query `
    --table-name Music `
    --key-condition-expression "Artist = :name" `
    --expression-attribute-values  '{\":name\":{\"S\": \"Acme Band\"}}' `
    --endpoint-url http://localhost:8000 
```

####  [Create a Global Secondary Index](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/getting-started-step-6.html)

```shell script
aws dynamodb update-table `
    --table-name Music `
    --attribute-definitions AttributeName=AlbumTitle,AttributeType=S `
    --global-secondary-index-updates `
    '[{ \"Create\":{\"IndexName\": \"AlbumTitle-index\",\"KeySchema\":[{\"AttributeName\":\"AlbumTitle\",\"KeyType\":\"HASH\"}],\"ProvisionedThroughput\": {\"ReadCapacityUnits\": 10, \"WriteCapacityUnits\": 5      },\"Projection\":{\"ProjectionType\":\"ALL\"}}}]' `
    --endpoint-url http://localhost:8000
```
Result is
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
            },
            {
                "AttributeName": "AlbumTitle",
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
        "TableSizeBytes": 146,
        "ItemCount": 2,
        "TableArn": "arn:aws:dynamodb:ddblocal:000000000000:table/Music",
        "GlobalSecondaryIndexes": [
            {
                "IndexName": "AlbumTitle-index",
                "KeySchema": [
                    {
                        "AttributeName": "AlbumTitle",
                        "KeyType": "HASH"
                    }
                ],
                "Projection": {
                    "ProjectionType": "ALL"
                },
                "IndexStatus": "CREATING",
                "Backfilling": false,
                "ProvisionedThroughput": {
                    "ReadCapacityUnits": 10,
                    "WriteCapacityUnits": 5
                },
                "IndexArn": "arn:aws:dynamodb:ddblocal:000000000000:table/Music/index/AlbumTitle-index"
            }
        ]
    }
}
```

####  [Query the Global Secondary Index](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/getting-started-step-7.html)

```shell script
aws dynamodb query `
    --table-name Music `
    --index-name AlbumTitle-index `
    --key-condition-expression "AlbumTitle = :name" `
    --expression-attribute-values  '{\":name\":{\"S\": \"Somewhat Famous\"}}' `
    --endpoint-url http://localhost:8000
```
Response is
```json
{
    "Items": [
        {
            "Artist": {
                "S": "No One You Know"
            },
            "AlbumTitle": {
                "S": "Somewhat Famous"
            },
            "Awards": {
                "N": "1"
            },
            "SongTitle": {
                "S": "Call Me Today"
            }
        }
    ],
    "Count": 1,
    "ScannedCount": 1,
    "ConsumedCapacity": null
}
```