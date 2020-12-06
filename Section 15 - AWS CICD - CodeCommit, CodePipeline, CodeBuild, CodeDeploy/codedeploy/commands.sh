#!/bin/bash

# Installing CodeDeploy Agent
sudo yum update -y
sudo yum install ruby -y

# Download the agent (replace the region)
wget https://aws-codedeploy-eu-west-3.s3.eu-west-3.amazonaws.com/latest/install
chmod +x ./install    #make it executable
sudo ./install auto   #install
sudo service codedeploy-agent status    #test agent is running