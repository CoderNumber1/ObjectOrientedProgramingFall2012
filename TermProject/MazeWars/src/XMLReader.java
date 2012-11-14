
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLReader {
    private File sourceFile;
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document xmlDocument;
    
    public XMLReader(){}
    public XMLReader(String path){
        this.loadDocument(path);
    }
    
    public void loadDocument(String path){
        try{
            this.sourceFile = new File(path);
            this.factory = DocumentBuilderFactory.newInstance();
            this.builder = this.factory.newDocumentBuilder();
            this.xmlDocument = builder.parse(sourceFile);
            
        }
        catch(Exception ex){}
    }
    
    public String getElementValue(String... path){
        return this.getElementValue(this.xmlDocument.getDocumentElement(), path);
    }
    public String getElementValue(Element rootElement, String... path){
        if(path.length > 1){
            ArrayList<String> newPath = new ArrayList<String>();
            Collections.addAll(newPath, path);
            
            newPath.remove(0);
            
            return this.getElementValue((Element)rootElement.getElementsByTagName(path[0]).item(0), newPath.toArray(new String[0]));
        }
        else{
            Element target = ((Element)rootElement.getElementsByTagName(path[0]).item(0));
            if(target != null){
                return target.getTextContent();
            }
            else{
                return null;
            }
        }
    }
    
    public ArrayList<Element> getElements(String...path){
        return this.getElements(this.xmlDocument.getDocumentElement(), path);
    }
    public ArrayList<Element> getElements(Element rootElement, String[] path){
        if(path.length > 1){
            ArrayList<String> newPath = new ArrayList<String>();
            Collections.addAll(newPath, path);
            
            newPath.remove(0);
            
            return this.getElements((Element)rootElement.getElementsByTagName(path[0]).item(0), newPath.toArray(new String[0]));
        }
        else{
            ArrayList<Element> result = new ArrayList<Element>();
            NodeList nodes = rootElement.getElementsByTagName(path[0]);
            
            for(int i = 0; i < nodes.getLength(); i++){
                result.add((Element)nodes.item(i));
            }
            
            return result;
        }
    }
}
