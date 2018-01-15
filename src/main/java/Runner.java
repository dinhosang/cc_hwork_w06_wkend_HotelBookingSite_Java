import RunnerAssistance.RunnerHelper;
import WebSite.TravelInCode;

public class Runner {

    public static void main(String[] args) {

        while(true){
            TravelInCode site = new TravelInCode();
            RunnerHelper helper = new RunnerHelper(site);
            helper.userLoginQuery();
            helper.mainMenu();
        }
    }
}
