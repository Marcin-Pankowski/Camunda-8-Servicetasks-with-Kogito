package de.piu.templates.kogito.test.listener;

import javax.enterprise.context.ApplicationScoped;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.TestOnly;
import org.kie.api.event.process.ProcessNodeTriggeredEvent;
import org.kie.kogito.internal.process.event.DefaultKogitoProcessEventListener;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@TestOnly
@ApplicationScoped
public class TestNodeTriggeredListener extends DefaultKogitoProcessEventListener {
    private List<String> triggeredNodesNames = new ArrayList<>();
    private String processNameToObserve;
    private String processIdToObserve;


    @Override
    public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
        if(event.getProcessInstance().getProcessName().equals(processNameToObserve) || event.getProcessInstance().getProcessId().equals(processIdToObserve)) {
            triggeredNodesNames.add(event.getNodeInstance().getNodeName());
        }
    }


    public void resetObserver() {
        triggeredNodesNames.clear();
    }
}
