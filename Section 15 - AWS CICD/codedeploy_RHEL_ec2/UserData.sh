#!/bin/bash

# Installing CodeDeploy Agent
yum update -y
yum install ruby -y

yum install wget -y

# Download the agent (replace the region)
wget https://aws-codedeploy-eu-west-3.s3.eu-west-3.amazonaws.com/latest/install
chmod +x ./install    #make it executable
./install auto   #install