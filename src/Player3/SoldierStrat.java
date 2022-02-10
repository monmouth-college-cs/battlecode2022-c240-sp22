package Player3;

import battlecode.common.*;

strictfp class SoldierStrat {
    static void runSoldier(RobotController rc) throws GameActionException {
        // Try to attack someone
        int radius = rc.getType().actionRadiusSquared;
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] enemies = rc.senseNearbyRobots(radius, opponent);
        if (enemies.length > 0) {
            MapLocation toAttack = enemies[0].location;
            if (rc.canAttack(toAttack)) {
                rc.attack(toAttack);
            }
        }
        Direction dir = RobotPlayer.directions[RobotPlayer.rng.nextInt(RobotPlayer.directions.length)];
        int getRoundNum = rc.getRoundNum();
        int e = 0, n = 0, w = 0, s = 0;
        if (rc.canMove(Direction.EAST) && e < 1 && getRoundNum > 0 && getRoundNum < 350 ) {
            rc.move(Direction.EAST);
        } else if (rc.canMove(Direction.WEST) && n < 1 && getRoundNum > 350  && getRoundNum < 600) {
            e++;
            rc.move(Direction.WEST);
        } else if (rc.canMove(Direction.NORTH) && n < 1 && getRoundNum > 600  && getRoundNum < 1000) {
            e++;
            rc.move(Direction.NORTH);
        } else if (rc.canMove(Direction.SOUTH) && s < 1 && getRoundNum > 1500  && getRoundNum< 2000) {
            s++;
            rc.move(Direction.SOUTH);
        } else if (rc.canMove(Direction.EAST) && s < 1 && getRoundNum > 1000  && getRoundNum< 1500) {
            s++;
            rc.move(Direction.EAST);
        } else if (rc.canMove(Direction.WEST) && s < 1 && getRoundNum > 1500  && getRoundNum< 2000) {
            s++;
            rc.move(Direction.WEST);
    }
    }
}

