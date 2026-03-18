#include <stdio.h>
//Simple calculator to demonstrate basic C skills
int main(void){
    //Initialization
    char operation;
    double num1, num2, result;
    //While calculator is still running
    while(1){
        //Print menu for calculator
        puts("+ for addition.");
        puts("- for subtraction.");
        puts("* for multiplication.");
        puts("/ for floating-point division.");
        puts("q to exit.");
        //Ask for calculation
        printf("Please, enter the operation: ");
        scanf(" %c", &operation);
        //If operation is q, then end operation
        if(operation == 'q'){
            break;
        }
        //Validation
        if(operation != '+' && operation != '-' && operation != '*' && operation != '/'){
            puts("Incorrect operation.");
            continue;
        }
        //Operands
        printf("Please, enter the first operand: ");
        scanf("%lf", &num1);
        printf("Please, enter the second operand: ");
        scanf("%lf", &num2);
        //If trying to divide by zero, end operation
        if(operation == '/' && num2 == 0){
            puts("Division by zero is not allowed.");
            continue;
        }
        //Calculations
        if(operation == '+'){
            result = num1 + num2;
        }
        else if(operation == '-'){
            result = num1 - num2;
        }
        else if(operation == '*'){
            result = num1 * num2;
        }
        else if(operation == '/'){
            result = num1 / num2;
        }
        //Print calculation
        printf("%.2f %c %.2f = %.2f\n", num1, operation, num2, result);
    }
    puts("Done");
    return 0;
}