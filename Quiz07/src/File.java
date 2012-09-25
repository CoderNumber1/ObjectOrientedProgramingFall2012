
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class File {
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public File(){
        
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Queries">
    public String loadFile(String path)
        throws FileNotFoundException, IOException{
        StringBuffer result = new StringBuffer();
        
        BufferedReader in = null;
        try{
            FileReader reader = new FileReader(path);
            in = new BufferedReader(reader);
            
            String line = in.readLine();
            while(line != null){
                result.append(line + "\n");
                
                line = in.readLine();
            }
        }
        finally{
            if(in != null){
                try{
                    in.close();
                }
                catch(IOException ex){
                    //Reader close failed.
                }
            }
        }
        
        return result.toString();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Commands">
    public void mergeFiles(String[] sourcePaths, String targetPath)
        throws FileNotFoundException, IOException{
        ArrayList<String> lines = new ArrayList<String>();
        
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            for(String sourcePath : sourcePaths){
                FileReader reader = new FileReader(sourcePath);
                in = new BufferedReader(reader);
                
                String line = in.readLine();
                while(line != null){
                    lines.add(line);
                    
                    line = in.readLine();
                }
                
                try{
                    in.close();
                }
                catch(IOException ex){}
            }
            
            //Store the lines in an array list, so that if something goes wrong
            // with one of the files, then the target is not created if it wont
            // be used.
            FileWriter writer = new FileWriter(targetPath);
            out = new PrintWriter(writer);
            for(String mergeLine : lines){
                out.println(mergeLine);
            }
        }
        finally{
            try{
                if(in != null){
                    in.close();
                }
            }
            catch(IOException ex){ }
            
            if(out != null){
                out.close();
            }
        }
    }
    // </editor-fold>
}
