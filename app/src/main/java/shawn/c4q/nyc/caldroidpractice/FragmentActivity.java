package shawn.c4q.nyc.caldroidpractice;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.util.Calendar;
import java.util.Date;

import static android.widget.Toast.makeText;

public class FragmentActivity extends AppCompatActivity {
    CaldroidFragment caldroidFragment = new CaldroidFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle args = new Bundle();
        args.putInt(CaldroidFragment.THEME_RESOURCE, com.caldroid.R.style.CaldroidDefaultDark);
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.activity_main, caldroidFragment);
        t.commit();


        final CaldroidListener listener = new CaldroidListener() {
            private Toast lastToast;

            @Override
            public void onSelectDate(Date date, View view) {
                killToast(lastToast);
                lastToast = Toast.makeText(getApplicationContext(),"",
                        Toast.LENGTH_SHORT);
                lastToast.show();
            }

            @Override
            public void onChangeMonth(int month, int year) {
                String text = numToMonth(month)+ " " + year;
                killToast(lastToast);
                lastToast = makeText(getApplicationContext(), text,
                        Toast.LENGTH_SHORT);
                lastToast.show();
            }

            @Override
            public void onLongClickDate(Date date, View view) {
                killToast(lastToast);
                lastToast = makeText(getApplicationContext(),
                        "Long click " ,
                        Toast.LENGTH_SHORT);
                lastToast.show();
            }

            @Override
            public void onCaldroidViewCreated() {
                if (caldroidFragment.getLeftArrowButton() != null) {
                    caldroidFragment.setShowNavigationArrows(false);
                }
            }

        };

        caldroidFragment.setCaldroidListener(listener);

    }


    public void killToast(Toast lastToast){
       if(lastToast != null){
           lastToast.cancel();
       }
    }

    public String numToMonth(int month){
        switch(month){
            case 1:
                return "Jan";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "Aug";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
        }
        return null;
    }

}