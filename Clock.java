package Clock;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Clock extends JFrame{
    Calendar calendar;
    SimpleDateFormat timeFormat;
    SimpleDateFormat dayFormat;
    SimpleDateFormat dateFormat;

    JLabel timeLabel;
    JLabel dayLabel;
    JLabel dateLabel;

    private JLabel alarmStatusLabel;
    private JTextField alarmField;
    private JButton setAlarmButton;

    private Date alarmTime;
    // Creating a new clock object.
    Clock(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Digital Clock");
        setLayout(new FlowLayout());
        setSize(350, 300);
        setResizable(false);

        timeFormat = new SimpleDateFormat("hh:mm:ss a");
        dayFormat = new SimpleDateFormat("EEEE");
        dateFormat = new SimpleDateFormat("dd MMMMM, yyyy");
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
        timeLabel.setBackground(Color.BLUE);
        timeLabel.setForeground(Color.RED);
        timeLabel.setOpaque(true);
        
        dayLabel = new JLabel();
        dayLabel.setFont(new Font("Ink Free", Font.BOLD, 34));

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Ink Free", Font.BOLD, 30));

        alarmStatusLabel = new JLabel("No alarm set.");
         alarmStatusLabel.setFont(new Font("Ink Free", Font.BOLD, 18));

        alarmField = new JTextField(10);

        setAlarmButton = new JButton("Set Alarm");
        setAlarmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAlarm();
            }
        });

        add(timeLabel);
        add(dayLabel);
        add(dateLabel);
        add(alarnStatusLabel);
        add(new JLabel("Set Alarms: "));
        add(alarmField);
        add(setAlarmButton);
        setVisible(true);

        setTimer();
    }

    public void setTimer(){
        while(true){
            Calander calander = Calander.getInstance();
            
            String time = timeFormat();
            timeLabel.setText(time);

            String day = dayFormat.format(Calendar.getInstance().getTime());
            dayLabel.setText(day);

            String date = dateFormat.format(Calendar.getInstance().getTime());
            dateLabel.setText(date);

            checkAlarmTrigger();

            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.getStackTrace();
            }
        }
    }
   private void setAlarm() {
        String alarmText = alarmField.getText();
        SimpleDateFormat alarmFormat = new SimpleDateFormat("hh:mm a");

        try {
            alarmTime = alarmFormat.parse(alarmText);
            alarmStatusLabel.setText("Alarm set for " + alarmFormat.format(alarmTime));
        } catch (Exception e) {
            alarmStatusLabel.setText("Invalid alarm format");
            e.printStackTrace();
        }
    }

private void checkAlarmTrigger() {
        if (alarmTime != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(alarmTime);

            Calendar currentTime = Calendar.getInstance();

            if (calendar.get(Calendar.HOUR_OF_DAY) == currentTime.get(Calendar.HOUR_OF_DAY)
                    && calendar.get(Calendar.MINUTE) == currentTime.get(Calendar.MINUTE)
                    && calendar.get(Calendar.SECOND) == currentTime.get(Calendar.SECOND)) {
                JOptionPane.showMessageDialog(null, "Alarm triggered!");
                alarmTime = null;
                alarmStatusLabel.setText("No alarm set");
            }
        }
    }
    
    public static void main(String[] args) {
        new Clock();
        
    }
    
}
