//Branden Castle

//import libraries
package classification;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import chapman.graphics.JPlot2D;
import javax.swing.*;
//USE BOX LAYOUT
public class classify {
public static JFrame fr= new JFrame();
//main function
public static void main(String[] args) throws IOException {
// variables are declared
final int MAX_SAMPLES = 10;
double tcdata[][] = new double[MAX_SAMPLES][2];
double fcdata[][] = new double[MAX_SAMPLES][2];
double tcdatax[] = new double[MAX_SAMPLES];
double tcdatay[] = new double[MAX_SAMPLES];
double fcdatax[] = new double[MAX_SAMPLES];
double fcdatay[] = new double[MAX_SAMPLES];
//used to select a file
String read;
//Scanner sc = new Scanner(System.in);
//prompts user to manually enter file name
read= JOptionPane.showInputDialog(null,"what file would you like to use?: ");
// uses file reading function to read files into array
readfile(read, tcdata, fcdata);
make1d(tcdata, tcdatax, tcdatay);
make1d(fcdata, fcdatax, fcdatay);
// allows for files to be written
PrintWriter outwrite = new PrintWriter(
new BufferedWriter(new FileWriter("classification/data_organized.txt")));
// data is written into a new file
outwrite.println("X1 True\tX2 True\tX1 False X2 False");
outwrite.println("--------------------------------------");
// prints data out into a new file called data_organized.txt
for (int j = 0; j < MAX_SAMPLES; j++) {// goes through columns
// prints out data
outwrite.println(tcdata[j][0] + "\t" + fcdata[j][1] + "\t" + fcdata[j]
[0] + "\t"
+ fcdata[j][1]);
}
makeplot(tcdatax, tcdatay, fcdatax, fcdatay);
outwrite.close();// closes file writer
fr.setSize(1000, 1000);
// WindowHandler l= new WindowHandler();
//fr.addWindowListener(l);
gui fg= new gui();
fg.init();
fr.getContentPane().add(fg,BorderLayout.EAST);
fr.setVisible(true);
}
// function for taking in file data
public static void readfile(String file, double[][] data, double[][] data2)
throws FileNotFoundException {
final int MAX_SAMPLES = 10;
int count = 0;
try {// reads chosen file
File doc = new File("classification/"+file);
Scanner str = new Scanner(doc);
while (str.hasNextLine()) {
outter1: for (int i = 0; i < 2; i++) {// reads in true class data
for (int j = 0; j < MAX_SAMPLES; j++) {
if (count == 40)
break outter1;
data[j][i] = str.nextDouble();
count++;
}
}
for (int i = 0; i < 2; i++) {// reads in false class data
for (int j = 0; j < MAX_SAMPLES; j++) {
data2[j][i] = str.nextDouble();
}
}
}
str.close();// closes file
} catch (FileNotFoundException e) {// happens when file location cannot be found
System.out.println("File not found");
e.printStackTrace();
}
}
//one dimensional version of existing array is made
public static void make1d(double[][] data2d, double[] datax, double[] datay) {
for (int i = 0; i < 10; i++) {
datax[i] = data2d[i][0];
}
for (int i = 0; i < 10; i++) {
datay[i] = data2d[i][1];
}
}
//creates a 2d plot and adds in to the gui
public static void makeplot(double[] datax1, double[] datay1, double[] datax2,
double[] datay2) {
// creates two plots each representing true and false class data respectively
//variables declared here
int xl=datax1.length;
int x2= datax2.length;
int yl=datay1.length;
int y2= datay2.length;
double datax[]= new double[xl+x2];
double datay[]= new double[yl+y2];
//create array containing all x values
System.arraycopy(datax1,0,datax,0,xl);
System.arraycopy(datax2, 0, datax, xl, x2);
//create array containing all y values
System.arraycopy(datay1,0,datay,0,yl);
System.arraycopy(datay2, 0, datay, yl, y2);
//create plot and make markers red circles or blue triangles based on their place on the graph
JPlot2D plot = new JPlot2D(datax1, datay1);
plot.setMarkerState(JPlot2D.MARKER_ON);
plot.setLineState(JPlot2D.LINE_OFF);
plot.setMarkerColor(Color.red);
plot.setMarkerStyle(JPlot2D.MARKER_CIRCLE);
//add second curve
plot.addCurve(datax2, datay2);
plot.setMarkerState(JPlot2D.MARKER_ON);
plot.setLineState(JPlot2D.LINE_OFF);
plot.setMarkerColor(Color.blue);
plot.setMarkerStyle(JPlot2D.MARKER_TRIANGLE);
//title and labels for the plot
plot.setTitle("Project 1: Fruit Classification");
plot.setXLabel("X1 Feature");
plot.setYLabel("Y1 Feature");
plot.setGridState(JPlot2D.GRID_ON);
// plot2.setGridState(JPlot2D.GRID_ON);
fr.getContentPane().add(plot);
//fr.setSize(500, 500);
}
//allows for the window to be closed
class WindowHandler extends WindowAdapter {
public void windowClosing(WindowEvent e) {
System.exit(0);
};
}
}
//creates gui parameters
class gui extends JPanel {
private JButton push;
private JLabel label;
public void init() throws FileNotFoundException{
//sets the random number between the higehest and lowest numbers given
//setLayout(new BorderLayout());
//creates prompt in the GUI window
label = new JLabel("feature 1\tfeature2");
add(label);
// label.setHorizontalAlignment(BoxLayout.Y_AXIS);
// push= new JButton("Guess age");
//push.addActionListener(new ButtonHandler(this));
//add(push);
}
//updates on the press of a button
public void updatelabel(){
Random r= new Random();
int max=30;
int min=20;
int rnum=r.nextInt((max-min)+1)+min;
// label.setText(""+(rnum));
}
//handles when a button is pressed
public class ButtonHandler implements ActionListener{
private gui gui;
public ButtonHandler (gui guii){
gui = guii;
}
public void actionPerformed(ActionEvent e){
gui.updatelabel();
}
}
}