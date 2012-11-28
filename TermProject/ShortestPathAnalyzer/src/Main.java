
import java.awt.Point;
import java.io.File;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Main {
    public static void main(String[] args) throws TransformerConfigurationException{
        Stack directions = new Stack();
        Field field = new Field();
        
        field.loadField("Level_One");
        field.findAllPossiblePaths();
        
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("fieldanalysis");
        doc.appendChild(rootElement);
        
        for(FieldSection key : field.pathTables.keySet()){
            Element pathCollection = doc.createElement("pathcollection");
            rootElement.appendChild(pathCollection);
            
            Element origin = doc.createElement("key");
            pathCollection.appendChild(origin);
            
            Element xOrigin = doc.createElement("x");
            xOrigin.appendChild(doc.createTextNode(String.valueOf(key.x)));
            origin.appendChild(xOrigin);
            
            Element yOrigin = doc.createElement("y");
            yOrigin.appendChild(doc.createTextNode(String.valueOf(key.y)));
            origin.appendChild(yOrigin);
            
            Element pathTable = doc.createElement("pathtable");
            pathCollection.appendChild(pathTable);
            
            for(FieldSection destKey : field.pathTables.get(key).paths.keySet()){
                Element pathTableEntry = doc.createElement("pathtableentry");
                pathTable.appendChild(pathTableEntry);
                
                Element destination = doc.createElement("destination");
                pathTableEntry.appendChild(destination);
                
                Element xDestination = doc.createElement("x");
                xDestination.appendChild(doc.createTextNode(String.valueOf(destKey.x)));
                destination.appendChild(xDestination);
                
                Element yDestination = doc.createElement("y");
                yDestination.appendChild(doc.createTextNode(String.valueOf(destKey.y)));
                destination.appendChild(yDestination);
                
                if(field.pathTables.get(key).paths.get(destKey).path != null){
                    Element path = doc.createElement("path");
                    pathTableEntry.appendChild(path);

                    Element xPath = doc.createElement("x");
                    xPath.appendChild(doc.createTextNode(String.valueOf(field.pathTables.get(key).paths.get(destKey).path.x)));
                    path.appendChild(xPath);

                    Element yPath = doc.createElement("y");
                    yPath.appendChild(doc.createTextNode(String.valueOf(field.pathTables.get(key).paths.get(destKey).path.y)));
                    path.appendChild(yPath);
                }
            }
        }
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("C:\\levelOneAnalysis.xml"));
        try {
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        Point startPoint = field.getStartPoint();
//        FieldSection start = new FieldSection(startPoint.x, startPoint.y);
//        
//        PathTable paths = field.pathTables.get(start);
//        
//        Point endPoint = field.getEndPoint();
//        FieldSection end = new FieldSection(endPoint.x, endPoint.y);
//        
//        while(!end.equals(start)){
//            directions.push(end);
//            
//            PathTable.PathTableEntry entry = paths.paths.get(end);
//            end = entry.path;
//        }
//        directions.push(end);
//        
//        while(!directions.isEmpty()){
//            FieldSection direction = (FieldSection)directions.pop();
//            System.out.printf("x:%d, y:%d\n", direction.x, direction.y);
//        }
    }
}
