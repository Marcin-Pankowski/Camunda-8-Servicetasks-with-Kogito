# Kogito Template for Camunda 8 Service Tasks (External Tasks)

This template showcases how Kogito is leveraged as an External Service Task Worker, Handler, and Service. It enables us to apply our BPMN expertise in the context of service tasks. Finally, upon the completion and operational status of the service, the documentation is available because the process itself is the documentation.

# Advantages

## Decision Makers:
Product Owners benefit from comprehensive transparency regarding the progress and development status of a service at all times. This constant insight allows for effective control and adjustment of project objectives, ensuring optimal resource allocation and facilitating decision-making at every level of the project.

## Developers:
Developers enjoy the advantage of being able to design every part of the service efficiently and free from unnecessary bulk (BloatCode). This lean development approach promotes high code quality and maintainability. Moreover, each component of the service is individually testable, which eases the detailed examination and validation of functionalities. Integration tests can be implemented simply and effectively thanks to the modular structure and clear interfaces between service components. This method not only improves the reliability and stability of the entire service but also accelerates the development process through rapid feedback and early identification of potential issues.

## Resuable and Company No-Code Repository

Unlike with a traditional approach, Kogito makes it easier to create reusable functions. An in-house NoCode repository is automatically created. Every additional service will be created faster, more cost-effectively, more clearly, and more reliably.

# How to use it?

To experiment with the processes, please use Visual Studio Code and load the Kogito Bundle extension. 
Start your Docker environment.

1. Pull this repository with VSC.
2. Configure your Zeebe gateway address in the 'application.properties' file.
3. Start the Service : mvn clean install quarkus:dev.
4. Start the Camunda Process from the Camunda folder.

or

3. Open ShortPollJobWorkerExample.bpmn.
    3.1 Click on "Activate Camunda Jobs"-Service Task.
    ![ServiceTask](images/ServiceTask.png)
    3.2 Open Properties.
    ![ServiceTask](images/EditServiceTask.png)
    ![ServiceTask](images/EditServiceTask2.png)
    3.3 Open Zeebe Client Properties.
    ![Zeebe Client Properties](images/EditJobHandlerProperies.png)
    3.4 Configure your JobType.
5. Start the service : mvn clean install quarkus:dev.
6. Start Camunda 8.
7. Start your Camunda 8 Process.

## How to use Quarkus

Start developing:
mvn quarkus:dev

Open Quarkus Dev UI:
press 'd'

Change code and recompile:
No need to recompile, just save the code and continue developing.

Change the BPMN and recompile:
No need to recompile, just save the code and continue modeling.

Change a Test and recompile:
No need to recompile, just save the code and continue testing.

## API first?!

Sure! Everywhere!

Just open your process properties, add process data, and include a tag (input). Then, open the Dev UI and access SwaggerUI to examine the schema of POST ComplexServiceExample. This approach is applicable to every element, including outputs as well!

Wondering about complex data structures? Absolutely, they're supported!

## How to write delegates to use Services in your process...

There's no need to. Simply write your Java Service and don't forget to annotate it with @Singleton, then use it in a service task. No boilerplate code is needed in Kogito.

You can even use services in script tasks—it's all Java!

En Example Service is provided in this template.

# Test
A Simple Test Example is provided in the test folder. 

Copyright 2024 www.piu.de