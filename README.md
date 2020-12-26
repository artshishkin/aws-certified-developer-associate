# aws-certified-developer-associate
AWS Certified Developer Associate - Tutorial by Stephane Maarek (Udemy)

##### 5. AWS Budget Setup

-  Billing dashboard ->
-  Budgets ->
-  Create budget ->
-  Cost budget ->
    -  Name: Learning AWS
    -  Budgeted amount: $20
    -  Configure thresholds
    -  Set email
-  Confirm -> Create
 
#####  11. AWS Regions and AZs

-  [Global Infrastructure](https://aws.amazon.com/about-aws/global-infrastructure/)
-  Overview
-  Regions and AZs
-  AWS Regional Services ->
    -  Region Table
    
#####  12. IAM Introduction

#####  13. IAM Hands-On

-  IAM - it's a Global Service
-  Multi-factor authentication (MFA)
    -  Activate MFA
    -  Virtual -> Google Auth
-  Users -> Add User
    -  Programmatic access - `true`
    -  AWS Management Console access - `true`
    -  Next: Set permissions ->
    -  Attach existing policies directly
    -  AdministratorAccess
    -  Next: Review ->
    -  Create User
    -  **Download CSV file with credentials**
    -  Close
-  Groups -> New Group
    -  Name: admin
    -  Next ->
    -  Attach policy: AdministratorAccess 
    -  Create group
-  IAM->Groups->admin
    -  Add Users to Group
    -  add `art-admin`
-  Go to users
    -  remove `AdministratorAccess` from `Attached directly` as it is already in groups `Attached permissions`
-  Apply an IAM Password Policy
    -  Allow users to change their own password
    -  Enable password expiration (90 days)
-  Dashboard
    -  Sign-in URL for IAM users in this account->
    -  Customize : `artarkatesoft`
    -  copy link
    -  login
    -  change password

#####  15. EC2 Introduction

-  EC2
    -  Launch Instance
    -  AMI - Amazon Machine Image
        -  `Amazon Linux 2`
    -  Step 5: Add Tags
        -  Name: My Instance From Stephane Tutorial
    -  Step 6: Configure Security Group
        -  Security group name: aws-tutorial-first-ec2
        -  Description: Created from my first EC2
    -  Launch
    -  Create a new key pair

#####  17. How to SSH using Linux or Mac

-  `ssh -i "certified-dev-assoc-course.pem" ec2-user@ec2-13-48-23-85.eu-north-1.compute.amazonaws.com`
-  `chmod 0400`  

#####  18. How to SSH using Windows

-  `Putty`
    -  use `PuttyGen` to convert .rem private key to .ppk
    -  use .ppk

#####  19. How to SSH using Windows 10

-  sometimes it is need to configure access (like chmod on Linux)        

#####  21. EC2 Instance Connect

#####  22. Introduction to Security Groups

#####  23. Security Groups Deep Dive

-  Security group can be attached to multiple instances (many-to-many)
-  Locked down to a region/VPC combination
-  **EC2 with same security group can access this EC2 no matter what IP it has** (??? Does not work with RDS ???)
-  Referencing other security group diagram 

#####  24. Private vs Public vs Elastic IP

#####  25. Private vs Public vs Elastic IP Hands On

-  Elastic IP
    -  Allocate
    -  Associate
    -  Test it
    -  Disassociate
    -  Release

#####  26. Install Apache on EC2

-  `sudo su`
-  `yum update -y` - **-y** - do not prompt
-  `yum install -y httpd.x86_64`
-  `systemctl start httpd.service`
-  `systemctl enable httpd.service` - starts after reboot
-  `curl localhost:80`
-  `publicIP:80` - wait,wait,wait,wait,wait,wait, - timeout **!CONFIGURE SECURITY GROUP!**
-  `echo "Hello World" > /var/www/html/index.html`
-  `hostname -f` - name of machine
-  `echo "Hello World $(hostname -f)" > /var/www/html/index.html`

#####  27. EC2 User Data

-  script **only runs ONCE** at the instance **FIRST start**
-  the EC2 User Data script runs with the **root** user
-  hands on:
    -  terminate old instance
    -  launch new instance ->
    -  security group: use existing
    -  Configure Instance Details ->
    -  Advanced Details -> 
    -  User Data
    -  [Specify instance user data at launch](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/user-data.html)

```shell script
#!/bin/bash
yum update -y
yum install -y httpd
systemctl start httpd
systemctl enable httpd
echo "Hello World $(hostname -f)" > /var/www/html/index.html
``` 

#####  28. EC2 Instances Launch Types

-  On Demand Instances
-  Reserved: (minimum 1 year)
    -  Reserved instances
    -  Convertible Reserved instances
    -  Scheduled Reserved instances
-  Spot Instances
-  Dedicated Instances
-  Dedicated Hosts

#####  29. EC2 Instances Launch Types Hands On 

#####  30. EC2 Elastic Network Interfaces

-  create EC2
    -  Number of instances: 2
    -  Subnet: choose ONE (1b for example)
    -  Review and Launch
    -  Look at network interface
- create new Elastic Network Interface
    -  Description: My secondary interface
    -  Subnet: same (1b)
    -  IPv4 Private IP: Auto-assign
    -  Security group: the same
    -  Create
    -  Attach to the first ec2
    -  Detach
    -  Attach to the second ec2
    -  You can:
        -  Manage IP addresses (assign new address)
        -  Change security groups
        -  etc
-  clean up:
    -  detach secondary eni
    -  delete eni
    -  terminate 2 ec2 

####  Section 4: AWS Fundamentals: ELB + ASG

#####  33. High Availability and Scalability

-  **Scalability**:
    -  **Vertical**:
        -  junior -> senior
        -  **_t2.micro -> t2.large_**
        -  RDS: micro -> large
        -  ElastiCache: can scale vertically too
        -  non-distributed system
        -  has limit (hardware limit)
    -  **Horizontal**:
        -  1 operator -> N operators
        -  **_increase No of instances_**
        -  distributed systems
        -  EC2 - increase count
-  **High Availability**:
    -  hand-in-hand with horizontal scaling (usually)
    -  means running app in >=2 AZs (data centers)
    -  goal is to survive a data center loss
    -  can be **passive** (for RDS **_Multi AZ_** for example)        
    -  can be **active** (horizontal scaling)        

#####  34. Elastic Load Balancing (ELB) Overview

-  **Load balancers** are servers that forward internet traffic to multiple EC2s
-  ELB (EC2 Load Balancer) - is a managed load balancer
-  Health Check
    -  `/health`
    -  not `200 OK` - unhealthy
    -  port (4567 for example)
-  AWS Load Balancers
    1.  Classic Load Balancer (v1 - old generation) - 2009
        -  HTTP,HTTPS, TCP
    2.  Application Load Balancer (v2 - new generation) - 2016
        -  HTTP, HTTPS, WebSocket
    3.  Network Load Balancer (v2 - new generation) - 2017
        -  TCP, TLS (Secure TCP) & UDP
-  You can setup ELBs
    -  internal (private)
    -  external (public)
-  Security    
    -  Load Balancers Security Groups
        -  HTTP - 80
        -  HTTPS - 443
        -  Source: 0.0.0.0/0 - from everywhere
    -  Application Security Group: allow traffic only from LB
        -  HTTP - 80
        -  Source: sg-... (load balancer)
        
#####  35. Classic Load Balancer (CLB) with Hands On

-  Load Balancers
    -  Create -> `Classic Load Balancer`
        -  Name: `MyFirstCLB`
        -  LB protocol: HTTP
        -  LB port: 80
        -  Instance protocol: HTTP
        -  Instance port: 80
        -  Next: Assign Security Group
        -  SG name: `my-first-load-balancer`
        -  Description: `My first load balancer security group`
        -  Next: Configure Security Settings -> Warning for HTTP (that's OK for now)
        -  Next: Configure Health Check
        -  Ping Path: `/index.html` (OK for now) or just `/`
        -  interval: set 10s
        -  Healthy threshold: 5
        -  Next: Add EC2 Instances
            -  Choose `My second EC2` (or whatever EC2 you want)
        -  Create
-  now we can access **BOTH** CLB and EC2 on port 80
    -  need to modify this:
    -  go to security group `aws-tutorial-first-ec2`
    -  modify source `0.0.0.0/0` to `my-first-load-balancer`
-  Instances:
    -  right mouse click -> Image and Templates
    -  **Launch more like this**
    -  add another from **different AZ**
-  Load balancers
    -  Edit Instances
    -  Add newly created instances
    -  Save
-  Play with load balancer
    -  visit url `http://myfirstclb-29850709.eu-north-1.elb.amazonaws.com/`
    -  update page -> server name changes
    -  stop some instances
    -  play again
-  Delete Load Balancer

##### 36. Application Load Balancer (ALB) with Hands On

-  Characteristics:
    -  ALB - is Layer 7 (HTTP)
    -  LB to multiple HTTP applications across machines (target groups)
    -  LB to multiple applications on the same machine (ex: containers)
    -  Support for HTTP/2 and WebSocket
    -  Support redirects (ex: HTTP -> HTTPS)
    -  Routing tables to different target groups
        -  path (`example.com/users` & `example.com/posts`)  
        -  hostname in URL (`users.example.com` & `posts.example.com`)
        -  query string, headers (`example.com/users?id=123&order=false`)
    -  ALBs are a great fit for microservices & container-based apps (ex: Docker, Amazon ECS)
    -  Has a port mapping feature to redirect dynamic port in ECS
    -  Target Groups
        -  EC2 instances (can be managed by Auto Scaling Group) - HTTP
        -  ECS tasks (managed by ECS itself) - HTTP
        -  Lambda functions - HTTP request is translated into a JSON event
        -  IP Addresses - must be private IPs
    -  ALB can route to multiple target groups
    -  Health Checks are at the target group level
    -  Good to know
        -  Fixed hostname (XXX.region.elb.amazonaws.com)
        -  The application servers EC2 don't see client's IP directly
            -  true IP of the client is inserted in the header `X-Forwarded-For`
            -  we can also get port (`X-Forwarded-Port`) and proto (`X-Forwarded-Proto`)
-  Create Load Balancer
    -  Name: MyFirstALB
    -  Availability Zones: all 3 zones
    -  Security groups: we gonna reuse `my-first-load-balancer`
    -  Target group
        -  Name: `my-first-target-group`
        -  Target type: instance
        -  Advanced Health Check
            - Interval: 10s
    -  Register targets -> register only 2 targets (for now - 1a, 1b)
    -  Create
    -  Wait while `provisioning`
    -  Copy DNS name and go to the that page -> OK
-  Create another target group 
    -  Name: `my-second-target-group`
    -  add 1 instance `1c`
-  Go to `MyFirstALB`
    -  Listeners
        -  1 Listener -> View/edit rules
        -  Add rule
            -  IF `Path` is `/test`
            -  THEN `Forward to` -> `my-second-target-group`
        -  Add rule
            -  IF `Path` is `/constant`
            -  THEN `return fixed response`
            -  404, `OOOOPPPPPSSSS!!!`
-  Clean up
    -  delete 2 unnecessary rules
    -  delete `my-second-target-group`
    -  to the `my-first-target-group` add missing target

#####  37. Network Load Balancer (NLB) with Hands On

-  Characteristics:
    -  Forward TCP & UDP traffic to your instances
    -  Handle millions of request per second
    -  Less latency ~ 100 ms (vs 400 ms for ALB)  
    -  NLB has **one static IP per AZ**
    -  supports assigning Elastic IP (helpful for whitelisting specific IP)
    -  NLB are used for extreme performance, TCP or UDP traffic
    -  Not included in the AWS free tier
-  Create NLB
    -  Name: `MyFirstNLB`
    -  AZ: all 3 AZs
    -  Routing
    -  Target Group
        -  New
        -  Name: my-target-group-nlb
        -  interval: 10s
    -  Register Targets: all 3
    -  Create
    -  then go to DNS name 
        -> Nothing loads (need to configure security)
-  Configure EC2 security
    -  go to security group `aws-tutorial-first-ec2`
    -  add rule:
        -  from anywhere
        -  port 80
        -  TCP
-  Test everything is OK now
-  Clean Up
    -  delete NLB
    -  delete target group
    -  delete rule from security group `aws-tutorial-first-ec2`
    
#####  38. Elastic Load Balancer - Stickiness

-  Target Groups
    -  Edit Attributes
        -  Enable
        -  Stickiness duration: 2 minutes
-  test it
-  disable it

#####  39. Elastic Load Balancer - Cross Zone Load Balancing

1.  Characteristics
    -  Classic Load Balancer
        -  disabled by default
        -  no charges for inter AZ if enabled
    -  Application Load Balancer
        -  always **ON** (can't be disabled)
        -  no charges for inter AZ
    -  Network Load Balancer
        -  disabled by default
        -  you pay charges ($) if enabled
2.  Hands on          
                    
#####  40. Elastic Load Balancer - SSL Certificates

-  Characteristics
    -  Classic Load Balancer
        -  supports only one SSL certificate
        -  must use multiple CLB for multiple hostname with multiple SSL certificates
    -  Application Load Balancer
        -  supports multiple listeners with multiple SSL certificates
        -  uses Server Name Indication (SNI) to make it work
    -  Network Load Balancer
        -  supports multiple listeners with multiple SSL certificates
        -  uses Server Name Indication (SNI) to make it work

#####  41. Elastic Load Balancer - Connection Draining

Names:
-  CLB:
    -  Connection Draining
-  ALB, NLB:
    -  Target group: Deregistration Delay    

#####  43. Auto Scaling Groups Hands On

-  Creating Auto Scaling
    -  Auto Scaling Groups ->
    -  Create
    -  Name: MyFirstASG
    -  Create Launch Template
        -  Name: MyFirstTemplate
        -  Template version description: My first template
        -  Autoscaling guidance: false
        -  Create new launch template
            -  same as EC2 from previous sections (with User Data!!!)
    -  in ASG console choose newly created ASG
    -  Next
    -  Subnets: choose all 3 AZs
    -  Next
    -  Load Balancing: ALB
        -  Group:  `my-first-target-group`
        -  Health checks:
            -  EC2: true
            -  ELB: true
        -  Enable group metrics collection within CloudWatch: true
    -  Next
    -  Configure group size and scaling policies
        -  Group size 
            -  Desired capacity: 1
            -  Minimum capacity: 1
            -  Maximum capacity: 3
        -  Scaling policies: None (for now, see next lesson)
        -  Next->Next->Next->Create Auto Scaling group
-  Playing  with ASG
    -  ASG: MyFirstASG
    -  Details
    -  Activity:
        -  Activity history
    -  Instance management
    -  Go to Target Groups -> see Targets -> healthy
    -  Go to ASG -> MyFirstASG
        -  Details -> Group Details -> Edit
            - Desired Capacity: 2
        -  Activity -> see Diff (starting new EC2) 
        -  Instance Management
            -  see both EC2 healthy
    -  go to ALB DNS -> refresh -> monitor different IPs (make sure TargGr->GroupDetails->Stickiness is DISABLED)
    -  Target Groups -> Targets (healthy, healthy)
-  Scale in
    -  change Desired Capacity back to 1
    -  Activity History: WaitingForELBConnectionDraining
    -  after 300 sec instance was terminated

#####  44. Auto Scaling Groups - Scaling Policies

-  Auto Scaling Groups - Scaling Cooldowns
-  Automatic Scaling -> Scaling Policies
    -  Add policy
        -  Target Tracking policy
        -  Instances need **10**  seconds warm up before including in metric (for testing purpose)
        -  Create
-  Monitoring
    -  EC2 ->
    -  CPU Utilization -> see low
-  Increase desired instances to 2
    -  Details -> Edit -> Desired Capacity
-  CloudWatch
-  After certain period of Time 
    -  ```Terminating EC2 instance: i-082c8ce5284deddd2```
    -  Desired capacity made 1 by scaling policy
-  Delete Target Tracking Policy
-  Look at Creation Step Scaling    
-  Look at Creation Simple Scaling
-  Create Scheduled Action
    -  ScaleAtXPM
    -  delete then after tests
        
####  Section 5: EC2 Storage - EBS & EFS

#####  46. EBS Intro Hands On

-  Elastic Block Storage (EBS)
-  Create new EC2
    -  As usual
    -  Step 4: Storage
        -  Add New Volume
            -  Size: 2 GiB
            -  Delete on Termination: false
    -  Tags:
        -  Name: EBSDemo
-  Volumes
    -  2 volumes
-  SSH to EC2
    -  `lsblk`
    -  [Making an Amazon EBS volume available for use on Linux](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-using-volumes.html)
    -  `sudo file -s /dev/xvdf` - is there file system on drive?
    -  `sudo file -s /dev/nvme1n1` - `data` - my drive does not have fs, need to format
    -  `sudo mkfs -t ext4 /dev/nvme1n1` - create fs
    -  `sudo mkdir /data`
    -  `sudo mount /dev/nvme1n1 /data`
    -  `cd /data`
    -  `sudo touch Hello.txt`
    -  `nano Hello.txt`
    -  `hello world`
    -  `sudo cp /etc/fstab /etc/fstab.orig`
    -  `sudo nano /etc/fstab`
    -  add `/dev/nvme1n1 /data ext4 defaults,nofail  0  2`
    -  **or** with using UUID
    -  add `UUID= ??? /data ext4 defaults,nofail  0  2`
    -  `sudo umount /data` or `sudo umount -l /data` (if busy) 
    -  `lsblk`
    -  `sudo mount -a`
    -  `lsblk` -> if `/data` is mounted then OK
    
#####  48. EBS vs Instance Store

-  `c5d.large` - look but do not create
-  `ephemeral0	/dev/nvme0n1` - this is instance store - physical drive not network like EBS 

#####  49. EFS Overview

-  `EBS` - **single** AZ
-  `EFS` - **multi** AZs - Elastic File System
-  expensive - ~3*gp2 drive, but __pay per use__

#####  50. EFS Hands On

1.  Services -> Storage -> EFS
    -  Create File System -> Customize
    -  Name: <leave empty>
    -  All default
    -  Network access:
        -  all 3 AZs
        -  create new security group
            -  name: `my-efs-demo`
            -  description: `SG for EFS`
            -  no Inbound rule (for now)
        -  delete default SGs for AZs
        -  use SG `my-efs-demo` for all 3 AZs
    -  Create
2.  EC2 -> Launch instance
    -  instance 1
        -  Subnet: 1a
        -  File Systems: do **NOT** add for now
        -  Security Group -> create new
            -  Name: `ec2-to-efs`
            -  only SSH for now
        -  Launch
    -  instance 2
        -  launch more like this (like previous)
        -  Subnet: 1b
        -  Launch
3.  SSH to both instances
    -  EFS console
        -  my efs -> Attach
        -  Using DNS
        -  `sudo mount -t efs -o tls fs-06cb7797:/ efs` -> `mount: efs: mount point does not exist.`
        -  need to install the **amazon-efs-utils package**
            -  [Mounting EFS file systems](https://docs.aws.amazon.com/efs/latest/ug/mounting-fs.html)
            -  [Installing the amazon-efs-utils Package on Amazon Linux](https://docs.aws.amazon.com/efs/latest/ug/installing-amazon-efs-utils.html)
            -  `sudo yum install -y amazon-efs-utils`
        -  `mkdir efs`
        -  `sudo mount -t efs -o tls fs-06cb7797:/ efs` -> timeout -> `Connection reset by peer`
        -  need to modify security group
        -  modify `my-efs-demo` inbound rule
            -  Type: NFS
            -  Source: sg `ec2-to-efs`
        -  `sudo mount -t efs -o tls fs-06cb7797:/ efs` -> OK
        -  `cd efs`
        -  `ll` -> no files
        -  `sudo touch ReadArt.txt`
        -  `ll` in both EC2s -> file present
4.  Clean Up (**51. EBS & EFS - Section Cleanup**)
    -  terminate both EC2 instances
    -  delete file system
    -  volumes - delete available
    -  snapshots - delete snapshots (I have none)
    -  delete security groups (**do not delete** default)
    
####  Section 6: AWS Fundamentals: RDS + Aurora + ElastiCache

#####  55. AWS RDS Hands On

1.  RDS Console
    -  Paris -> Free tier
    -  MySQL
    -  DB instance identifier (name) must be unique across region: `my-first-mysql`
    -  Master username: `art`
    -  Master password: `password`
    -  DB instance size: `db.t2.micro`
    -  Enable storage autoscaling: `false`
    -  Public access: `Yes` (for study purpose)
    -  VPC security group: `Create new`
        -  name: `my-first-rds-sg`
    -  Additional configuration
        -  Initial database name: `mydb`
    -  Create database
2.  Use SQLectron
    -  Add
    -  Name: `My RDS database for AWS Udemy course (Stephane)`
    -  Save
    -  Connect
3.  RDS Console -> `my-first-mysql`
    -  Create read replica
        -  `my-first-mysql-replica`
    -  Create table
        `CREATE TABLE Persons (
             PersonID int,
             LastName varchar(255),
             FirstName varchar(255),
             Address varchar(255),
             City varchar(255)
         );`
    -  Insert row
        `INSERT INTO `Persons` VALUES (1,'Shyshkin','Art','my address','my city');`
4.  Connect to REPLICA
    -  test table exists too
    -  trying to insert another row
        -  `INSERT INTO `Persons` VALUES (2,'Shyshkina','Kate','my address','my city');`
    -  got an error
        -  `The MySQL server is running with the --read-only option so it cannot execute this statement`

#####  58. Aurora Hands On

1.  RDS Console
    -  create new database
    -  Aurora -> MySQL compatibility
    -  version 5.6.10a (Stephane version)
    -  DB Cluster Identifier: `my-aurora-db`
    -  Templates: `Production` (then I choosed `Dev/Test`)
    -  DB instance size: use cheapest
    -  Multi-AZ deployment:  yes
    -  Initial database name: `aurora`
    -  Enable deletion protection: true
    -  KMS key ID (generated): `6c8e68f7-fb9b-4fa9-a680-bae90321affc`
    -  Create database
2.  Test it
    -  `my-aurora-db.cluster-cha39fdqzzb3.eu-west-3.rds.amazonaws.com`
    -  Endpoints
        -  `my-aurora-db.cluster-cha39fdqzzb3.eu-west-3.rds.amazonaws.com` - writer
        -  `my-aurora-db.cluster-ro-cha39fdqzzb3.eu-west-3.rds.amazonaws.com` - **ro** means read-only
    -  Click `my-aurora-db-instance-1`
        -  Endpoint is `my-aurora-db-instance-1.cha39fdqzzb3.eu-west-3.rds.amazonaws.com`
        -  You can connect but **IT IS NOT RECOMMENDED WAY**
        -  choose Endpoint for Writer (shown above)
    -  Click `my-aurora-db-instance-1-eu-west-3b`
        -  Endpoint is `my-aurora-db-instance-1-eu-west-3b.cha39fdqzzb3.eu-west-3.rds.amazonaws.com`
        -  You can connect but **IT IS NOT RECOMMENDED WAY**
        -  choose Endpoint for Reader (shown above)
    -  Actions possible:
        -  Add reader
        -  Create cross-Region read replica
        -  Create clone
        -  Restore to point in time
        -  Add replica auto-scaling
    -  Actions -> Add replica auto-scaling
        -  Policy name: `MyScalingAurora`
        -  Avg CPU Utilization: 60
        -  Rest leave default
        -  Add policy
3.  Clean Up
    -  delete Writer Endpoint
    -  `delete me`       
    -  Modify Cluster ->
        -  `Enable deletion protection` : false
        -  Scheduling of modifications: Immediately
    -  delete Reader Endpoint
        
#####  60. ElastiCache Hands On

1.  ElastiCache console
    -  Redis
    -  Name: `MyFirstRedis`
    -  Description: `My first Redis instsance`
    -  Node type: `cache.t2.micro` - free tier
    -  Number of replicas: 0 (for study, otherwise pay money)
        -  so we loose MultiAZ option
    -  Subnet group:
        -  Name: `my-first-subnet-group`
    -  Encryption
        -  `at-rest` blank
        -  `in-transit` leave blank
    -  Create
2.  Clean Up
    -  Delete Redis Cluster    

####  Section 7: Route 53

#####  63. Route 53 Hands On

1.  Route 53 Console
    -  Create record
    -  `nslookup dockerapp.shyshkin.net`
    -  **or**
    -  `nslookup dockerapp.shyshkin.net 8.8.8.8`                 
    -  `nslookup dockerapp.shyshkin.net dns.google`                 
     

#####  64. Route 53 - EC2 Setup

1.  [Instance metadata and user data](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-instance-metadata.html)
    -  `http://169.254.169.254/latest/meta-data/`
    -  `http://169.254.169.254` - aws metadata server
2.  Create EC2 instance in one Region (for example Stockholm)
    -  User Data
        `#!/bin/bash
         yum update -y
         yum install -y httpd
         systemctl start httpd
         systemctl enable httpd
         EC2_AVAIL_ZONE=$(curl -s http://169.254.169.254/latest/meta-data/placement/availability-zone)
         echo "<h1>Hello World $(hostname -f) in AZ $EC2_AVAIL_ZONE </h1>" > /var/www/html/index.html`
    -  new security group -> HTTP enable all       
3.  Create EC2 in another region (Paris)          
4.  Create EC2 in Asia (Tokyo)
5.  Table
    -  http://52.47.145.218/ - eu-west-3b
    -  http://13.48.49.160/ - eu-north-1c
    -  http://3.112.13.107/ - ap-northeast-1a
6.  LoadBalancer
    -  New ALB
    -  new SG
    -  new Target Group `DemoRoute53TG`
    -  add Target to TG
    - Review -> Create             

#####  65. Route 53 - TTL

1.  Route53 Console
    -  `ttldemo.shyshkin.net` -> to EC2 in Paris (15.236.141.98)
    -  TTL -> 120 sec
2.  `dig ttldemo.shyshkin.net`
    -  got an answer with such a row `ttldemo.shyshkin.net.   46      IN      A       15.236.141.98`
    -  46 is seconds left to request DNS of 120 sec (TTL 120) - IP Cached            
3.  ttldemo changed to Tokyo but still got Paris while TTL expires       

#####  66. Route 53 CNAME vs Alias  

1.  Theory
    -  CNAME - Points a hostname to any other hostname
        -  mydemobalancer.shyshkin.net -> DemoALBRoute53-187376732.eu-north-1.elb.amazonaws.com
        -  **ONLY FOR NON ROOT DOMAIN** (something.mydomain.com)
    -  Alias - Points a hostname to an AWS Resource 
        -  app.mydomain.com -> blabla.amazonaws.com
        -  works for ROOT DOMAIN and NON ROOT DOMAIN (aka mydomain.com)
        -  _Free of charge_
        -  Native health check
2.  Hands on
    -  CNAME
        - `myapp.shyshkin.net`
        -  Record type: CNAME
        -  Value/Route traffic to -> IP Addr or another... -> LoadBalancer DNS
    -  Alias
        -  `myalias.shyshkin.net`
        -  Alias for ALB
    -  Alias ROOT
        -  Record name: empty (will be just `shyshkin.net`)
        -  Value/Route traffic to:
            -  Alias to another record in this hosted zone
            -  `us-east-1` (only one that available)
            -  `myalias.shyshkin.net` (or directly to LoadBalancer)
        -  Record type: A
    -  CNAME ROOT
        -  Record name: empty (will be just `shyshkin.net`)
        -  Value/Route traffic to:
            -  IP Address or another value depending on the record type
            -  `myalias.shyshkin.net`
        -  Record type: CNAME
        -  got an **Error**
        -  `Bad request.
            (InvalidChangeBatch 400: RRSet of type CNAME with DNS name shyshkin.net. is not permitted at apex in zone shyshkin.net.)`
        -  so we can not use CNAME for apex domain (root domain)

#####  67. Routing Policy - Simple

-  `simple.shyshkin.net`
-  IPs:
    -  52.47.145.218
    -  13.48.49.160
    -  3.112.13.107
-  TTL: 60
-  client randomly choose one of IP

#####  68. Routing Policy - Weighted

1.  Create Record:
    -  Weighted
    -  `weighted.shyshkin.net`
    -  TTL 60
    -  Define weighted record
        -  Paris IP
        -  Tokyo IP
        -  Stockholm LB Alias
        -  All with different weights (10, 20, 70 % _or_ 2,3,10 weight coefs, no matter)
        -  without health check for now
2.  Testing
    -  go to  `weighted.shyshkin.net`
    -  `dig weighted.shyshkin.net`                

#####  69. Routing Policy - Latency

1.  Create Record
    -  Latency
    -  `latency.shyshkin.net`
    -  TTL 10
    -  Define latency record
        -  Paris IP
        -  Tokyo IP
        -  Stockholm LB Alias        
2.  Testing
    -  using VPN (NordVPN, TouchVPN)
    -  monitor different EC2 instances

#####  70. Route 53 Health Checks

1.  Route 53 Console
    -  Health checks -> Create new
    -  Name: `Paris Health Check`
    -  Endpoint
        -  IP: Paris IP (`52.47.145.218`)
        -  Hostname: empty
        -  Port: 80
    -  create health check
2.  Create Tokyo health check
3.  Create Stockholm ALB health check
    -  Domain name
    -  Domain name: ALB DNS

#####  71. Routing Policy - Failover

1.  Create Record
    -  Failover
        -  `failover.shyshkin.net`
        -  TTL 30
    -  Define failover record
        -  to ALB in Stockholm `myalias.shyshkin.net` - Primary
            -  Health check: Stockholm ALB Health Check                   
        -  to Paris EC2 - Secondary
        -  ~~to Tokyo EC2~~ - Secondary (not enabled)
2.  Testing
    -  shut down EC2 in Stockholm so ALB health status will be unhealthy
    -  make sure Route 53 switches to Paris         

#####  72. Routing Policy - Geolocation

1.  Create record
    -  Geolocation
        -  `geolocation.shyshkin.net`
    -  Define geolocation record Paris
        -  IP: `Paris IP`
        -  Location: `Europe` (can choose country)
    -  Define geolocation record Tokyo
        -  IP: `Tokyo IP`
        -  Location: `Asia` (for test choose Ukraine)
    -  Define geolocation record Stockholm
        -  Alias to another record: `myalias.shyshkin.net` (ALB)
        -  Location: `Default`
2.  Testing
    -  use VPN
    -  from Europe -> should redirect to Paris
    -  from Ukraine -> should be Tokyo
    -  from anywhere else -> Stockholm
3.  Note
    -  **DEFAULT** should be present          
        
#####  73. Routing Policy - Multi Value

1.  Create record
    -  Multivalue answer
    -  `multi.shyshkin.net`
    -  Define multivalue answer record for Paris
        -  IP: `Paris IP`
        -  health check: Paris
    -  Define multivalue answer record for Tokyo
        -  IP: `Tokyo IP`
        -  health check: Tokyo
    -  ~~Define multivalue answer record for Stockholm~~
        -  ~~IP: `DemoALBRoute53-187376732.eu-north-1.elb.amazonaws.com`~~
        -  ~~health check: Stockholm~~
        -  `Bad request.
        (InvalidChangeBatch 400: ARRDATAIllegalIPv4Address (Value is not a valid IPv4 address) encountered with 'DemoALBRoute53-187376732.eu-north-1.elb.amazonaws.com')`
    -  Define multivalue answer record for Stockholm
           -  IP: `Stockholm EC2 IP`
           -  health check: Stockholm
2.  Testing
    -  `dig multi.shyshkin.net`
    -  it will show available IPs
```
;; ANSWER SECTION:
multi.shyshkin.net.     33      IN      A       13.48.134.232
multi.shyshkin.net.     33      IN      A       3.112.13.107
multi.shyshkin.net.     33      IN      A       52.47.145.218
```               
It works like **FAULT TOLERANCE ON THE CLIENT SIDE**                
    
#####  74. Section Cleanup

1.  Route 53 Console
    -  peek all unused records one-by-one
    -  delete
2.  Terminate all unused EC2s
3.  Delete load balancer
4.  Delete health checks 

####   Section 8: VPC Fundamentals

#####  75. VPC Fundamentals - Section Introduction

#####  76. VPC, Subnets, IGW and NAT

#####  77. NACL, SG, VPC Flow Logs

#####  78. VPC Peering, Endpoints, VPN, DX

#####  79. VPC Cheat Sheet & Closing Comments

#####  80. Three Tier Architecture
      
####  Section 9: Amazon S3 Introduction

#####  82. S3 Buckets and Objects

1.  S3 Console
    -  S3 is Global
    -  but Buckets tight to a certain Region
    -  Bucket's name must be **globally** unique
    -  if we try to create bucket _test_ got an error
        -  `Bucket with the same name already exists`
        -  Must be unique among **ALL THE USERS**
2.  Create S3 Bucket
    -  Name: `the-bucket-of-art-2020`
    -  Leave all other default values
3.  Testing
    -  Upload file
    -  ~~Right Click -> Open~~ - you can open file (does not work for me)
    -  `the-bucket-of-art-2020` -> tick file -> Actions -> Open
        -  we can see image content
        -  and url will be `https://the-bucket-of-art-2020.s3.eu-north-1.amazonaws.com/springsecurity.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEOz%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJIMEYCIQD0TFO3zC%2BtwX6yvnq1jtbCaW4l10InqaCaCQNGxHiqlgIhAOSo6%2FYWW9EbZvO%2BGG1oaDXvkMTC1yBQ04Vt1GfEXlrkKuYCCGUQARoMMzkyOTcxMDMzNTE2IgxkBwDyowgu9iOyjQIqwwLTRBeo7UdFGSTG9DFg8QD6MOchYG2jTQ5xO4V6QstLc%2BjZbKBFfqkBJewiNcwn3hUMLfVfplaSRmLpRDe3ozqvF1gkOcWMiE9tSPtY55HioU3ikGDhKFrMSsETO2awAv5xBthxXDZ%2Bd82Lobh4oKOQDCxGOe95vLwbVh4V3s1fd2GuiHDoDsonnC8%2FTKL7zDu0xVwC2x2fFOfRp2S9S5e8XLgL42gs%2BM7WWin%2FTuBxQPj5z9dBnSDjyIpLyIIC2Ydc1Qh6kgLEJ2MQ1Q8GkgP9Wi%2BwKpdOBGqljc4ob2EgA0qeDZof46QTt%2BAroPoIQPyI%2BhiLwCb1x%2FZl7Y%2FDv4j5uq8oj9hfUJuuJJ2qFLdx2yVL8Xg1yL%2FbwyZ20bsIWK2thruDm2A7wGxBSZcljUglIFFYb%2BLKvlmTUoBqqEc2fa7xdjCGu9n9BTquAtYxNKOZU4pUsyVxocGrjfTydhfP42rxW6WG%2F2nT5r8bVQF5tWzgUsMCMBua1su6TUDsFZJjZLwyuD2X%2FkLb%2B8TLGwF7Vy0rxaoPZcCg7Ecd28lz9LoUD%2B8k%2Fqfw9iECaoBswsmW7DQ6cgH8D%2BusjMbyVGj733nGsUYGXejWtke4qlP2M83CF90DyqiWVvgPT9xJMIhM9piL%2BeyOWe8AoJfdhnxB1%2FSUpxUvAOaw%2Fzgz7kqSjI6BozXq8oa2atVLVAruaebZHK1VyaCVXezVwar7fVm%2BggRe2CwCk4XLLNJmnqs3HSIABVZ69cO2hZYipSS%2F%2FgZ7smb%2BDGwpAu478INIpQAt3R2QY9daOfZKexTbuOaVEUziUtOq8rrSwUEXCqs%2BtE8DI18B21rCxtZ0&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20201119T210915Z&X-Amz-SignedHeaders=host&X-Amz-Expires=300&X-Amz-Credential=ASIAVW7XGDOWBVDUS56U%2F20201119%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=44139a1d2f529394d9a6ee59a24e16159099d2a141674b8d2cf26a2d9ca53482`
    -  if we go inside image
        -  Object URL: `https://the-bucket-of-art-2020.s3.eu-north-1.amazonaws.com/springsecurity.png`
        -  got an XML with error description
```xml
<Error>
<Code>AccessDenied</Code>
<Message>Access Denied</Message>
<RequestId>125F0F949DB840A7</RequestId>
<HostId>4dY01dcGST3onp2ucHDbQkkQSD0zsinnZaQMygKSgMMnJ6gLsAxB9FPr9bmXJJ7Zq0VsdxUilvs=</HostId>
</Error>
```                        
Reason: this file is not public. We can access it with pre-sign URL (with owner's credentials)

#####  83. S3 Versioning

1.  Bucket console
    -  Properties -> Edit
        -  Bucket Versioning -> Enable
2.  Objects
    -  List versions
        -  `springsecurity.png` (my file) - Version ID null
    -  Upload one more time the same file
        -  Version ID - `Zu2CZ.V0XnZcGdWnp7N.hye8HJyWdRa0`
3.  Deleting latest version
    -  delete `springsecurity.png`
    -  Objects -> no more `springsecurity.png` **BUT**
    -  List versions
        -  latest file marked as `Delete marker`
    -  tick on delete marker
    -  delete -> `permanently delete`
    -  now we see latest version (not deleted)
4.  Deleting specific version
    -  tick on version -> delete
5.  Suspending versioning
    -  Properties -> Bucket Versioning -> Suspend
    -  Upload one new `springsecurity.png`
    -  Added one with version ID: null
    -  Upload one more time -> that replaces old one with Version ID null
          
#####  84. S3 Encryption

1.  Methods
    -  SSE-S3 - Server-Side Encryption by AWS  
    -  SSE-KMS - leverage Key Management Service to manage encryption keys
    -  SSE-C - when you want to manage your own encryption keys
    -  Client Side Encryption
2.  SSE-S3
    -  **AES-256** ancryption type
    -  Must set header: "x-amz-server-side-encryption":"AES256"
    -  keys handled and managed by Amazon S3 (keys are stored on amazon servers)
3.  SSE-KMS
    -  KMS Advantages: user control + audit trail
    -  Must set header: "x-amz-server-side-encryption":"aws:kms"
4.  SSE-C
    -  keys are fully managed by the customer outside of AWS
    -  Amazon S3 does not store keys
    -  **HTTPS must be used** (because you send keys over ethernet)
    -  Encryption key must be provided in HTTP headers for every request made
5.  Client Side Encryption (CSE)
    -  Client library such as the `Amazon S3 Encryption Client`
    -  Clients must:
        -  encrypt data themselves before sending to S3 
        -  decrypt data themselves when retrieving from S3 
6.  Encryption in transit (SSL/TLS)    
    -  Amazon S3 exposes:
        -  HTTP endpoint: non encrypted
        -  HTTPS endpoint: encryption in flight
    -  You're free to use the endpoint you want, but HTTPS is recommended
    -  Most clients would use the HTTPS endpoint by default
    -  HTTPS is mandatory for SSE-C
    -  Encryption in flight is also called SSL/TLS
7.  Hands on
    - go to details of my file
        -  see `Server-side encryption None`
    -  upload new file
        -  Encryption: use Amazon SSE-S3
    -  upload new file
        -  Encryption: use AWS-KMS
            -  key: `AWS managed key (aws/s3)`
            -  `arn:aws:kms:eu-north-1:392971033516:alias/aws/s3`
8.  Default Encryption
    -  `the-bucket-of-art-2020`
    -  Properties
        - Default encryption: `SSE-S3`           

#####  85. S3 Security & Bucket Policies

1.  S3 Security
    -  User Based
        -  IAM Policies - which API calls should be allowed for a specific user from IAM console
    -  Resource Based
        -  Bucket Policies  - bucket wide rules from the S3 console - allows cross account
        -  Object Access Control List (ACL) - finer grain
        -  Bucket Access Control List (ACL) - less common    
    -  **Note**: an IAM principal can access an S3 object if
        -  the user IAM permissions allow it _OR_ the resource policy ALLOWS it
        -  **AND** there's no explicit DENY
2.  S3 Bucket Policies
    -  JSON based policies
        -  Resources: buckets and objects
        -  Actions: Set of API to Allow or Deny
        -  Effect: Allow/Deny
        -  Principal: The account or user to apply the policy to
    -  Use S3 bucket for policy to:
        -  Grant public access to the bucket
        -  Force objects to be encrypted at upload
        -  Grant access to another account (Cross Account)
3.  Bucket settings for Block Public Access
    -  Block public access to buckets and objects granted through
        -  new access control lists (ACLs)        
        -  any access control lists (ACLs)        
        -  new public bucket or access point policies
    -  Block public and cross-account access to buckets and objects through any public bucket or access point policies        
4.  S3 Security - Other
    -  Networking:
        -  Supports VPC Endpoints (for instances in VPC without www internet)
    -  Logging and Audit:
        -  S3 Access Logs can be stored in other S3 bucket
        -  API calls can be logged in AWS CloudTrail
    -  User Security:
        -  MFA Delete: MFA (multi factor auth) can be required in versioned buckets to delete objects
        -  Pre-Signed URLs: URLs that are valid only for a limited time (ex: premium video service for logged in users)

#####  86. S3 Bucket Policies Hands On

1.   Permissions
    -  Bucket Policy -> Edit
    -  Policy Generator
        -  Select Policy Type: S3
        -  Statement 1:
            -  Deny
            -  Principal: `*`
            -  Actions: `PutObject`
            -  ARN : `bucket ARN` + `/*` = `arn:aws:s3:::the-bucket-of-art-2020/*`
            -  Add Conditions
                -  if `x-amz-server-side-encryption` is null then deny Putting Object to the Bucket
                -  Condition: `Null`
                -  Key: `s3:x-amz-server-side-encryption`
                -  Value: true
                -  Add condition
        -  Statement 2:
            -  Deny
            -  Principal: `*`
            -  Actions: `PutObject`
            -  ARN : `bucket ARN` + `/*` = `arn:aws:s3:::the-bucket-of-art-2020/*`
            -  Add Conditions
                -  if encryption is not `aes256` deny too
                -  Condition: `StringNotEqual`
                -  Key: `s3:x-amz-server-side-encryption`
                -  Value: `AES256`
                -  Add condition
        -  Generate Policy
            -  copy content of policy
    -  Paste created policy
    -  Save changes
2.  Testing
    -  upload new file (unencrypted)
        -  encryption: None
        -  result: `You don't have permissions to upload files and folders.`
    -  upload encrypted by KMS
        -  encryption: KMS
        -  result: `You don't have permissions to upload files and folders.`
    -  upload encrypted by AES256
        -  encryption: Amazon S3 key (SSE-S3)
        -  result: `Upload succeeded`
3.  ACL for bucket
4.  ACL for object

#####  87. S3 Websites

1.  Create HTML files
    -  index.html
    -  error.html
    -  upload them to the bucket
2.  Enabling static website hosting  
    -  Bucket
    -  Properties
    -  Static website hosting -> Edit
    -  Index document:  index.html
    -  Error document:  error.html
3.  Go to `Bucket website endpoint`
    -  `http://the-bucket-of-art-2020.s3-website.eu-north-1.amazonaws.com`
    -  Got an error
    -  `403 Forbidden`
4.  Enabling public access
    -  Permissions
        -  Block public access
            -  disable all blocks        
    -  Must create a bucket policy
        -  Bucket policy -> Edit
        -  Use policy generator
            -  Effect: Allow
            -  Principal: *
            -  Action: GetObject
            -  ARN: bucketARN /*
            -  Add Statement
            -  Create policy
        -  Copy statement content to the existing bucket policy
5.  S3 Control panel
    -  `the-bucket-of-art-2020	EU (Stockholm) eu-north-1	 Public        November 19, 2020, 22:54 (UTC+02:00)`
    -  **Public**
6.  Testing
    -  `http://the-bucket-of-art-2020.s3-website.eu-north-1.amazonaws.com`
    -  All OK
    -  `http://the-bucket-of-art-2020.s3-website.eu-north-1.amazonaws.com/lol`
    -  getting `error.html`
    
#####  88. S3 CORS

-  Origin = `protocol://host:port`
    -  `https://example.com`
        -  protocol: `https`
        -  host: `example.com`
        -  port: 443
-  CORS - Cross-Origin Resource Sharing
-  Same origins:
    -  `http://example.com/foo` & `http://example.com/foo`
-  Different origins
    -  `http://foo.example.com` & `http://bar.example.com`
-  The requests won't be fulfilled unless the other origin allows for the requests, using CORS Headers (ex: Access-Control-Allow-Origin)    

#####  89. S3 CORS Hands On

1.  Modify `index.html`
2.  Create `extra-page.html`
3.  Upload them to the bucket (Origin bucket)
4.  Test everything works fine
    -  `http://the-bucket-of-art-2020.s3-website.eu-north-1.amazonaws.com`
    -  `http://the-bucket-of-art-2020.s3-website.eu-north-1.amazonaws.com/extra-page.html` 
5.  Create another bucket as website (Cross-Origin bucket)
    -  `the-bucket-of-art-2020-assets`
    -  publicly available
    -  policy to get objects from everywhere
    -  enable static website hosting
    -  upload `extra-page.html`
6.  Test second static website
    -  `http://the-bucket-of-art-2020-assets.s3-website.eu-north-1.amazonaws.com/extra-page.html`
7.  Delete `extra-page.html` from `Origin bucket`
8.  Modify `index.html` to fetch this page from second website
9.  Go to `http://the-bucket-of-art-2020.s3-website.eu-north-1.amazonaws.com/`
    -  open Chrome Dev Tools `Console`
    -  got an error
        -  `Access to fetch at 'http://the-bucket-of-art-2020-assets.s3-website.eu-north-1.amazonaws.com/extra-page.html' from origin 'http://the-bucket-of-art-2020.s3-website.eu-north-1.amazonaws.com' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource. If an opaque response serves your needs, set the request's mode to 'no-cors' to fetch the resource with CORS disabled.`
    -  need to enable CORS
10.  Edit cross-origin resource sharing
    -  config like `cors-config.json`
11.  Testing
    -  Chrome Dev Tools - Network
    -  extra-page headers
```
Access-Control-Allow-Credentials: true
Access-Control-Allow-Methods: GET
Access-Control-Allow-Origin: http://the-bucket-of-art-2020.s3-website.eu-north-1.amazonaws.com
Access-Control-Max-Age: 3000
```

#####  90. S3 Consistency Model

1.  Read after write consistency for **PUTS** of new objects
    -  As soon as a new object is written, we can retrieve it
        -  ex: (PUT 200 => GET 200)
    -  This is true, except if we did a GET before to see if the object existed
        -  ex: (GET 404 => PUT 200 => GET 404) – eventually consistent
2.  Eventual Consistency for **DELETES** and **PUTS** of existing objects
    -  If we read an object after updating, we might get the older version
        -  ex: (PUT 200 => PUT 200 => GET 200 (might be older version))
    -  If we delete an object, we might still be able to retrieve it for a short time
        -  ex: (DELETE 200 => GET 200)
3.  Note: there’s no way to request “strong consistency”

####  Section 10: AWS CLI, SDK, IAM Roles & Policies

#####  92. AWS CLI Setup on Windows

-  [Installing, updating, and uninstalling the AWS CLI version 2 on Windows](https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2-windows.html)

#####  96. AWS CLI Configuration

1.  Creating new access key
    -  IAM Console
    -  Users
        -  art_admin
        -  Security Credentials
        -  Create access key
2.  Configuring aws cli
    -  `aws configure`
    -  enter all required fields
3.  Config location
    -  on Windows: `dir %USERPROFILE%\.aws` (using cmd)
    -  on Windows: `ls $env:USERPROFILE\.aws` (using PowerShell)
    -  on Linux or Mac: `ls ~/.aws`
4.  Testing
    -  `aws s3 ls`    
    
#####  97. AWS CLI on EC2

1.  Bad way
    -  ssh to EC2
    -  aws config through ssh
    -  **NEVER PUT YOUR PERSONAL CREDENTIALS ON EC2**
2.  Good way -  IAM Roles
    -  start EC2 instance
    -  ssh to it
    -  `aws` - it is present on Amazon Linux 2 AMI
    -  `aws --version`
    -  `aws s3 ls`
        -  Got an error: `Unable to locate credentials. You can configure credentials by running "aws configure".`
3.  Creating IAM Role for EC2 instance
    -  IAM Console
    -  Roles
    -  Create Role
        -  EC2 instance
        -  Permissions
        -  Filter through s3
        -  Policy: `AmazonS3ReadOnlyAccess`
        -  Role Name: `MyFirstEC2Role`
        -  Create role
4.  Modify IAM role of EC2
    -  EC2 management console
    -  Security
    -  Modify IAM role
5.  Testing
    -  from ec2 ssh
    -  `aws s3 ls`
    -  `aws s3 ls s3://the-bucket-of-art-2020`
    -  `aws s3 mb s3://attempt-to-create-bucket` - make bucket
        -  got an error
        -  `make_bucket failed: s3://attempt-to-create-bucket An error occurred (AccessDenied) when calling the CreateBucket operation: Access Denied`                          
    -  attach to role new policy:  `AmazonS3FullAccess`
    -  now run again: `aws s3 mb s3://attempt-to-create-bucket`
        -  success: `make_bucket: attempt-to-create-bucket`
    -  `aws s3 rb s3://attempt-to-create-bucket` - remove bucket
6.  Conclusion
    -  EC2 instance may have only **ONE** IAM Role at a time
    -  IAM Role may have **MULTIPLE** policies    

#####  98. AWS CLI Practice with S3

1.  Google `aws s3 cli`
    -  Available Commands
        -  [ls](https://awscli.amazonaws.com/v2/documentation/api/latest/reference/s3/ls.html)
            -  `aws s3 ls s3://the-bucket-of-art-2020 --recursive`
        -  cp - copy
        -  `aws s3 cp help` - documentation, `q` - to quit
        -  `aws s3 cp s3://the-bucket-of-art-2020/springsecurity.png sprsec.png` - from s3 to local
        -  mb - make bucket
        -  rb - remove bucket (if empty)

#####  99. IAM Roles and Policies Hands On

1.  One can
    -  Create policy
    -  _or_
    -  Add inline policy (through Role Permissions tab) - **just for that role**
        -  it is not recommended, the better way is to manage policy globally
2.  Create policy using Visual Editor
    -  Service: S3
    -  Actions -> Read
        -  `GetObject`
    -  Resources: 
        -  ~~All resources (this is `*`)~~
        -  Specific -> Add ARN
            -  Bucket name: `the-bucket-of-art-2020`
            -  Object name: Any (`*`)
            -  Will receive: `arn:aws:s3:::the-bucket-of-art-2020/*`
    -  Review policy
        -  Name:  `MyTestS3CustomPolicy`
        -  Create policy
    -  Look at the JSON
3.  Attach created policy to our role    

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "VisualEditor0",
            "Effect": "Allow",
            "Action": "s3:GetObject",
            "Resource": "arn:aws:s3:::the-bucket-of-art-2020/*"
        }
    ]
}
```                    

#####  100. AWS Policy Simulator

1.  Clean testing role
    -  leave only `AmazonS3ReadOnlyAccess`
2.  AWS Policy Simulator
    -  Google it
    -  [Testing IAM policies with the IAM policy simulator](https://docs.aws.amazon.com/IAM/latest/UserGuide/access_policies_testing-policies.html)
    -  [IAM Policy Simulator](https://policysim.aws.amazon.com/home/index.jsp?#)
3.  Testing role
    -  choose `FirstEC2Role`
    -  S3
    -  Actions:
        -  GetObject:  allowed
        -  ListObjects:  allowed
        -  PutObject:  denied
    -  Run Simulation      

#####  101. AWS CLI Dry Run

1.  google `aws ec2 api` for available commands
2.  SSH into EC2 that has `FirstEC2Role`
    -  `aws ec2 run-instances help`
3.  Copy AMI ID of running instance through EC2 console
    -  `ami-0b26bf4b43c8d995d`
4.  Run command
    -  `aws ec2 run-instances --dry-run --image-id ami-0b26bf4b43c8d995d`
        -  `You must specify a region. You can also configure your region by running "aws configure".`
        -  when I was configuring aws I did not set default region
    -  `aws ec2 run-instances --dry-run --image-id ami-0b26bf4b43c8d995d --instance-type t3.micro --region eu-north-1`
    -  Got an error
        -  `An error occurred (UnauthorizedOperation) when calling the RunInstances operation: You are not authorized to perform this operation. Encoded authorization failure message: rJfXElUnzsu28AikT92`                
5.  Modify role to enable run EC2s
    -  attach our policy `MyTestS3CustomPolicy`
    -  modify policy
        -  `MyTestS3CustomPolicy` -> Edit policy
            -  Visual editor -> Add additional permissions
            -  Service: EC2
            -  Access level -> in `Write` block
                -  `RunInstances`
            -  Resources: all resources (for simplicity)
        -  Review policy
        -  Save changes 
6.  Run after policy modification
    -  `aws ec2 run-instances --dry-run --image-id ami-0b26bf4b43c8d995d --instance-type t3.micro --region eu-north-1`
    -  `An error occurred (DryRunOperation) when calling the RunInstances operation: Request would have succeeded, but DryRun flag is set.`
    -  **OK**
7.  Testing without `--dry-run`
    -  `aws ec2 run-instances --image-id ami-0b26bf4b43c8d995d --instance-type t3.micro --region eu-north-1`
    -  created new instance and launched it

#####  102. AWS CLI STS Decode

1.  google `aws decode error message`
    -  [decode-authorization-message](https://docs.aws.amazon.com/cli/latest/reference/sts/decode-authorization-message.html)
2.  decode it
    -  `aws sts decode-authorization-message --encoded-message <encoded message>`       
    -  `aws sts decode-authorization-message --encoded-message MEhu185ys1NP7emfrQM58Ozd3-aWjA56nrYEJfzTOftZ66RWvRdE8G78P31nWULSlfHLGVs82ZUse8UfV2izv5rsr79JHus_qqRhESJBCpLdVqv5pP6L2VcXg0q-4DHbWa92kdLu9nDZ-Ab0wQIsUcTpyyUWjxaC3_XF_rCF0pPAYYIv2Dif6EmVjFqP50SPX0K1bV3WqoQX_nEqcbjRklIR3yGGPxCOJ6RbEkrJFBghLbkpc2Szzw4JZv2DggT0WxEoYzoie9dtD6lWjyqkbf6CKjR_obtUxZyeKk5Uzuxc_w79iNAGi5TICjp2IhKPVgSTsUPk3U538-ARwwVITAIuQjPGdZd99wq9GtCQ01D8SdqYwG5OB5HTncrkdOV7ObU5Gt2Mi3tzsezbnSDvOopMcgSZN9oUFJ6ACcA1FzOmT7rgcP1YQBw9EtMH_RpVBIf6VPHm02lgA8AwXBxn5dUnZZBX4HLMH9REuPLvBYVjGMMlYnqz8Dlp1srvPmidXMgEnmdaZTXsL8bFtmWjTnKKajxhhYnm7k21t2N3TBOBEyc-jWEORdVPkU47tAZfS3l0QYBoDfd-O4DvW2gJXmBikVznYRPvFam5TzAaNIhSev0LS7elkhHEqw6GdCof94EJTdrWteh5EHNOPkkrxqtCJN_Gy6EF8p4RE_lj-STlC7TFK6OpkIOiZVL0zN4`
    -  running desktop aws cli gave response
        -  [sts-desktop-response.json](https://github.com/artshishkin/aws-certified-developer-associate/blob/main/Section%2010%20-%20AWS%20CLI%2C%20SDK%2C%20IAM%20Roles%20%26%20Policies/sts-desktop-response.json)
    -  running on ec2 gave an error
        -  `An error occurred (AccessDenied) when calling the DecodeAuthorizationMessage operation: User: arn:aws:sts::392971033516:assumed-role/MyFirstEC2Role/i-001c5375a6b98650b is not authorized to perform: sts:DecodeAuthorizationMessage`
    -  to enable `sts:DecodeAuthorizationMessage` just add or modify policy
    -  run again -> **OK**
    -  copy message except curly braces 
    -  `echo `+ paste _copied message_ -> slightly better view
    -  copy->paste it into file [sts-response.json](https://github.com/artshishkin/aws-certified-developer-associate/blob/main/Section%2010%20-%20AWS%20CLI%2C%20SDK%2C%20IAM%20Roles%20%26%20Policies/sts-response.json)
    -  `Ctrl+Alt+L` -> better format
 
#####  103. AWS EC2 Instance Metadata

1.  Theory
    -  it allows EC2 instances to **learn about themselves**
    -  the URL is **http://169.254.169.254/latest/meta-data** (private in AWS, work only for EC2 instances)
    -  Metadata = INFO about EC2 instance
    -  Userdata = launch script of the EC2 instance
2.  Hands on
    -  ssh to ec2 instance
    -  `curl http://169.254.169.254`
        -  got list of api call versions
    -  `curl http://169.254.169.254/latest`
        -  `dynamic`
        -  `meta-data`
        -  `user-data` (may be absent)
            -  `curl http://169.254.169.254/latest/user-data` -> same as UserData section
    -  `curl http://169.254.169.254/latest/meta-data`
        -  if ends with `/` then has more options (like directory)
        -  `ami-id
            ami-launch-index
            ami-manifest-path
            block-device-mapping/
            events/
            hibernation/
            hostname
            iam/
            identity-credentials/
            instance-action
            instance-id
            instance-life-cycle
            instance-type
            local-hostname
            local-ipv4
            mac
            metrics/
            network/
            placement/
            profile
            public-hostname
            public-ipv4
            public-keys/
            reservation-id
            security-groups
            services/`
    -  `curl http://169.254.169.254/latest/meta-data/local-ipv4`
    -  `curl http://169.254.169.254/latest/meta-data/iam/security-credentials/MyFirstEC2Role`
        -  when we call api that needs credentials then behind the sceens ec2 call that endpoint to get
            -  "AccessKeyId"
            -  "SecretAccessKey"
            -  "Token" 
    -  if Role is not attached to the EC2 instance then there is no `meta-data/iam`
              
#####  104. AWS CLI Profiles

1.  AWS folder content
    -  `cat .aws/config` (Linux)
    -  `type .aws/config` (Windows PowerShell) 
        -  `[default]`
        -  `region = eu-north-1`
    -  `type .aws/credentials`
        -  `[default]`
        -  `aws_access_key_id = AKIAVW7XGDOWKR6HKHCY` (modified)
        -  `aws_secret_access_key = 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` (modified)
2.  Configuring profiles
    -  `aws configure` - configure default profiles
    -  `aws configure --profile my-first-profile`
        -  `AWS Access Key ID [None]: DUMMYaccess`
        -  `AWS Secret Access Key [None]: FOObarBUZZ`
        -  `Default region name [None]: ma-mars-2`
    -  `type .aws/credentials`
3.  Using profile
    -  `aws s3 ls` - use default     
    -  `aws s3 ls --profile default` - use default
    -  `aws s3 ls --profile my-first-profile` - use my-first-profile
        -  `Could not connect to the endpoint URL: "https://s3.ma-mars-2.amazonaws.com/"` - fake region
        -  changed region to `eu-north-1`
        -  `An error occurred (InvalidAccessKeyId) when calling the ListBuckets operation: The AWS Access Key Id you provided does not exist in our records.` - fake Access Key ID
        -  changed AccessKeyId to correct
        -  `An error occurred (SignatureDoesNotMatch) when calling the ListBuckets operation: The request signature we calculated does not match the signature you provided. Check your key and signing method.`
         
#####  105. AWS CLI with MFA

1.  For tests create new user `art_mfa`
    -  download `csv` with AccessKeyID and SecretAccessKey
2.  Create local aws profile
    -  `aws configure --profile art_mfa`
3.  Test working
    -  `aws s3 ls --profile art_mfa` - should list all buckets
4.  Enable MFA
    -  login as `art_mfa`
    -  `art_mfa@artarkatesoft` -> `My security credentials`
    -  `AWS IAM credentials` -> 
    -  `Multi-factor authentication (MFA)` -> Assign MFA Device
    -  Virtual MFA Device
    -  **or**
    -  IAM -> Users -> `art_mfa` -> Security Credentials -> Assigned MFA Device
5.  Using **Long Term** credentials `art_mfa` still **has access**
6.  Enabling **Short Term** MFA Token  
    -  Copy Assigned MFA device ARN
        -  `arn:aws:iam::392971033516:mfa/art_mfa`
    -  Getting session token
        -  `aws sts get-session-token help`
        -  `aws sts get-session-token --serial-number arn:aws:iam::392971033516:mfa/art_mfa --token-code 436488`
            -  `An error occurred (AccessDenied) when calling the GetSessionToken operation: MultiFactorAuthentication failed, unable to validate MFA code.  Please verify your MFA serial number is valid and associated with this user.`
        -  `aws --profile art_mfa sts get-session-token --serial-number arn:aws:iam::392971033516:mfa/art_mfa --token-code 641369`
            -  created [SessionToken](https://github.com/artshishkin/aws-certified-developer-associate/blob/main/Section%2010%20-%20AWS%20CLI%2C%20SDK%2C%20IAM%20Roles%20%26%20Policies/mfa-session-token.json)
7.  Create new profile for short-term access
    -  `aws configure --profile mfa`
    -  `aws s3 ls --profile mfa`
        -  Got an error
        -  `An error occurred (InvalidAccessKeyId) when calling the ListBuckets operation: The AWS Access Key Id you provided does not exist in our records.`
    -  modify profile
        -  in `.aws/credentials` add token
            -  `aws_session_token = IQoJb3JpZ2luX2VjEE...ewg0SEHQ==`
    -  `aws s3 ls --profile mfa` -> **OK**            

#####  106. AWS SDK Overview

#####  107. Exponential Backoff & Service Limit

#####  108. AWS Credentials Provider & Chain

#####  109. AWS Signature v4 Signing

1.  SigV4
    -  You should sign an AWS request using Signature V4 (SigV4)
2.  Request Examples
    -  HTTP Header option
        -  `Authorization: AWS4-HMAC-SHA256 Credential=...`
        -  `SignedHeaders=content-type;host;x-amz-date,`
        -  `Signature=63472y4hkj....`
        -  etc
    -  Query String option (ex: S3 pre-signed URLs)
        -  `GET https://iam.amazonaws.com/?Action=ListUsers&Version=2010-05-08 HTTP/1.1`
        -  `X-Amz-Algoriythm=AWS4-HMAC-SHA256&`            
        -  `X-Amz-Credential=AKI...&`            
        -  `X-Amz-Date=20201123T...&X-Amz-Expires=...&`            
        -  `X-Amz-Signature=37...`
        -  etc
    -  `https://the-bucket-of-art-2020.s3.eu-north-1.amazonaws.com/springsecurity.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEEMaDGV1LWNlbnRyYWwtMSJHMEUCIE1VzqonHlxCokHNf3udtSiENyo%2BXaPsrL9QWw3GCpq%2FAiEA1kn0juYDxw1%2FO%2Bpff6eZfib1wbWhmMUWq5ddMs7PEDAqmgMIvP%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARABGgwzOTI5NzEwMzM1MTYiDBNmewBcyJVOI3ypZiruAuXDtOgAFsxJio1U1HWzXtYEnO1PihURLkOGhrj5W2q4YOWo9KuYIwzC2i76rMQ6mrHItOhkYtHj57pf6r5nX%2BO7xmDPmBudUIO6DapA4VEWhCVP4Uefyf7Rgfx5W0LK1%2Bn8SOttjiLGTBR57LN%2FDPGSqlyzCgSxwDDsemutd6Iz70m30%2FeZBc%2FyTke0TOs%2BEvl1heOqpOwAFJ36otKDTRkbLhuQYyGzrRk8WQ6ARS%2Bsb3tR%2FsRRN%2BQAVL2TbKQwS2O8KsVRjJpVS0O6NEBzXlaBd9W%2Fo2LrpZXj%2B5cCk0kXqI1ux5CCNmEvOvwtaDnqxByw9bN13V%2FYpB7KQuFlcaehSTL5AkMhyDYVdY10dJ7aOqzSY6CBpsZi35J%2B59gIDm1J2azzHsQGzQ%2Bgt9K9UDkSbYdqLSbV5Mtxdjxyk0yMzTxLrjrcqoxkEsRLXimTg%2F%2Bt1dhl9ogq84VW89bXPH%2BgwKBxaT1OoKsxCCZgEzDu4%2B39BTqzAtrEt13RRemiV3ZosVuJhSf2CVR9K0OL81Qu060rRn4tnRnBkLeDDBS%2FBl8RJa2zzbwXZLRc%2FUGTGI2Qx5IQTbwEXkyMf9eXatsUNOmlojuCmnHNRnNHQTMVFCbjfRfg4F288OHvrIpjIZKy%2FZV0kmJV6xD0SvaY0uXa0Y1XEsvaUJnBhbvxoY5NswCgVReZdO07qiCJR3sWhNQJcD5vdh5XCkWEYsUl1vvq3IJEJDkj5FqHhC0Y5oJxS1ukYpJLoACXhQOYhRNOuo2ZqD4QamxqMHDdz6j2pPUhUxCyyuCklGHY8wejCUCLJGhun3qqC6bcihrxmLk3jr0OiSsULIFA891otn%2Fa3V9Xp8atZrX23XXAgHOlQ%2BxLAEqJh2lN2IKSRP81a3A%2BcJeJXeOAi4EoOYE%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20201123T105644Z&X-Amz-SignedHeaders=host&X-Amz-Expires=300&X-Amz-Credential=ASIAVW7XGDOWLCRX3FVQ%2F20201123%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=f7b1723633750222ab7f35bc6628b0e2c9b68022343d3b56013c2f86277bf385`

####  Section 11: Advanced S3 & Athena

#####  110. S3 MFA Delete

1.  Create new Bucket
    -  `mfa-demo-art`
    -  enable versioning
    -  upload file
    -  delete file
    -  delete market
2.  Copy MFA device serial number (arn)
3.  Create access keys
4.  Configure CLI
    -  `aws configure --profile root-art`
    -  use access keys from previous step
    -  test all ok -> s3 ls
5.  Configure MFADelete
    -  `aws s3api put-bucket-versioning --bucket mfa-demo-art --versioning-configuration Status=Enabled,MFADelete=Enabled --mfa "arn-of-mfa-device mfa-code" --profile root-art`
    -  **got an error**
    -  `An error occurred (AccessDenied) when calling the PutBucketVersioning operation: This operation may only be performed by the bucket owner`
    -  **MUST USE ROOT ACCOUNT**
    -  configure previous steps using ROOT account    
6.  Through Console try to delete file
    -  then delete `Delete marker`    
    -  `You can’t delete object versions because Multi-factor authentication (MFA) delete is enabled for this bucket.
        To modify MFA delete settings, use the AWS CLI, AWS SDK, or the Amazon S3 REST API`    
7.  Try to `Suspend versioning`
    -  option is inactive
8.  Disable MFADelete          
    -  `aws s3api put-bucket-versioning --bucket mfa-demo-art --versioning-configuration Status=Enabled,MFADelete=Disabled --mfa "arn-of-mfa-device mfa-code" --profile root-art`
    -  test deletion
    -  test suspend versioning
9.  Clean Up
    -  delete access keys              

#####  112. S3 Access Logs

1.  Create bucket `art-s3-access-logs` - where logs will be saved
2.  Create bucket `art-s3-monitored-bucket` - bucket every action in which will be monitored
3.  Modify `art-s3-monitored-bucket` for logging  
    -  Server access logging -> Edit
    -  Enable
    -  Target bucket: `art-s3-access-logs`
4.  Testing
    -  enable versioning
    -  add file
    -  delete file
    -  add file
    -  delete file
    -  permanently delete
5.  After a couple of hours look at the `art-s3-access-logs`
    -  got many logs files
    -  similar to [s3access02.log](https://github.com/artshishkin/aws-certified-developer-associate/blob/main/Section%2011%20-%20Advanced%20S3%20And%20Athena/s3access02.log)

#####  113. S3 Replication (Cross Region and Same Region)

1.  Theory
    -  `CRR` - Cross-Region Replication
    -  `SRR` - Same-Region Replication
    -  Must be enabled versioning in both buckets
    -  Buckets may be in different accounts
    -  Copying is **Asynchronous**
    -  Must give proper IAM permissions to S3
    -  After activating only new objects are replicated
    -  Any **DELETE** operation is **NOT REPLICATED**
        -  if you delete without version ID, it adds a delete marker, not replicated
        -  if you delete with version ID, it deletes in the source, not replicated
        -  **MODIFIED** we can enable `Replicate delete markers`
    -  There is **NO "chaining"**
        -  if bucket 1 has replication into bucket 2, which has replication into bucket 3
        -  then objects created in bucket 1 are **not replicated** into bucket 3
2.  Hands on
    -  create bucket `art-origin-bucket-in-stockholm`                  
    -  create bucket `art-replica-bucket-in-paris`
    -  upload file `file1` into origin
    -  activate versioning in both buckets
    -  Management (in origin)
        -  enable CRR
        -  entire bucket
        -  destination - replica bucket
        -  Replication rule name
            -  ReplicaDemoRule
        -  create new IAM role            
    -  look at the IAM role
        -  policy
    -  origin has `file1`       
    -  replica has **NO** `file1`
    -  upload new file `file2` into origin
    -  replica has `file2` TOO 
    -  delete
        -  from origin `file2`
        -  in replica file **is not** deleted
    -  enable `Replicate delete markers`
        -  `Delete markers created by S3 delete operations will be replicated. Delete markers created by lifecycle rules are not replicated.`
        -  delete file in origin
        -  will be created delete marker in origin
        -  same marker will be created in replica
        -  if we delete permanently this marker in origin it will still be present in replica

#####  114. S3 Pre-signed URLs

-  `aws s3 presign help`
-  `aws s3 presign s3://art-origin-bucket-in-stockholm/UC-MultithreadingParallelAsync-Dilip.pdf --expires-in 30`
-  got pre-signed URL for 30sec
-  `https://art-origin-bucket-in-stockholm.s3.eu-north-1.amazonaws.com/UC-MultithreadingParallelAsync-Dilip.pdf?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVW7XGDOWKR6HHHCY%2F20201124%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Date=20201124T124935Z&X-Amz-Expires=30&X-Amz-SignedHeaders=host&X-Amz-Signature=6f2d4bb42f6b01b2dd675b8c976c466855674d236735381e877f7c69d6f86092`
-  after expired got an error
```xml
<Error>
<Code>AccessDenied</Code>
<Message>Request has expired</Message>
<X-Amz-Expires>30</X-Amz-Expires>
<Expires>2020-11-24T12:50:05Z</Expires>
<ServerTime>2020-11-24T12:52:22Z</ServerTime>
<RequestId>B78A612EF182E2F3</RequestId>
<HostId>7eigN9J23CTU2hc+Rd6FuneD3MDoaymtq8a54ygG7Au9E3+PfopeazdaktUrCAKEuZxHoDzqpoE=</HostId>
</Error>
``` 
-  Stephane says it needs to configure
    -  `aws configure set default.s3.signature_version s3v4` - allow generated URL to be compatible with KMS encrypted object
    -  and you may have issues if not specify region, so
    -  `aws s3 presign s3://art-origin-bucket-in-stockholm/UC-MultithreadingParallelAsync-Dilip.pdf --expires-in 30 --region eu-north-1`   

#####  115. S3 Storage Tiers + Glacier

[Amazon S3 Storage Classes](https://aws.amazon.com/s3/storage-classes/)       
-  select file in a bucket
-  Action
-  Edit storage class

|Storage class|Designed for|Availability Zones|Min storage duration|Min billable object size|Monitoring and auto-tiering fees|Retrieval fees|
|---|---| :---: | :---: | :---: | :---: | :---: |
|Standard|Frequently accessed data| ≥ 3 | - | - | - | - |
|Standard-IA|Long-lived, infrequently accessed data|	≥ 3|	30 days|	128 KB|	-|  Per-GB fees apply|
|One Zone-IA|	Long-lived, infrequently accessed, non-critical data|	1	|30 days|	128 KB|	-|	Per-GB fees apply|
|Reduced redundancy|	Frequently accessed, non-critical data|	≥ 3|	-|	-|	-|	Per-GB fees apply|
|Intelligent-Tiering|	Long-lived data with changing or unknown access patterns|	≥ 3|	30 days|	-	|Per-object fees apply|	-|
|Glacier|	Long-term data archiving with retrieval times ranging from minutes to hours|	≥ 3|	90 days|	-	|-	|Per-GB fees apply|
|Glacier Deep Archive|	Long-term data archiving with retrieval times within 12 hours|	≥ 3|	180 days|	-	|-	|Per-GB fees apply|

#####  116. S3 Lifecycle Policies

Hands on:

-  Bucket -> Management -> Lifecycle
-  Create lifecycle rule -> MyFirstLifecycleRule
-  No tags or prefix
-  Current version
    -  Transition to Standart-IA after 30 days
    -  Transition to Glacier after 60 days
    -  Transition to Glacier Deep Archive after 150 days
-  Previous version    
    -  Transition to Standard-IA after 30 days
    -  Transition to Glacier Deep Archive after 365 days
-  Configure expiration
    -  expire current version: after 515 days
    -  permanently delete the previous version: after 730 days
    -  clean up incomplete multipart uploads after 7 days 
        
#####  119. S3 Event Notifications

1.  Create bucket `art-event-notifiaction-demo`
    -  versioning must be enabled
2.  Create notification   
    -  Events -> Add notification
    -  Name: `DemoNotificationSQS`
    -  Events: All objects create events
    -  Create SQS
        -  SQS Console
            -  Name: demo-s3-event
            -  Standard Queue
            -  Pick up ARN
    -  SQS queue ARN insert
    -  Save
    -  Got an Error
    -  SQS Console -> Permissions
        -  Effect: Allow
        -  Principal: everybody
        -  Action: SendMessage
3.  Testing
    -  upload file
    -  got 2 messages in a queue    
        -  service from aws to test connection
        -  message of our upload into s3
    -  View/Delete messages tot view content (deprecated)
    -  Receive messages -> Poll for messages
4.  Clean up
    -  delete SQS Queue
    -  delete bucket                

#####  121. Athena Hands On

1.  s3 bucket with access logs: `s3://art-s3-access-logs`
2.  Athena Management Console
    -  `Before you run your first query, you need to set up a query result location in Amazon S3. Learn more`
    -  Settings -> Query result location: `s3://aws-art-athena-results/stockholm/`
    -  [How do I analyze my Amazon S3 server access logs using Athena?](https://aws.amazon.com/premiumsupport/knowledge-center/analyze-logs-athena/)
    -  run queries from [athena-s3-access-logs.sql](https://github.com/artshishkin/aws-certified-developer-associate/blob/main/Section%2011%20-%20Advanced%20S3%20And%20Athena/athena-s3-access-logs.sql)
        -  create database
        -  create table        
        -  `select * from mybucket_logs where requester like '%art_admin';`
        -  Tables -> `my-bucket-logs` -> 3 dots -> preview table (it will create select statement)
        -  `select requesturi_operation, httpstatus, count(*) FROM mybucket_logs group by requesturi_operation, httpstatus;`
        -  `SELECT * FROM mybucket_logs WHERE httpstatus = '403';`
3.  Google:
    -  `athena analyze elb logs`
    -  `athena analyze cloudfront logs`
    -  etc
    -  [CSV Analysis with Amazon Athena](https://medium.com/avmconsulting-blog/csv-analysis-with-amazon-athena-b241f87f010c)
4.  CSV Athena
    -  create bucket `art-kate-library`
    -  make sure `library.csv`'s encoding is **utf-8**
        -  otherwise convert it (I used Notepad++) 
    -  put `library.csv` into `s3://art-kate-library/csv`
    -  in Athena Console create table
        -  use sql commands from [athena-csv-analyze.sql](https://github.com/artshishkin/aws-certified-developer-associate/blob/main/Section%2011%20-%20Advanced%20S3%20And%20Athena/athena-csv-analyze.sql)
    -  analyze data using SQL
         
####  Section 12: CloudFront

#####  124. CloudFront Hands On

1.  Create bucket
    -  `art-content-through-cloudfront-2020`
    -  upload some files into it
2.  Creating Distribution
    -  CloudFront Management Console
    -  create a distribution
    -  Web type
    -  Origin Domain name: our bucket name
    -  Origin Path: empty
    -  Restrict Bucket Access: **Yes**
    -  Origin Access Identity: Create a New Identity
        -  `access-identity-demo-cloudfront`
    -  Grant Read Permissions on Bucket: Yes, update bucket policy
    -  Viewer protocol policy: Redirect HTTP to HTTPS
    -  Allowed HTTP Methods: GET, HEAD
    -  Leave other parameters default
3.  While creating
    -  Security
        -  look at the `Origin Access Identity` (created)
    -  go to bucket
        -  Permissions -> Bucket Policy -> look through
4.  CloudFront Distributions
    -  domain name: `blabla.cloudfront.net`
    -  browse that `url/springsecurity.png`
        -  got an `Access Denied` error
        -  because of DNS Issue -> wait for about 3 hours
    -  to fix this make files public
        -  s3 console -> choose file -> make public 
        -  got an error
            `Failed to edit public access
             For more information, see the Error column in the Failed to edit table below.`
        -  bucket -> Block public access -> Edit
            -  `Block all public access` -> untick all -> confirm
        -  again: s3 console -> choose file -> make public
    -  `http://d14hl7alrjeva1.cloudfront.net/springsecurity.png` -> OK
    -  URL changes (307 Temporarily redirect)
    -  `https://art-content-through-cloudfront-2020.s3.eu-north-1.amazonaws.com/springsecurity.png` -> OK
5.  Wait for ~4 hours
    -  `http://d14hl7alrjeva1.cloudfront.net/springsecurity.png` -> OK
    -  DNS fixes and now no redirect
    -  make file private again `springsecurity.png`
        - s3 console -> choose file -> 
            -  Access control list -> Edit
            -  Everyone: untick Read
    -  make bucket private again
        -  permissions -> Block public access: all
6.  Testing access through CloudFront
    -  `http://d14hl7alrjeva1.cloudfront.net/springsecurity.png`
    -  now we can access to files in private s3 **ONLY** through CloudFront

#####  125. CloudFront Caching & Caching Invalidations - Hands On

1.  Upload `index.html`
2.  Visit `http://d14hl7alrjeva1.cloudfront.net/index.html` -> OK -> 
    -  CloudFront made cache
    -  Behaviors -> default -> Edit
        -  Cache Policy -> View policy details (ManagedCachingOptimized)
        -  Minimum TTL:          1
        -  Maximum TTL:          31536000
        -  Default TTL:          86400
3.  Update `index.html`
    -  s3 -> open -> ensure that file is new
4.  Visit `http://d14hl7alrjeva1.cloudfront.net/index.html`
    -  got **OLD** version of `index.html` (cached)
5.  Invalidate caches
    -  Invalidations -> create invalidation
    -  `*` - invalidate everything
    -  wait while it is in progress
    -  invalidation -> Details - to view details    
6.  Visit `http://d14hl7alrjeva1.cloudfront.net/index.html`
    -  updated

#####  126. CloudFront Security

1.  CloudFront Geo Restriction
    -  Whitelist
    -  Blacklist
2.  CloudFront and HTTPS
3.  Hands On
    -  OAI
        -  s3 -> Permissions -> Bucket policy
            -  only `arn:aws:iam::cloudfront:user/CloudFront Origin Access Identity E38HJLL6Y2K8VY` is Allowed to GetObject from s3
        -  CloudFront -> Security -> Origin Access Identity (OAI)
        -  Distributions -> Origins and Origin Groups
            -  Origins -> Edit
            -  Your Identities (make sure `access-identity-demo-cloudfront` is used)
    -  Behaviors
        -  Edit -> 
        -  Viewer Protocol Policy -> Redirect HTTP to HTTPS
    -  Restrictions
        -  Edit
            -  Restriction Type: Blacklist
            -  Countries: UA--Ukraine
        -  Visit
            -  got an error
            -  `The Amazon CloudFront distribution is configured to block access from your country.`
            -  `We can't connect to the server for this app or website at this time.`
            -  `There might be too much traffic or a configuration error.`
            -  `Try again later, or contact the app or website owner.` 
        -  VPN to USA
            -  try to access -> OK
        -  modify Restriction to be Whitelist in Ukraine     
                       
####  Section 13: ECS, ECR & Fargate - Docker in AWS

ECS - Elastic Container Service

#####  130. ECS Clusters

1.  Create Cluster
    -  ECS Management Service
    -  Clusters ->
    -  EC2 Linux plus Networking
    -  Cluster name: `cluster-demo`
    -  Provisioning Model: On-Demand (or Spot for cost saving)
    -  EC2 instance type: t3.micro
    -  Number of Instances: 1 (for now)
    -  Root EBS Volume Size (GiB): 30 (minimum available)
    -  Key Pair: `cert...`
    -  VPC: reuse VPC
    -  Subnets: choose all 3
    -  Security group inbound rule: from everywhere to 22 (SSH)
    -  Container instance IAM Role
        -  create new
2.  View ECS instances
    -  Clusters -> ECS Instances -> 
    -  EC2Instance
        -  CPU Available: 2048
        -  Memory Available: 957
        -  Agent version
        -  Docker version
    -  EC2 Console
        -  Auto Scaling group -> autocreated ASG
        -  Instance management
        -  if you want to scale your ASG you can do it here
        -  Launch configurations
            -  view User Data (it creates file `/etc/ecs/ecs.config` with `ECS_CLUSTER=cluster-demo  and ECS_BACKEND_HOST=`
        -  IAM -> ecsInstanceRole -> policy
    -  ssh into EC2
        -  `cat /etc/ecs/esc.config`
        -  `docker ps` -> container `amazon/amazon-ecs-agent:latest` is running
        -  `docker logs ecs-agent` (or use container id)

#####  131. ECS Task Definition

-  ECS Console ->
-  Create task definition
-  launch type compatibility: EC2
-  Name: `my-httpd`
-  Task Role: now is empty  (None)
-  Task Memory: 300
-  Task CPU: 250
-  Add container
    -  Name: `httpd`
    -  Image: `httpd:latest` <- from docker hub
    -  Memory Limits (MiB): 300
    -  Port mappings
        -  Host port: 8080
        -  Container port: 80
    -  Other leave default
    -  Add
-  Create
-  View JSON form of task definition 
            
#####  132. ECS Service

-  Clusters -> `cluster-demo`
-  Services -> Create
    -  Launch type: EC2
    -  Service name:  `httpd-service`
    -  Service type: REPLICA - you can run as many tasks as possible
    -  Number of task: 1
    -  Minimum healthy percent: 0
    -  Task placement: AZ Balanced Spread
    -  Next
    -  Load Balancer Type: None
    -  Enable service discovery integration: **untick** for now
    -  Next
    -  Create service
    -  View service
-  Modify security group to access to port 8080
-  go to `public_ip:8080` -> It Works!
-  ssh to ec2 -> `docker ps` -> view container `httpd` is running
    -  `curl localhost:8080` -> It Works!
-  Scaling service -> for example run 2 tasks
    -  Service -> update
    -  Number of tasks: 2
    -  Update service
    -  **BUT**
    -  Events:
    -  `service httpd-service was unable to place a task because no container instance met all of its requirements.`
    -  `The closest matching container-instance 5168782916c544ac83c45b1b3a8ef3ce is already using a port required by your task.`
    -  `For more information, see the Troubleshooting section.`
    -  for this we need to scale cluster
-  Scaling cluster
    -  Cluster : `cluster-demo`
    -  ECS Instances
    -  Button `Scale ECS Instances` (if present)
    -  _OR_
    -  Add additional ECS Instances using Auto Scaling or Amazon EC2.
        -  Auto Scaling -> Auto Scaling Groups
        -  Group details -> Edit
        -  Desired Capacity: 2
        -  Max capacity: 2          

#####  133. ECS Service with Load Balancers

1.  Changing task definition
    -  Task Definitions -> `my-httpd`
    -  Create New Revision
    -  Scroll down to container -> httpd
    -  Port Mapping -> Host post -> **EMPTY**
    -  Create
    -  View JSON format of Task Definition `my-httpd:2`
        -  hostPort: 0 <- means random
2.  Update service to use new Task Definition
    -  Service `httpd-service` -> Update
    -  Task Definition -> Revision 2 (latest)
3.  Monitor events
    - Service -> Events
    -  `service httpd-service has stopped 2 running tasks: task 29e94b2acde9455fa89e7b8686617088 task 7934789c28394fddb673562de94c87ca.`
    -  then
    -  `service httpd-service has started 4 tasks: task c5abbfd2bbee4291a38c586ef2d20b4e task 00bc74075a8c4d8ab0a22fc35eb9cc63 task e75fce0d3aba49249b84c26ebb5c432d task 243847c41fae4da385a9c47e9cb6f1ae.`
4.  View tasks
    -  cluster
    -  Tasks: 4 containers
    -  ECS Instances
        -  2 ec2 instances with 2 running containers each
5.  View `docker ps`
    -  ssh into ec2
    -  `docker ps` - view different ports
6.  Create new Load Balancer:
    -  Application Load Balancer
    -  Create New: `my-ecs-cluster-elb`
    -  all AZs
    -  Configure Security Groups: new `ecs-alb-sg` port 80 from everywhere
    -  Configure routing (no need to but have to)
        -  target group -> new -> `dummy-tg`
    -  Next -> Review -> Create
7.  Update Security Group
    -  you must enable ALB to talk to EC2
    -  EC2 Console -> Security Groups
    -  `EC2ContainerService-cluster-demo-EcsSecurityGroup`
    -  Edit Inbound Rules
    -  All traffic from SG of ALB (`ecs-alb-sg`)
    -  Description: Allow ALB to talk to any port on EC2 instance for dynamic port feature on ECS       
8.  Adding Load Balancer
    -  you can not update existing Service to use ALB
    -  Create new Service
        -  Name: `httpd-alb`
        -  Number of Tasks: 4
        -  Load Balancer:
            -  `my-ecs-cluster-elb`
        -  Container to load balance: `httpd:0:80` -> Add to Load Balancer
            -  Production listener port: 80
            -  Target group name: create new
            -  Path pattern: `/`
            -  Evaluation order: 1
            -  Health check path: `/`
        -  Next -> Next -> Create Service
9.  Disable old service
    -  `httpd-service` -> Update
    -  Number of tasks: 0
10.  Delete old service
    -  `delete me`
11.  Testing
    -  EC2 Console
    -  ELB DNS -> go -> It works!

#####  134. ECR - Part I

ECR - Elastic Container Repository

1.  Install Docker
    -  then verify `docker --version`
2.  Create [Dockerfile](https://github.com/artshishkin/aws-certified-developer-associate/blob/main/Section%2013%20-%20ECS,%20ECR%20&%20Fargate%20-%20Docker%20in%20AWS/Dockerfile)
3.  Build docker image
    -  `docker build -t my-httpd-image .` - `.` - search in current directory
4.  Create repository in ECR
    -  ECS Console
    -  Repositories -> Create repository
    -  Name: demo
    -  tag immutability: Disabled (for now)
    -  Create repo
5.  Push image to ECR
    -  View push commands
    -  `aws ecr get-login-password --region eu-north-1` - works for Windows 10 too
    -  `aws ecr get-login-password --region eu-north-1 | docker login --username AWS --password-stdin 392971033516.dkr.ecr.eu-north-1.amazonaws.com`
    -  `Login Succeded` - **COOL**
    -  `docker build -t demo .`
    -  `docker tag demo:latest 392971033516.dkr.ecr.eu-north-1.amazonaws.com/demo:latest`
    -  `docker push 392971033516.dkr.ecr.eu-north-1.amazonaws.com/demo:latest`
        -  OK
        -  `latest: digest: sha256:33ae349033c29d5918f3a22256aa445c28fd9f9822e491198f9aa40429015e6e size: 2197`
6.  We can Pull image from ECR
    -  `docker pull 392971033516.dkr.ecr.eu-north-1.amazonaws.com/demo:latest`
    
#####  135. ECR - Part II

1.  Modify Task Definitions
    -  `my-httpd` -> Create new revision
    -  Edit container
        -  image: full image name
        -  `392971033516.dkr.ecr.eu-north-1.amazonaws.com/demo` - IMAGE URI
        -  `392971033516.dkr.ecr.eu-north-1.amazonaws.com/demo:latest` - with TAG
2.  Cluster modification
    -  `cluster-demo` -> Services -> `httpd-alb`
    -  Update -> revision: 3 (latest)
3.  ECS Instances Count modification
    -  Cluster -> ECS Instances -> Auto Scaling ->
    -  `EC2ContainerService-cluster-demo-EcsIn...` 
    -  Desired Capacity: 2
4.  Load Balancers
    -  `my-ecs-cluster-elb`
    -  DNS: `http://my-ecs-cluster-elb-1778748836.eu-north-1.elb.amazonaws.com/`
    -  Hello world from custom Docker image
    -  `This image is running on ECS, here's some information about this container and task:`
    -  Refresh page -> DockerId changes (we have 4 instances on 2 EC2s)                   

#####  136. Fargate

1.  Create Cluster
    -  Networking only
    -  Name: `fargate-demo`
    -  VPC: do not create, use that we already have                       
2.  Create Service (attempt 1)
    -  our test definition is not compatible with old one
    -  `The selected task definition is incompatible with the selected launch type.`
    -  `Please create a compatible new revision or select a different task definition.`
    -  we need create new one
3.  Create new Task Definition
    -  Fargate
    -  `fargate-task-definition-demo`
    -  Task memory: 0.5 GB
    -  Task CPU: 0.25 vCPU
    -  Container name: `httpd`
    -  Image: `392971033516.dkr.ecr.eu-north-1.amazonaws.com/demo:latest`
    -  Memory limit: Hard Limit 512MiB
    -  Port mapping: 80 tcp
4.  Create Service (attempt 2)
    -  Cluster: `fargate-demo`
    -  Services -> Create
    -  Service name: `farget-service-demo`
    -  Number of tasks: 2
    -  Minimum healthy percent: 0
    -  Configure network
        -  select existing VPC
        -  all 3 subnets    
        -  Select existing security group: `EC2ContainerService-cluster-d...` (all incoming traffic from ELB, plus SSH)
        -  Load Balancer: ALB -> `my-ecs-cluster-elb`
        -  Container to load balance: -> Container name : port -> httpd:80:80 -> **Add to Load Balancer**
        -  Production listener port* : 80
        -  Target group name: create new (default choice)
        -  Path pattern: `/`
            -  BUT got an error
            -  `Path-pattern already in use for this listener`
            -  go to LoadBalancer -> `my-ecs-cluster-elb` -> Listeners -> Rules -> View/edit rules ->
            -  delete rule that we will not use anymore
        -  Evaluation order: 1
        -  Health check path: `/`
    -  Set Auto Scaling (do not adjust for now)
    -  Create service
5.  Testing
    -  Cluster : fargate-demo -> Tasks -> 2 tasks
    -  go to ELB DNS -> refresh -> DockerId changes             

#####  137. ECS IAM Deep Dive & Hands On

1.  Roles that created for us
    -  IAM -> Roles
    -  search `ecs` -> 4 roles
        - ecsInstanceRole
            -  attached to ec2 instances
            -  Trust relationships -> Trusted entities: `The identity provider(s) ec2.amazonaws.com` - **EC2**
            -  policy: `AmazonEC2ContainerServiceforEC2Role`
            -  allows to connect to ecs service
            -  to ecr (pull images from ecr)
            -  logs (log to CloudWatch using ecs service)
            -  base role that allow ECS **Agent** to perform everything it needs to do
        -  ecsServiceRole
            -  Trust relationships -> Trusted entities: `The identity provider(s) ecs.amazonaws.com` - **ECS**
            -  policy: `AmazonEC2ContainerServiceRole`
        -  ecsTaskExecutionRole
            -  Trust relationships -> Trusted entities: `The identity provider(s) ecs-tasks.amazonaws.com` - **ECS-Tasks**
            -  policy: `AmazonECSTaskExecutionRolePolicy`
            -  get images from ecr and send logs to CloudWatch
        -  AWSServiceRoleForECS
            -  Trust relationships -> Trusted entities: `The identity provider(s) ecs.amazonaws.com` - **ECS**
            -  policy: `AmazonECSServiceRolePolicy`
            -  ec2, elasticloadbalancing, route53, servicediscovery, autoscaling, logs, cloudwatch
2.  Create custom role
    -  IAM -> Roles -> Create role ->
    -  Elastic Container Service -> Select your use case ->
    -  **Elastic Container Service Task** -> Next (Permissions)
    -  AmazonS3ReadOnlyAccess
    -  Role name: `MyCustomECSTaskRoleToReadS3`
   
#####  138. ECS Task Placement and Constraints

1.  ECS Console
    -  `cluster-demo`
    -  Create new service: type EC2
    -  Task definition: `httpd`
    -  Service name: `demo-task-placement`
    -  Number of tasks: 2
    -  Task placements
        -  Binpack: 
            -  Strategy: binpack(MEMORY)
        -  One task per Host:
            -  Constraint: distinctInstance
        -  AZ Balanced Spread:
            -  Strategy: spread(attribute:ecs.availability-zone), spread(instanceId)
        -  AZ Balanced BinPack:
            -  Strategy: spread(attribute:ecs.availability-zone), binpack(MEMORY)
        -  Custom:
            -  choose strategies
                -  binpack, spread, random
            -  choose constraints
                -  distinctInstance, memberOf
               
#####  139. ECS Auto Scaling

1.  Capacity Provider
    -  ECS Console -> `cluster-demo`
    -  Capacity Providers -> Create
    -  Name: `CapacityDemo`
    -  ASG: `EC2ContainerService-cluster-demo-EcsInstanceAsg-1TJSQ84Q3ZVQ`
    -  Target capacity: 70% - when it reached then Launch more ec2 instances
    -  Managed termination protection: Disabled
    -  Create
2.  Modify ASG
    -  desired capacity: 2
    -  max capacity: 4
3.  Create new service
    -  name: `httpd-service-capacity`
    -  capacity provider strategy: Capacity Provider
    -  task-definition: `my-httpd`
    -  number of tasks: **10**
    -  Task placement: Custom -> ~~random~~ AZ Balanced Spread
    -  Next ->
    -  Load Balancer: no
    -  Service Discovery: no
    
    -  ~~Set Auto Scaling~~: (**skip it**)
        -  minimum: 1
        -  desired: 10                     
        -  maximum: 20
        -  IAM role for Service Auto Scaling: `AWSServiceRoleForApplicationAut...`
    
    -  Create
4.  Testing
    -  `httpd-service-capacity`: 10 tasks are running
    -  `cluster-demo` -> ECS Instances : 4 Instances
5.  Clean up
    -  delete `httpd-service-capacity`
    -  delete Capacity Provider
    -  ASG:
        -  max: 2
        -  desired: 0

#####  141. ECS Section Cleanup

-  Cluster `fargate-demo` -> `fargate-service-demo` -> delete me               
-  Cluster `cluster-demo` -> `httpd-alb` -> delete me
-  delete cluster `cluster-demo`
-  delete cluster `fargate-demo`
-  Repository -> delete repo `demo`
-  you may delete task definitions (but may leave them because you will not billed for them)
-  delete load balancer
-  Target groups: 
    -  dummy-tg -> delete
    -  all ecs target groups -> delete

####  Section 14: AWS Elastic Beanstalk

#####  144. Beanstalk First Environment

1.  Creating Application
    -  Elastic Beanstalk console
    -  Create application
    -  Name: `my-first-webapp-beanstalk`
    -  Platform: Node.js
    -  Application code: Sample
    -  Create application
2.  Events (Left panel)    
    -  S3 storage bucket for environment data
    -  Security group
    -  ~~Elastic IP (EIP)~~ 
    -  EC2 instances: 1 created and running
3.  Logs
    -  100 lines
4.  Health
5.  Monitoring
6.  Environments
    -  only 1 created `MyFirstWebappBeanstalk-env`
7.  Applications
    -  only 1 `my-first-webapp-beanstalk`         

#####  145. Beanstalk Second Environment

1.  Create a New Environment
    -  Web server environment: `MyFirstWebappBeanstalk-prod`    
    -  Domain: `myappinprodbyart`
    -  Description: `My beanstalk application in prod`
    -  Platform: same as for first environment
    -  Sample application
    -  Configure more options
        -  Configuration presets: High availability
        -  Capacity:
            -  ASG min 1, max 4
            -  Placement: all 3 AZs
            -  Save
        -  Load Balancer
            -  If you choose type of load balancer later you can not change it
        -  Database
            -  If you configure RDS in Beanstalk and later delete your application then you will loose DB
        -  Configuration presets: **High availability**
        -  Create Environment
2.  Testing
    -  EC2 Console
    -  Instances -> prod instance -> public DNS
    -  ASG: 2 groups:
        -  1 for dev (previous created): ~~1 max~~ (Stephane version) but for me 4 max???
        -  2 for prod: 4 max, 1 min, 1 desired
    -  Security groups
        -  search for beanstalk
        -  SG for LoadBalancer
 
#####  147. Beanstalk Deployment Modes Hands On

1.  Configure prod environment
    -  Elastic Beanstalk
    -  prod env
    -  Configuration
    -  Rolling updates and deployments -> Edit:
        -  Deployment policy: Immutable
        -  Apply
2.  Google `beanstalk sample application zip`
    -  [Tutorials and samples](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/tutorials.html)
    -  nodejs.zip
    -  download
    -  modify `index.html` -> background color make blue
    -  zip
3.  Update application
    -  Beanstalk console
    -  `MyFirstWebappBeanstalk-prod`
    -  Upload and deploy
    -  choose file
    -  Version label: `Blue version`
    -  Deploy
    -  View Events
    -  Health:
        -  one app is healthy
        -  another one in progress
    -  EC2 Console: ASG (temporary ASG) - immutable stack
    -  Health: 2 instances healthy
    -  open DNS -> blue app has been deployed
4.  Swap environment (emulation blue-green deployment)
    -  All Environments -> Choose one -> Actions
    -  Swap environment URLs
    -  View result (url to 2 environments)
5.  Revert swap back
    -  just swap one more time))           

#####  148. Beanstalk CLI and Deployment Process

#####  149. Beanstalk Lifecycle Policy Overview + Hands On

1.  Elastic Beanstalk Console
    -  `my-first-webapp-beanstalk` -> Application versions
    -  Settings -> Application version lifecycle settings
    -  Set the application versions limit by total count: 200
    -  **OR**
    -  Set the application versions limit by age: 180
    -  Retention: retain or delete from S3
    -  Save
2.  S3 console
    -  bucket `elasticbeanstalk-eu-north-1-392971033516`
    -  we have `2020335TQA-nodejs-v2-blue.zip` - we uploaded it

#####  150. Beanstalk Extensions

1.  Theory
    -  A zip file containing our code must be deployed to Elastic Beanstalk
    -  All the parameters set in the UI can be configured with code using files
    -  Requirements:
        -  in the .ebextensions/ directory in the root of source code
        -  YAML / JSON format
        -  .config extensions (example: logging.config)
        -  Able to modify some default settings using: option_settings
        -  Ability to add resources such as RDS, ElastiCache, DynamoDB, etc…
    -  Resources managed by .ebextensions get deleted if the environment goes away
2.  Hands on
    -  place `environment-variables.config` into `.ebextensions` directory
    -  set environment variables using `aws:elasticbeanstalk:application:environment`
    -  ZIP project
    -  Upload
    -  View -> Configuration -> Software -> Edit
    -  Environment properties -> View DB_URL, DB_USER from `environment-variables.config` file

#####  151. Beanstalk & CloudFormation

1.  CloudFormation console
    -  `awseb-e-qqeyynd66n-stack` - `AWS Elastic Beanstalk environment (Name: 'MyFirstWebappBeanstalk-prod' Id: 'e-qqeyynd66n')`
    -  `awseb-e-ssf3uza9gq-stack` - `AWS Elastic Beanstalk environment (Name: 'MyFirstWebappBeanstalk-env' Id: 'e-ssf3...`
    -  go to `awseb-e-qqeyynd66n-stack` - CloudFormation Stack
        -  Resources -> all the resources that CloudFormation made for us
        -  9 resources for `dev` (aka `env`)
        -  16 resources for `prod`

#####  152. Beanstalk Cloning

1.  Theory
    -  Clone an environment with the exact same configuration
    -  Useful for deploying a “test” version of your application
    -  All resources and configuration are preserved:
        -  Load Balancer type and configuration
        -  RDS database type (but the data is not preserved)
        -  Environment variables
    -  After cloning an environment, you can change settings        
2.  Hands on
    -  choose `env` environment
    -  clone
    -  play with
    -  terminate
    
#####  153. Beanstalk Migrations

1.  LoadBalancer
    -  After creating an Elastic Beanstalk environment, you cannot change the Elastic Load Balancer type (only the configuration)
    -  To migrate:
        1.  save configuration of desired environment
        2.  create a new environment with the same configuration except LB (can’t clone)
        3.  deploy your application onto the new environment (just choose right version while configuring) 
        4.  perform a CNAME swap or Route 53 update
2.  RDS with Elastic Beanstalk
    -  RDS can be provisioned with Beanstalk, which is great for dev / test
    -  This is not great for prod as the database lifecycle is tied to the Beanstalk environment lifecycle
    -  The best for prod is to separately create an RDS database and provide our EB application with the connection string
    -  Decouple RDS:
        1.  Create a snapshot of RDS DB (as a safeguard)
        2.  Go to the RDS console and protect the RDS database from deletion
        3.  Create a new Elastic Beanstalk environment, without RDS, point your application to existing RDS
        4.  perform a CNAME swap (blue/green) or Route 53 update, confirm working
        5.  Terminate the old environment (RDS won’t be deleted)
        6.  Delete CloudFormation stack (in DELETE_FAILED state)        

#####  154. Beanstalk with Docker

1.  Create new Environment with Docker
    -  Name: `...-docker`
    -  Multiple Docker
    -  Sample Application
    -  Create Environment
2.  Download sample code to view
    -  [Tutorials and samples](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/tutorials.html)
    -  Multicontainer Docker – docker-multicontainer-v2.zip
    -  Docker.aws.json looks very similar to ECS Task Definition
3.  View
    -  ECS Console -> created ECS Cluster `awseb-MyFirstWebappBeanstalk-docker-wvi5dhyinm`
    -  Tasks -> 1 is running
    -  ASG was created too
    -  Task Definition
        -  Builder
        -  JSON (a lot like `Dockerrun.aws.json`)
4.  Terminate environment

####  Section 15: AWS CICD: CodeCommit, CodePipeline, CodeBuild, CodeDeploy

#####  160. CodeCommit Hands On Part I

1.  Create repository
    -  CodeCommit console
    -  Create Repository: `my-node-js`
    -  Create
2.  Upload a file
    -  upload `index.html` (same as in previous section)
3.  Notifications
    -  Settings -> Notifications
    -  Create notification rule
    -  Name: `DemoNotificationRule`
    -  Events that trigger notifications: All
    -  Create target: SNS topic -> `codecommit-lab`
    -  Submit
4.  Triggers
    -  Settings -> Triggers
    -  Create trigger: `DemoTrigger`
    -  Events: `Push to existing branch`
    -  Branch name: master
    -  Choose the service to use: Amazon SNS
        -  SNS topic: `codecommit-lab`
    -  Create trigger    

#####  161. CodeCommit Hands On Part 2

1.  Generate credentials
    -  IAM -> Users -> art_admin -> Security credentials
    -  SSH keys for AWS CodeCommit (do it by yourself)
    -  HTTPS Git credentials for AWS CodeCommit (we will use this)
        -  Generate credentials -> Download credentials       
2.  Clone HTTPS
    -  CodeCommit -> Repo ->  `my-node-js`
    -  Clone HTTPS
    -  `https://git-codecommit.eu-north-1.amazonaws.com/v1/repos/my-node-js`
3.  Install git
    -  `git --version` - make sure all ok
4.  Cloning repo
    -  `git clone https://git-codecommit.eu-north-1.amazonaws.com/v1/repos/my-node-js`
    -  enter username and password
5.  Copy files from nodejs folder (from previous section)
6.  Git
    -  `git status`
    -  `git add .`
    -  `git commit -m "added missing file to repository"`
    -  `git push`
7.  Verify files are push
    -  CodeCommit -> Repo -> `my-node-js` -> Code
    
#####  163. CodePipeline Hands On

1.  Creating CodePipeline
    -  create pipeline: `MyFirstPipeline`
    -  **New** service role
    -  Other settings: Default
    -  Next
    -  Source provider:
    -  CodeCommit: our repo
    -  Change detection options: CloudWatch
    -  Skip build stage
    -  Deploy provider: Elastic Beanstalk
        -  Environment name - our dev env
    -  Create pipeline
    -  Started Source -> then Deploy -> All OK
2.  Testing 
    -  CodeCommit -> modify `index.html` -> make background red
    -  commit
    -  view pipeline status
    -  view environment url (EIP) -> it is RED -> OK
    -  go to Beanstalk -> Application versions -> added 2 versions (1 from first commit, 2 - from second manual commit)
3.  Extending pipeline
    -  `MyFirstPipeline` -> Edit
    -  After `Deploy` -> Add stage `DeployToProd`
    -  Add action group
        -  `ManualApproval`
        -  Configure the approval request: leave all default
    -  Add another Action group
        -  `DeployToBeanstalk`
        -  input artifacts: `SourceArtifact`
    -  Can Add parallel actions
    -  Done
    -  Save
4.  Testing
    -  create new commit
    -  from red to yellow
    -  Manual approval
        -  Review -> `Changes look great` -> Approve
    -  Starting deploy to prod environment    
    
#####  165. CodeBuild Hands On Part I

1.  Create Build Project
    -  CodeBuild ->  Getting Started -> 
    -  Create project: `MyBuildProject`
    -  Description: `Testing for *Congratulations* in CodeCommit`
    -  Source provider: CodeCommit
    -  Repo:  `my-node-js`
    -  Environment image: Managed
    -  Operating system: Ubuntu
    -  Runtime: Standard
    -  Image: standard:4.0
    -  New service role
    -  Additional parameters
        -  Timeout: 5 minutes
    -  VPC: do not select any
    -  Buildspec:
        -  `buildspec.yml` -> may be configured to be in diffenent directory (not only root)
    -  Artifacts
        -  could store artifacts in S3
        -  No Artifacts for now
        -  Additional config
            -  Encryption key
            -  Cache
    -  Logs:
        -  CloudWatch: On
        -  S3: Off
    -  Create build project
2.  Building
    -  Start build
    -  Fail:
        -  Phase details
        -  `DOWNLOAD_SOURCE | Failed  |  YAML_FILE_ERROR: YAML file does not exist`
        -  No `buildspec.yml`  
        -  Build logs
```
[Container] 2020/12/02 13:41:59 Waiting for agent ping
[Container] 2020/12/02 13:42:01 Waiting for DOWNLOAD_SOURCE
[Container] 2020/12/02 13:42:06 Phase is DOWNLOAD_SOURCE
[Container] 2020/12/02 13:42:06 CODEBUILD_SRC_DIR=/codebuild/output/src761570967/src/git-codecommit.eu-north-1.amazonaws.com/v1/repos/my-node-js
```

#####  166. CodeBuild Hands On Part 2

1.  Create build specification
    -  CodeCommit -> Add file -> Create file
    -  Filename: `buildspec.yml`
    -  insert content of `buildspec.yml`
    -  Commit changes
2.  Build project again
    -  CodeBuild -> `MyBuildProject` -> Start build
    -  Error -> wrong version
    -  YAML_FILE_ERROR: invalid buildspec `version` specified: 0.4, see documentation 
    -  changed version to 0.2
    -  Status:  Succeeded
3.  View result
    -  Phase Details
    -  Build Logs
    -  View entire log -> CloudWatch
4.  CodeBuild Pipeline integration
    -  CodePipeline -> `MyFirstPipeline` -> Edit
    -  Add stage `BuildAndTest`
    -  Add action group
        -  Action name: `TestForCongratulations`
        -  Provider: CodeBuild
        -  Input Artifacts: SourceArtifact
        -  Project name: `MyBuildProject`
        -  Output artifacts: `OutputOfTest`
        -  Done -> Done
    -  Save
5.  Testing
    -  modify `index.html`
    -  change `Congratulations` to `Horrible`
    -  commit
    -  Build FAILED
    -  `COMMAND_EXECUTION_ERROR: Error while executing command: grep -Fq "Congratulations" index.html. Reason: exit status 1`
    -  change `Horrible` to `Congratulations CodeBuild`
    -  Pipeline
        -  `Status Succeeded`
    
#####  167. CodeBuild in VPC

1.  Theory
    -  By default, your CodeBuild containers are launched outside your VPC
        -  Therefore, by default it cannot access resources in a VPC
    -  You can specify a VPC configuration:
        -  VPC ID
        -  Subnet IDs
        -  Security Group IDs
    -  Then your build can access resources in your VPC (RDS, ElastiCache, EC2, ALB..)
    -  Use cases: integration tests, data query, internal load balancers       
2.  Hands on
    -  CodeBuild -> `MyBuildProject`
    -  Edit -> Environment
    -  Additional configuration
        -  VPC: my vpc
        -  Subnets: all 3
        -  Security group
        -  Shows message
        -  `The VPC with ID vpc-d03187b9 might not have an internet connection because the provided subnet with ID subnet-ade616e0 is public. Provide a private subnet with the 0.0.0.0/0 destination for the target NAT gateway and try again.`              
        -  It is fine. We do not want internet connectivity for this build
    -  **Cancel** for now. We do not want VPC CodeBuild for now 
                  
#####  169. CodeDeploy Hands On

1.  Create 2 needed roles
    -  IAM console ->
    -  Create role
    -  CodeDeploy
        -  Select your use case: `CodeDeploy`
        -  Permissions
        -  Look through policy `AWSCodeDeployRole`
        -  Review:
            -  Role name: `CodeDeployServiceRole`
            -  Create role
    -  EC2 (CodeDeployAgent will be running on EC2 and must pull source code from S3)
        -  Filter policy: `S3`
        -  Choose `AmazonS3ReadOnlyAccess`
        -  Role Name: `EC2InstanceRoleForCodeDeploy`
2.  Create CodeDeploy Application  
    -  CodeDeploy management console
    -  Applications -> Create application
    -  App name: `MyDemoApplication`
    -  Compute platform: `EC2`
3.  Create EC2 Instance with `codedeploy-agent` to run an app on it
    -  IAM role: `EC2InstanceRoleForCodeDeploy`
    -  No User Data
    -  Security Group: new
        -  Inbound: port 80 from everywhere
    -  Start it
    -  ssh into EC2
    -  install `codedeploy-agent`
        -  use [commands.sh](https://github.com/artshishkin/aws-certified-developer-associate/blob/main/Section%2015%20-%20AWS%20CICD%20-%20CodeCommit%2C%20CodePipeline%2C%20CodeBuild%2C%20CodeDeploy/codedeploy/commands.sh)
    -  **OR**
    -  use same commands in User Data;)
    -  **Create Tag** 
        -  Environment: Dev (we may change key and value to everything we want)
4.  Create deployment group
    -  CodeDeploy console
    -  Create deployment group
        -  name: `DevelopmentInstances`
        -  Service role: `CodeDeployServiceRole`
        -  Deployment type: `In-place`
        -  Env config: Amazon EC2 instances
            -  Tag group 1: `Environment`:`Dev`
        -  Deployment settings: All at once
        -  Disable Load Balancer
        -  Create deployment group   
5.  Upload App into S3
    -  create bucket `art-codedeploy-2020`
    -  upload archive `SampleApp_Linux.zip`
        -  view `appspec.yml`
    -  copy S3 URI: `s3://art-codedeploy-2020/SampleApp_Linux.zip`
6.  Create deployment
    -  CodeDeploy -> Applications -> MyDemoApplication
    -  Deployment group: `DevelopmentInstances`
    -  Revision type: S3
    -  Revision location:
        -  S3 URI: `s3://art-codedeploy-2020/SampleApp_Linux.zip`
    -  Create deployment
7.  Testing  
    -  View progress
        -  Deployments lifecycle events: View events:
            -  ApplicationStop
            -  DownloadBundle
            -  BeforeInstall
            -  Install
            -  AfterInstall
            -  ApplicationStart
            -  ValidateService    
    -  Visit EC2 public IP -> OK    

#####  172. CodeStar - Hands On

1.  CodeStar management console
    -  create project
    -  create service role
    -  template:
        -  Python on Elastic Beanstalk
    -  Project name: `DemoCodestar`
    -  repo: CodeCommit
    -  Create project
2.  Wait a bit
3.  View result
    -  CloudFormation
        -  Resources
    -  CodeStar -> Project resources
        -  CodeCommit Repository
        -  CodePipeline
        -  CodeBuild
        -  Develop with AWS Cloud9
        -  Develop with other IDEs
        -  External issue tracking - **Jira**
        -  Team members
        -  View application
4.  Elastic Beanstalk
5.  Delete project
                             
#####  172. CodeStar - Java Hands On

1.  Create Java Spring App           
    -  Application type: Web service (Spring Boot application WAR)
    -  **OR**
    -  Application type: Web application (Spring application WAR)
    -  AWS service: AWS Elastic Beanstalk
2.  View simple source code 
3.  Modify project
    -  `git clone ...`
    -  Create project from existing source
    -  refactor, modify
    -  `git commit -m blabla`
    -  `git push`
4.  CodePipeline starts build and deploy         

#####  Trying on-premises instances with CodeDeploy (169)

[Tutorial: Deploy an application to an on-premises instance with CodeDeploy (Windows Server, Ubuntu Server, or Red Hat Enterprise Linux)](https://docs.aws.amazon.com/codedeploy/latest/userguide/tutorials-on-premises-instance.html)

-  Install Ubuntu Server 20.04LTS
-  SSH into it
-  [Install the CodeDeploy agent](https://docs.aws.amazon.com/codedeploy/latest/userguide/codedeploy-agent-operations-install.html)
-  [Install the CodeDeploy agent for Ubuntu Server](https://docs.aws.amazon.com/codedeploy/latest/userguide/codedeploy-agent-operations-install-ubuntu.html)

-  `sudo apt-get update`
-  `sudo apt-get install ruby`
-  `sudo apt-get install wget`
-  `cd /home/art` - (was `cd /home/ubuntu` in tutorial) - must be username
    -  `In the fifth command, /home/ubuntu represents the default user name for an Ubuntu Server instance. If your instance was created using a custom AMI, the AMI owner might have specified a different default user name.`
-  `wget https://bucket-name.s3.region-identifier.amazonaws.com/latest/install`
    -  for `bucket-name` and `region-identifier` use
    -  [Resource kit bucket names by Region](https://docs.aws.amazon.com/codedeploy/latest/userguide/resource-kit.html#resource-kit-bucket-names)
    -  `wget https://aws-codedeploy-eu-north-1.s3.eu-north-1.amazonaws.com/latest/install`
    -  `chmod +x ./install`
    -  `sudo ./install auto > /tmp/logfile`
    -  `sudo service codedeploy-agent status`
        -  must be `running`

-  [Use the register command (IAM user ARN) to register an on-premises instance](https://docs.aws.amazon.com/codedeploy/latest/userguide/instances-on-premises-register-instance.html#instances-on-premises-register-instance-1-install-cli)

Step 1: Install and configure the AWS CLI on the on-premises instance

    a)  Install the AWS CLI on the on-premises instance. Follow the instructions in Getting set up with the AWS command line interface in the AWS Command Line Interface User Guide.
        -  `aws --version`
    b)  Configure the AWS CLI on the on-premises instance. Follow the instructions in Configuring the AWS command line interface in AWS Command Line Interface User Guide.

-  create policy `CodeDeployAgentOnPremises`  
-  create user with this policy
    -  IAM console -> Users -> Add User
    -  User name: `on_premises`
    -  Access type: Programmatic access (only)
    -  Set permissions: Attach existing policies directly -> `CodeDeployAgentOnPremises`
    -  create user
    -  Download CSV -> close
    
-  configure aws cli
    -  `aws configure`
    -  enter all required fields
    -  config location: `ls ~/.aws`

Step 2: Call the register command    
    
-  `aws deploy register --instance-name MyFirstOnPremisesInstance`
-  **OR** with optional fields
-  `aws deploy register --instance-name MyFirstOnPremisesInstance --iam-user-arn arn:aws:iam::392971033516:user/on_premises --tags Key=Name,Value=MyFirstInstance-OnPrem --region eu-north-1`
-  the answer was
    -  `Registering the on-premises instance... DONE
        Adding tags to the on-premises instance... DONE
        Copy the on-premises configuration file named codedeploy.onpremises.yml to the on-premises instance, and run the following command on the on-premises instance to install and configure the AWS CodeDeploy Agent:
        aws deploy install --config-file codedeploy.onpremises.yml`
-  To register tags later, call the [add-tags-to-on-premises-instances](https://docs.aws.amazon.com/cli/latest/reference/deploy/add-tags-to-on-premises-instances.html) command.
-  **OR** through console
-  CodeDeploy -> On-premises instances -> `MyFirstOnPremisesInstance` YUU-HUUU)
-  Add Tag: Environment: DevOnPrem

[Use the register-on-premises-instance command (IAM user ARN) to register an on-premises instance](https://docs.aws.amazon.com/codedeploy/latest/userguide/register-on-premises-instance-iam-user-arn.html)

[Step 4: Add a configuration file to the on-premises instance](https://docs.aws.amazon.com/codedeploy/latest/userguide/register-on-premises-instance-iam-user-arn.html#register-on-premises-instance-iam-user-arn-4)

-  Create a file named `codedeploy.onpremises.yml` in the following location on the on-premises instance: `/etc/codedeploy-agent/conf`
```yaml
---
aws_access_key_id: secret-key-id
aws_secret_access_key: secret-access-key
iam_user_arn: iam-user-arn
region: supported-region
```

-  Redeploy
-  Got an error
```
Event details
Error code
ScriptFailed
Script name
scripts/install_dependencies
Message
Script at specified location: scripts/install_dependencies run as user root failed with exit code 127
```

```
LifecycleEvent - BeforeInstall
Script - scripts/install_dependencies
[stderr]/opt/codedeploy-agent/deployment-root/ab3ef8de-afd7-4acd-9be2-cd0c40d2187e/d-7L45WVF06/deployment-archive/scripts/install_dependencies: line 2: yum: command not found
```

##### CodeDeploy and Red Hat Enterprise Linux EC2

[Install the CodeDeploy agent for Amazon Linux or RHEL](https://docs.aws.amazon.com/codedeploy/latest/userguide/codedeploy-agent-operations-install-linux.html)

-  Creating RHEL EC2 instance for CodeDeploy
    -  use [UserData](https://github.com/artshishkin/aws-certified-developer-associate/blob/main/Section%2015%20-%20AWS%20CICD/codedeploy_RHEL/UserData.sh)
    -  IAM role:  `EC2IstanceRoleForCodeDeploy`
    -  Auto-assign Public IP: Enable
    -  Security group:  `EC2forCodeDeploy-sg` (created before)
    -  Tags: 
        -  Environment: `Dev`
        -  Name: `CodeDeploy_RHEL_UserData`
-  Redeploy application
    -  CodeDeploy -> Deployments
    -  last deployment -> Retry deployment -> **OK**
    
#####  CodeDeploy and Red Hat Enterprise Linux On-Premises

1.  Install RHEL
2.  Configure user to be in sudoers
    -  [How to enable sudo on Red Hat Enterprise Linux](https://developers.redhat.com/blog/2018/08/15/how-to-enable-sudo-on-rhel/)
    -  Become root by running: `su`
    -  Run `usermod -aG wheel your_user_id`
        -  `usermod -aG wheel art`
    -  Log out and back in again
        -  `exit` -> from `root`
        -  `exit` -> from `art`
        -  log in as `art`
3.  Check required software
    -  httpd: `which httpd` - present
    -  aws cli: `which aws` - absent
    -  wget:  `which wget` - present
    -  ruby: absent
4.  [Install AWS CLI version 2](https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2-linux.html)
    -  `curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"`
    -  `unzip awscliv2.zip`
    -  `sudo ./aws/install`
    -  `aws --version` - check everything is OK
5.  Configure the AWS CLI on the on-premises instance
    -  `aws configure`
    -  enter all required fields from user `on_premises` (created early in section `Trying on-premises instances with CodeDeploy (169)`)
    -  config location: `ls ~/.aws`
6.  [Install the CodeDeploy agent for Amazon Linux or RHEL](https://docs.aws.amazon.com/codedeploy/latest/userguide/codedeploy-agent-operations-install-linux.html)
    -  `sudo yum update`
    -  `sudo yum install ruby`
    -  `sudo yum install wget` (no need)
    -  `wget https://aws-codedeploy-eu-west-3.s3.eu-west-3.amazonaws.com/latest/install`
    -  `chmod +x ./install    #make it executable`
    -  `sudo ./install auto   #install`
    -  `sudo service codedeploy-agent status`
        -  must be `running`
7.  Create a file [codedeploy.onpremises.yml](https://github.com/artshishkin/aws-certified-developer-associate/blob/main/Section%2015%20-%20AWS%20CICD/codedeploy_RHEL_onPrem/codedeploy.onpremises.yml)
8.  Deregister unused OnPremises instance
    -  Step 9 will throw an error
        -  `Registering the on-premises instance... ERROR`
        -  `An error occurred (IamUserArnAlreadyRegisteredException) when calling the RegisterOnPremisesInstance operation: The on-premises instance could not be registered because the request included an IAM user ARN that has already been used to register an instance. Include either a different IAM user ARN or IAM session ARN in the request, and then try again.`
        -  `Register the on-premises instance by following the instructions in "Configure Existing On-Premises Instances by Using AWS CodeDeploy" in the AWS CodeDeploy User Guide.`
    -  We use the same user as previously with Ubuntu Server
    -  So we must either create new user for our RHEL or deregister `MyFirstOnPremisesInstance`
    -  `aws deploy deregister-on-premises-instance --instance-name MyFirstOnPremisesInstance`
9.  Register OnPrem instance
    -  `aws deploy register --instance-name RHEL_OnPremInstance`
    -  **OR** with optional fields
    -  `aws deploy register --instance-name RHEL_OnPremInstance --iam-user-arn arn:aws:iam::392971033516:user/on_premises --tags Key=Name,Value=MyRHELInstance-OnPrem Key=Environment,Value=DevOnPrem --region eu-north-1`
    -  the answer was
        -  `Registering the on-premises instance... DONE`
        -  `Adding tags to the on-premises instance... DONE`
        -  `Copy the on-premises configuration file named codedeploy.onpremises.yml to the on-premises instance, and run the following command on the on-premises instance to install and configure the AWS CodeDeploy Agent:`
        -  `aws deploy install --config-file codedeploy.onpremises.yml`
    -  To register tags later, call the [add-tags-to-on-premises-instances](https://docs.aws.amazon.com/cli/latest/reference/deploy/add-tags-to-on-premises-instances.html) command.
    -  **OR** through console
    -  CodeDeploy -> On-premises instances -> `RHEL_OnPremInstance`
    -  Add Tag: `Environment`: `DevOnPrem`
10.  Install configuration file from step 7
    -  `aws deploy install --config-file codedeploy.onpremises.yml`
        -  answer
        -  `Only Ubuntu Server, Red Hat Enterprise Linux Server and Windows Server operating systems are supported.`
        -  (does not work??? F__K - I have RHEL but **NOT SERVER** version?)
    -  we can view it at location on the on-premises instance: `/etc/codedeploy-agent/conf`
        -  `ls /etc/codedeploy-agent/conf` - **no** `codedeploy.onpremises.yml` there - WTF
    -  then copy that config file to  `/etc/codedeploy-agent/conf`
        -  `sudo cp codedeploy.onpremises.yml /etc/codedeploy-agent/conf`
11.  Redeploy Application
    -  CodeDeploy console -> Deployments -> last -> Retry deployment
    -  **Success** for `RHEL_OnPremInstance`
12.  Testing result
    -  test through ssh:
        -  `curl localhost` -> OK -> shows `index.html`
    -  test in browser on RHEL
        -  `localhost` -> OK -> shows `index.html`                 
    -  test in browser from local network:
        -  192.168.1.98 (my RHEL instance) -> Timeout
        -  Reason - closed port 80 on RHEL
13.  Open port 80 on RHEL for our app
    -  [How to open http port 80 on Redhat 7 Linux using firewall-cmd](https://linuxconfig.org/how-to-open-http-port-80-on-redhat-7-linux-using-firewall-cmd)
    -  `sudo firewall-cmd --zone=public --add-port=80/tcp --permanent`
    -  `sudo firewall-cmd --reload`
14.  Testing FINAL result
    -  test in browser from local network:
        -  192.168.1.98 (my RHEL instance) -> **OK**

####  Section 16: AWS CloudFormation

#####  183. CloudFormation Overview

**Infrastructure as Code** 

#####  184. CloudFormation Create Stack Hands On

1.  CloudFormation management console
    -  CloudFormation -> Stacks -> `awseb-e-r3gjhnb9pw-stack` (stack for dev environment)
    -  Template -> JSON
    -  View in Designer
2.  CloudFormation -> Choose region `us-east-1`
    -  Create stack
    -  Prepare template: `Template is ready`
    -  Specify template:  `Upload a template file`
    -  Choose file: `0-just-ec2.yaml`        
    -  Next
    -  Name: `first-stack` -> Next
    -  Create stack
3.  View
    -  `CREATE_IN_PROGRESS` -> Update -> `CREATE_COMPLETE`
    -  EC2 is running `MyInstance`
    -  Tags:
        -  `aws:cloudformation:logical-id	MyInstance`
        -  `aws:cloudformation:stack-name	first-stack`
        -  `aws:cloudformation:stack-id	arn:aws:cloudformation:us-east-1:392971033516:stack/first-stack/c07df0c0-3bc4-11eb-a687-0ea3a6460f01`   
    -  CloudFormation -> Stacks -> `first-stack` -> Resources
    -  Template -> View in Designer   

#####  185. CloudFormation Update and Delete Stack Hands On

1.  View `1-ec2-with-sg-eip.yaml`
2.  Update stack
    -  CloudFormation ->
    -  `first-stack` -> Update
    -  Replace current template -> Upload a template file `1-ec2-with-sg-eip.yaml`
    -  Next
    -  Parameters:
        -  SecurityGroupDescription:  enter `This is a cool security group`
    -  Next
    -  Change set preview
        -  3 `Add`
        -  ec2 -> `Modify`, Replacement: true
    -  Update stack
    -  UPDATE_IN_PROGRESS
    -  CREATE_IN_PROGRESS (3 elements)
    -  ...
    -  UPDATE_COMPLETE_CLEANUP_IN_PROGRESS
    -  DELETE_IN_PROGRESS
    -  Created 2 security groups
    -  Created EIP
3.  Delete stack
    -  choose stack -> delete
    -  deletion made in right order    
    
#####  187. CloudFormation Resources

[AWS resource and property types reference](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-template-resource-type-ref.html)

-  AWS::EC2::Instance

#####  188. CloudFormation Parameters

Reference function:
-  `Fn::Ref`
-  `!Ref`

Can be used to reference `Parameters` or `Resources`

Pseudo Parameters:
-  AWS::AccountId
-  AWS::NotificationARNs
-  AWS::Region
-  etc

#####  189. CloudFormation Mappings

-  `Fn::FindInMap`
-  `!FindInMap [ MapName, TopLevelKey, SecondLevelKey ]`

#####  190. CloudFormation Outputs

-  `Fn::ImportValue`

#####  191. CloudFormation Conditions

```yaml
Conditions:
  CreateProdResources:  !Equals [ !Ref EnvType, prod ]
```

-  Fn::Equals
-  Fn::And
-  Fn::If
-  Fn::Not
-  Fn::Or

Example:

```yaml
Resources:
  MountPoint:
    Type:  "AWS::EC2::VolumeAttachment"
    Condition:  CreateProdResources    
```

#####  192. CloudFormation Intrinsic Functions

-  Ref
-  Fn::GetAtt
-  Fn::FindInMap
-  Fn::ImportValue
-  Fn::Join
-  Fn::Sub
-  Condition functions (Fn::If, Fn::Not, Fn::Equals etc)

Examples

-  "a:b:c" <- `!Join [ ":" , [ a, b, c ] ]`
-  `!Sub` - substitution

```yaml
!Sub
  - String
  - { Var1Name: Var1Value, Var2Name: Var2Value }
```

#####  193. CloudFormation Rollbacks

1.  Create stack from template `0-just-ec2.yaml`
    -  name: `FailureDemo`
2.  Wait while complete
3.  Update with template `2-trigger-failure.yaml`
    -  UPDATE_IN_PROGRESS
    -  UPDATE_FAILED:
        -  `The image id '[ami-00123456]' does not exist `
    -  UPDATE_ROLLBACK_IN_PROGRESS
    -  UPDATE_ROLLBACK_COMPLETE_CLEANUP_IN_PROGRESS
    -  DELETE_IN_PROGRESS
    -  DELETE_COMPLETE
    -  UPDATE_ROLLBACK_COMPLETE
4.  Creation failure stack with Rollback **enabled**
    -  Create **new** stack from template `2-trigger-failure.yaml`
    -  CREATE_IN_PROGRESS
    -  CREATE_FAILED
        -  `The image id '[ami-00123456]' does not exist (Service: AmazonEC2; Status Code: 400; Error Code: InvalidAMIID.`
        -  ROLLBACK_COMPLETE
5.  Creation failure stack with Rollback **disabled**
    -  Create **new** stack from template `2-trigger-failure.yaml`
    -  Stack creation options:
        -  Rollback on failure: Disabled
    -  CREATE_IN_PROGRESS
    -  CREATE_FAILED
    -  All created resources remain
    -  For debug purposes
6.  Clean up
    -  delete all stacks

#####  194. CloudFormation ChangeSets, Nested Stacks & StackSet    
    
####  Section 17: AWS Monitoring & Audit: CloudWatch, X-Ray and CloudTrail        

#####  197. AWS CloudWatch Metrics

1.  CloudWatch Console
    -  Metrics ->
    -  EC2 ->
    -  Per-Instance Metrics -> my `dockerapp.shyshkin.net` (`i-044aea1876a062509`)
    -  CPUUtilization -> (3H, 1w) -> (Line, Stacked Area, Number, Bar, Pie)
    -  Actions -> Add to Dashboard -> create new dashboard -> `First-dashboard`
    -  Save dashboard
2.  EC2 -> Autoscaling groups
    -  Monitoring
    -  Enable metric collection    

#####  198. AWS CloudWatch Alarms

1.  CloudWatch Console
    -  Alarms -> 2 alarms created by Elastic Beanstalk for ASG
    -  if `NetworkOut > 6000000 for 1 datapoints within 5 minutes` then increase size of ASG
    -  if `NetworkOut < 2000000 for 1 datapoints within 5 minutes` then decrease ASG size (reduce EC2)
2.  ASG
    -  Scaling policies
        -  AWSEBAutoScalingScaleDownPolicy
            -  when AWSEBCloudwatchAlarmLow
        -  AWSEBAutoScalingScaleUpPolicy
            -  when AWSEBCloudwatchAlarmHigh
3.  Create Alarm
    -  select metrics
    -  NetworkOut for `docker-app` ec2
    -  Greater then 10000 bytes
    -  Period: 1 minute
    -  Datapoints to alarm: 3 of 3 (3*1 minute = 3 minutes)
    -  Notification
        -  SNS
        -  Email to me
    -  Auto Scaling action
        -  in alarm
        -  EC2 Auto Scaling group -> Add 1 instance
    -  Alarm name: `High network out for Beanstalk prod`
4.  Visit `docker.shyshkin.net:8080` for 3 minutes to increase traffic
    -  ASG will increase automatically
    -  when stop visiting that URL ASG will decrease ASG size (remove 1 EC2) 
    
#####  199. AWS CloudWatch Logs

1.  CloudWatch console
    -  Logs
    -  Log Groups
        -  `/aws/codebuild`
        -  `/ecs`
    -  Search All
        -  `echo`
        -  click link on `Log stream name` -> will go directly to log line
    -  Log Groups
        -  choose group -> Actions -> 
            -  edit retention settings -> period to store data
            -  [export data to S3](https://docs.aws.amazon.com/AmazonCloudWatch/latest/logs/S3ExportTasksConsole.html)
                -  Create S3 bucket `art-cloudwatch-logs-2020`
                -  Create IAM User `CWLExportUser`
                -  Set Permissions on an Amazon S3 Bucket
                    -  use `CWLExportUser_Policy.json`                    
                -  Login as `CWLExportUser`
                    -  Export
                    -  S3 bucket prefix - optional: `random-string` (when create)
2.  Beanstalk log into CloudWatch Logs
    -  Beanstalk -> Application
    -  Environment: `...-env` (dev with single EC2)
    -  Configuration -> Software
    -  Instance log streaming to CloudWatch Logs -> Enable
        -  Retention 7 days
        -  Keep after termination
        -  Apply
3.  Beanstalk health monitoring logs         
    -  Beanstalk -> Application
    -  Environment: `...-env` (dev with single EC2)
    -  Configuration -> Monitoring
    -  Health event streaming to CloudWatch Logs -> Enable -> Apply
4.  CloudWatch Logs
    -  groups
    -  created new 9 groups
    
#####  200. CloudWatch Agent & CloudWatch Logs Agent

1. Two agents:
    -  CloudWatch Logs Agent
    -  CloudWatch Unified Agent (newer one)
2.  [Installing the CloudWatch Agent](https://docs.aws.amazon.com/AmazonCloudWatch/latest/monitoring/install-CloudWatch-Agent-on-EC2-Instance.html)    
    -  `sudo yum install amazon-cloudwatch-agent -y`
    -  [Create IAM Roles to Use with the CloudWatch Agent on Amazon EC2 Instances](https://docs.aws.amazon.com/AmazonCloudWatch/latest/monitoring/create-iam-roles-for-cloudwatch-agent.html#create-iam-roles-for-cloudwatch-agent-roles)    
        -  `CloudWatchAgentServerRole` with AWS `CloudWatchAgentServerPolicy`
    -  Assign IAM Role to the EC2 Instance
    -  [Create the CloudWatch Agent Configuration File](https://docs.aws.amazon.com/AmazonCloudWatch/latest/monitoring/create-cloudwatch-agent-configuration-file.html)
        -  [with the Wizard](https://docs.aws.amazon.com/AmazonCloudWatch/latest/monitoring/create-cloudwatch-agent-configuration-file-wizard.html)
        -  `sudo /opt/aws/amazon-cloudwatch-agent/bin/amazon-cloudwatch-agent-config-wizard`
        -  answer all the questions
    -  [Start the CloudWatch Agent Using the Command Line](https://docs.aws.amazon.com/AmazonCloudWatch/latest/monitoring/install-CloudWatch-Agent-commandline-fleet.html)
        -  modify `sudo /opt/aws/amazon-cloudwatch-agent/bin/amazon-cloudwatch-agent-ctl -a fetch-config -m ec2 -s -c file:configuration-file-path`  
        -  **my impl**
        -  `sudo /opt/aws/amazon-cloudwatch-agent/bin/amazon-cloudwatch-agent-ctl -a fetch-config -m ec2 -s -c file:/opt/aws/amazon-cloudwatch-agent/bin/config.json`
        -  got an error
            -  `2020-12-15T13:46:57Z E! [telegraf] Error running agent: Error parsing /opt/aws/amazon-cloudwatch-agent/etc/amazon-cloudwatch-agent.toml, open /usr/share/collectd/types.db: no such file or directory`
        -  need to install `collectd`
            -  `sudo amazon-linux-extras install collectd`
        -  check cloudwatch-agent is running
            -  `sudo /opt/aws/amazon-cloudwatch-agent/bin/amazon-cloudwatch-agent-ctl -m ec2 -a status`
3.  Monitoring
    -  CloudWatch
    -  Metrics -> CWAgent                     
    
#####  CloudWatch Agent on OnPremise instance

1.  Install Agent
    -  [Installing and Running the CloudWatch Agent on Your Servers](https://docs.aws.amazon.com/AmazonCloudWatch/latest/monitoring/install-CloudWatch-Agent-commandline-fleet.html)
    -  `wget https://s3.amazonaws.com/amazoncloudwatch-agent/redhat/amd64/latest/amazon-cloudwatch-agent.rpm`   
    -  `sudo rpm -U ./amazon-cloudwatch-agent.rpm`
    -  (Installing on an On-Premises Server) Specify IAM Credentials and AWS Region
        -  `sudo aws configure --profile AmazonCloudWatchAgent`
        -  use the same parameters as for `on_premises`                       
2.  Security config
    -  add policy `CloudWatchAgentServerPolicy` to existing user `on_premises`
3.  Config and start    
    -  `sudo /opt/aws/amazon-cloudwatch-agent/bin/amazon-cloudwatch-agent-config-wizard`
    -  `sudo /opt/aws/amazon-cloudwatch-agent/bin/amazon-cloudwatch-agent-ctl -a fetch-config -m onPremise -s -c file:/opt/aws/amazon-cloudwatch-agent/bin/config.json`            
        
```
[art@MiWiFi-R4A-srv Downloads]$ sudo /opt/aws/amazon-cloudwatch-agent/bin/amazon-cloudwatch-agent-ctl -a fetch-config -m onPremise -s -c file:/opt/aws/amazon-cloudwatch-agent/bin/config.json
/opt/aws/amazon-cloudwatch-agent/bin/config-downloader --output-dir /opt/aws/amazon-cloudwatch-agent/etc/amazon-cloudwatch-agent.d --download-source file:/opt/aws/amazon-cloudwatch-agent/bin/config.json --mode onPrem --config /opt/aws/amazon-cloudwatch-agent/etc/common-config.toml --multi-config default
Got Home directory: /root
I! Set home dir Linux: /root
I! SDKRegionWithCredsMap region:
Unable to determine aws-region.
Please make sure the credentials and region set correctly on your hosts.
Refer to http://docs.aws.amazon.com/cli/latest/userguide/cli-chap-getting-started.html
Fail to fetch the config!
```

-  `sudo yum install https://dl.fedoraproject.org/pub/epel/epel-release-latest-8.noarch.rpm`
-  `sudo yum install collectd`

######  Solution found

1.  Login as root
2.  Configure AWS CLI
    -  `aws configure`
    -  same for
    -  `aws configure --profile AmazonCloudWatchAgent`
3.  Log out, use another account (I have `art`)    
4.  Configuration file
    -  `sudo /opt/aws/amazon-cloudwatch-agent/bin/amazon-cloudwatch-agent-config-wizard`    
5.  Restart AWS CloudWatch Agent       
    -  `/opt/aws/amazon-cloudwatch-agent/bin/amazon-cloudwatch-agent-ctl -a fetch-config -m onPremise -s -c file:/opt/aws/amazon-cloudwatch-agent/bin/config.json`

#####  201. CloudWatch Logs Metric Filters

1.  Create Metric Filter
    -  CloudWatch
    -  Logs
    -  choose one: `/aws/elasticbeanstalk/MyFirstWebappBeanstalk-env/var/log/nginx/access.log`
    -  Create metric filter
    -  Filter pattern: (can be very complex) - we use `curl`
    -  Select log data to test: directly from ec2
    -  Test pattern -> `Found 5 matches out of 26 event(s) in the sample log.`
    -  Next
    -  Filter name
    -  Filter name: `LinuxFilter`
    -  Metric namespace: `MetricFilter`
    -  Metric Name: `MyDemoFilter`
    -  Metric value: 1 (publish when filter switches)
    -  Default value: 0
    -  Next -> Create
2.  Trigger Metric filter
    -  Visit EC2 by using curl
    -  `curl http://myfirstwebappbeanstalk-env.eba-u9yvmmuz.eu-north-1.elasticbeanstalk.com/`
    -  It will trigger metrics filter    
3.  Create alarm
    -  Logs groups -> Metric filters
    -  tick `LinuxFilter` ->
    -  Create alarm
    -  Static, Greater then 20
    -  In alarm -> Select an existing SNS topic
    -  Alarm name: `DemoMetricFilterAlarm`
4.  Trigger alarm
    -  curl over 20 times per 5 minutes
    -  it will trigger alarm

#####  202. AWS CloudWatch Events

1.  Create Event
    -  CloudWatch console
    -  Events
    -  Create Rule
        -  we can do scheduled event
        -  _but_ we will use
        -  Event Pattern
            -  CodePipeline Execution State Change
            -  Specific State -> Failed
        -  Target
            -  SNS topic
        -  Configure Details
            -  Name:  `CodePipelineFailures`

#####  204. EventBridge Hands On

1.  EventBridge console
    -  Event buses
    -  default
2.  Create custom event bus
    -  `custom-bus` -> create ->  delete
3.  Partner event sources
    -  Symantec
    -  ... -> exit
4.  default EventBridge
    -  Rules -> same rules as in CloudWatch (EventBridge built on the same infrastructure)
    -  Recreating one of rules:
        -  `DemoCodePipeline`
        -  same settings as for `CodePipelineFailures`
5.  Schemas
    -  Search -> `aws.codepipeline`
    -  `aws.codepipeline@CodePipelineActionExecutionStateChange`
    -  Download code binding
        -  Java8+

#####  206. X-Ray Hands On

1.  XRay console
    -  Switch to `us-east-1`
    -  Select sample or your own application
        -  Instrument your application -> Java -> Look through
        -  Return back
        -  Launch a sample application (Node.js)
        -  [Sample application](https://github.com/aws-samples/eb-node-express-sample/tree/xray)
2.  CloudFormation console
    -  Next
    -  Specify a VPC and subnet. Optionally, edit the Stack name. Choose Next.
    -  Next
    -  Confirm that IAM resources will be created, and then choose Create
        -  tick `I acknowledge that AWS CloudFormation might create IAM resources.`
    -  Create stack
    -  View template
    -  CREATE_IN_PROGRESS -> CREATE_COMPLETE
    -  created 2 stacks
3.  Starting Auto Signup
    -  `xray-sample` stack -> Outputs
    -  ElasticBeanstalkEnvironmentURL:	54.224.80.70
    -  Auto signup fake mails: click `Start`
    -  wait for ~30 signups
4.  Back to X-Ray console
    -  Done
    -  Service map
5.  View ERROR responses    
    -  select ElasticBeanstalk 
        -  tick Error -> `View traces`
        -  `signup` - click on `http://54.224.80.70/signup`
        -  click on one - id: `1-5fda1ae9-141d36a051076c50d9e0565e`
        -  click on DynamoDB - view info and **Exceptions**    
6.  View OK responses
    -  tick OK -> `View traces`
    -  call to DynamoDB took 29 ms
    -  call to SNS took 31 ms                
    -  another call
    -  call to DynamoDB took 32 ms
    -  call to SNS took 95 ms
7.  Browser
    -  http://54.224.80.70/
    -  Ctrl+Shift+I ->  Network
    -  Start button
    -  jQuery call takes about 200ms
    -  is this because of `us-east-1`?
    -  in **eu-north-1** 
        -  AVG response time ~66.4 ms
        -  DynamoDB took 29.0 ms
        -  SNS took 36 ms
        -  browser shows ~120ms         
8.  Clean Up
    -  `xray-sample` stack -> Delete                      
                    
#####  208. X-Ray: Sampling Rules

1.  View default Sampling Rule
    -  XRay console
    -  Sampling
    -  View default
2.  Create custom Sampling Rule
    -  `DemoSamplingRule`
    -  Priority: 5000
    -  Reservoir size: 10
    -  Fixed rate: 1 Percent
    -  Service: ...

_Once you apply an X-Ray sampling rule this rule will be automatically applied to all XRay daemons_         

#####  210. X-Ray with Beanstalk

1.  Enable X-Ray for Beanstalk
    -  Elastic Beanstalk
    -  env Environment
    -  Configuration
    -  Software -> Edit -> 
    -  AWS X-Ray -> Enabled
    -  _We can enable X-Ray daemon by config file in **.ebextensions** folder_
2.  Make sure EC2 has permissions
    -  Health -> EC2 instance
    -  EC2 console
    -  instance of beanstalk environment
    -  IAM role
    -  Permissions: AWSElasticBeanstalkWebTier ->
    -  Policy Summary
    -  X-Ray
        -  GetSamplingRules
        -  GetSamplingStatisticSummaries
        -  GetSamplingTargets
        -  PutTelemetryRecords
        -  PutTraceSegments        

```json
{
    "Sid": "XRayAccess",
    "Action": [
        "xray:PutTraceSegments",
        "xray:PutTelemetryRecords",
        "xray:GetSamplingRules",
        "xray:GetSamplingTargets",
        "xray:GetSamplingStatisticSummaries"
    ],
    "Effect": "Allow",
    "Resource": "*"
}
```

#####  211. X-Ray & ECS

#####  212. AWS CloudTrail

-  CloudTrail console
-  Search by Event Name: DeleteBucket

#####  X-Ray for Spring application

######  Start XRay Daemon Docker container locally

```shell script
docker pull amazon/aws-xray-daemon
```

From example

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
  
```shell script
docker run --attach STDOUT -v ~/.aws/:/root/.aws/:ro --net=host -e AWS_REGION=eu-west-3 --name xray-daemon -p 2000:2000/udp -p 2000:2000/tcp amazon/aws-xray-daemon -o
```

On Windows Power Shell

```shell script
docker run --attach STDOUT -v C:\Users\Admin\.aws:/root/.aws/:ro -e AWS_REGION=eu-west-3 --name xray-daemon -p 2000:2000/udp -p 2000:2000/tcp xray-daemon -o
```

**OR** build docker image from Dockerfile

[Running the X-Ray daemon in a Docker container](https://docs.aws.amazon.com/xray/latest/devguide/xray-daemon-local.html#xray-daemon-local-docker)

-  `docker build -t xray-daemon .`

```shell script
docker run \
      --attach STDOUT \
      -v ~/.aws/:/root/.aws/:ro \
      --net=host \
      -e AWS_REGION=eu-west-3 \
      --name xray-daemon \
      -p 2000:2000/udp \
      xray-daemon -o
```

######  Configure SDK for XRay

-  [Tracing incoming requests with the X-Ray SDK for Java](https://docs.aws.amazon.com/xray/latest/devguide/xray-sdk-java-filters.html)
-  [AOP with Spring and the X-Ray SDK for Java](https://docs.aws.amazon.com/xray/latest/devguide/xray-sdk-java-aop-spring.html) for AWS XRay.

```xml
<dependency>
	<groupId>com.amazonaws</groupId>
	<artifactId>aws-xray-recorder-sdk-spring</artifactId>
	<version>2.4.0</version>
</dependency>
```

#####  Install XRay daemon on EC2

-  Install XRay daemon

```shell script
#!/bin/bash
curl https://s3.us-east-2.amazonaws.com/aws-xray-assets.us-east-2/xray-daemon/aws-xray-daemon-3.x.rpm -o /home/ec2-user/xray.rpm
yum install -y /home/ec2-user/xray.rpm

```

-  to xray start automatically 
    -  `chkconfig xray on`

-  Security setting
    -  EC2 must have IAM role with policy `AWSXRayDaemonWriteAccess`
```json
{
    "Sid": "XRayAccess",
    "Action": [
        "xray:PutTraceSegments",
        "xray:PutTelemetryRecords",
        "xray:GetSamplingRules",
        "xray:GetSamplingTargets",
        "xray:GetSamplingStatisticSummaries"
    ],
    "Effect": "Allow",
    "Resource": "*"
}
```
-  I Added this policy to previously created IAM role `CloudWatchAgentServerRole`

######  When I was debugging XRay on EC2 with my app running in docker I made some steps 

-  [Running the X-Ray daemon on Linux](https://docs.aws.amazon.com/xray/latest/devguide/xray-daemon-local.html)
-  You can run the daemon executable from the command line. Use the -o option to run in local mode, and -n to set the region.
-  To run the daemon in the background, use &.
-  `./xray -o -n eu-west-3 &`
-  `sudo service xray status` - must be running (I had something broken)
-  uninstall `sudo yum  remove xray`
-  install ones again
-  logs `cat /var/log/xray/xray.log` 
-  on EC2 docker said 
    -  `Could not resolve host: host.docker.internal`
    -  when I tried to `curl host.docker.internal` - on Windows Docker works fine for me      
      
####  Section 18: AWS Integration & Messaging: SQS, SNS & Kinesis

#####  218. SQS - Standard Queue Hands On

1.  Create queue
    -  SQS console -> create queue
    -  Standard
    -  Name: `DemoQueue`
    -  Access policy: Basic
    -  Encryption:
        -  Server-side encryption:  Enabled
    -  Create queue
2.  Working with queue messages
    -  console
    -  Send and Receive messages
    -  `hello message`
    -  Send
    -  Got 1 message -> Poll for messages
        -  Details
        -  Body
        -  Attributes
    -  tick message -> Delete
3.  Working with Queue
    -  Purge - delete all the messages in the queue
4.  Monitoring
    -  Approximate Number Of Messages Visible
    -  Approximate Age Of Oldest Message
5.  Access policy

#####  219. SQS - Message Visibility Timeout

1.  Open 2 pages in browser
    -  in 1: 
        -  send message
        -  poll message
    -  in 2:
        -  message became invisible for **30** sec
        -  wait for 30 sec
        -  delete message
2.  Modify Visibility Timeout to 1 minute

#####  220. SQS - Dead Letter Queues

1.  Create Dead Letter Queue
    -  create queue: `DemoQueueDLQ`
    -  Message retention period: 14 days
2.  Modify main queue
    -  set Visibility Timeout to 5 sec (for tests)
    -  DeadLetterQueue: Enabled
    -  Maximum receives: 4
3.  Testing
    -  open 2 pages in browser
    -  DLQ
        -  start polling
    -  DemoQueue
        -  send message
    -  after 4 messages this message will be sent to DLQ
    
#####  221. SQS - Delay Queues

1.  Create Delay Queue
    -  create queue
    -  `DelayQueue`
    -  Delivery Delay: 10 sec
2.  Testing
    -  start polling messages
    -  send message
    -  wait for 10 sec
    -  should appear
    
#####  222. SQS - Certified Developer concepts

1.  Long Polling
    -  `DemoQueue`
    -  Edit
    -  Receive message wait time: 20sec
2.  Testing
    -  poll for messages
    -  send message
    -  it will immediately appear in consumer's window

#####  223. SQS - FIFO Queues

1.  Create FIFO Queue
    -  create queue: **DemoQueue.fifo**
    -  FIFO
2.  Working with FIFO
    -  send message 1
        -  Message 1
        -  Message group ID: demo
        -  Message deduplication ID: 1                                                                 
    -  send message 2
        -  Message 2
        -  Message group ID: demo
        -  Message deduplication ID: 2
    -  send message 3
    -  send message 4
3.  Poll messages    

#####  224. SQS - FIFO Queues Advanced

1.  Add deduplication
    -  queue DemoQueue.fifo -> Edit
    -  Content-based deduplication - Enable -> Save
2.  Testing Deduplication ID
    -  Save and receive messages
    -  Send message
        -  Message body: `message 1`
        -  Message group ID: `demo`
        -  Message deduplication ID - Optional <- because we are using content-based (sha256)
    -  Send again MANY times but Available message remains 1
    -  then new
    -  `message 2`  -> many sends
    -  available 2 messages
    -  then
    -  send `message 2` but set deduplication ID 123 -> many times
    -  available 3 messages
3.  Testing Message Group ID
    -  Deduplication ID: empty
    -  Message Group ID: `user1`
        -  m11, m12, m13 
    -  Message Group ID: `user2`
        -  m21, m22, m23
    -  Was sending: m11,m12,m21,m22,m23,m13
    -  Received:    m11,m12,m13,  m21,m22,m23
    -  Order in ONE group is Guaranted
    -  Overall order is NOT Guaranted
    
#####  226. SNS Hands On

1.  Create SNS topic
    -  SNS management console
    -  Create topic: `MyFirstTopic`
    -  Standard
    -  Create topic
2.  Create subscription
    -  Email
    -  Go to [mailinator](https://www.mailinator.com/v3/#/#inboxpane) 
    -  inbox `artshyshkin` -> email will be `artshyshkin@mailinator.com`
    -  create subscription     
    -  go to mailinator.com -> confirm subscription
    -  SNS -> Subscriptions -> Status Confirmed
3.  Publishing
    -  Topics -> `MyFirstTopic`
    -  Publish message
    -  Subject: `Hello World Subject`
    -  Message: `Hello World from the console`         
    
#####  227. SNS and SQS - Fan Out Pattern

1.  Fan Out Pattern
    -  SNS -> many SQS
    -  **OR**
    -  SNS.fifo -> many SQS.fifo
2.  Application: S3 Events to Multiple Queues    

#####  229. AWS Kinesis Hands On

1.  Create Data Stream
    -  Kinesis console
    -  create data stream: `my-first-kinesis-stream`
    -  Number of open shards: 1
    -  Create
2.  Edit Configuration
    -  Enhanced (shard-level) metrics: Enable all (for study purpose)
3.  Monitoring: nothing yet
4.  AWS CLI
    -  `aws kinesis help`
    -  `aws kinesis list-stream help`   
    -  `aws kinesis list-stream`
    -  `aws kinesis describe-stream help`
    -  `aws kinesis describe-stream --stream-name my-first-kinesis-stream`
5.  Put Records    
    -  `aws kinesis put-record help`
    -  `aws kinesis put-record --stream-name my-first-kinesis-stream --data "some data" --partition-key user_123` (**error** for me)   
    -  `aws kinesis put-record --cli-binary-format raw-in-base64-out --stream-name my-first-kinesis-stream --data "some data" --partition-key user_123`
        -  answer:
        -  `{
               "ShardId": "shardId-000000000000",
               "SequenceNumber": "49613841384246730583938226464908902055888307798918823938"
           }`
    -  `aws kinesis put-record --cli-binary-format raw-in-base64-out --stream-name my-first-kinesis-stream --data "user signup" --partition-key user_123`
    -  `aws kinesis put-record --cli-binary-format raw-in-base64-out --stream-name my-first-kinesis-stream --data "user login" --partition-key user_123`
    -  `aws kinesis put-record --cli-binary-format raw-in-base64-out --stream-name my-first-kinesis-stream --data "user visit home page" --partition-key user_123`
        -  SequenceNumbers are different but begin of them is similar 
6.  Get Records
    -  `aws kinesis get-shard-iterator help`
    -  `aws kinesis get-shard-iterator --stream-name my-first-kinesis-stream --shard-id shardId-000000000000 --shard-iterator-type TRIM_HORIZON`    
        -  answer:
        -  `{
                "ShardIterator": "AAAAAAAAAAFU3cSh3W3fn+808baCUrsJpeL46evq+0p7f6FVxSmpftTlp8vLCg1br1K2KQ43r7iueabuTmFO1hFNgCoa5VlVFeHPuKn9lDDqeSu4cLFhnfh0W807sEzuob2Jqyk5MhgQZaA9CwFKE12wbFO8iofLlcwb0e+DCI63gOzRrYYstfW/d14RNj5QxFuEl2q+xSCzBxIWZGW9AcQu6mqAPoTlOIibfYxYQ5sKv1kBCHsgiw=="
            }`      
    -  `aws kinesis get-records help`
    -  `aws kinesis get-records --shard-iterator AAAAAAAAAAFU3cSh3W3fn+808baCUrsJpeL46evq+0p7f6FVxSmpftTlp8vLCg1br1K2KQ43r7iueabuTmFO1hFNgCoa5VlVFeHPuKn9lDDqeSu4cLFhnfh0W807sEzuob2Jqyk5MhgQZaA9CwFKE12wbFO8iofLlcwb0e+DCI63gOzRrYYstfW/d14RNj5QxFuEl2q+xSCzBxIWZGW9AcQu6mqAPoTlOIibfYxYQ5sKv1kBCHsgiw==`
        -  output:        

```json
{
    "Records": [
        {
            "SequenceNumber": "49613841384246730583938226464908902055888307798918823938",
            "ApproximateArrivalTimestamp": "2020-12-22T16:39:31.486000+02:00",
            "Data": "bXkgZmlyc3Qga2luZXNpcyBtZXNzYWdl",
            "PartitionKey": "user_123"
        },
        {
            "SequenceNumber": "49613841384246730583938226464910110981707948129177829378",
            "ApproximateArrivalTimestamp": "2020-12-22T16:45:44.726000+02:00",
            "Data": "dXNlciBzaWdudXA=",
            "PartitionKey": "user_123"
        },
        {
            "SequenceNumber": "49613841384246730583938226464911319907527563445547302914",
            "ApproximateArrivalTimestamp": "2020-12-22T16:45:55.494000+02:00",
            "Data": "dXNlciBsb2dpbg==",
            "PartitionKey": "user_123"
        },
        {
            "SequenceNumber": "49613841384246730583938226464912528833347178761916776450",
            "ApproximateArrivalTimestamp": "2020-12-22T16:46:05.354000+02:00",
            "Data": "dXNlciB2aXNpdCBob21lIHBhZ2U=",
            "PartitionKey": "user_123"
        }
    ],
    "NextShardIterator": "AAAAAAAAAAGU+ksRdChCYHWSVSrr+Kyt3nnmz4Ai92UAp5ZUSvp6GfGkFAfAuba4qbSFwj6K/NJ+IHMtHlloyqgw/gzvri28UeAanjT1Tye3NetLpBa/QQuFZ/dPKk+k2HAaU8fkYEr58KLxcYlajsoHi+nmSCncabPXCNqrPgX526toB1bPoyncjp24z8rjxjafnP1Tajv/sQGxXHbqkmbNmR6oHH0r1m4PuTww9kfDAqn2YQNbMQ==",
    "MillisBehindLatest": 0
}
```
-  Data is Encoded Base64
-  go to [decode64](https://www.base64decode.org/)
-  insert `dXNlciB2aXNpdCBob21lIHBhZ2U=` -> decode -> `user visit home page` - OK    
                                                                                 
-  **Clean Up**
    -  stream delete                                                                                 

####  Section 19: AWS Serverless: Lambda

##### 237. AWS Lambda - First Hands On

1.  Lambda management console
    -  [begin with samples](https://eu-north-1.console.aws.amazon.com/lambda/home?region=eu-north-1#/begin)
    -  for Java, Node.js etc
    -  Run
    -  Next: Lambda responds to events -> play
    -  Next: Scale seamlessly -> play
2.  Create function
    -  use a blueprint
    -  type `hello` -> find example with Python -> Configure
        -  Function name: `hello-world`
        -  Execution role: `Create a new role with basic Lambda permissions`
        -  Create
3.  Test event
    -  crete new
    -  Template: `Hello World` (`hello-world`)           
    -  Name: `SimpleEvent`
    -  Create Event
    -  Invoke
        -  Output:
        -  `Execution result: succeeded`
        -  return `value1`
        -  Log output
            -  `START RequestId: 03955e12-cb6d-4464-a466-1b6148cd0ef8 Version: $LATEST
                value1 = value1
                value2 = value2
                value3 = value3
                END RequestId: 03955e12-cb6d-4464-a466-1b6148cd0ef8
                REPORT RequestId: 03955e12-cb6d-4464-a466-1b6148cd0ef8	Duration: 1.55 ms	Billed Duration: 2 ms	Memory Size: 128 MB	Max Memory Used: 49 MB	Init Duration: 109.79 ms`
            -   `Click here to view the CloudWatch log group.`
4.  Lambda function code modification
    -  comment out `return` statement
    -  uncomment `raise Exception('Something went wrong')`
    -  Save
    -  Deploy
    -  Test
    -  `Execution result: failed`
    -  `{
          "errorMessage": "Something went wrong",
          "errorType": "Exception",
          "stackTrace": [
            "  File \"/var/task/lambda_function.py\", line 12, in lambda_handler\n    raise Exception('Something went wrong')\n"
          ]
        }`            
5.  Some thoughts
    -  Our function is `lambda_function.py`
    -  Handler is `lambda_function.lambda_handler`
    -  our lambda function has Execution Role to log to CloudWatch Logs:
        -  Configuration -> Permissions
        -  Role name: `hello-world-role-lzl5fa1w`
6.  Revert function
    -  uncomment `return`
    -  comment `exception`
    
#####  239. Lambda Synchronous Invocations Hands On

1.  CLI
    -  `aws lambda list-functions --region eu-north-1`
    -  response -> my `hello-world` info in JSON
    -  ~~aws lambda invoke --function-name hello-world --cli-binary-format raw-in-base64-out --payload "'{"key1":"value1","key2":"value2","key3":"value3"}'" --region eu-north-1 response.json~~
    -  `aws lambda invoke --function-name hello-world --cli-binary-format raw-in-base64-out --payload '{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\"}' --region eu-north-1 response.json`
    -  response
    -  `{
            "StatusCode": 200,
            "ExecutedVersion": "$LATEST"
        }`

#####  241. Lambda & Application Load Balancer Hands On

1.  Create Lambda:
    -  Lamdba management console
    -  create new function
    -  Author from scratch
    -  Name: `lambda-alb`
    -  Runtime: Python 3.8
    -  Create a new role with basic lambda permissions (the best practice is to use separate IAM role for every lambda)
    -  Create
2.  Create load balancer
    -  EC2 console
    -  Name: `lambda-demo-alb`
    -  Internate facing
    -  in 3 EZs
    -  Create new Security group: port 80 from anywhere
    -  New Target group: `tg-lambda`
    -  Target type: **lambda**
    -  Health checks: Disable
    -  Register targets: `lambda-alb`
    -  Create
3.  Testing Lambda function
    -  Lambda console
    -  modify source code
        -  add `print(event)`
        -  Save 
        -  Test on new TestEvent
            -  response: `{
                            "statusCode": 200,
                            "body": "\"Hello from Lambda!\""
                          }`
            -  print into console `{'key1': 'value1', 'key2': 'value2', 'key3': 'value3'}`   
4.  Testing ALB
    -  copy DNS name -> 
    -  url to it
    -  response has been downloaded
        -  `"Hello from Lambda!"`
    -  not optimal
5.  Modifying code
    -  [Using AWS Lambda with an Application Load Balancer](https://docs.aws.amazon.com/lambda/latest/dg/services-alb.html)
    -  replace output with
        -  `{
                "statusCode": 200,
                "statusDescription": "200 OK",
                "isBase64Encoded": False,
                "headers": {
                    "Content-Type": "text/html"
                },
                "body": "<h1>Hello from Lambda!</h1>"
            }`
    -  Now we have correct response from ALB url
6.  View CloudWatch logs
    -  `lambda-alb` -> Monitoring
    -  View logs in CloudWatch
        -  view latest with `{'requestContext': {'elb': {'targ...`        
7.  Enabling multi-value headers
    -  ALB ->
    -  TargetGroup -> `tg-lambda`
    -  Attributes ->
    -  Multi value headers: `Enable`
    -  Save
8.  Testing multi-value headers
    -  go to ALB `url/?name=foo&name=bar`
    -  go to CLoudWatch Logs
        -  view `'multiValueQueryStringParameters': {'name': ['foo', 'bar']},`
9.  Why ALB may Invoke Lambda
    -  Lambda console
    -  `lambda-alb`
    -  Permissions
    -  **Resource-based policy**
10.  CleanUp
    -  delete ALB

#####  244. Lambda Asynchronous Invocations Hands On

1.  Invoking Lambda asynchronously
    -  `hello-world`
    -  need to add `--invocation-type Event`
    -  `aws lambda invoke --function-name hello-world --cli-binary-format raw-in-base64-out --payload '{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\"}' --invocation-type Event --region eu-north-1 response.json`
    -  response
        -  `{
                "StatusCode": 202
            }`
        -  202 Accepted
2.  View logs
    -  CloudWatch
    -  CloudWatch Logs
    -  Log groups
    -  `/aws/lambda/hello-world`
3.  Make Lambda function fail
    -  modify code for lambda to throw an exception
4.  Invoke lambda one more
    -  `aws lambda invoke --function-name hello-world --cli-binary-format raw-in-base64-out --payload '{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\"}' --invocation-type Event --region eu-north-1 response.json`
    -  got response
    -  `{
            "StatusCode": 202
        }`
    -  CloudWatch Logs: was 3 attempts
        -  one initial attempt with Error
        -  after 1 minute
        -  after 2 minutes (3min from original attempt)
5.  Enabling Dead-Letter Queue
    -  `hello-world`
    -  Configuration
    -  Asynchronous invocation
        -  Retry attempts: 2 (default, can be 0, 1, 2)
        -  Dead-letter queue service: SQS
            -  Queue: create new (`LambdaHelloWorldDLQ`) or use existing 
            -  got an error
            -  `The provided execution role does not have permissions to call SendMessage on SQS`
    -  Modify execution role
        -  Configuration -> Permission -> Execution Role -> hello-world-role-lzl5fa1w -> link to IAM console
        -  Attach policy: `AmazonSQSFullAccess`
    -  Retry attaching to Lambda Function dead-letter queue
6.  Invoke Lambda with Exception once again
    -  `aws lambda invoke --function-name hello-world --cli-binary-format raw-in-base64-out --payload '{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\"}' --invocation-type Event --region eu-north-1 response.json`                             
    -  after 3 min view DLQ
        -  1 message available
        -  view Error message
    -  view CloudWatch Logs
        -  same RequestId 3 times (3 tries)
        
#####  245. Lambda & CloudWatch Events / EventBridge

1.  Create Lambda Function
    -  `lambda-eventbridge`
    -  Python 3.8
2.  EventBridge
    -  create rule `InvokeLambdaEveryMinute`
    -  target: Lambda Function
    -  `lambda-eventbridge`
3.  Modify lambda
    -  print(event)
4.  Function Visualization
    -  Lambda -> Functions -> `lambda-eventbridge` -> 
    -  Overview -> Function Visualization
5.  View Resource-based policy
6.  View CloudWatch Logs
7.  Clean Up
    -  Rules: `InvokeLambdaEveryMinute`    
    -  **Disable**
    
#####  246. Lambda & S3 Event Notifications

1.  Create Lambda function
    -  `lambda-s3`
    -  Python 3.8
2.  Create s3 bucket
    -  `art-s3-events-demo`
    -  block all public access (default)
    -  create
3.  Create Event notifications for S3
    -  s3 ->  `art-s3-events-demo` -> Properties -> 
    -  Create Event notification
        -  Name: `InvokeLambdaOnNewObjects`
        -  Events: `All object create event`
        -  SendTo: Lambda Function `lambda-s3`
        -  Save
4.  Lambda Console
    -  Function Visualization
5.  Modify Lambda Function
    -  `print(event)`
6.  View Resource-based policy
7.  Upload file to S3 bucket
8.  View CloudWatch Logs
             
#####  248. Lambda Event Source Mapping Hands On (SQS)

1.  Create Lambda function
    -  from scratch
    -  Name: `lambda-sqs`
    -  Python 3.8
2.  Create Queue
    -  SQS
    -  `demo-lambda-queue`
    -  Standard
3.  Lambda console
    -  add trigger: SQS ->  `demo-lambda-queue`
    -  batch size: 1 - 10
    -  Add -> got an Error: no permissions
        -  `The provided execution role does not have permissions to call ReceiveMessage on SQS`
4.  Security settings
    -  IAM -> Roles ->  `lambda-sqs-role-tyubyo92`
    -  Attach policy: `AWSLambdaSQSQueueExecutionRole`
    -  Finish step 3
5.  Modify Lambda function
    -  `print(event)`
    -  `return 'success'`                                     
6.  Sending message
    -  SQS -> Send
    -  Message: `Hello from SQS to Lambda`
    -  Add attribute: `foo`:`bar`
    -  Lambda -> Monitoring -> CloudWatch Logs
7.  Cleanup
    -  Lambda -> Configuration -> triggers -> `lambda-sqs` -> Disable        
8.  View Kinesis trigger possibility
    -  Lambda -> Add trigger
    -  Kinesis
        -  Kinesis stream
        -  Consumer
        -  Batch size, Batch window
        -  On-failure destination
9.  Same for DynamoDB            
    
#####  250. Lambda Destinations Hands On

1.  Create Queues for destination
    -  `s3-failure`
    -  `s3-success`
2.  Add **failure** destination to `lambda-s3`
    -  Async invocation
    -  On failure
    -  SQS queue: `s3-failure`
        -  `Your function's execution role doesn't have permission to send result to the destination. By clicking save we'll attempt to add permission to the role for you.`
    -  Save
    -  IAM Roles: `lambda-s3-role-7n0zk4dn` was created
3.  Add **success** destination to `lambda-s3`
4.  Testing SUCCESS scenario
    -  Upload a file into s3 `art-s3-events-demo`
    -  SQS -> `s3-success` has 1 message
5.  Testing FAILURE scenario
    -  Modify lambda code
    -  `raise Exception("WTF Got an Error")`
    -  Upload one more file
    -  CloudWatch Logs shows
        -  error
        -  retry in 1 min
        -  another retry in 2 min
    -  SQS -> `s3-failure` has 1 message
        -  `"condition": "RetriesExhausted",`
        -  to see what triggers failure view        
**"object":**          
```json
{
    "key": "template.yml",
    "size": 4230,
    "eTag": "f7a65b0951f7f86a3a921995b4a03bb4",
    "sequencer": "005FE6008C519637CC"
}
```                             
    
#####  251. Lambda Permissions - IAM Roles & Resource Policies

1.  Execution roles
    -  IAM
    -  Roles
    -  search `lambda`
        -  these are roles attached to lambdas (execution roles)
2.  Resource-based policies
    -  every lambda
    -  Configuration
    -  Permissions
    -  Resource-based policy
    -  Another resources Invoking Lambda Function

#####  252. Lambda Environment Variables & Hands On

1.  Create Lambda function
    -  `lambda-config-demo`
2.  Add Environment variables
    -  Manage Environment variables
    -  Add
        -  `ENVIRONMENT_NAME`: `dev`
3.  Modify code
    -  `import os` - import system package to access to Env variables
    -  `return os.getenv("ENVIRONMENT_NAME")`    
4.  Test
    -  SampleTest
    -  Invoke
    -  Output: "dev"
    -  modify Env var: `prod`
    -  test        

#####  253. Lambda Monitoring & X-Ray Tracing

1.  Lambda console
    -  `hello-world` 
    -  `lambda-s3`
    -  Duration, Error count and success rate, Invocations
2.  XRay
    -  `lambda-config-demo`
    -  Configuration
    -  Monitoring tools -> Edit
    -  AWS X-Ray
        -  Active tracing
        -  `The required permissions were not found. The Lambda console will attempt to add them to the execution role.`
    -  View attached policy to existing role
        -  `AWSLambdaTracerAccessExecutionRole-406cdac0-f86d-4bd5-9e1c-5d19716127a9`

#####  254. Lambda in VPC

1.  Create Lambda
    -  `lambda-vpc`
2.  Edit VPC configuration
    -  Configuration -> VPC -> Edit
    -  Custom VPC: Warning message:
        -  `When you connect a function to a VPC in your account, it does not have access to the internet unless your VPC provides access. To give your function access to the internet, route outbound traffic to a NAT gateway in a public subnet. `
    -  Security group (peek one just for tests)
    -  Save
        -  got an error
        -  `The provided execution role does not have permissions to call CreateNetworkInterface on EC2`
        -  Lambda to provide in VPC must have permission to create Elastic Network Interface (ENI)
        -  IAM -> role for our Lambda -> Attach policy -> search ENI -> `AWSLambdaENIManagementAccess` (has `ec2:CreateNetworkInterface`)
    -  Save once again
3.  Test
    -  Invoke
    -  Got result

```json
{
  "statusCode": 200,
  "body": "\"Hello from Lambda!\""
}
```  
4.  View Elastic Network Interface
    -  created 3 ENIs for 3 AZs (we choosed 3)

#####  255. Lambda Function Performance

1.  Configure `lambda-config-demo`
    -  Configuration -> General -> Basic Settings -> Edit
    -  Description
    -  Memory: 128 MB - 10240 MB
        -  **More Memory you have more vCPU power you get**
    -  Timeout (min 1sec, max 15min, default 3sec)
2.  Modify Code
    -  `import time`
    -  `time.sleep(2)` - sleep for 2 sec
    -  Test: Invoke
        -  Billed duration: 2006 ms -> OK
    -  `time.sleep(5)` - hard work simulation
    -  Invoke
    -  Got an error
        -  `"errorMessage": "2020-12-26T13:57:06.025Z 7734f0ee-7049-443b-bed1-62f0a6dd478d Task timed out after 3.00 seconds"`
3.  Cleanup
    -  remove `time.sleep(...)`

#####  257. Lambda Concurrency Hands On

1.  Configure Reserved Concurrency
    -  Use `lambda-config-demo`
    -  Configuration -> Concurrency
    -  `Unreserved account concurrency 1000` - default
        -  Edit
        -  Reserve concurrency: 20 (then unreserved became 980)
        -  Reserve concurrency: 0 (`Your function is always throttled.`)
        -  Save
    -  Test -> Invoke
        -  **Error**
        -  `Calling the invoke API action failed with this message: Rate Exceeded.`
    -  Test with 2 functions
        -  set reserved capacity to 1
        -  modify function
            -  set timeout to 6sec
            -  modify code -> add pause `time.sleep(5)`
        -  open 2 test pages
        -  invoke both tests -> 
            -  first test - OK
            -  second - `Calling the invoke API action failed with this message: Rate Exceeded.`
        -  That is **throttle** in action
    -  Increase reserved concurrency to 20
2.  Provisioned concurrency
    -  Alias or Version
    -  Will take additional pricing
    -  Disable for now                                                 