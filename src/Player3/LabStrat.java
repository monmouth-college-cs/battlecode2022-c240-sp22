package Player3;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class LabStrat {
    public static void runLaboratory(RobotController rc) throws GameActionException {
        int turns = 0;
        if (rc.getTeamLeadAmount(rc.getTeam()) > 500 && rc.canTransmute() && turns >= 500){
            rc.transmute();
        } else if(turns < 500){
            rc.transmute();
        }
        turns++;
    }
}
