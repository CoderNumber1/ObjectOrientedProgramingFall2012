
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Quiz07 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        File file = new File();
        int menuOption;
        
        do{
            do{
                System.out.println("1. Diplay the content of a file");
                System.out.println("2. Merge files");
                System.out.println("3. Quit");
                
                System.out.print("Select a menu option: ");
            
                menuOption = scanner.nextInt();
            }while(menuOption < 1 || menuOption > 3);
            
            switch(menuOption){
                case 1:
                    System.out.print("Enter a file path: ");
                    String path = scanner.next();
                    
                    try{
                        String fileString = file.loadFile(path);
                        System.out.print(fileString);
                    }
                    catch(FileNotFoundException ex){
                        System.out.println("File not found " + ex);
                    }
                    catch(IOException ex){
                        System.out.println("Read error " + ex);
                    }
                    catch(Exception ex){
                        System.out.println("Unknown error " + ex);
                    }
                    break;
                case 2:
                    System.out.print("How many files will be merged: ");
                    int numberOfSources = scanner.nextInt();
                    String[] sources = new String[numberOfSources];
                    
                    for(int i = 0; i < numberOfSources; i ++){
                        System.out.printf("Enter the path of source #%d: ", i + 1);
                        sources[i] = scanner.next();
                    }
                    
                    System.out.print("Enter the path of the target file: ");
                    String target = scanner.next();
                    
                    try{
                        file.mergeFiles(sources, target);
                    }
                    catch(FileNotFoundException ex){
                        System.out.println("File not found " + ex);
                    }
                    catch(IOException ex){
                        System.out.println("Read/Write error " + ex);
                    }
                    catch(Exception ex){
                        System.out.println("Unknown error " + ex);
                    }
                    break;
            }
        }while(menuOption != 3);
    }
}
