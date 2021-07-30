import java.io.IOException;

public class Driver {
    public static void main(String[] args){
        Driver d = new Driver();
        d.go(args);
    }

    public void go(String[] args){
        try {
            int size = Integer.parseInt(args[0]);
            BigLoop(size);
        }catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid size provided, defaulting to 10000");
            try {
                Thread.sleep(2500);
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
            BigLoop(10000);
        }
    }

    private double LoadingBar(int pos, int total, double currentPercentage) {
        if(pos > total){
            System.out.println("Percentage Overflow");
            return 0;
        }else{
            int maxStars = 50;
            float progressFraction = (float) pos / total;
            double graphicalProgress = Math.floor(progressFraction * maxStars);
            double percentageProgress = Math.floor((graphicalProgress/maxStars)*100);
            if(percentageProgress > currentPercentage){
                currentPercentage = percentageProgress;
                try {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
                System.out.print("Progress: "+currentPercentage+"%   [");
                for(int j = 0; j < maxStars; j++){
                    if(j < graphicalProgress){
                        System.out.print("*");
                    }else{
                        System.out.print("-");
                    }
                }
                System.out.print("]");
                if(currentPercentage >= 100){
                    System.out.println("\n");
                }
            }
            return percentageProgress;
        }
    }

    private void BigLoop(int size){
        int index = 0;
        double currentPercent = 0;
        for(int x = 0; x < size; x++){
            index++;
            if(LoadingBar(index, size, currentPercent) > currentPercent){
                currentPercent = LoadingBar(index, size, currentPercent);
            }
        }
    }
}