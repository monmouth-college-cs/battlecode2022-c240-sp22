package Player1;

import battlecode.common.*;

import java.util.Random;

strictfp class BuildStrat {
    //turn counter
    static int turn = 0;

    static void runBuilder(RobotController rc) throws GameActionException {
        //move to repair locations
        turn ++;
        MapLocation me = rc.getLocation();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                MapLocation repLocation = new MapLocation(me.x + dx, me.y + dy);
                while (rc.canRepair(repLocation)) {
                    rc.repair(repLocation);
                }
            }
        }
            // find robots that need repairs
        RobotInfo[] robots = rc.senseNearbyRobots();
        int distance = Integer.MAX_VALUE;
        Direction dir = null;
        for(RobotInfo robot : robots){
            if(robot.getTeam().equals(rc.getTeam()) && robot.type.isBuilding()  && robot.health < robot.type.getMaxHealth(robot.level)){
                if(rc.getLocation().distanceSquaredTo(robot.location) < distance){
                    dir = rc.getLocation().directionTo(robot.getLocation());
                    distance = rc.getLocation().distanceSquaredTo(robot.location);
                }
            }
        }
        if(dir != null && rc.canMove(dir)){
            rc.move(dir);
        }
        int directionIndex = RobotPlayer.rng.nextInt(RobotPlayer.directions.length);
        dir = RobotPlayer.directions[directionIndex];
        if (rc.canMove(dir)) {
            rc.move(dir);
            System.out.println("I moved!");
        }
        //if lead is greater than 10000 build a lab
        if(rc.getTeamLeadAmount(rc.getTeam()) > 2000 && turn % 30 == 0 && rc.canBuildRobot(RobotType.LABORATORY, dir)){
            rc.buildRobot(RobotType.LABORATORY, dir);
        }else if(rc.getTeamLeadAmount(rc.getTeam()) > 2500 && turn % 100 == 0 && rc.canBuildRobot(RobotType.WATCHTOWER, dir)) {
            rc.buildRobot(RobotType.WATCHTOWER, dir);
        }
    }
}
