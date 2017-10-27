package com.josh2.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button playAgainButton;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    GridLayout gridLayout;
    TextView timerView;
    TextView scoreView;
    TextView questionView;
    TextView resultView;
    CountDownTimer timer;
    Random q1;
    Random q2;
    int answer;
    int[] answers;
    int correct = 0;
    int attempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        timerView = (TextView) findViewById(R.id.timerView);
        scoreView = (TextView) findViewById(R.id.scoreView);
        questionView = (TextView) findViewById(R.id.questionView);
        resultView = (TextView) findViewById(R.id.resultView);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
    }

    public void start(View view) {
        setAnswerButtons();
        startGame();

        timer = new CountDownTimer(30000 + 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerView.setText(millisUntilFinished/1000 + "s");
            }

            @Override
            public void onFinish() {
                resultView.setText("Your score: " + correct + "/" + attempts);
                playAgainButton.setVisibility(View.VISIBLE);
                timerView.setText("0s");
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
            }
        }.start();
    }

    public void answer(View view) {
        Button clicked = (Button) view;
        int selectedButton = Integer.parseInt(clicked.getTag().toString());
        if(answers[selectedButton-1] == answer) {
            resultView.setText("Correct!");
            correct++;
            attempts++;
            resultView.setVisibility(View.VISIBLE);
            questionGenerator();
            setAnswerButtons();
            scoreView.setText(correct + "/" + attempts);
        } else {
            resultView.setText("Incorrect!");
            attempts++;
            resultView.setVisibility(View.VISIBLE);
            questionGenerator();
            setAnswerButtons();
            scoreView.setText(correct + "/" + attempts);
        }
    }

    public int[] setAnswerButtons() {
        int sum = questionGenerator();
        answers = new int[4];
        Random one = new Random();
        Random two = new Random();
        Random three = new Random();
        int r1 = one.nextInt(40) + 1;
        int r2 = two.nextInt(40) + 1;
        int r3 = three.nextInt(40) + 1;

        Random c = new Random();
        int correct = c.nextInt(4) + 1;
        if(correct == 1) {
            button1.setText(Integer.toString(sum));
            button2.setText(Integer.toString(r1));
            button3.setText(Integer.toString(r2));
            button4.setText(Integer.toString(r3));
            answers[0] = sum;
            answers[1] = r1;
            answers[2] = r2;
            answers[3] = r3;
        } else if(correct == 2) {
            button1.setText(Integer.toString(r1));
            button2.setText(Integer.toString(sum));
            button3.setText(Integer.toString(r2));
            button4.setText(Integer.toString(r3));
            answers[0] = r1;
            answers[1] = sum;
            answers[2] = r2;
            answers[3] = r3;
        } else if(correct == 3) {
            button1.setText(Integer.toString(r1));
            button2.setText(Integer.toString(r2));
            button3.setText(Integer.toString(sum));
            button4.setText(Integer.toString(r3));
            answers[0] = r1;
            answers[1] = r2;
            answers[2] = sum;
            answers[3] = r3;
        } else if(correct == 4) {
            button1.setText(Integer.toString(r1));
            button2.setText(Integer.toString(r2));
            button3.setText(Integer.toString(r3));
            button4.setText(Integer.toString(sum));
            answers[0] = r1;
            answers[1] = r2;
            answers[2] = r3;
            answers[3] = sum;
        }
        return answers;
    }

    public int questionGenerator() {
        q1 = new Random();
        q2 = new Random();
        int n1 = q1.nextInt(40) + 1;
        int n2 = q2.nextInt(40) + 1;
        questionView.setText(n1 + " + " + n2);
        answer = n1 + n2;
        return answer;
    }

    public void startGame() {
        startButton.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        timerView.setVisibility(View.VISIBLE);
        scoreView.setVisibility(View.VISIBLE);
        questionView.setVisibility(View.VISIBLE);
    }

    public void resetGame() {
        timer.cancel();
        timerView.setText("30s");
        scoreView.setText("0/0");
        correct = 0;
        attempts = 0;
        startButton.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.INVISIBLE);
        timerView.setVisibility(View.INVISIBLE);
        scoreView.setVisibility(View.INVISIBLE);
        questionView.setVisibility(View.INVISIBLE);
        resultView.setVisibility(View.INVISIBLE);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
    }

    public void playAgain(View view) {
        resetGame();
    }
}
