import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
        	Content content=new Content("A123", "blood", 'A', 10, 60.67, ContentType.BLOOD);
        	
        
            RobotArm robotArm = new RobotArm(10, 10);
            robotArm.storeItemAtLocation(content,4,5);
            System.out.println(robotArm.retrieveItemAtLocation(4, 5).toString());
            robotArm.storeItemAtLocation(content,5,4);
            robotArm.storeItemAtLocation(content,6,7);
            Order order=robotArm.fulfilOrderWithMinimalCostForVolumeAndType(7, ContentType.BLOOD);
            System.out.println(order);
        } catch (Exception e) {
            System.out.println("You probably should start by implementing the constructor :)");

        }
        System.out.println("Congratulations, you run the application");

    }

}
