# associate with existing log group
aws logs associate-kms-key --log-group-name /aws/lambda/hello-world --kms-key-id arn:aws:kms:eu-north-1:392971033516:key/09736498-ff70-4181-997b-8a3714d20100 --region eu-north-1

# create new log group
aws logs create-log-group --log-group-name /example-encrypted --kms-key-id arn:aws:kms:eu-north-1:392971033516:key/09736498-ff70-4181-997b-8a3714d20100 --region eu-north-1
