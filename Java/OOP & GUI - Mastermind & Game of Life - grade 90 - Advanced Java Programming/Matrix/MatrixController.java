import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
public class MatrixController {
    Matrix mainMatrix= new Matrix();
    @FXML
    private Canvas canvas;
    private GraphicsContext gc;

    @FXML
    void DrawMatrix(ActionEvent event) {
    	initialize();
    	drawMatrix();
    	mainMatrix.refreshMatrix();
    }
    public void initialize()/*initializing the GraphicsContext for the canvas*/ 
    {
    	gc = canvas.getGraphicsContext2D();
    }

    private void drawMatrix() {/*drawing the canvas by the matrix values and print to the matrix to the console for effective testing */
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        double skipWidth=canvas.getWidth()/mainMatrix.getSize();
        double skipHeight=canvas.getHeight()/mainMatrix.getSize();
        gc.clearRect(0, 0,canvas.getWidth() , canvas.getHeight());
        gc.setFill(Color.GREEN);
        for(int i=0;i<mainMatrix.getSize();i++) {
            for (int j=0;j<mainMatrix.getSize();j++)
            {
            	System.out.print(mainMatrix.getValue(i, j));
                if(mainMatrix.getValue(i,j)==1)
                {
                	
                    gc.fillRect(j*skipWidth,i*skipHeight,skipWidth,skipHeight);
                    
                }
            }
            System.out.println();
        }
        System.out.println();

        for(int i=0;i<=mainMatrix.getSize();i++)
            gc.strokeLine(i*skipWidth,0,i*skipWidth,canvas.getHeight());
        for(int i=0;i<=mainMatrix.getSize();i++)
            gc.strokeLine(0,i*skipHeight,canvas.getWidth(),i*skipHeight);

    }
}
