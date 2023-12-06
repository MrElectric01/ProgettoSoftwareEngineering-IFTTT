/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.model.rule;

public class RulesCheckThread extends Thread {

    private static RulesCheckThread instance = null;
    private final int checkingPeriod = 3000;

    private RulesCheckThread() {
    }

    public static RulesCheckThread getInstance() {
        if (instance == null) {
            instance = new RulesCheckThread();
        }
        return instance;
    }

//    Static method useful for launch the Thread if isn't started yet.
    public static void startChecking() {
        if (!RulesCheckThread.getInstance().isAlive()) {
            RulesCheckThread.getInstance().start();
        }
    }

//    Method that check all the Rules every CheckingPeriod time,
//    and if a Rule is verified, the Thread activate it.
    @Override
    public void run() {
        while (!RuleCollection.getInstance().getRules().isEmpty()) {
            try {
                Thread.sleep(checkingPeriod);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (Rule rule : RuleCollection.getInstance()) {
                if (rule.checkRule()) {
                    rule.activateRule();
                }
            }
        }
        instance = null;
    }

//    Method useful only for testing (infact is package).
    int getCheckingPeriod() {
        return checkingPeriod;
    }
}
