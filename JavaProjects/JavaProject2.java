import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
public class JavaProject2 {
    public static void main(String[] args) throws IOException {
        //Create file object
        File targetFile = new File("PinballMachineTargets.txt");
        //Create a new scanner object for the file
        Scanner fileInput = new Scanner(targetFile);
        //Read number of rows and columns
        int numRows = fileInput.nextInt();
        int numColumns = fileInput.nextInt();
        //Create pinball machine
        PinballMachine machine = new PinballMachine(numRows, numColumns);
        //Loop for reading file
        //Read data
        //place data in target
        //place target in machine
        while(fileInput.hasNextInt()){
            int row = fileInput.nextInt();
            int column = fileInput.nextInt();
            String type = fileInput.next();
            int id = fileInput.nextInt();
            int points = fileInput.nextInt();
            Target target = new Target(type, id, points);
            machine.addTarget(row, column, target);
        }
        //Main process code
        fileInput.close();
        machine.displayPlayingField();
        machine.play(new File("Play.txt"));
        printTargetsByHits(machine);
    }
    public static void printTargetsByHits(PinballMachine pinballmachine){
        ArrayList<Target> display = new ArrayList<>();
        //Collect all targets from playing field
        for(int i = 0; i < pinballmachine.getNumRows(); i++){
            for(int j = 0; j < pinballmachine.getNumColumns(); j++){
                if(pinballmachine.getTarget(i, j) != null){
                    display.add(pinballmachine.getTarget(i, j));
                }
            }
        }
        Collections.sort(display);
        //Display header
        System.out.print("*********************************\n");
        System.out.print("\tPinball Machine Target Hit Report\n");
        System.out.print("\t(From most hits to least)\n");
        System.out.print("*********************************\n");
        System.out.print("Type\tID\tPoints\tNumber Hits\n");
        System.out.print("---------------------------------\n");
        //Display each object in arraylist
        for(int i = 0; i < display.size(); i++){
            System.out.println(display.get(i));
        }
    }
}
//Pinball machine class that is used to represent the class
//Handels targets, playing the game, and displaying results
class PinballMachine{
    //Initialize
    private int numRows;
    private int numColumns;
    private Target[][] playingField;
    //Constructor
    public PinballMachine(int numRows, int numColums){
        this.numRows = numRows;
        this.numColumns = numColums;
        playingField = new Target[numRows][numColumns];
    }
    public int getNumRows(){
        return numRows;
    }
    public int getNumColumns(){
        return numColumns;
    }
    public void addTarget(int row, int column, Target target){
        playingField[row][column] = target;
    }
    public Target getTarget(int row, int column){
        Target temp = playingField[row][column];
        return temp;
    }
    //Display the area in which the game takes place
    public void displayPlayingField(){
        System.out.println("\t");
        for(int i = 0; i < numColumns; i++){
            System.out.print("Column " + i + "\t");
        }
        System.out.println();
        for(int i = 0; i < numRows; i++){
            System.out.print("Row " + i + "");
            for(int j = 0; j < numColumns; j++){
                if(playingField[i][j] == null){
                    System.out.print("- \t");
                }
                else{
                    Target temp = playingField[i][j];
                    System.out.print(temp.getType() + "\t");
                }
            }
            System.out.println();
        }
    }
    //This method is responsible for the main actions of the game
    public void play(File playFile) throws IOException{
        //Create new scanner
        Scanner playInput = new Scanner(playFile);
        //Initialize
        int score = 0;
        //Display blank table
        System.out.print("---------------------");
        System.out.print("Target Hit\t");
        System.out.print("ID\t");
        System.out.print("Points\t");
        System.out.print("Score\n");
        System.out.print("---------------------");
        //Read each target and store data
        while(playInput.hasNext()){
            int row = playInput.nextInt();
            int column = playInput.nextInt();
            Target target = getTarget(row, column);
            //Only record if viable
            if(target != null){
                target.incrementHits();
                score += target.getPoints();
                System.out.print(target.getType() + "\t");
                System.out.print(target.getId() + "\t");
                System.out.print(target.getPoints()+ "\t");
                System.out.print(score + "\n");
            }
        }
        playInput.close();
    }
}
//Target class used to represent the target object
//Handels the data for targets, creation of targets, and record of targets
class Target implements Comparable<Target>{
    //Initialize
    private String type;
    private int id;
    private int points;
    //counter initialize
    private int numTimesHit;
    //Constructor
    public Target(String type, int id, int points){
        this.type = type;
        this.id = id;
        this.points = points;
    }
    //Getters for target
    public String getType(){
        return type;
    }
    public int getId(){
        return id;
    }
    public int getPoints(){
        return points;
    }
    public int getNumTimesHit(){
        return numTimesHit;
    }
    //Increment(dont return anything)
    public void incrementHits(){
        numTimesHit++;
    }
    //Compare two target objects 
    //Compare the number of times hit
    public int compareTo(Target otherTarget){
        if(this.numTimesHit > otherTarget.getNumTimesHit()){
            return -1;
        }
        else if(this.numTimesHit < otherTarget.getNumTimesHit()){
            return 1;
        }
        else{
            return 0;
        }
    }
    @Override
    public String toString() {
        return String.format("%-10s\t%d\t%d\t%d",
                type,
                id,
                points,
                numTimesHit);
    }
}
