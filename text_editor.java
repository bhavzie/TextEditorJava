import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.text.*;
import javax.swing.text.BadLocationException;

public class text_editor extends JFrame implements ActionListener,MouseListener
{
// Component Initialized
static JFrame jf=new JFrame("Text Editor");
static BorderLayout bl=new BorderLayout();
static JComboBox<String> open=new JComboBox<>();
static JLabel name=new JLabel("Untitled Document");
static JButton saveDoc=new JButton("Save");
static JButton close=new JButton("Close");
static JComboBox<String> options=new JComboBox<>();
static JTextArea area=new JTextArea();
static JPanel temp=new JPanel();
static JPanel temp1=new JPanel();
static JLabel numberOfLines=new JLabel();
static JFrame jf1=new JFrame();
static JLabel jl1=new JLabel("Enter the Filename:");
static JTextField jtf1=new JTextField();
static JButton ok1=new JButton("Ok");
static JFrame jf2=new JFrame();
static JLabel jl2=new JLabel("Enter the filename:");
static JTextField jtf2=new JTextField("UntitledDocument");
static JButton ok2=new JButton("Ok");
static JTextArea areatemp[]=new JTextArea[10];
static JScrollPane jsp=new JScrollPane(area);
static JTextField jtf3=new JTextField();
static JFrame jf3=new JFrame();
static JButton ok3=new JButton("Ok");
static JFrame jf4=new JFrame();
static JTextField jtf4=new JTextField();
static JButton ok4=new JButton("Ok");
static JButton clear=new JButton("Clear ALL");

public static void main(String args[])
{

// Component Adjusted
area.setFont(new Font("SansSerif", Font.BOLD, 14));
jf.setLayout(bl);
jf.setSize(1000,1000);
jf1.setSize(600,200);
jf2.setSize(600,200);
jf3.setSize(200,100);
jf4.setSize(300,100);
jf1.setLayout(new GridLayout(1,3));
jf2.setLayout(new GridLayout(1,3));
jf3.setLayout(new GridLayout(1,2));
jf4.setLayout(new GridLayout(1,2));
temp.setLayout(new GridLayout(1,10));
jf.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
jf1.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
jf2.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
jf3.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
jf4.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
jf.addWindowListener( new WindowAdapter() {
    public void windowOpened( WindowEvent e ){
        area.requestFocus();
    }
});
jf1.addWindowListener( new WindowAdapter() {
    public void windowOpened( WindowEvent e ){
        jtf1.requestFocus();
    }
});  
jf2.addWindowListener( new WindowAdapter() {
    public void windowOpened( WindowEvent e ){
        jtf2.requestFocus();
    }
});
jf3.addWindowListener( new WindowAdapter() {
    public void windowOpened( WindowEvent e ){
        jtf3.requestFocus();
    }
}); 
jf4.addWindowListener( new WindowAdapter() {
    public void windowOpened( WindowEvent e ){
        jtf4.requestFocus();
    }
}); 

//Components Added
temp.add(open);
temp.add(saveDoc);
temp.add(clear);
temp.add(name);
temp.add(saveDoc);
temp.add(options);
temp.add(close);
temp1.setLayout(new GridLayout(1,10));
temp1.add(new JLabel());
temp1.add(new JLabel());
temp1.add(new JLabel());
temp1.add(new JLabel());
temp1.add(numberOfLines);
temp1.add(new JLabel());
temp1.add(new JLabel());


// Open Box
open.addItem("Open");
File f=new File("/home/bhavzie/Desktop");
File listOfFiles[]=f.listFiles();
if(listOfFiles!=null)
{
for(int ip=0;ip<listOfFiles.length;ip++)
{
if(listOfFiles[ip].isFile())
open.addItem(listOfFiles[ip].getName());
if(ip==4)
break;
}
}
open.addItem("Other Document");
open.setSelectedItem("Open");

// Other Options
options.addItem("Options");
options.addItem("Save As");
options.addItem("Go To Line");
options.addItem("Find");
options.setSelectedItem("Options");
options.addItem("Copy");
options.addItem("Cut");
options.addItem("Paste");

// ActionListeners
// Saving a document
saveDoc.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e)
{
// Create new Jframe where you ask for details of the FileName to save it
File listOfFiles[]=f.listFiles();
boolean flag=true;
for(int ip=0;ip<listOfFiles.length;ip++)
{
if((listOfFiles[ip].isFile())&&(name.getText().equals(listOfFiles[ip].getName())))
{
flag=false;
try
{
BufferedWriter bw=new BufferedWriter(new FileWriter(name.getText()));
bw.write(area.getText());
bw.close();
}catch(IOException m)
{

}
}
}
if(flag)
{
jf2.setVisible(true);
}
}
});
ok2.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e)
{
boolean flag=true;
File listOfFiles[]=f.listFiles();
for(int ip=0;ip<listOfFiles.length;ip++)
{
if((listOfFiles[ip].isFile())&&(jtf2.getText().equals(listOfFiles[ip].getName())))
{
flag=false;
jl2.setText("Already Exists ");
}
}
if(flag)
{
try
{
BufferedWriter bw=new BufferedWriter(new FileWriter(jtf2.getText()));
bw.write(area.getText());
bw.close();
}catch(IOException m)
{

}
jf2.setVisible(false);
jl2.setText("Enter the filename:");
name.setText(jtf2.getText());
jtf2.setText("");
}
}
});

// Other Options
options.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e)
{
if((options.getSelectedItem().equals("Save As")))
{
jf2.setVisible(true);
jtf2.grabFocus();
}
else if((options.getSelectedItem().equals("Find")))
{
jf4.setVisible(true);
jtf4.grabFocus();
}
else if((options.getSelectedItem().equals("Go To Line")))
{
jf3.setVisible(true);
jtf3.grabFocus();
}
else if((options.getSelectedItem().equals("Copy")))
{
area.copy();
area.grabFocus();
}
else if((options.getSelectedItem().equals("Cut")))
{
area.cut();
area.grabFocus();
}
else if((options.getSelectedItem().equals("Paste")))
{
area.paste();
area.grabFocus();
}
}
});

//Go to line

ok3.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e)
{
jf3.setVisible(false);
area.grabFocus();
if(Integer.parseInt(jtf3.getText())>=area.getLineCount())
{
area.moveCaretPosition(area.getDocument().getDefaultRootElement().getElement(area.getLineCount()-1).getStartOffset());
}
else if(Integer.parseInt(jtf3.getText())<=1)
{
area.moveCaretPosition(area.getDocument().getDefaultRootElement().getElement(0).getStartOffset());
}
else
{
area.moveCaretPosition(area.getDocument().getDefaultRootElement().getElement(Integer.parseInt(jtf3.getText())-1).getStartOffset());
}
jtf3.setText("");
}
});

// Closing the File
close.addActionListener(new ActionListener() {     
public void actionPerformed(ActionEvent e)
{
Object[] options = { "OK", "CANCEL" };
int n=JOptionPane.showConfirmDialog(null,"Do you wish to close "+name.getText()+" ?","", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
if(n==JOptionPane.YES_OPTION)
{
System.exit(0);
}
else
{
area.grabFocus();
}
}
});

// Fiind
ok4.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e)     
{
jf4.setVisible(false);
area.grabFocus();
try
{
int l1=(area.getDocument().getText(0,area.getDocument().getLength())).indexOf(jtf4.getText());
int l2=l1+((jtf4.getText()).length());
if(l1!=-1)
area.select(l1,l2);
jtf4.setText("");
}catch(BadLocationException mr)
{

}
}
});

// Open 
open.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e)
{
if((open.getSelectedItem().equals("Other Document")))
{
jtf1.grabFocus();
jf1.setVisible(true);
}
else
{
try
{
String inp;
BufferedReader br=new BufferedReader(new FileReader(open.getSelectedItem().toString()));
inp=br.readLine();
area.setText("");
name.setText(open.getSelectedItem().toString());
open.setSelectedItem("Open");
while(inp!=null)
{
area.append("\n");
area.append(inp);
inp=br.readLine();
}
br.close();
area.grabFocus();
}catch(IOException m)
{
}
}
}
});
ok1.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e)
{
boolean flag=true;
area.grabFocus();
File listOfFiles[]=f.listFiles();
for(int ip=0;ip<listOfFiles.length;ip++)
{
if((listOfFiles[ip].isFile())&&(jtf1.getText().equals(listOfFiles[ip].getName())))
{
flag=false;
try
{
String inp;
BufferedReader br=new BufferedReader(new FileReader(jtf1.getText()));
inp=br.readLine();
area.setText("");
name.setText(jtf1.getText());
open.setSelectedItem("Open");
while(inp!=null)
{
area.append("\n");
area.append(inp);
inp=br.readLine();
}
br.close();
}catch(IOException m)
{

}
jf1.setVisible(false);
jl1.setText("Enter the filename:");
jtf1.setText("");
}
}
if(flag)
{
jl1.setText("Invalid");
}
}
});

// Update the linenumber on mouse clicks
area.addMouseListener(new MouseListener()
{
public void mouseClicked(MouseEvent e)
{
try
{
int row=area.getLineOfOffset(area.getCaretPosition());
int col=Math.abs(area.getLineStartOffset(row)-area.getCaretPosition());
numberOfLines.setText("Ln"+(row+1)+"\t"+"\t"+",Col"+(col+1));
}catch(BadLocationException m)
{
}
}
public void mousePressed(MouseEvent e)
{

}
public void mouseReleased(MouseEvent e)
{

}
public void mouseEntered(MouseEvent e)
{

}
public void mouseExited(MouseEvent e)
{

}
});

// clear all
clear.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e)
{
Object[] options = { "OK", "CANCEL" };
int n=JOptionPane.showConfirmDialog(null,"Do you wish to clear "+name.getText()+" ?","", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
if(n==JOptionPane.YES_OPTION)
{
area.setText("");
area.grabFocus();
}
else
{
area.grabFocus();
}
}
});

// Main Frame
jf.add(temp,BorderLayout.NORTH);
jf.add(temp1,BorderLayout.SOUTH);
jf.add(jsp,BorderLayout.CENTER);
numberOfLines.setText("Ln"+area.getColumns()+",Col"+area.getRows());
jf1.add(jl1);
jf1.add(jtf1);
jf1.add(ok1);
jf2.add(jl2);
jf2.add(jtf2);
jf2.add(ok2);
jf3.add(jtf3);
jf3.add(ok3);
jf4.add(jtf4);
jf4.add(ok4);
jf.setVisible(true);
}



// Override
public void actionPerformed(ActionEvent e)
{

}
public void mouseClicked(MouseEvent e)
{

}
public void mousePressed(MouseEvent e)
{

}
public void mouseReleased(MouseEvent e)
{

}
public void mouseEntered(MouseEvent e)
{

}
public void mouseExited(MouseEvent e)
{

}
}
