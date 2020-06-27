package com.example.beacon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class S_Menu extends AppCompatActivity implements BeaconConsumer, AutoPermissionsListener {

    SampleData sample = new SampleData();
    private Context context = this;
    private BeaconManager beaconManager;
    String name = "";
    String state = "";
    Boolean beacon = false;
    Boolean usim = false;
    String beaconUUID = "e2c56db5-dffb-48d2-b060-d0f5a71096e0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s__menu);

        AutoPermissions.Companion.loadAllPermissions(this,101); // AutoPermissions
        //Beacon Setting
        beaconManager = BeaconManager.getInstanceForApplication(this);
        // ibeacon layout
        beaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));

        ListView listView = findViewById(R.id.student_list);
        Button attd = findViewById(R.id.attd_button);

        attd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                recreate();
                show_dialog(beacon);
            }
        });

        //ListView Setting
        final P_menu_Adapter myAdapter = new P_menu_Adapter(this, sample.getCourses());

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), S_Class_Detail.class);
                intent.putExtra("Classname", sample.getCourses().get(position).getName());
                startActivity(intent);
            }
        });

//        class NewRunnable implements Runnable {
//            @Override
//            public void run() {
//                    try{
//                        if(getCourse())
//                        {
//                            beaconManager.bind(consumer);
//                            Thread.sleep(3000);
//                            notification(beacon);
//                        }
//                    } catch(Exception e)
//                    {
//                    }
//                }
//        }
//        NewRunnable nr = new NewRunnable();
//        Thread t = new Thread(nr);
//        t.start();
        if(getCourse())
        {
            beaconManager.bind(this);
        }
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.removeAllMonitorNotifiers();
        beaconManager.addMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                Toast.makeText(context, "비콘 연결됨", Toast.LENGTH_SHORT).show();
                beacon = true;
                notification(beacon);
            }

            @Override
            public void didExitRegion(Region region) {
                Toast.makeText(context, "비콘 연결 끊김", Toast.LENGTH_SHORT).show();
                beacon = false;
                notification(beacon);
            }

            @Override
            public void didDetermineStateForRegion(int state, Region region) {
            }

        });

        try {
            beaconManager.startMonitoringBeaconsInRegion(new Region("beacon", Identifier.parse(beaconUUID), null, null));
        } catch (RemoteException e) {    }
    }// onBeaconServiceConnect()..

    @Override
    public void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }
    //출석 결과 Push알림
    public void notification(Boolean beacon)
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        if(tm.getSimState() == TelephonyManager.SIM_STATE_ABSENT)
            usim = false;
        else if(tm.getSimState() == TelephonyManager.SIM_STATE_READY)
            usim = true;
        else
            usim = false;

        if(beacon == false && this.usim == false)
        {
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("출석 실패");
            builder.setContentText("블루투스 또는 디바이스 USIM 상태를 확인해주세요");
        }
        else if(beacon == false && usim == true)
        {
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("출석 실패");
            builder.setContentText("블루투스 상태를 확인해주세요");
        }
        else if(beacon == true && usim == false)
        {
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("출석 싪패");
            builder.setContentText("디바이스 USIM 상태를 확인해주세요");
        }
        else
        {
            Calendar calendar = Calendar.getInstance();
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("출결 상태 : " + this.state);
            builder.setContentText(this.name + " : " + calendar.get(Calendar.HOUR) + "시 " +
                    calendar.get(Calendar.MINUTE) + "분");
        }

        // 사용자가 탭을 클릭하면 자동 제거
        builder.setAutoCancel(true);

        // 알림 표시
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }

        // id값은
        // 정의해야하는 각 알림의 고유한 int값
        notificationManager.notify(1, builder.build());
    }

    //사용자가 수동으로 출석을 한 경우.
    void show_dialog(Boolean beacon)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        if(tm.getSimState() == TelephonyManager.SIM_STATE_ABSENT)
            usim = false;
        else if(tm.getSimState() == TelephonyManager.SIM_STATE_READY)
            usim = true;
        else
            usim = false;
        if(getCourse())
        {
            if(beacon == false && usim == false)
            {
                builder.setTitle("출석 실패");
                builder.setMessage("블루투스와 USIM 상태를 확인해주세요.");
                builder.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //DataBase에 변경된 내용 송신
                            }
                        });
            }
            else if(beacon == false && usim == true)
            {
                builder.setTitle("출석 실패");
                builder.setMessage("블루투스 상태를 확인해주세요.");
                builder.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //DataBase에 변경된 내용 송신
                            }
                        });
            }
            else if(beacon == true && usim == false)
            {
                builder.setTitle("출석 실패");
                builder.setMessage("USIM 상태를 확인해주세요.");
                builder.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //DataBase에 변경된 내용 송신
                            }
                        });
            }
            else
            {
                Calendar calendar = Calendar.getInstance();
                builder.setTitle("출결 상태 : " + this.state);
                builder.setMessage(this.name + " : " + calendar.get(Calendar.HOUR_OF_DAY) + "시 " +
                        calendar.get(Calendar.MINUTE) + "분");
                builder.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //DataBase에 변경된 내용 송신
                            }
                        });
            }
        }
        else
        {
            builder.setTitle("출석 시간이 아닙니다.");
            builder.setMessage("시간표를 확인해주세요.");
            builder.setPositiveButton("확인",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //DataBase에 변경된 내용 송신
                        }
                    });
        }
        builder.show();
    }

    Boolean getCourse()
    {
        Calendar calendar = Calendar.getInstance();

        if (calendar.get(Calendar.DAY_OF_WEEK) == Database.date[0]) {
            double time = calendar.get(Calendar.HOUR_OF_DAY) + ((double)calendar.get(Calendar.MINUTE) / 60.0);
            if (time - Database.date1_start_time < 0.17 && Database.date1_start_time <= time)
            {
                this.state = "출석";
                this.name = Database.name;
                return true;
            }
            else if(Database.date1_end_time > time && Database.date1_start_time < time)
            {
                this.state = "지각";
                this.name = Database.name;
                return true;

            }
            else
                return false;
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == Database.date[1]) {
            double time = calendar.get(Calendar.HOUR_OF_DAY) + ((double)calendar.get(Calendar.MINUTE) / 60.0);
            if (time - Database.date2_start_time < 0.17 && Database.date2_start_time <= time)
            {
                this.state = "출석";
                this.name = Database.name;
                return true;

            }
            else if(Database.date2_end_time > time && Database.date2_start_time < time)
            {
                this.state = "지각";
                this.name = Database.name;
                return true;

            }
            else
                return false;
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == OS.date[0]) {
            double time = calendar.get(Calendar.HOUR_OF_DAY) + ((double)calendar.get(Calendar.MINUTE) / 60.0);
            if (time - OS.date1_start_time < 0.17 && OS.date1_start_time <= time)
            {
                this.state = "출석";
                this.name = OS.name;
                return true;

            }
            else if(OS.date1_end_time > time && OS.date1_start_time < time)
            {
                this.state = "지각";
                this.name = OS.name;
                return true;
            }
            else
                return false;
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == OS.date[1]) {
            double time = calendar.get(Calendar.HOUR_OF_DAY) + ((double)calendar.get(Calendar.MINUTE) / 60.0);
            if (time - OS.date2_start_time < 0.17 && OS.date2_start_time <= time)
            {
                this.state = "출석";
                this.name = OS.name;
                return true;
            }
            else if(OS.date2_end_time > time && OS.date2_start_time < time)
            {
                this.state = "지각";
                this.name = OS.name;
                return true;
            }
            else
                return false;
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == SW.date[0]) {
            double time = calendar.get(Calendar.HOUR_OF_DAY) + ((double)calendar.get(Calendar.MINUTE) / 60.0);
            if (time - SW.date1_start_time < 0.17 && SW.date1_start_time <= time)
            {
                this.state = "출석";
                this.name = SW.name;
                return true;
            }
            else if(SW.date1_end_time > time && SW.date1_start_time < time)
            {
                this.state = "지각";
                this.name = SW.name;
                return true;
            }
            else
                return false;
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == SW.date[1]) {
            double time = calendar.get(Calendar.HOUR_OF_DAY) + ((double)calendar.get(Calendar.MINUTE) / 60.0);
            if (time - SW.date2_start_time < 0.17 && SW.date2_start_time <= time)
            {
                this.state = "출석";
                this.name = SW.name;
                return true;
            }
            else if(SW.date2_end_time > time && SW.date2_start_time < time)
            {
                this.state = "지각";
                this.name = SW.name;
                return true;
            }
            else
                return false;
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == BasicP.date[0]) {
            double time = calendar.get(Calendar.HOUR_OF_DAY) + ((double)calendar.get(Calendar.MINUTE) / 60.0);
            if (time - BasicP.date1_start_time < 0.17 && BasicP.date1_start_time <= time)
            {
                this.state = "출석";
                this.name = BasicP.name;
                return true;
            }
            else if(BasicP.date1_end_time > time && BasicP.date1_start_time < time)
            {
                this.state = "지각";
                this.name = BasicP.name;
                return true;
            }
            else
                return false;
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == BasicP.date[1]) {
            double time = calendar.get(Calendar.HOUR_OF_DAY) + ((double)calendar.get(Calendar.MINUTE) / 60.0);
            if (time - BasicP.date2_start_time < 0.17 && BasicP.date2_start_time <= time)
            {
                this.state = "출석";
                this.name = BasicP.name;
                return true;
            }
            else if(BasicP.date2_end_time > time && BasicP.date2_start_time < time)
            {
                this.state = "지각";
                this.name = BasicP.name;
                return true;
            }
            else
                return false;
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == CCP.date[0]) {
            double time = calendar.get(Calendar.HOUR_OF_DAY) + ((double)calendar.get(Calendar.MINUTE) / 60.0);
            if (time - CCP.date1_start_time < 0.17 && CCP.date1_start_time <= time)
            {
                this.state = "출석";
                this.name = CCP.name;
                return true;
            }
            else if(CCP.date1_end_time > time && CCP.date1_start_time < time)
            {
                this.state = "지각";
                this.name = CCP.name;
                return true;
            }
            else
                return false;
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == CCP.date[0]) {
            double time = calendar.get(Calendar.HOUR_OF_DAY) + ((double)calendar.get(Calendar.MINUTE) / 60.0);
            if (time - CCP.date2_start_time < 0.17 && CCP.date2_start_time <= time)
            {
                this.state = "출석";
                this.name = CCP.name;
                return true;
            }
            else if(CCP.date2_end_time > time && CCP.date2_start_time < time)
            {
                this.state = "지각";
                this.name = CCP.name;
                return true;
            }
            else
                return false;
        }

        return false;
    }

    //AutoPermission
    @Override
    public void onDenied(int i, String[] strings) {

    }

    @Override
    public void onGranted(int i, String[] strings) {

    }
}
