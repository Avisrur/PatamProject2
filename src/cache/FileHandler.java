package cache;

import solver.Solution;

import java.io.*;


public class FileHandler implements CacheManager {

    String id;

    public FileHandler(String boardID){
            this.id = boardID;
    }

    @Override
    public void saveSolvedLevel(Solution solution) {
        try{
            File file = new File(this.id);

            if (solution != null && !file.exists()) {

                if (file.createNewFile()) {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
                    objectOutputStream.writeObject(solution);
                    objectOutputStream.close();
                }
            }
        }catch (Exception e){
            System.out.println("FileHandler.saveSolvedLevel() Error:" + e.getMessage());
        }
    }

    @Override
    public Solution loadSolvedLevel() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(this.id));
            Solution solution = (Solution) objectInputStream.readObject();

            return solution;

        } catch (Exception e) {
            System.out.println("FileHandler.loadSolvedLevel() Error: " + e.getMessage());
            return null;
        }
    }
}
