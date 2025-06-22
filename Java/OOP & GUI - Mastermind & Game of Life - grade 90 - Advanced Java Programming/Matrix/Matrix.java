
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Random;


public class Matrix extends Application {

    public void start(Stage stage) throws Exception{
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Matrix.fxml"));
            Scene scene=new Scene(root);
            stage.setTitle("Matrix");
            stage.setScene(scene);
            stage.show();
    }
    private int lifeMatrix[][];
    private int SIZE=10;
    private Random rand;
    public Matrix() {
        this.lifeMatrix=new int[SIZE][SIZE];
        rand=new Random();
        
        for(int i=0;i<SIZE;i++)
            for (int j=0;j<SIZE;j++) {
                lifeMatrix[i][j] = rand.nextInt(2);/*1-does live ,0-doesnt live*/
            }
        
    }
    private int sumNeighbors(int m[][],int i1, int j1)/*sum the neighbors around m[i][j] by adding the surrounding values */
    {
        
        int sum=0;
        for(int i=0;i<SIZE;i++)
            for(int j=0;j<SIZE;j++)
                if((Math.abs(i-i1)==1&&Math.abs(j-j1)==1)||(Math.abs(i-i1)==0&&Math.abs(j-j1)==1)||(Math.abs(i-i1)==1&&Math.abs(j-j1)==0))
                    sum+=m[i][j];
        return sum;
    }

    public void refreshMatrix()/*planning the next generation using sumNeighbors*/
    {
        int m2[][]=new int[SIZE][SIZE];
        for(int i=0;i<SIZE;i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                if (this.lifeMatrix[i][j] == 0&&sumNeighbors(this.lifeMatrix,i,j)==3)
                    m2[i][j]=1;
                else if (this.lifeMatrix[i][j] == 1&&(sumNeighbors(this.lifeMatrix,i,j)==2||sumNeighbors(this.lifeMatrix,i,j)==3)) {
                    m2[i][j]=1;
                }
            }
        }
        for(int i=0;i<SIZE;i++) 
            for(int j=0;j<SIZE;j++) 
                this.lifeMatrix[i][j]=m2[i][j];
                
    }
    public int getValue(int i,int j)/*return the value by its position*/
    {
        return this.lifeMatrix[i][j];
    }
    public int getSize()/*returning the size of the matrix*/ 
    {
    	return this.SIZE;
    }
    public static void main(String[] args) {
        launch(args);
        

    }
}