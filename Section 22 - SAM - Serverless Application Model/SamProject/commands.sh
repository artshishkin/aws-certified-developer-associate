# Create s3 bucket
aws s3 mb s3://art-code-sam-2021

# Package CloudFormation
aws cloudformation package help
aws cloudformation package --s3-bucket art-code-sam-2021 --template-file template.yaml --output-template-file gen/template-generated.yaml
#sam package... - the same

# Deploy
#aws cloudformation deploy --template-file gen\template-generated.yaml --stack-name hello-world-sam
aws cloudformation deploy --template-file gen\template-generated.yaml --stack-name hello-world-sam --capabilities CAPABILITY_IAM
