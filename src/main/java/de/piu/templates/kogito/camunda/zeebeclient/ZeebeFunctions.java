package de.piu.templates.kogito.camunda.zeebeclient;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivateJobsResponse;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.quarkus.logging.Log;

@ApplicationScoped
public class ZeebeFunctions {

    @Inject
    ZeebeClient zeebeClient;

    public List<ActivatedJob> activateJobs(String jobType, String workerName, int maxJobsToActivate,
            Integer timeoutInMinutes) {

        ActivateJobsResponse activateJobsResponse = zeebeClient.newActivateJobsCommand()
                .jobType(jobType)
                .maxJobsToActivate(maxJobsToActivate)
                .timeout(Duration.ofMinutes(timeoutInMinutes))
                .workerName(workerName)
                .send()
                .join();
        return activateJobsResponse.getJobs();
    }

    public void completeJob(Long jobKey, Map<String,Object> variables) {
        zeebeClient.newCompleteCommand(jobKey)
                .variables(variables)
                .send();
                
        Log.info("Job completed with key: " + jobKey);    
        Log.info("\n ██████╗ █████╗ ███╗   ███╗██╗   ██╗███╗   ██╗██████╗  █████╗ \n" + //
        "██╔════╝██╔══██╗████╗ ████║██║   ██║████╗  ██║██╔══██╗██╔══██╗\n" + //
        "██║     ███████║██╔████╔██║██║   ██║██╔██╗ ██║██║  ██║███████║\n" + //
        "██║     ██╔══██║██║╚██╔╝██║██║   ██║██║╚██╗██║██║  ██║██╔══██║\n" + //
        "╚██████╗██║  ██║██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██████╔╝██║  ██║\n" + //
        " ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═════╝ ╚═╝  ╚═╝\n"+
        "███████╗███████╗██████╗ ██╗   ██╗██╗ ██████╗███████╗████████╗ █████╗ ███████╗██╗  ██╗\n" +                                        ""+
        "██╔════╝██╔════╝██╔══██╗██║   ██║██║██╔════╝██╔════╝╚══██╔══╝██╔══██╗██╔════╝██║ ██╔╝\n"+
        "███████╗█████╗  ██████╔╝██║   ██║██║██║     █████╗     ██║   ███████║███████╗█████╔╝ \n"+
        "╚════██║██╔══╝  ██╔══██╗╚██╗ ██╔╝██║██║     ██╔══╝     ██║   ██╔══██║╚════██║██╔═██╗ \n"+
        "███████║███████╗██║  ██║ ╚████╔╝ ██║╚██████╗███████╗   ██║   ██║  ██║███████║██║  ██╗\n"+
        "╚══════╝╚══════╝╚═╝  ╚═╝  ╚═══╝  ╚═╝ ╚═════╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝\n"+
        "███████╗██╗   ██╗██╗     ██╗     ███████╗██╗██╗     ███████╗██████╗\n"+
        "██╔════╝██║   ██║██║     ██║     ██╔════╝██║██║     ██╔════╝██╔══██╗\n"+
        "█████╗  ██║   ██║██║     ██║     █████╗  ██║██║     █████╗  ██║  ██║\n"+
        "██╔══╝  ██║   ██║██║     ██║     ██╔══╝  ██║██║     ██╔══╝  ██║  ██║\n"+
        "██║     ╚██████╔╝███████╗███████╗██║     ██║███████╗███████╗██████╔╝\n"+
        "╚═╝      ╚═════╝ ╚══════╝╚══════╝╚═╝     ╚═╝╚══════╝╚══════╝╚═════╝\n");                                                                                            
    }

    public void failJob(Long jobKey) {
        zeebeClient.newFailCommand(jobKey)
                .retries(3)
                .errorMessage("Failed to process job")
                .send();
        Log.error("Job failed with key " + jobKey);
    }

    public void broadcastMessage(String messageName, String correlationKey, Map<String, Object> variables) {
        zeebeClient.newPublishMessageCommand()
                .messageName(messageName)
                .correlationKey(correlationKey)
                .variables(variables)
                .send();
    }

}