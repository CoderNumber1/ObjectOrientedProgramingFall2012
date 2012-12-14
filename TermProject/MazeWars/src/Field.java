
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.w3c.dom.Element;

public class Field extends JPanel implements GameFigure{
    FieldSection field[][];
    private FieldReader reader;
    BufferedImage fieldImage;
    boolean isFieldLoaded;
    
    public int width;
    public int height;
    public int sectionWidth;
    public int sectionHeight;
    public int tankSize;
    public String fieldName;
    
    public int tankSpeed;
    
    public Field(){
    }
    
    public void loadField(String fieldName){
            this.fieldName = fieldName;
            this.reader = new FieldReader(fieldName);
            this.width = this.reader.getWidth();
            this.height = this.reader.getHeight();
            this.field = new FieldSection[this.width][this.height];
            
            this.tankSpeed = this.reader.getTankSpeed();

            this.tankSize = this.reader.getTankSize();
            this.sectionWidth = this.reader.getSectionWidth();
            this.sectionHeight = this.reader.getSectionHeight();
            Point start = reader.getStart();
            int xStart = this.sectionWidth * start.x;
            int yStart = this.sectionHeight * start.y;
            this.field[start.x][start.y] = new FieldSection(xStart, yStart, this.sectionWidth, this.sectionHeight, true);

            ArrayList<HorizontalRun> hRuns = reader.getHorizontalRuns();
            for(HorizontalRun run : hRuns){
                for(int i = run.xBegin; i <= run.xEnd; i++){
                    int xOffset = this.sectionWidth * i;
                    int yOffset = this.sectionHeight * run.y;

                    this.field[i][run.y] = new FieldSection(xOffset, yOffset, this.sectionWidth, this.sectionHeight, true);
                }
            }

            ArrayList<VerticalRun> vRuns = reader.getVerticalRuns();
            for(VerticalRun run : vRuns){
                for(int i = run.yBegin; i <= run.yEnd; i++){
                    int xOffset = this.sectionWidth * run.x;
                    int yOffset = this.sectionHeight * i;

                    this.field[run.x][i] = new FieldSection(xOffset, yOffset, this.sectionWidth, this.sectionHeight, true);
                }
            }

            for(int x = 0; x < this.field.length; x++){
                for(int y = 0; y < this.field[x].length; y++){
                    if(this.field[x][y] == null){
                        int xOffset = this.sectionWidth * x;
                        int yOffset = this.sectionHeight * y;

                        this.field[x][y] = new FieldSection(xOffset, yOffset, this.sectionWidth, this.sectionHeight, false);
                    }
                }
            }
        
        this.fieldImage = new BufferedImage(this.getPixelWidth(), this.getPixelHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        Graphics fieldGraphics = this.fieldImage.createGraphics();
        fieldGraphics.clearRect(0, 0, this.getPixelWidth(), this.getPixelHeight());
        
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
        
        GameData.getInstance().signalFieldChange(this);
    }
    
    public void render(Graphics g){
        if(this.fieldImage != null){
            g.drawImage(fieldImage, 0, 0, null);
        }
    }
    
    public boolean getIsFieldLoaded(){
        return this.isFieldLoaded;
    }
    
    public boolean isMovePossible(int x, int y, int direction, int width, int height){
        boolean movesToWall = false;
        
        int primaryX = -1, primaryY = -1, secondaryX = -1, secondaryY = -1;
        Point primaryPoint = null, secondaryPoint = null;
        int xRight, xLeft, yDown, yUp;
        
        xRight = x + width - 1;
        xLeft = x - width + 1;
        yDown = y + height - 1;
        yUp = y - height + 1;
        
        switch(direction){
            case GameFigure.DIRECTION_LEFT:
                if(x < 0){
                    movesToWall = true;
                }
                else{
                    primaryX = (int)Math.floor((double)x/this.sectionWidth);
                    primaryY = (int)Math.floor((double)y/this.sectionHeight);

                    secondaryX = (int)Math.floor((double)x/this.sectionWidth);
                    secondaryY = (int)Math.floor((double)yDown/this.sectionHeight);
                    
                    primaryPoint = new Point(x,y);
                    secondaryPoint = new Point(x, yDown);
                }
                break;
            case GameFigure.DIRECTION_RIGHT:
                if(xRight > this.getPixelWidth()){
                    movesToWall = true;
                }
                else{
                    primaryX = (int)Math.floor((double)xRight/this.sectionWidth);
                    primaryY = (int)Math.floor((double)y/this.sectionHeight);

                    secondaryX = (int)Math.floor((double)xRight/this.sectionWidth);
                    secondaryY = (int)Math.floor((double)yDown/this.sectionHeight);
                    
                    primaryPoint = new Point(xRight,y);
                    secondaryPoint = new Point(xRight, yDown);
                }
                break;
            case GameFigure.DIRECTION_FORWARD:
                if(y < 0){
                    movesToWall = true;
                }
                else{
                    primaryX = (int)Math.floor((double)x/this.sectionWidth);
                    primaryY = (int)Math.floor((double)y/this.sectionHeight);

                    secondaryX = (int)Math.floor((double)xRight/this.sectionWidth);
                    secondaryY = (int)Math.floor((double)y/this.sectionHeight);
                    
                    primaryPoint = new Point(x,y);
                    secondaryPoint = new Point(xRight, y);
                }
                break;
            case GameFigure.DIRECTION_BACKWARD:
                if(yDown > this.getPixelHeight()){
                    movesToWall = true;
                }
                else{
                    primaryX = (int)Math.floor((double)x/this.sectionWidth);
                    primaryY = (int)Math.floor((double)yDown/this.sectionHeight);

                    secondaryX = (int)Math.floor((double)xRight/this.sectionWidth);
                    secondaryY = (int)Math.floor((double)yDown/this.sectionHeight);
                    
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
    
    public boolean isPointInWall(Point point, boolean cellLocation){
        return this.isPointInWall(point.x, point.y, cellLocation);
    }
    public boolean isPointInWall(double x, double y, boolean cellLocation){
        int cellX, cellY;
        
        if(!cellLocation){
            cellX = (int)Math.floor((double)x/this.sectionWidth);
            cellY = (int)Math.floor((double)y/this.sectionHeight);
        }
        else{
            cellX = (int)x;
            cellY = (int)y;
        }
        
        FieldSection cell = null;
        try{
            cell = this.field[cellX][cellY];
        }
        catch(Exception e){
            if(true){
                
            }
        }
        
        if(!cellLocation){
            return cell.contains(x, y) && !cell.isPath;
        }
        else{
            return !cell.isPath;
        }
    }
    
    public Point getStartPoint(){
        return this.reader.getStart();
    }
    
    public Point getEndPoint(){
        return this.reader.getEnd();
    }
    
    public int getPixelHeight(){
        return this.height * this.sectionHeight;
    }
    
    public int getPixelWidth(){
        return this.width * this.sectionWidth;
    }
    
    public FieldReader getFieldReader(){
        return this.reader;
    }
    
    public Point getCellLocation(Point point){
        return this.getCellLocation(point.x, point.y);
    }
    public Point getCellLocation(double x, double y){
        int cellX = (int)Math.floor((double)x/this.sectionWidth);
        int cellY = (int)Math.floor((double)y/this.sectionHeight);
        
        return new Point(cellX, cellY);
    }
    
    public Point getCellCorner(int x, int y){
        Point result = new Point();
        
        result.x = x * this.sectionWidth;
        result.y = y * this.sectionHeight;
        
        return result;
    }
    
    public Point getCellCenter(int x, int y){
        Point corner = this.getCellCorner(x, y);
        
        corner.x += this.sectionWidth / 2;
        corner.y += this.sectionHeight / 2;
        
        return corner;
    }
    
    public ArrayList<AITank> getAITanks(){
        ArrayList<AITank> result = new ArrayList<AITank>();
        
        for(Element tank : this.reader.getElements("aitanks", "tank")){
            String strategyName = reader.getElementValue(tank, "strategy");
            AITank aiTank = new AITank();
            AIStrategy strategy = new AIStrategy(aiTank, strategyName, GameData.getInstance().pathManager, GameData.getInstance().strategyManager);
            aiTank.setStrategy(strategy);
            Point startPoint = new Point();
            
            startPoint.x = Integer.parseInt(this.reader.getElementValue(tank, "x"));
            startPoint.y = Integer.parseInt(this.reader.getElementValue(tank, "y"));
            
            aiTank.setStart(startPoint);
            
            result.add(aiTank);
        }
        
        return result;
    }

    @Override
    public void update() {
        
    }

    @Override
    public int getState() {
        return GameFigure.STATE_STATIONARY;
    }

    @Override
    public int getDirection() {
        return GameFigure.DIRECTION_FORWARD;
    }
}
