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
    