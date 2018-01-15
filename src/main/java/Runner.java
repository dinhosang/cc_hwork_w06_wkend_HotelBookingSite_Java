import RunnerAssistance.RunnerHelper;
import WebSite.TravelInCode;

public class Runner {

    public static void main(String[] args) {

        TravelInCode site = new TravelInCode();
        RunnerHelper helper = new RunnerHelper(site);
        helper.welcomeScreen();

    }
}
