
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Field extends JPanel{
    FieldSection field[][];
    private FieldReader reader;
    BufferedImage fieldImage;
    boolean isFieldLoaded;
    
    public Field(){
        this.field = new FieldSection[24][24];
        super.setPreferredSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
    }
    
    public void loadField(String fieldName){
        synchronized(this.field){
            this.reader = new FieldReader(fieldName);

            Start start = reader.getStart();
            int xStart = GamePanel.SECTION_WIDTH * start.x;
            int yStart = GamePanel.SECTION_HEIGHT * start.y;
            this.field[start.x][start.y] = new FieldSection(xStart, yStart, true);

            ArrayList<HorizontalRun> hRuns = reader.getHorizontalRuns();
            for(HorizontalRun run : hRuns){
                for(int i = run.xBegin; i <= run.xEnd; i++){
                    int xOffset = GamePanel.SECTION_WIDTH * i;
                    int yOffset = GamePanel.SECTION_HEIGHT * run.y;

                    this.field[i][run.y] = new FieldSection(xOffset, yOffset, true);
                }
            }

            ArrayList<VerticalRun> vRuns = reader.getVerticalRuns();
            for(VerticalRun run : vRuns){
                for(int i = run.yBegin; i <= run.yEnd; i++){
                    int xOffset = GamePanel.SECTION_WIDTH * run.x;
                    int yOffset = GamePanel.SECTION_HEIGHT * i;

                    this.field[run.x][i] = new FieldSection(xOffset, yOffset, true);
                }
            }

            for(int x = 0; x < this.field.length; x++){
                for(int y = 0; y < this.field[x].length; y++){
                    if(this.field[x][y] == null){
                        int xOffset = GamePanel.SECTION_WIDTH * x;
                        int yOffset = GamePanel.SECTION_HEIGHT * y;

                        this.field[x][y] = new FieldSection(xOffset, yOffset, false);
                    }
                }
            }
        }
        
        this.fieldImage = new BufferedImage(GamePanel.WIDTH, GamePanel.HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics fieldGraphics = this.fieldImage.createGraphics();
//        this.fieldImage = super.createImage(GamePanel.WIDTH, GamePanel.HEIGHT);
//        Graphics fieldGraphics = this.fieldImage.getGraphics();
        fieldGraphics.clearRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        
        synchronized(this.field){
            for(int x = 0; x < this.field.length; x++){
                for(int y = 0; y < this.field[x].length; y++){
                    FieldSection section = this.field[x][y];
                    
                    if(section != null){
                        section.render(fieldGraphics);
                    }
                }
            }
        }

        this.isFieldLoaded = true;
    }
    
    public void render(Graphics g){
        if(this.fieldImage != null){
            g.drawImage(fieldImage, 0, 0, null);
        }
    }
    
    public boolean getIsFieldLoaded(){
        return this.isFieldLoaded;
    }
    
    public boolean isMovePossible(int x, int y, int direction){
        boolean movesToWall = false;
        
        int primaryX = -1, primaryY = -1, secondaryX = -1, secondaryY = -1;
        Point primaryPoint = null, secondaryPoint = null;
        int xRight, xLeft, yDown, yUp;
        
        xRight = x + (int)Tank.SIZE - 1;
        xLeft = x - (int)Tank.SIZE + 1;
        yDown = y + (int)Tank.SIZE - 1;
        yUp = y - (int)Tank.SIZE + 1;
        
        switch(direction){
            case GameFigure.DIRECTION_LEFT:
                if(x < 0){
                    movesToWall = true;
                }
                else{
                    primaryX = (int)Math.floor((double)x/GamePanel.SECTION_WIDTH);
                    primaryY = (int)Math.floor((double)y/GamePanel.SECTION_HEIGHT);

                    secondaryX = (int)Math.floor((double)x/GamePanel.SECTION_WIDTH);
                    secondaryY = (int)Math.floor((double)yDown/GamePanel.SECTION_HEIGHT);
                    
                    primaryPoint = new Point(x,y);
                    secondaryPoint = new Point(x, yDown);
                }
                break;
            case GameFigure.DIRECTION_RIGHT:
                if(xRight > GamePanel.WIDTH){
                    movesToWall = true;
                }
                else{
                    primaryX = (int)Math.floor((double)xRight/GamePanel.SECTION_WIDTH);
                    primaryY = (int)Math.floor((double)y/GamePanel.SECTION_HEIGHT);

                    secondaryX = (int)Math.floor((double)xRight/GamePanel.SECTION_WIDTH);
                    secondaryY = (int)Math.floor((double)yDown/GamePanel.SECTION_HEIGHT);
                    
                    primaryPoint = new Point(xRight,y);
                    secondaryPoint = new Point(xRight, yDown);
                }
                break;
            case GameFigure.DIRECTION_FORWARD:
                if(y < 0){
                    movesToWall = true;
                }
                else{
                    primaryX = (int)Math.floor((double)x/GamePanel.SECTION_WIDTH);
                    primaryY = (int)Math.floor((double)y/GamePanel.SECTION_HEIGHT);

                    secondaryX = (int)Math.floor((double)xRight/GamePanel.SECTION_WIDTH);
                    secondaryY = (int)Math.floor((double)y/GamePanel.SECTION_HEIGHT);
                    
                    primaryPoint = new Point(x,y);
                    secondaryPoint = new Point(xRight, y);
                }
                break;
            case GameFigure.DIRECTION_BACKWARD:
                if(yDown > GamePanel.HEIGHT){
                    movesToWall = true;
                }
                else{
                    primaryX = (int)Math.floor((double)x/GamePanel.SECTION_WIDTH);
                    primaryY = (int)Math.floor((double)yDown/GamePanel.SECTION_HEIGHT);

                    secondaryX = (int)Math.floor((double)xRight/GamePanel.SECTION_WIDTH);
                    secondaryY = (int)Math.floor((double)yDown/GamePanel.SECTION_HEIGHT);
                    
                    primaryPoint = new Point(x,yDown);
                    secondaryPoint = new Point(xRight, yDown);
                }
                break;
        }
        
        if(!movesToWall){
            if(primaryX >= 0 && primaryX < this.field.length && primaryY >= 0 && primaryY < this.field[0].length
                && secondaryX >= 0 && secondaryX < this.field.length && secondaryY >= 0 && secondaryY < this.field[0].length){
                movesToWall = (this.field[primaryX][primaryY].contains(primaryPoint) && !this.field[primaryX][primaryY].isPath)
                        || (this.field[secondaryX][secondaryY].contains(secondaryPoint) && !this.field[secondaryX][secondaryY].isPath);
            }
            else{
                movesToWall = true;
            }
        }
        
        return !movesToWall;
    }
    
    public Start getStartPoint(){
        return this.reader.getStart();
    }
}
