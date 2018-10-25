package OOD;
import java.util.*;
/**
 Talk about singleton
 */
enum State {
    IDLE, UP, DOWN, WAIT, OFF, OUT;
}

class Request {
    int currentFloor;
    State direction;
}

class ElevatorManager {
    private static ElevatorManager obj;

    final private List<Elevator> elevators;
    private List<Elevator> operatingElevators;
    final int MIN_FLOOR = -2;
    final int MAX_FLOOR = 100;
    boolean systemOn;

    private ElevatorManager() {
        elevators = new ArrayList<>();
    }

    public static ElevatorManager getInstance() {
        if(obj == null) {
            obj = new ElevatorManager();
        }
        return obj;
    }

    void addElevator(Elevator e) {
        elevators.add(e);
    }

    public boolean systemTurnOn() {
        if(systemOn) {
            return true;
        }
        List<Elevator> operating = new ArrayList<>();
        for(Elevator e: elevators) {
            if(e.switchOn()) {
                operating.add(e);
            }
        }
        if(operating.size() > 0) {
            operatingElevators = operating;
            systemOn = true;
            return true;
        }
        return false;
    }

    public boolean systemTurnOff() {
        if(systemOn) {
            //Timer timer = new Timer(60000);
            for(Elevator e: operatingElevators) {
                while(e.state == State.UP || e.state == State.DOWN) {
                    //if(timer.timeout()) return false;
                    //wait();
                }
                e.switchOff();
            }
            operatingElevators = null;
        }
        systemOn = false;
        return true;
    }

    public void newRequest(Request request) {
        Elevator expected = null;
        for(Elevator e: operatingElevators) {
            if(expected == null) {
                expected = e;
            } else if(e.currentFloor == request.currentFloor && e.state == request.direction) {
                expected = e;
                break;
            } else if(Elevator.compare(e, expected, request) < 0) {
                expected = e;
            }
        }
    }


    public boolean elevatorTurnOff(Elevator e) {
        return true;
    }

    public boolean elevatorTurnOn(Elevator e) {
        return true;
    }
}


public class Elevator {
    State state;
    int currentFloor;
    int[] operatingFloors;//-1 if not reachable, n means current index is the nth reachable floor
    Queue<Integer> upQueue;
    Queue<Integer> downQueue;

    Elevator(int initFloor, int[] operatingFloors) {
        upQueue = new LinkedList<>();
        downQueue = new LinkedList<>();
        state = State.OFF;
        currentFloor = initFloor;
        this.operatingFloors = operatingFloors;
    }


    public static int compare(Elevator a, Elevator b, Request request) {
        return 0;
    }

    public boolean switchOff() {
        return true;
    }

    public boolean switchOn() {
        return true;
    }

    public boolean setOutOfService() {
        return true;
    }

    public boolean openDoor() {
        return true;
    }

    public boolean closeDoor() {
        return true;
    }

    public boolean clickOnFloor(int desination) {
        return true;
    }
}
