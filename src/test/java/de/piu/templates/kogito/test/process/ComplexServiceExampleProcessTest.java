package de.piu.templates.kogito.test.process;

import de.piu.templates.kogito.test.listener.TestNodeTriggeredListener;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.kie.kogito.process.Processes;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import java.util.HashMap;
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
        var model = process.createModel();

        Map<String, Object> processVariables = new HashMap<>();

        Map<String, Object> inputVariables = new HashMap<>();
        inputVariables.put("variableA", "Kogito");
        inputVariables.put("variableB", "rocks");

        processVariables.put("inputVariables", inputVariables);
        processVariables.put("jobKey", 1234L);

        model.fromMap(processVariables);

        var processInstance = process.createInstance(model);
        processInstance.start();

        assertThat(processInstance.status()).isEqualTo(org.kie.api.runtime.process.ProcessInstance.STATE_COMPLETED);

        var triggeredNodesNames = testNodeTriggeredListenerInstance.get().getTriggeredNodesNames();
        //Is the end node reached?
        assertThat(triggeredNodesNames.stream().reduce((first, second) -> second).get()).isEqualTo("Service fulfilled");
    }

    @Test
    public void checkOutputVariablesAreExpected() {

        var process = processes.processById("ComplexServiceExample");
        var model = process.createModel();

        Map<String, Object> processVariables = new HashMap<>();

        Map<String, Object> inputVariables = new HashMap<>();
        inputVariables.put("variableA", "Kogito");
        inputVariables.put("variableB", "rocks");

        processVariables.put("inputVariables", inputVariables);
        processVariables.put("jobKey", 1234L);

        model.fromMap(processVariables);

        var processInstance = process.createInstance(model);
        processInstance.start();

        var processVariablesAfterCompletion = processInstance.variables();
        @SuppressWarnings("unchecked")
        var outputVariables = (Map<String, Object>) processVariablesAfterCompletion.toMap().get("outputVariables");
        assertThat(outputVariables.get("resultText")).isEqualTo("Services with Kogito rocks the World! with complex input variables : {variableA=Kogito, variableB=rocks}");
    }
}

