package src.main.java;//package src.main.java.SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class SimpleClock extends JFrame {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat;
        SimpleDateFormat timeFormatMilitary;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;

        TimeZone local = calendar.getTimeZone();
        TimeZone GMT = TimeZone.getTimeZone("GMT");
    
        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        JButton timeSwap;
        JButton gmtLocal;
        String time;
        String day;
        String date;

        Thread t;
        boolean isMilitary = false;

    SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            this.setLayout(new FlowLayout());
            this.setSize(400, 250);
            this.setResizable(true);
    
            timeFormat = new SimpleDateFormat("hh:mm:ss a");
            timeFormatMilitary = new SimpleDateFormat("HH:mm:ss");
            dayFormat = new SimpleDateFormat("EEEE");
            dateFormat = new SimpleDateFormat("dd MMMMM, yyyy");
            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
            timeLabel.setBackground(Color.BLACK);
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setOpaque(true);
            dayLabel = new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,34));
    
            dateLabel = new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));

            timeSwap = new JButton();
            timeSwap.setText("Military");
            timeSwap.addActionListener(this::militaryButton);

            gmtLocal = new JButton();
            gmtLocal.setText("GMT");
            gmtLocal.addActionListener(this::gmtLocalButton);
    
    
            this.add(timeLabel);
            this.add(dayLabel);
            this.add(dateLabel);
            this.add(timeSwap);
            this.add(gmtLocal);
            this.setVisible(true);
    
            setTimer();
        }
    
        public void setTimer() {

            while (true) {

                timeSet();
    
                day = dayFormat.format(Calendar.getInstance().getTime());
                dayLabel.setText(day);
    
                date = dateFormat.format(Calendar.getInstance().getTime());
                dateLabel.setText(date);
    
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
        public static void main(String[] args) {
            new SimpleClock();
        }


    public void militaryButton(ActionEvent e) {
        if (timeSwap.getText().equals("Military")) {
            timeSwap.setText("Standard");
        } else {
            timeSwap.setText("Military");
        }
        isMilitary = !isMilitary;
        timeSet();
    }

    public void gmtLocalButton(ActionEvent e){
        if(gmtLocal.getText().equals("Local")){
            gmtLocal.setText("GMT");
            timeFormatMilitary.setTimeZone(local);
            timeFormat.setTimeZone(local);
        }else{
            gmtLocal.setText("Local");
            timeFormatMilitary.setTimeZone(GMT);
            timeFormat.setTimeZone(GMT);
        }
        timeSet();
    }

    public void timeSet(){
        if(isMilitary){
            time = timeFormatMilitary.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);
        }else {
            time = timeFormat.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);
        }
    }


}
