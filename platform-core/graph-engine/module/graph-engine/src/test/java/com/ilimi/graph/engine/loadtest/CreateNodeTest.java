package com.ilimi.graph.engine.loadtest;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import scala.concurrent.Future;
import akka.actor.ActorRef;
import akka.pattern.Patterns;

import com.ilimi.common.dto.Request;
import com.ilimi.graph.common.enums.GraphHeaderParams;
import com.ilimi.graph.dac.enums.GraphDACParams;
import com.ilimi.graph.dac.model.Node;
import com.ilimi.graph.engine.router.GraphEngineManagers;
import com.ilimi.graph.model.node.DefinitionDTO;

public class CreateNodeTest {
    ActorRef reqRouter = null;
//    String graphId = "GRAPH_" + System.currentTimeMillis();
    String graphId = "JAVA_CS";
    String SCENARIO_NAME ="CREATE_NODE";
    
    private static final Logger logger = LogManager.getLogger("PerformanceTestLogger");
    
    @BeforeTest
    public void init() throws Exception {
        String logFileName = SCENARIO_NAME +"_" + System.currentTimeMillis();
        System.out.println("Logs are captured in "+logFileName+".log file.");
        LoggerUtil.config(logFileName);
        reqRouter = TestUtil.initReqRouter();
        createGraph(reqRouter, graphId);
        Thread.sleep(5000);
        Future<Object> defNodeRes = saveDefinitionNode(reqRouter, graphId);
        TestUtil.handleFutureBlock(defNodeRes, "saveDefinitionNode", GraphDACParams.node_id.name());
        Thread.sleep(5000);
    }
    
    private Future<Object> saveDefinitionNode(ActorRef reqRouter2, String graphId2) {
        Request request = new Request();
        request.getContext().put(GraphHeaderParams.graph_id.name(), graphId);
        request.setManagerName(GraphEngineManagers.NODE_MANAGER);
        request.setOperation("saveDefinitionNode");
        DefinitionDTO dto = new DefinitionDTO();
        dto.setObjectType("COURSE");
        request.put(GraphDACParams.definition_node.name(), dto);
        Future<Object> req = Patterns.ask(reqRouter, request, TestUtil.timeout);
        return req;
    }

    @AfterTest
    public void destroy() throws Exception {
        Thread.sleep(10000);
    }
        
    //@Test(threadPoolSize=500, invocationCount=500)
    public void testCreateNode1() {
        try {
            String nodeId = "Node_"+System.currentTimeMillis()+"_"+Thread.currentThread().getId();
            String objectType = "COURSE";
            Map<String, Object> metadata = TestUtil.getMetadata((int)Thread.currentThread().getId()%10);
            Future<Object> nodeReq = createDataNode(reqRouter, graphId, nodeId, objectType, metadata);
            TestUtil.handleFutureBlock(nodeReq, "createDataNode", GraphDACParams.node_id.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //@Test
    public void testCreateNode2() {
        try {
            logger.info("Sleep for 10 seconds");
            logger.info("****************************************************************************************************");
            logger.info("");
            Thread.sleep(10000);
            logger.info("");
            logger.info("");
            logger.info("****************************************************************************************************");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //@Test(threadPoolSize=500, invocationCount=500)
    public void testCreateNode3() {
        try {
            String nodeId = "Node_"+System.currentTimeMillis()+"_"+Thread.currentThread().getId();
            String objectType = "COURSE";
            Map<String, Object> metadata = TestUtil.getMetadata((int)Thread.currentThread().getId()%10);
            Future<Object> nodeReq = createDataNode(reqRouter, graphId, nodeId, objectType, metadata);
            TestUtil.handleFutureBlock(nodeReq, "createDataNode", GraphDACParams.node_id.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private Future<Object> createDataNode(ActorRef reqRouter, String graphId, String nodeId, String objectType, Map<String, Object> metadata) {
        Request request = new Request();
        request.getContext().put(GraphHeaderParams.graph_id.name(), graphId);
        request.getContext().put(GraphHeaderParams.request_id.name(), "REQUEST_"+Thread.currentThread().getId());
        request.getContext().put(GraphHeaderParams.scenario_name.name(), SCENARIO_NAME);
        request.setManagerName(GraphEngineManagers.NODE_MANAGER);
        request.setOperation("createDataNode");
        Node node = new Node(graphId, metadata);
        node.setObjectType(objectType);
        request.put(GraphDACParams.node.name(), node);
        Future<Object> req = Patterns.ask(reqRouter, request, TestUtil.timeout);
        return req;
    }
    
    private Future<Object> createGraph(ActorRef reqRouter, String graphId) {
        Request request = new Request();
        request.getContext().put(GraphHeaderParams.request_id.name(), "REQUEST_"+Thread.currentThread().getId());
        request.getContext().put(GraphHeaderParams.scenario_name.name(), "CREATE_GRAPH");
        request.getContext().put(GraphHeaderParams.graph_id.name(), graphId);
        request.setManagerName(GraphEngineManagers.GRAPH_MANAGER);
        request.setOperation("createGraph");
        Future<Object> req = Patterns.ask(reqRouter, request, TestUtil.timeout);
        return req;
    }
}
