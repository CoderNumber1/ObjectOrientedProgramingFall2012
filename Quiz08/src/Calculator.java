
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame implements ActionListener{
    public Calculator(){
        this.values = new ArrayList<>();
        this.values.add(0.0);
        this.values.add(0.0);
        
        this.result = 0.0;
        
        this.inputField = new JTextField(20);
        this.inputField.setHorizontalAlignment(JTextField.RIGHT);
        
        this.outputField = new JTextField(20);
        this.outputField.setHorizontalAlignment(JTextField.RIGHT);

        Container cp = super.getContentPane();
        cp.add(this.inputField, "South");
        cp.add(this.outputField, "North");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,3));

        this.enterButton = new JButton("Enter");
        this.enterButton.addActionListener(this);

        this.addButton = new JButton("Add");
        this.addButton.addActionListener(this);

        this.subtractButton = new JButton("Subtract");
        this.subtractButton.addActionListener(this);

        this.multiplyButton = new JButton("Multiply");
        this.multiplyButton.addActionListener(this);

        this.divideButton = new JButton("Divide");
        this.divideButton.addActionListener(this);

        this.getResultButton = new JButton("Get Result");
        this.getResultButton.addActionListener(this);

        buttonPanel.add(this.addButton); buttonPanel.add(this.subtractButton); buttonPanel.add(this.getResultButton);
        buttonPanel.add(this.multiplyButton); buttonPanel.add(this.divideButton); buttonPanel.add(this.enterButton);

        cp.add(buttonPanel, "Center");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source == this.enterButton){
            String input = this.inputField.getText();
            
            try{
                Double value = Double.parseDouble(input);
                
                this.values.set(0, this.values.get(1));
                this.values.set(1, value);
                
                this.outputField.setText("");
                this.inputField.setText("");
            }
            catch(Exception ex){
                this.outputField.setText("Input Error");
            }
        }
        else if(source == this.getResultButton){
            this.outputField.setText(this.result.toString());
        }
        else if(source == this.addButton){
            this.result = this.values.get(0) + this.values.get(1);
        }
        else if(source == this.subtractButton){
            this.result = this.values.get(1) - this.values.get(0);
        }
        else if(source == this.multiplyButton){
            this.result = this.values.get(0) * this.values.get(1);
        }
        else if(source == this.divideButton){
            if(this.values.get(0) == 0){
                this.outputField.setText("Divide By 0");
            }
            else{
                this.result = this.values.get(1) / this.values.get(0);
            }
        }
        
        super.repaint();
    }

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private ArrayList<Double> values;
    private Double result;
    
    private JTextField inputField;
    private JTextField outputField;

    private JButton enterButton;
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton getResultButton;
    // </editor-fold>
}