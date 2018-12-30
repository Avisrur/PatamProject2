package board;

import java.util.HashMap;

public class Pipe {

    Character val;
    HashMap<Character, Character> rotateMap;
    Location location;

    public Pipe(Character val, HashMap<Character, Character> rotateMap, Location location) {
        this.val = val;
        this.rotateMap = rotateMap;
        this.location = location;
    }

    public Pipe(Character pipe, Location loc) {
        try {
            rotateMapInit();
            setVal(pipe);
            setLocation(loc);
        } catch (Exception e) {
            System.out.println("Pipe.Pipe(Character pipe) :" + e.getMessage());
        }
    }

    public Pipe(Pipe pipe) {
        try {
            rotateMapInit();
            setVal(pipe.getVal());
            setLocation(pipe.getLocation());
        } catch (Exception e) {
            System.out.println("Pipe.Pipe(Pipe pipe) :" + e.getMessage());
        }
    }

    public Pipe(Character row, Character col) {
        rotateMapInit();
        setLocation(new Location(row, col));
    }

    public Pipe(Location loc){
        rotateMapInit();
        setLocation(loc);
    }

    public Character getVal() {
        return val;
    }

    public void setVal(Character val) {
        this.val = val;
    }

    public HashMap<Character, Character> getRotateMap() {
        return rotateMap;
    }

    public void setRotateMap(HashMap<Character, Character> rotateMap) {
        this.rotateMap = rotateMap;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void rotateMapInit() {
        if (this.rotateMap == null) {
            this.rotateMap = new HashMap<Character, Character>() {{
                put('F', '7');
                put('7', 'J');
                put('J', 'L');
                put('L', 'F');
                put('-', '|');
                put('|', '-');
            }};
        }
    }

    public Character rotate() {
        try {
            if (this.val != 's' && this.val != 'g' && this.val != ' ') {
                try {
                    setVal(this.rotateMap.get(this.val));
                } catch (NullPointerException e) {
                    System.out.println("Null ");
                }
            }
        } catch (Exception e) {
            System.out.println("Pipe.Rotate :" + e.getMessage());
        }
        return getVal();
    }

    public boolean isEmpty() {
        return (this.val == ' ');
    }


    public boolean equals(Pipe pipe) {
        boolean isEqual = false;
        try {
            isEqual = this.val == (pipe.getVal());
        } catch (Exception e) {
            System.out.println("Pipe.Equals :" + e.getMessage());
        }
        return isEqual;
    }

    public Character rotate(Integer num) {
        Character updatedVal = ' ';
        for (int i = 0; i < num ; i++) {
            updatedVal = this.rotate();
        }
        return updatedVal;
    }


}

