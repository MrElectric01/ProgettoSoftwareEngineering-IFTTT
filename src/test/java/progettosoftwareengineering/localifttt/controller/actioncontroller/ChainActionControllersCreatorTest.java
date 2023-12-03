/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettosoftwareengineering.localifttt.controller.actioncontroller;

import org.junit.*;
import static org.junit.Assert.*;

public class ChainActionControllersCreatorTest {
    
    private BaseActionController chain;
 
    @Before
    public void setUp() {
        chain = ChainActionControllersCreator.chain();
    }
    
//    In order to verify that the Singleton pattern is proprerly implemented,
//    we a second instance, and verify if the second one is the same object of the first one.
    @Test
    public void testChainSingleton() {
        BaseActionController instance2 = ChainActionControllersCreator.chain();
        assertEquals(chain, instance2);
    }
}
