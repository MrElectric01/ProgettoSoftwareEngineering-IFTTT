package progettosoftwareengineering.localifttt.model.rule.action;

import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BaseActionCreatorTest {
    
    private BaseActionCreator base;
    
//    We need to use a concrete version of BaseActionCreator, so
//    we use an its anonymous implementation that do nothing in the abstract method.
    @Before
    public void setUp() {
        base = new BaseActionCreator() {
            @Override
            public Action createAction(ActionType action, Map<String,String> actParam) {
                return null;
            }
        };
    }
    
//    In order to verify that if there isn't a next we got "null" as default, 
//    we execute the nextCreator method without set a next.
    @Test
    public void testNextCreatorWithNullNext() {
        Action action = base.nextCreator(null, null);
        assertNull(action);
    }
    
//    In order to verify that if there is a next, we obtain the return of his createAction method, 
//    we execute the nextCreator method after have setted a next, and check
//    if his createAction method has been executed.
    @Test
    public void testNextCreatorWithNotNullNext() {
        base = spy(base);
        BaseActionCreator creator2 = base;
        base.setNext(creator2);
        base.nextCreator(null, null);
        verify(creator2, times(1)).createAction(null, null);
    }
}

