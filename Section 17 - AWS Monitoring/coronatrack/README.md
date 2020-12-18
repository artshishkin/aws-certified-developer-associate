![Spring Boot version][springver]
![Project licence][licence]

# Coronavirus tracker

[springver]: https://img.shields.io/badge/dynamic/xml?label=Spring%20Boot&query=%2F%2A%5Blocal-name%28%29%3D%27project%27%5D%2F%2A%5Blocal-name%28%29%3D%27parent%27%5D%2F%2A%5Blocal-name%28%29%3D%27version%27%5D&url=https%3A%2F%2Fraw.githubusercontent.com%2Fartshishkin%2Fart-spring-core-devops-aws%2Fmaster%2Fpom.xml&logo=Spring&labelColor=white&color=grey
[licence]: https://img.shields.io/github/license/artshishkin/art-spring-core-devops-aws.svg

## Tutorial from Java Brains (Udemy)

### Modified by SHyshkin Artem 

#####  Deploying application into docker container (local net)

######  Steps

1.  Add Fabric8 Maven Plugin
2.  Enabling connect to remote docker daemon
    -  `https://nickjanetakis.com/blog/docker-tip-73-connecting-to-a-remote-docker-daemon`
3.  Creating Docker Image in Fabric 8 
    -  `mvn clean package docker:build`
4.  Pushing to Dockerhub (45)
    -  create dockerhub account
    -  add server to maven `settings.xml`:
    -  encrypt password by using `mvn --encrypt-password`
```xml
<server>
    <id>docker.io</id>
    <username>artarkatesoft</username>
    <password>{your_encrypted_password}</password>
</server>
```
    -  run `mvn clean package docker:build docker:push`

-  Start docker container from Maven command
    -  `mvn clean verify docker:stop docker:build docker:start`

#####  Deploying application into docker container (AWS)

1.  Created new EC2 for Docker
    -  security (ports: 2375, 8080, 80)
2.  Installed Docker and configured
    -  `sudo amazon-linux-extras install docker`
    -  `service docker start`
    -  `usermod -a -G docker ec2-user` - Add the ec2-user to the docker group so you can execute Docker commands without using sudo.
    -  `sudo chkconfig docker on`
    -  `sudo mkdir -p /etc/systemd/system/docker.service.d`
    -  `sudo vi /etc/systemd/system/docker.service.d/options.conf`    
        ```
        # Now make it look like this and save the file when you're done:
        [Service]
        ExecStart=
        ExecStart=/usr/bin/dockerd -H unix:// -H tcp://0.0.0.0:2375
        ```
    -  `sudo systemctl daemon-reload`
    -  `sudo systemctl restart docker`

#####  Logging docker container through docker-maven-plugin

-  [docker-maven-plugin](https://github.com/fabric8io/docker-maven-plugin)
-  [docker:logs](http://dmp.fabric8.io/#docker:logs)
-  `mvn docker:logs -Ddocker.follow`

#####  Making docker container start automatically after EC2 reboot

-  use same as `docker start --restart unless-stopped ...` (not found in docs)
-  use `restartPolicy`
```xml
<restartPolicy>
    <name>always</name>
</restartPolicy>
```
    
#####  Adding ability to debug application using AWS X-Ray

######  Start AWS XRay Daemon

    -  native for Linux, osX or Windows
    -  **or**
    -  start docker image
    
```shell script
docker pull amazon/aws-xray-daemon
```

For Linux

```shell script
docker run \
      --attach STDOUT \
      -v ~/.aws/:/root/.aws/:ro \
      --net=host \
      -e AWS_REGION=eu-west-3 \
      --name xray-daemon \
      -p 2000:2000/udp \
      amazon/aws-xray-daemon -o
```
On Windows Power Shell

```shell script
docker run --attach STDOUT -v C:\Users\Admin\.aws:/root/.aws/:ro -e AWS_REGION=eu-west-3 --name xray-daemon -p 2000:2000/udp amazon/aws-xray-daemon -o
```

######  Configure SDK for XRay

-  [Tracing incoming requests with the X-Ray SDK for Java](https://docs.aws.amazon.com/xray/latest/devguide/xray-sdk-java-filters.html)
-  [AOP with Spring and the X-Ray SDK for Java](https://docs.aws.amazon.com/xray/latest/devguide/xray-sdk-java-aop-spring.html) for AWS XRay.


    