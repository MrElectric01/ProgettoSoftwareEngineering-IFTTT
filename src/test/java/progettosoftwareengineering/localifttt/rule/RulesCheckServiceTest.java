/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettosoftwareengineering.localifttt.rule;

import org.junit.*;
import static org.junit.Assert.*;

public class RulesCheckServiceTest {
    
    private static RulesCheckService service;
    
    @Before
    public void setUp() {
        service =  RulesCheckService.getInstance();
    }

//    In order to verify that the implementation of the Singleton pattern is correctly done,
//    we get a second instance, and verify if the second one is the same object of the first one.
    @Test
    public void testGetInstance() {
        RulesCheckService service2 = RulesCheckService.getInstance();
        assertEquals(service, service2);
    }

//    TODO when we understand how to test something that call the Eventh Thread
    @Test
    public void testStartChecking() {
    }
    
//    TODO when we understand how to test something that call the Eventh Thread
    @Test
    public void testCreateTask() {
    }
    
}
