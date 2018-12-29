package view;

import board.Location;

import java.util.HashMap;

public class PipeView {

    Character val;
    HashMap<Character, Character> rotateMap;
    Location location;
    HashMap<String,Double> pixels;

    public PipeView(Double height, Double width,Character val, HashMap<Character, Character> rotateMap, Location location) {
        this.val = val;
        this.rotateMap = rotateMap;
        this.location = location;
        setPixels(height,width);
    }

    public PipeView(Double height,Double width,Character pipe, Location loc) {
        try {
            rotateMapInit();
            setVal(pipe);
            setLocation(loc);
            setPixels(height,width);
        } catch (Exception e) {
            System.out.println("Pipe.Pipe(Character pipe) :" + e.getMessage());
        }
    }

    public PipeView(Double height,Double width,PipeView pipe) {
        try {
            rotateMapInit();
            setVal(pipe.getVal());
            setLocation(pipe.getLocation());
            setPixels(height,width);
        } catch (Exception e) {
            System.out.println("Pipe.Pipe(Pipe pipe) :" + e.getMessage());
        }
    }

    public PipeView(Double height,Double width,Character row, Character col) {
        rotateMapInit();
        setLocation(new Location(row, col));
        setPixels(height,width);
    }

    public PipeView(Double height,Double width,Location loc){
        rotateMapInit();
        setLocation(loc);
        setPixels(height,width);
    }

    private void setPixels(Double height, Double width) {
        this.pixels = new HashMap<String,Double>(){
            {
                put("startX", location.getCol() * width);
                put("startY", location.getRow() * height);
                put("endX", location.getCol() * width + width);
                put("endY", location.getRow() * height + height);
            }};
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

    public Character rotate(Integer num) {
        Character updatedVal = ' ';

        return updatedVal;
    }

    public boolean isEmpty() {
        return (this.val == ' ');
    }


    public boolean equals(PipeView pipe) {
        boolean isEqual = false;
        try {
            isEqual = this.val == (pipe.getVal());
        } catch (Exception e) {
            System.out.println("Pipe.Equals :" + e.getMessage());
        }
        return isEqual;
    }



}

