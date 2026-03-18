import java.util.Stack;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
public class JavaProject1 {
    public static void main(String[] args) throws IOException{
        //Initialization
        File numberFile = new File("Numbers.txt");
        File stringFile = new File("Strings.txt");
        int[] numbers = {7, 2, 4, 3, 5, 1, 6, 8, 9};
        Stack<Integer> nStack = new Stack<>();
        Scanner numberFileInput = new Scanner(numberFile);
        Scanner stringFileInput = new Scanner(stringFile);
        Scanner input = new Scanner(System.in);
        //Loop to push numbers from array on to the stack
        for(int i = 0; i < numbers.length; i++){
            nStack.push(numbers[i]);
        }
        //Print before menu
        System.out.println("-----------------------------");
        System.out.println("Stack Before Odds Were Removed");
        System.out.println("-----------------------------");
        printStack(nStack);
        //Remove odds from stack
        removeOddValues(nStack);
        //Print after menu
        System.out.println("-----------------------------");
        System.out.println("Stack After Odds Were Removed");
        System.out.println("-----------------------------");
        printStack(nStack);
        
        //Generic class part

        //Create a generic stack that holds integers
        GenericStack<Integer> numberStack = new GenericStack<>();
        //Fill that stack with values from file
        while(numberFileInput.hasNext()){
            int value = numberFileInput.nextInt();
            numberStack.push(value);
        }
        //print the stack of ints
        System.out.println("-----------------------------");
        System.out.println("Stack of Numbers before sort");
        System.out.println("-----------------------------");
        printStack(numberStack);
        //Sort the stack
        sortStack(numberStack);
        //Print that sorted stack
        System.out.println("-----------------------------");
        System.out.println("Stack of Numbers after sort");
        System.out.println("-----------------------------");
        printStack(numberStack);
        //Ask user for value to move to bottom
        System.out.println("Which value would you like to move to bottom? ");
        int numberValue = input.nextInt();
        //While value is not valid, continue loop
        while(!moveToBottom(numberStack, numberValue)){
            System.out.println("Please enter a valid value: ");
            numberValue = input.nextInt();
        }
        //Print stack after moving value
        System.out.println("-----------------------------");
        System.out.println("Stack after value moved");
        System.out.println("-----------------------------");
        printStack(numberStack);

        //Create a generic stack that holds strings
        GenericStack<String> stringStack = new GenericStack<>();
        //Fill that stack with values from file
        while(stringFileInput.hasNext()){
            String value = stringFileInput.next();
            stringStack.push(value);
        }
        //print the stack of strings
        System.out.println("-----------------------------");
        System.out.println("Stack of Strings before sort");
        System.out.println("-----------------------------");
        printStack(stringStack);
        //Sort the stack
        sortStack(stringStack);
        //Print that sorted stack
        System.out.println("-----------------------------");
        System.out.println("Stack of Strings after sort");
        System.out.println("-----------------------------");
        printStack(stringStack);
        //Ask user for value to move to bottom
        System.out.println("-----------------------------");
        System.out.println("Please enter value to move to bottom ");
        String stringValue = input.next();
        //While value is not valid, continue loop
        while(!moveToBottom(stringStack, stringValue)){
            System.out.println("Please enter a valid value: ");
            stringValue = input.next();
        }
        //Print stack after moving value
        System.out.println("-----------------------------");
        System.out.println("Stack after moving value");
        System.out.println("-----------------------------");
        printStack(stringStack);
        //Close scanners
        input.close();
        numberFileInput.close();
        stringFileInput.close();
    }
    public static void removeOddValues(Stack<Integer> stack){
        //Initialization
        Stack <Integer> tempStack = new Stack<>();
        int size = stack.size();
        //Go through each number on the stack, adding it to temp stack if positive, removing if negative
        for(int i = 0; i < size; i++){
            int temp = stack.pop();
            if((temp % 2) == 0){
                tempStack.push(temp);
            }
        }
        //Move elements from stack back to original
        size = tempStack.size();
        for(int i = 0; i < size; i++){
            int temp = tempStack.pop();
            stack.push(temp);
        }
    } 
    //This method prints the NON GENERIC int stack
    public static void printStack(Stack<Integer> stack){
        //Initialization
        Stack <Integer> tempStack = new Stack<>();
        int size = stack.size();
        //Loop to print and push to temp
        for(int i = 0; i < size; i++){
            int temp = stack.pop();
            System.out.println(temp);
            tempStack.push(temp);
        }
        //loop to reverse
        size = tempStack.size();
        for(int i = 0; i < size; i++){
            int temp = tempStack.pop();
            stack.push(temp);
        }
    } 
    //This method prints the GENERIC stack
    public static <E> void printStack(GenericStack<E> stack){
        //Create a temporary generic stack
        GenericStack<E> tempStack = new GenericStack<>();
        //Pop value from main stack, print, and then push onto temp stack
        while(stack.getSize() != 0){
            E temp = stack.pop();
            System.out.println(temp);
            tempStack.push(temp);
        }
        //loop to reverse
        while(tempStack.getSize() != 0){
            E temp = tempStack.pop();
            stack.push(temp);
        }
    }
    public static <E extends Comparable<E>> E removeSmallest(GenericStack<E> stack){
        //Assume first is the smallest
        E tempSmallest = stack.pop();
        //Create temp stack
        GenericStack<E> tempStack = new GenericStack<>();
        //Loop to compare and transfer
        while(stack.getSize() != 0){
            E temp = stack.pop();
            if(temp.compareTo(tempSmallest) < 0){
                tempStack.push(tempSmallest);
                tempSmallest = temp;
            }
            else{
                tempStack.push(temp);
            }
        }
        //Loop to reverse
        while(tempStack.getSize() != 0){
            E temp = tempStack.pop();
            stack.push(temp);
        }
        //Return smallest
        return tempSmallest;
    }
    public static <E extends Comparable<E>> void sortStack(GenericStack<E> stack){
        //Create temporary stack
        GenericStack<E> tempStack = new GenericStack<>();
        //Loop to repeat smallest
        while(stack.getSize() != 0){
            E temp = removeSmallest(stack);
            tempStack.push(temp);
        }
        //Loop to reverse order
        while(tempStack.getSize() != 0){
            E temp = tempStack.pop();
            stack.push(temp); 
        }
    }
    public static <E> boolean moveToBottom(GenericStack<E> stack, E valueToMove){
        //Create temp stack
        GenericStack<E> tempStack = new GenericStack<>();
        //Initialization
        E tempBottom = null;
        //Search for value to move while moving stack
        while(stack.getSize() !=0){
            E temp = stack.pop();
            //If statement checking for value
            if(temp.equals(valueToMove) && tempBottom == null){
                tempBottom = temp;
            }
            else{
                tempStack.push(temp); 
            }  
        }
        //if value wasnt found
        if(tempBottom == null){
            while(tempStack.getSize() != 0){
                stack.push(tempStack.pop());
            }
            return false;
        }
        //Move value to bottom
        stack.push(tempBottom);
        //Reverse stack
        while(tempStack.getSize() != 0){
            E temp = tempStack.pop();
            stack.push(temp); 
        }

        return true;
    }
}
//Generic stack class using ArrayList
class GenericStack<E>{
    //Initialization
    private ArrayList<E> stackList;
    //Methods
    //Constructor
    public GenericStack(){
        stackList = new ArrayList<>();
    }
    //Check if object is empty
    public boolean isEmpty(){
        return (stackList.size() == 0);
    }
    //Get the size of the object
    public int getSize(){
        int size = stackList.size();
        return size;
    }
    //Look at the top value of object without modification
    public E peek(){
        E top = stackList.get(stackList.size() - 1);
        return top;
    }
    //Look at the top value of object and remove it
    public E pop(){
        E top = stackList.get(stackList.size() - 1);
        stackList.remove(stackList.size() - 1);
        return top;
    }
    //Add to the top of the object
    public void push (E value){
        stackList.add(value);
    }
}
