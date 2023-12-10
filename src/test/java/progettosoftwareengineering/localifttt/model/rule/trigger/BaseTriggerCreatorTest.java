package progettosoftwareengineering.localifttt.model.rule.trigger;

import java.util.Map;
import org.junit.*;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class BaseTriggerCreatorTest {
    
    private BaseTriggerCreator base;
    
//    We need to use a concrete version of BaseTriggerCreator, so
//    we use an its anonymous implementation that do nothing in the abstract method.
    @Before
    public void setUp() {
        base = new BaseTriggerCreator() {
            @Override
            public Trigger createTrigger(TriggerType trigger, Map<String,String> trigParam) {
                return null;
            }
        };
    }
    
//    In order to verify that if there isn't a next we got "null" as default, 
//    we execute the nextCreator method without set a next.
    @Test
    public void testNextCreatorWithNullNext() {
        Trigger trigger = base.nextCreator(null, null);
        assertNull(trigger);
    }
    
//    In order to verify that if there is a next, we obtain the return of his createTrigger method, 
//    we execute the nextCreator method after have setted a next, and check
//    if his createTrigger method has been executed.
    @Test
    public void testNextCreatorWithNotNullNext() {
        base = spy(base);
        BaseTriggerCreator creator2 = base;
        base.setNext(creator2);
        base.nextCreator(null, null);
        verify(creator2, times(1)).createTrigger(null, null);
    }
}
