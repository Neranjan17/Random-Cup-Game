package com.neranjan.cupgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView iconOne,iconTow,iconThree;
    TextView txtPercentage, txtRec, txtLost, txtWin, txtNote, txtRouCount, txtSelect1, txtSelect2, txtSelect3;
    EditText etAutoRou;
    Button btnShowRec, btnNo, btnYes, btnRun, btnYesRoNo;
    ConstraintLayout msgLayout;

    Handler handler;

    int randomNum, roundCount, winRounds, lostRounds, selectCunNum = 0;
    double percentage;
    String [] rec = {""};
    char switchCup = '-';
    char win_Ro_lost = '-';

    boolean alwaysSelectYES = true;

    Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iconOne = findViewById(R.id.ivIconOne);
        iconTow = findViewById(R.id.ivIconTow);
        iconThree = findViewById(R.id.ivIconThree);
        txtRouCount = findViewById(R.id.txtRouCount);
        txtNote = findViewById(R.id.txtNote);
        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);
        btnYesRoNo = findViewById(R.id.btnYesRoNo);
        msgLayout = findViewById(R.id.msgLayouts);
        txtPercentage = findViewById(R.id.txtPercentage);
        txtWin = findViewById(R.id.txtWin);
        txtLost = findViewById(R.id.txtLost);
        txtRec = findViewById(R.id.txtRec);
        btnShowRec = findViewById(R.id.btnShowRec);
        btnRun = findViewById(R.id.btnRun);
        etAutoRou = findViewById(R.id.etAutoRou);
        txtSelect1 = findViewById(R.id.txtSelect1);
        txtSelect2 = findViewById(R.id.txtSelect2);
        txtSelect3 = findViewById(R.id.txtSelect3);


        generateRandomNum();
        setRecordsString();


    }//end onCreate


    void setRecordsString() {


        int i = 0;
        rec [i++] = roundCount + "\t\t\t|\t\t\t" + randomNum + "\t\t\t|\t\t\t" + selectCunNum + "\t\t\t|\t\t\t" + switchCup + "\t\t\t|\t\t\t" + win_Ro_lost;



    }
    public void ivIconOne(View view) {

        if (msgLayout.getVisibility() == View.GONE) {

            reset();

            selectCunNum = 1;
            setRoundCount();
            setSelectVisibility('a');
            openEmptyCup('a');
            setNote();


        }else {
            Toast.makeText(this,R.string.error_toast,Toast.LENGTH_SHORT).show();
        }
    }
    public void ivIconTow(View view) {

        if (msgLayout.getVisibility() == View.GONE) {

            reset();

            selectCunNum = 2;
            setRoundCount();
            setSelectVisibility('b');
            openEmptyCup('b');
            setNote();

        }else {
            Toast.makeText(this,R.string.error_toast,Toast.LENGTH_SHORT).show();
        }
    }
    public void ivIconThree(View view) {


        if (msgLayout.getVisibility() == View.GONE) {

            reset();

            selectCunNum = 3;
            setRoundCount();
            setSelectVisibility('c');
            openEmptyCup('c');
            setNote();

        }else {
            Toast.makeText(this,R.string.error_toast,Toast.LENGTH_SHORT).show();
        }

    }


    public void btnYes(View view) {

        switchCup = 'Y';
        changingCup();
        winRoLost();
        setRecordsString();
        msgLayout.setVisibility(View.GONE);

        setPostAndCloseCup();






    }
    public void btnNo(View view) {


        switchCup = 'N';
        winRoLost();
        setRecordsString();
        msgLayout.setVisibility(View.GONE);

        setPostAndCloseCup();







    }
    public void btnRun(View view) {

        if ((etAutoRou.getText().length()>0) && (msgLayout.getVisibility() == View.GONE)) {

            int rounds = Integer.parseInt(etAutoRou.getText().toString());

            for (int i=1; i<=rounds; i++ ) {

                reset();

                switch ((int) (Math.random() * 3)+ 1) {

                    case 1:

                        selectCunNum = 1;
                        setRoundCount();
                        setSelectVisibility('a');
                        openEmptyCup('a');
                        setNote();
                        break;

                    case 2:

                        selectCunNum = 2;
                        setRoundCount();
                        setSelectVisibility('b');
                        openEmptyCup('b');
                        setNote();
                        break;

                    case 3:

                        selectCunNum = 3;
                        setRoundCount();
                        setSelectVisibility('c');
                        openEmptyCup('c');
                        setNote();
                        break;
                }


                if (alwaysSelectYES) {

                    switchCup = 'Y';
                    changingCup();
                    winRoLost();
                    msgLayout.setVisibility(View.GONE);

                }else {

                    switchCup = 'N';
                    winRoLost();
                    setRecordsString();
                    msgLayout.setVisibility(View.GONE);

                }


            }

        }else {
            Toast.makeText(this,R.string.error_toast,Toast.LENGTH_SHORT).show();
        }

    }

    void generateRandomNum() {

        randomNum = random.nextInt(3) + 1;


    }
    void setPostAndCloseCup() {

        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                iconOne.setBackgroundResource(R.drawable.close);
                iconTow.setBackgroundResource(R.drawable.close);
                iconThree.setBackgroundResource(R.drawable.close);

                txtSelect1.setVisibility(View.INVISIBLE);
                txtSelect2.setVisibility(View.INVISIBLE);
                txtSelect3.setVisibility(View.INVISIBLE);

            }
        }, 1000);
    }

    void reset() {



        generateRandomNum();

        selectCunNum = 0;
        switchCup = '-';
        //rec = rec + "\n";

        iconOne.setBackgroundResource(R.drawable.close);
        iconTow.setBackgroundResource(R.drawable.close);
        iconThree.setBackgroundResource(R.drawable.close);

        txtSelect1.setVisibility(View.INVISIBLE);
        txtSelect2.setVisibility(View.INVISIBLE);
        txtSelect3.setVisibility(View.INVISIBLE);


        txtRec.setVisibility(View.GONE);






    }
    void winRoLost() {

        if (randomNum == selectCunNum) {

            txtWin.setText("Win - \t\t" + (++winRounds));
            win_Ro_lost = 'W';



            switch (randomNum) {
                case 1:
                    iconOne.setBackgroundResource(R.drawable.open);
                    iconTow.setBackgroundResource(R.drawable.close);
                    iconThree.setBackgroundResource(R.drawable.close);
                    break;
                case 2:
                    iconOne.setBackgroundResource(R.drawable.close);
                    iconTow.setBackgroundResource(R.drawable.open);
                    iconThree.setBackgroundResource(R.drawable.close);
                    break;
                case 3:
                    iconOne.setBackgroundResource(R.drawable.close);
                    iconTow.setBackgroundResource(R.drawable.close);
                    iconThree.setBackgroundResource(R.drawable.open);
                    break;
            }

        }else {
            txtLost.setText("Lost - \t\t" + (++lostRounds));
            win_Ro_lost = 'L';



            switch (selectCunNum) {
                case 1:
                    iconOne.setBackgroundResource(R.drawable.no);
                    iconTow.setBackgroundResource(R.drawable.close);
                    iconThree.setBackgroundResource(R.drawable.close);
                    break;
                case 2:
                    iconOne.setBackgroundResource(R.drawable.close);
                    iconTow.setBackgroundResource(R.drawable.no);
                    iconThree.setBackgroundResource(R.drawable.close);
                    break;
                case 3:
                    iconOne.setBackgroundResource(R.drawable.close);
                    iconTow.setBackgroundResource(R.drawable.close);
                    iconThree.setBackgroundResource(R.drawable.no);
                    break;
            }

        }

        setPercentage();
    }
    void setPercentage() {


        percentage = (double)winRounds / roundCount *100;
        txtPercentage.setText("Win percentage - " + percentage + " %");


    }

    void changingCup() {

        if (selectCunNum == 1 && iconTow.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.no).getConstantState())) {

            selectCunNum = 3;
            setSelectVisibility('c');

        } else if (selectCunNum == 1 && iconThree.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.no).getConstantState())) {

            selectCunNum = 2;
            setSelectVisibility('b');

        } else if (selectCunNum == 2 && iconOne.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.no).getConstantState())) {

            selectCunNum = 3;
            setSelectVisibility('c');

        } else if (selectCunNum == 2 && iconThree.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.no).getConstantState())) {

            selectCunNum = 1;
            setSelectVisibility('a');

        } else if (selectCunNum == 3 && iconOne.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.no).getConstantState())) {

            selectCunNum = 2;
            setSelectVisibility('b');

        } else if (selectCunNum == 3 && iconTow.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.no).getConstantState())) {

            selectCunNum = 1;
            setSelectVisibility('a');
        }

    }



    public void btnShowRec(View view) {

        if (txtRec.getVisibility() == View.GONE) {

            for (int x =0; x<rec.length; x++) {


                txtRec.setText(txtRec.getText() + "\n" + rec[x] );


            }

            txtRec.setVisibility(View.VISIBLE);
            btnShowRec.setText(R.string.hide_records);

        }else {

            txtRec.setVisibility(View.GONE);
            btnShowRec.setText(R.string.show_records);

        }
    }

    public void btnYesRoNo(View view) {

        if (alwaysSelectYES) {

            alwaysSelectYES = false;
            btnYesRoNo.setText(R.string.btn_no);


        }else {

            alwaysSelectYES = true;
            btnYesRoNo.setText(R.string.btn_yes);


        }



    }

    void setRoundCount() {

        txtRouCount.setText("Round " + (++roundCount));
        setRecordsString();


    }

    void setSelectVisibility(char cup) {

        switch (cup) {
            case 'a':
                txtSelect1.setVisibility(View.VISIBLE);
                txtSelect2.setVisibility(View.INVISIBLE);
                txtSelect3.setVisibility(View.INVISIBLE);
                break;
            case 'b':
                txtSelect2.setVisibility(View.VISIBLE);
                txtSelect1.setVisibility(View.INVISIBLE);
                txtSelect3.setVisibility(View.INVISIBLE);
                break;
            case 'c':
                txtSelect3.setVisibility(View.VISIBLE);
                txtSelect1.setVisibility(View.INVISIBLE);
                txtSelect2.setVisibility(View.INVISIBLE);
                break;
        }

    }
    void openEmptyCup(char cup) {

        switch (cup) {
            case 'a':

                switch (randomNum) {

                    case 1:
                        switch (random.nextInt(2)) {

                            case 0:
                                iconTow.setBackgroundResource(R.drawable.no);
                                break;
                            case 1:
                                iconThree.setBackgroundResource(R.drawable.no);
                                break;
                        }
                        break;
                    case 2:
                        iconThree.setBackgroundResource(R.drawable.no);
                        break;
                    case 3:
                        iconTow.setBackgroundResource(R.drawable.no);
                        break;

                }


                break;
            case 'b':

                switch (randomNum) {

                    case 2:
                        switch (random.nextInt(2)) {

                            case 0:
                                iconOne.setBackgroundResource(R.drawable.no);
                                break;
                            case 1:
                                iconThree.setBackgroundResource(R.drawable.no);
                                break;
                        }
                        break;
                    case 1:
                        iconThree.setBackgroundResource(R.drawable.no);
                        break;
                    case 3:
                        iconOne.setBackgroundResource(R.drawable.no);
                        break;

                }


                break;
            case 'c':

                switch (randomNum) {

                    case 3:
                        switch (random.nextInt(2)) {

                            case 0:
                                iconOne.setBackgroundResource(R.drawable.no);
                                break;
                            case 1:
                                iconTow.setBackgroundResource(R.drawable.no);
                                break;
                        }
                        break;
                    case 1:
                        iconTow.setBackgroundResource(R.drawable.no);
                        break;
                    case 2:
                        iconOne.setBackgroundResource(R.drawable.no);
                        break;

                }

                break;

        }

    }
    void setNote() {

        txtNote.setText(R.string.noteText);
        msgLayout.setVisibility(View.VISIBLE);

        txtRec.setVisibility(View.GONE);


    }


}//endClass








