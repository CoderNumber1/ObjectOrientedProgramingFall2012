
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
    
    public int getSectionHeight(){
        return Integer.parseInt(super.getElementValue("dimensions", "sectionheight"));
    }
    
    public int getSectionWidth(){
        return Integer.parseInt(super.getElementValue("dimensions", "sectionwidth"));
    }
    
    public Start getStart(){
        Start result = new Start();
        
        result.x = Integer.parseInt(super.getElementValue("start", "x"));
        result.y = Integer.parseInt(super.getElementValue("start", "y"));
        
        return result;
    }
    
    public ArrayList<HorizontalRun> getHorizontalRuns(){
        ArrayList<HorizontalRun> result = new ArrayList<HorizontalRun>();
        
        for(Element set : super.getElements(super.getElements("paths").get(0), new String[]{"pathset"})){
            ArrayList<Element> paths = super.getElements(set, new String[]{"path"});
            
            if(paths.get(0).getAttribute("yStart").equals(paths.get(0).getAttribute("yEnd"))){
                for(Element run : paths){
                    HorizontalRun hRun = new HorizontalRun();
                    
                    hRun.y = Integer.parseInt(run.getAttribute("yStart"));
                    hRun.xBegin = Integer.parseInt(run.getAttribute("xStart"));
                    hRun.xEnd = Integer.parseInt(run.getAttribute("xEnd"));

                    result.add(hRun);
                }
            }
        }
        
        return result;
    }
    
    public ArrayList<VerticalRun> getVerticalRuns(){
        ArrayList<VerticalRun> result = new ArrayList<VerticalRun>();
        
        for(Element set : super.getElements(super.getElements("paths").get(0), new String[]{"pathset"})){
            ArrayList<Element> paths = super.getElements(set, new String[]{"path"});
            
            if(paths.get(0).getAttribute("xStart").equals(paths.get(0).getAttribute("xEnd"))){
                for(Element run : paths){
                    VerticalRun vRun = new VerticalRun();
            
                    vRun.x = Integer.parseInt(run.getAttribute("xStart"));
                    vRun.yBegin = Integer.parseInt(run.getAttribute("yStart"));
                    vRun.yEnd = Integer.parseInt(run.getAttribute("yEnd"));

                    result.add(vRun);
                }
            }
        }
        
        return result;
    }
}
