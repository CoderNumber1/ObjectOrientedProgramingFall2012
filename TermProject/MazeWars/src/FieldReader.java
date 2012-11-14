
import java.util.ArrayList;
import org.w3c.dom.Element;

public class FieldReader extends XMLReader {
    public FieldReader(String fieldName){
        super();
        
        String basePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        
        super.loadDocument(basePath + separator + "Fields" + separator + fieldName + ".xml");
    }
    
    public int getHeight(){
        return Integer.parseInt(super.getElementValue("size", "height"));
    }
    
    public int getWidth(){
        return Integer.parseInt(super.getElementValue("size", "width"));
    }
    
    public Start getStart(){
        Start result = new Start();
        
        result.x = Integer.parseInt(super.getElementValue("start", "x"));
        result.y = Integer.parseInt(super.getElementValue("start", "y"));
        
        return result;
    }
    
    public ArrayList<HorizontalRun> getHorizontalRuns(){
        ArrayList<HorizontalRun> result = new ArrayList<HorizontalRun>();
        
        for(Element run : super.getElements("horizontalPath")){
            HorizontalRun hRun = new HorizontalRun();
            
            hRun.y = Integer.parseInt(super.getElementValue(run, "y"));
            hRun.xBegin = Integer.parseInt(super.getElementValue(run, "xBegin"));
            hRun.xEnd = Integer.parseInt(super.getElementValue(run, "xEnd"));
            
            result.add(hRun);
        }
        
        return result;
    }
    
    public ArrayList<VerticalRun> getVerticalRuns(){
        ArrayList<VerticalRun> result = new ArrayList<VerticalRun>();
        
        for(Element run : super.getElements("verticalPath")){
            VerticalRun vRun = new VerticalRun();
            
            vRun.x = Integer.parseInt(super.getElementValue(run, "x"));
            vRun.yBegin = Integer.parseInt(super.getElementValue(run, "yBegin"));
            vRun.yEnd = Integer.parseInt(super.getElementValue(run, "yEnd"));
            
            result.add(vRun);
        }
        
        return result;
    }
}
