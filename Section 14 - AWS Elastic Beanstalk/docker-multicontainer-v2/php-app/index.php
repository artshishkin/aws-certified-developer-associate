<?
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
  $file = '/var/log/sample-app/sample-app.log';
  $message = file_get_contents('php://input');
  file_put_contents($file, @date('Y-m-d H:i:s') . " Received message: " . $message . "\n", FILE_APPEND);
} else {
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <!--
    Copyright 2012 Amazon.com, Inc. or its affiliates. All Rights Reserved.

    Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with the License. A copy of the License is located at

        http://aws.Amazon/apache2.0/

    or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
  -->
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Welcome</title>
  <style>
  body {
    color: #ffffff;
    background-color: #E0E0E0;
    font-family: Arial, sans-serif;
    font-size:14px;
    -moz-transition-property: text-shadow;
    -moz-transition-duration: 4s;
    -webkit-transition-property: text-shadow;
    -webkit-transition-duration: 4s;
    text-shadow: none;
  }
  body.blurry {
    -moz-transition-property: text-shadow;
    -moz-transition-duration: 4s;
    -webkit-transition-property: text-shadow;
    -webkit-transition-duration: 4s;
    text-shadow: #fff 0px 0px 25px;
  }
  a {
    color: #0188cc;
  }
  .textColumn, .linksColumn {
    padding: 2em;
  }
  .textColumn {
    position: absolute;
    top: 0px;
    right: 50%;
    bottom: 0px;
    left: 0px;

    text-align: right;
    padding-top: 11em;
    background-color: #24B8EB;
  }
  .textColumn p {
    width: 75%;
    float:right;
  }
  .linksColumn {
    position: absolute;
    top:0px;
    right: 0px;
    bottom: 0px;
    left: 50%;
    background-color: #A9A9A9;
  }

  h1 {
    font-size: 500%;
    font-weight: normal;
    margin-bottom: 0em;
  }
  h2 {
    font-size: 200%;
    font-weight: normal;
    margin-bottom: 0em;
  }
  ul {
    padding-left: 1em;
    margin: 0px;
  }
  li {
    margin: 1em 0em;
  }
  </style>
</head>
<body id="sample">
  <div class="textColumn">
    <h1>Congratulations!</h1>
    <p>Your Docker Container is now running in Elastic Beanstalk on your own dedicated environment in the AWS Cloud.</p>
  </div>
  
  <div class="linksColumn">
    <h2>Video Tutorials</h2>
    <ul>
    <li>YouTube: <a href="https://www.youtube.com/watch?v=lBu7Ov3Rt-M&feature=youtu.be">Run a Docker Container from the Docker Registry</a></li>
    <li>YouTube: <a href="https://www.youtube.com/watch?v=pLw6MLqwmew&feature=youtu.be">Use Private Docker Repositories</a></li>
    </ul>
    <h2>Sample Apps</h2>
    <ul>
    <li>GitHub: <a href="https://github.com/awslabs/eb-docker-nginx-proxy">PHP-FPM with Nginx as reverse proxy</a></li>
    <li>GitHub: <a href="https://github.com/awslabs/eb-docker-virtual-hosting">PHP and Tomcat with Nginx (Virtual Hosting)</a></li>
    <li>GitHub: <a href="https://github.com/awslabs/eb-docker-multiple-ports">Node.js and Tomcat (Multiple Ports)</a></li>
    </ul>
    <h2>Documentation</h2>
    <ul>
    <li><a href="http://docs.aws.amazon.com/elasticbeanstalk/latest/dg/create_deploy_docker_ecs.html">Multi Container Docker Environments</a></li>
    <li><a href="http://docs.aws.amazon.com/elasticbeanstalk/latest/dg/create_deploy_docker_ecstutorial.html">Getting Started with Multi Container Docker Environments</a></li>
    <li><a href="http://docs.aws.amazon.com/elasticbeanstalk/latest/dg/">AWS Elastic Beanstalk overview</a></li>
    <li><a href="http://docs.aws.amazon.com/elasticbeanstalk/latest/dg/index.html?concepts.html">AWS Elastic Beanstalk concepts</a></li>
    </ul>
  </div>
</body>
</html>
<?
}
?>
