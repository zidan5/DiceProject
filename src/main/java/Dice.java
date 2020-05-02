public class Dice {
    private int sides;
    private int value;
    private  int[] probabilities;


    Dice(int diceSides , int... probabilities) throws Exception {  //Overloaded constructor
        if(diceSides > 0 ){ //Sides should have a value grater than zero

               sides = diceSides;   //Assigning sides of a dice
                //checking if probabilities have a value and it is equal to the dice sides
               if( probabilities.length != 0 && probabilities.length != diceSides){
                  throw new  Exception("Probabilities must equal the sides of your dice");
               }
              //if probabilities have a value and the above condition is not met then probabilities equal to the dice sides
               if(probabilities.length != 0 ){ // NB only
                  setProbabilities(probabilities);
               }

        }else{
            throw new Exception("Sides cannot be less than 1");
        }
    }
    public  void setProbabilities(int[] probabilities)throws Exception{
        int sum = 0;

        if( probabilities.length != sides){
            throw new  Exception("Probabilities must equal the sides of your dice");
        }else{
            for (int item : probabilities) {
                if (item < 0) {  //Checking for negative numbers
                    throw new Exception("Negative numbers not allowed");
                }
                sum += item;
            }
            if(sum < 1){  // should the sum be less than 1 then throw exception
                throw new Exception("Probability sum must be greater than zero");
            }
            this.probabilities = probabilities; //Assigning the probabilities
        }

    }
    public void roll(){
         int normalRoll = 1 + (int) (Math.random() * sides);
         if(probabilities == null){// If no probabilities are set then use the normal roll criteria
             value = normalRoll;
         }else {
             int min = 1;
             int max = 10;
             int randomValue = min + (int) (Math.random() * ((max - min) + 1));//
             for (int i = 0; i < probabilities.length; i++) {
                 if (probabilities[i] > 1) { //checking any value that is probabilities that is weighted
                     int sideWithWeight = (i + 1);
                     System.out.println("Side " + sideWithWeight + " has more weight");
                     value = (randomValue < 3) ? sideWithWeight : normalRoll; //Weighing the probability
                     break;
                 }
             }
             value = normalRoll;
         }

    }


    public static void main(String[] Args) throws Exception{
        System.out.println("================KEEP ROLLING THE DICE======================");

        Dice dice1 = DiceFactory.makeDie(6); //Instantiating a perfect normal Dice using factory class
        dice1.roll();
        System.out.println(dice1.value);
        System.out.println("=================================================================================");

        Dice dice2 = DiceFactory.makeDie(6,1,1,1,1,1,2);//Instantiating a weighted Dice using factory class
        dice2.roll();                               //and using the overloaded constructor to set the probabilities
        System.out.println(dice2.value);
        System.out.println("=================================================================================");

        Dice dice3 = DiceFactory.makeDie(6,1,1,1,1,1,1); //This is the same as dice 1 or normal dice
        dice3.roll();

        System.out.println(dice3.value);
        System.out.println("=================================================================================");

        Dice dice4 = DiceFactory.makeDie(6);
        dice4.setProbabilities(new int[]{2,1,1,1,1,1}); // Using set probabilities methods to set probabilities
        dice4.roll();

        System.out.println(dice4.value);
    }
}
class DiceFactory{ // Defining my object factory pattern class

    public static Dice makeDie(int sides, int... probabilities)throws Exception{
          return new Dice(sides,probabilities);
    }
}