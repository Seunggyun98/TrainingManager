package GUITest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class WorkoutGUI {
    int id;
    public void main(int id) {
        this.id = id;
        EventQueue.invokeLater(() -> {
            WorkoutFrame workoutFrame = new WorkoutFrame(id);

            workoutFrame.setTitle("Trainning Manager");
            workoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            workoutFrame.setVisible(true);

        });
    }
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
        textField = new JTextField("텍스트 필드");
        leftPanel.add(textField,BorderLayout.CENTER);
        contentPlane.add(leftPanel,BorderLayout.CENTER);

        //오른쪽 버튼 패널
        JPanel rightPanel = new JPanel();
        JPanel textPanel = new JPanel();

        //textPanel.setLayout(new GridLayout(5,2));
       textPanel.setLayout(new GridLayout(10,1));
        JLabel exerciseLabel = new JLabel("운동 부위");
        JTextField exercise = new JTextField(4);
        JLabel exerciseNameLabel = new JLabel("운동 이름");
        JTextField exerciseName = new JTextField(4);
        JLabel exerciseSetLabel = new JLabel("세트");
        JTextField exerciseSet = new JTextField(4);
        JLabel exerciseRepsLabel = new JLabel("횟수");
        JTextField exerciseReps = new JTextField(4);
        JLabel exerciseWeightLabel = new JLabel("중량");
        JTextField exerciseWeight = new JTextField(4);
        JLabel exerciseDateLabel = new JLabel("날짜");
        JLabel exerciseDateYearLabel = new JLabel("년");
        JLabel exerciseDateMonthLabel = new JLabel("월");
        JLabel exerciseDateDayLabel = new JLabel("일");

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
        JButton addExercise = new JButton("운동 추가");
        JButton searchExercise = new JButton("운동 찾기");

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
            //Date객체가 같은지 확인 -> 같으면 해당 workoutList의 ExercisList에 Exercise를 추가
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
            //날짜가 같은 WorkoutList에서 getExercise
            Date date = new Date(year,month,day);
            textField.setText(((Trainee)Main.memberSet.get(id)).getWorkoutList());
        }
    }

}


