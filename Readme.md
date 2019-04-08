# Objective

Implement assignment [option 1](https://github.com/jesusjavierdediego/assignments)

### Clue Game
The objective is making the structure to build the clue game following the [requirements](/definitions/MVPRequirements)

1. A front App module to play
2. A services API to support the front module
3. API push to send messages to user meanwhile playing
4. A persistence module for logged-in users, their self-made games and their played games

### Minimum Value Product.

The goal is developing the point 2 and 3 as minimum requirements.

### Tools
- Maven to build project delivery
- SparkJava for endpoints API
- BDD for integration and acceptance test
- TDD for development
- Dockerfile to generate Dockers
- TravisCI for continuous integration [project url](https://travis-ci.com/fmsolana/assignment)
- DockerHub to registry container [project url](https://hub.docker.com/r/fmsolana/cluegame)
- Amazon ECS for deployments
- SoapUI for stresstest
- AmazonMQ for queue

##### [Destop Develop tool](/defitions/tools.txt)


### URLs
- http://ec2co-ecsel-148u7gdkhyabo-2121828102.us-east-2.elb.amazonaws.com:4567/smoke
- https://travis-ci.com/fmsolana/assignment
- https://hub.docker.com/r/fmsolana/cluegame

### Test

With SoapUI [here](/ClueGameApiSoapUI.xml). I did a load test during 10 minutes, first in local environment after against ECS. All test was running from pc.

#### The SoapUI data for Loadtest in localhost:

![LoadTestLocalhost](resources/LoadTestLocalhost.PNG)

#### The SoapUI data for Loadtest in Amazon ECS:

![LoadTestAmazon](resources/LoadTestAmazon.PNG)

#### Graph with container during execution

![ContainerGraph](resources/ContainerGraph.PNG)

#### Cluster Services graph

![ClusterServiceECS](resources/ClusterServiceECS.PNG)

#### Amazon Balancer

![ELBDuringLoadTest](resources/ELBDuringLoadTest.PNG)


##### [Task by day](/definitions/task)


