package de.piu.templates.kogito.test.process;

import de.piu.templates.kogito.test.listener.TestNodeTriggeredListener;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.kie.kogito.process.Processes;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ComplexServiceExampleProcessTest {


    @Inject
    Processes processes;

    @Inject
    Instance<TestNodeTriggeredListener> testNodeTriggeredListenerInstance;

    @BeforeEach
    void resetListener(){
        testNodeTriggeredListenerInstance.get().setProcessNameToObserve("ComplexServiceExample");
        testNodeTriggeredListenerInstance.get().resetObserver();
    }

    @Test
    public void checkIfProcessEndIsReached() {

        var process = processes.processById("ComplexServiceExample");
        var processInstance = process.createInstance(process.createModel());
        processInstance.start();

        assertThat(processInstance.status()).isEqualTo(org.kie.api.runtime.process.ProcessInstance.STATE_COMPLETED);

        var triggeredNodesNames = testNodeTriggeredListenerInstance.get().getTriggeredNodesNames();
        //Is the end node reached?
        assertThat(triggeredNodesNames.stream().reduce((first, second) -> second).get()).isEqualTo("Service fulfilled");
    }

    @Test
    public void checkOutputVariablesAreExpected() {

        var process = processes.processById("ComplexServiceExample");
        var processInstance = process.createInstance(process.createModel());
        processInstance.start();

        var processOutputvariables = processInstance.variables().toMap();
        assertThat(((String)processOutputvariables.get("outputVariables")).equals("Services with Kogito rocks the World! with complex input variables : {KeyB=as input!!!, KeyA=JavaObject}"));

    }


}

