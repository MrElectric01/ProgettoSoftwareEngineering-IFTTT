package progettosoftwareengineering.localifttt.controller.actioncontroller;

import java.util.Observable;
import org.junit.*;
import static org.mockito.Mockito.*;
import progettosoftwareengineering.localifttt.model.rule.action.Action;

public class BaseActionControllerTest {
    
    private BaseActionController base;
    
//    We need to use a concrete version of BaseActionController, so
//    we use an its anonymous implementation that do nothing in the abstract methods.
    @Before
    public void setUp() {
        base = new BaseActionController() {
            @Override
            public void observeAction(Action action) {
            }
            
            @Override
            public void update(Observable o, Object arg) {
            }
        };
    }
    
//    In order to verify that if there is a next, we call his observeAction method, 
//    we execute the nextController method after have setted a next, and check
//    if his observeAction method has been executed.
    @Test
    public void testNextController() {
        base = spy(base);
        BaseActionController controller2 = base;
        base.setNext(controller2);
        base.nextController(null);
        verify(controller2, times(1)).observeAction(null);
    }
}
