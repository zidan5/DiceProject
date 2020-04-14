public class Dice {
    private int sides;
    private int value;
    private  int[] probabilities;


    Dice(int diceSides , int... probabilities) throws Exception {  //Overloaded constructor
        if(diceSides > 0 ){
            sides = diceSides;   //Assigning sides of a dice

            int sum = 0;

            if( probabilities.length != 0 && probabilities.length != diceSides){
                throw new  Exception("Probabilities must equal the sides of your dice");
            }

            if(probabilities.length != 0 ){ // probabilities not empty
                for (int item : probabilities) {
                    if (item < 0) {  //Checking for negative numbers
                        throw new Exception("Negative probabilities not allowed");
                    }
                    sum += item;
                }
                if(sum < 1){  // should the sum be less than 1 then throw an exception
                    throw new Exception("Probability sum must be greater than zero");
                }
                this.probabilities = probabilities; //Assigning the probabilities
            }

        }else{
            throw new Exception("Sides cannot be less than 1");
        }
    }
    public void roll(){
        int normalRoll = 1 + (int) (Math.random() * sides);
        if(probabilities == null){
            value = normalRoll;
        }else{
            int min = 1;
            int max = 10;
            int randomValue = min + (int)(Math.random()*((max-min) + 1));
            for(int i=0; i<probabilities.length; i++){
                if(probabilities[i] > 1){
                    int sideWithWeight = (i + 1);
                    System.out.println("Side "+sideWithWeight+" has more weight");
                    value = (randomValue < 3) ? sideWithWeight : normalRoll; //Weighing the probability
                    break;
                }else{
                    value = normalRoll;
                }
            }
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

    public static void main(String[] Args) throws Exception{
        Dice dice2 = DiceFactory.makeDie(6,1,1,1,1,1,2); // Utilizing the Dice factory class
        dice2.roll();
        System.out.println(dice2.value);

        System.out.println("=================================================================================");

        Dice dice1 = DiceFactory.makeDie(6); // Utilizing the Dice factory class
        dice1.setProbabilities(new int[]{1,1,2,1,1,1}); //using set method to set the probabilities
        dice1.roll();

        System.out.println(dice1.value);

    }
}
class DiceFactory{ // Creational/factory pattern  //Objects factory pattern class

    public static Dice makeDie(int sides, int... probabilities)throws Exception{
        return new Dice(sides,probabilities);
    }
}