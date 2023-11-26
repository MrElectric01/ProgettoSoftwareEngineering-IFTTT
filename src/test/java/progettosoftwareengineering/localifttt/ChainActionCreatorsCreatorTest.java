/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettosoftwareengineering.localifttt;

import org.junit.*;
import static org.junit.Assert.*;

public class ChainActionCreatorsCreatorTest {
    
    private BaseActionCreator chain;
    
    @Before
    public void setUp() {
        chain = ChainActionCreatorsCreator.chain();
    }
    
//    In order to verify that the Singleton pattern is proprerly implemented,
//    we a second instance, and verify if the second one is the same object of the first one.
    @Test
    public void testChainSingleton() {
        BaseActionCreator instance2 = ChainActionCreatorsCreator.chain();
        assertEquals(chain, instance2);
    }
//    TODO when the chain contains at least one Creator     
//    In order to verify that no one can Handle a request with ActionType = TEST,
//    and so the whole Chain is traversed (where all the specific Creator handler is 
//    tested in the specific test class), we check that the created Action is null.
    @Test
    public void testChainElements() {
//        Action action = chain.createAction(null, null);
//        assertNull(action);
    }
    
}