package GUITest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiTest {
    int id=5262;
    public void gui(int id) {
       //this.id = id;
        EventQueue.invokeLater(() -> {
            WorkoutFrame workoutFrame = new WorkoutFrame(id);

            workoutFrame.setTitle("Trainning Manager");
            workoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            workoutFrame.setVisible(true);

        });
    }



class WorkoutFrame extends JFrame{
    private static final int DEFAULT_WIDTH = 660;
    private static final int DEFAULT_HEIGHT = 600;
    private int id;

    JTextField textField;
    public WorkoutFrame(int id){
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        //setResizable(false);
        this.id=id;
        Container contentPlane = this.getContentPane();
        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("WORKOUT PAGE");
        topPanel.add(label);
        contentPlane.add(topPanel,BorderLayout.NORTH);

        Container center = new Container();
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        textField = new JTextField("ï¿½Ø½ï¿½Æ® ï¿½Êµï¿½");
        leftPanel.add(textField,BorderLayout.CENTER);
        contentPlane.add(leftPanel,BorderLayout.CENTER);

        //ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½Æ° ï¿½Ð³ï¿½
        JPanel rightPanel = new JPanel();
        JPanel textPanel = new JPanel();

        //textPanel.setLayout(new GridLayout(5,2));
       textPanel.setLayout(new GridLayout(10,1));
        JLabel exerciseLabel = new JLabel("ï¿½îµ¿ ï¿½ï¿½ï¿½ï¿½");
        JTextField exercise = new JTextField(4);
        JLabel exerciseNameLabel = new JLabel("ï¿½îµ¿ ï¿½Ì¸ï¿½");
        JTextField exerciseName = new JTextField(4);
        JLabel exerciseSetLabel = new JLabel("ï¿½ï¿½Æ®");
        JTextField exerciseSet = new JTextField(4);
        JLabel exerciseRepsLabel = new JLabel("È½ï¿½ï¿½");
        JTextField exerciseReps = new JTextField(4);
        JLabel exerciseWeightLabel = new JLabel("ï¿½ß·ï¿½");
        JTextField exerciseWeight = new JTextField(4);
        JLabel exerciseDateLabel = new JLabel("ï¿½ï¿½Â¥");
        JLabel exerciseDateYearLabel = new JLabel("ï¿½ï¿½");
        JLabel exerciseDateMonthLabel = new JLabel("ï¿½ï¿½");
        JLabel exerciseDateDayLabel = new JLabel("ï¿½ï¿½");

        JPanel datePanel = new JPanel();
        JTextField exerciseDateYear = new JTextField(4);
        JTextField exerciseDateMonth = new JTextField(2);
        JTextField exerciseDateDay = new JTextField(2);
        datePanel.add(exerciseDateYear);
        datePanel.add(exerciseDateYearLabel);
        datePanel.add(exerciseDateMonth);
        datePanel.add(exerciseDateMonthLabel);
        datePanel.add(exerciseDateDay);
        datePanel.add(exerciseDateDayLabel);

        textPanel.add(exerciseLabel);
        textPanel.add(exercise);
        textPanel.add(exerciseNameLabel);
        textPanel.add(exerciseName);
        textPanel.add(exerciseSetLabel);
        textPanel.add(exerciseSet);
        textPanel.add(exerciseRepsLabel);
        textPanel.add(exerciseReps);
        textPanel.add(exerciseWeightLabel);
        textPanel.add(exerciseWeight);
        textPanel.add(exerciseDateLabel);
        textPanel.add(datePanel);
        JPanel btnPanel = new JPanel();
        JButton addExercise = new JButton("ï¿½îµ¿ ï¿½ß°ï¿½");
        JButton searchExercise = new JButton("ï¿½îµ¿ Ã£ï¿½ï¿½");

        JPanel emptyPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        btnPanel.add(addExercise);
        btnPanel.add(searchExercise);
        rightPanel.setLayout(new GridLayout(2,2));;
        rightPanel.add(textPanel);
        rightPanel.add(btnPanel);
        contentPlane.add(rightPanel,BorderLayout.EAST);
        //add(rightPanel);
        AddExerciseAction addExerciseAction = new AddExerciseAction(exercise.getText(),exerciseName.getText(),exerciseSet.getText(),exerciseReps.getText(),exerciseWeight.getText(),exerciseDateYear.getText(),exerciseDateMonth.getText(),exerciseDateDay.getText());
        SearchExerciseAction listExerciseAction = new SearchExerciseAction(exerciseDateYear.getText(),exerciseDateMonth.getText(),exerciseDateDay.getText());

        addExercise.addActionListener(addExerciseAction);
        searchExercise.addActionListener(listExerciseAction);




    }
    private class AddExerciseAction implements ActionListener{
        String exTarget,exName;
        int exSet,exReps;
        Double exWeight;
        int year,month,day;
        public AddExerciseAction(String exTarget, String exName,String exSet,String exReps,String exWeight,String year, String month,String day){
            this.exTarget = exTarget;
            this.exName=exName;
            this.exSet=Integer.valueOf(exSet);
            this.exReps=Integer.valueOf(exReps);
            this.exWeight=Double.valueOf(exWeight);
            this.year = Integer.valueOf(year);
            this.month = Integer.valueOf(month);
            this.day=Integer.valueOf(day);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Date date = new Date(this.year,this.month,this.day);
            Exercise exercise = new Exercise(exTarget,exName,Integer.valueOf(exSet),Integer.valueOf(exReps),Double.valueOf(exWeight));


            //((Trainee)Main.memberSet.get(id))
            //Date°´Ã¼°¡ °°ÀºÁö È®ÀÎ -> °°À¸¸é ÇØ´ç workoutListÀÇ ExercisList¿¡ Exercise¸¦ Ãß°¡
        }
    }
    private class SearchExerciseAction implements ActionListener{
        int year,month,day;
        public SearchExerciseAction(String year, String month,String day){
            this.year = Integer.valueOf(year);
            this.month = Integer.valueOf(month);
            this.day=Integer.valueOf(day);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            //³¯Â¥°¡ °°Àº WorkoutList¿¡¼­ getExercise
            Date date = new Date(year,month,day);
            textField.setText(((Trainee)Main.memberSet.get(id)).getWorkoutList());
        }
    }

}
}


