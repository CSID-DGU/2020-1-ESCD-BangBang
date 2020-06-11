package com.example.beacon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.List;

public class S_Menu extends AppCompatActivity implements BeaconConsumer {

    SampleData sample = new SampleData();
    private BeaconManager beaconManager;
    private List<Beacon> beaconList = new ArrayList<>();
    Boolean beacon = false;
    Boolean usim = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s__menu);

        //Beacon Setting
        beaconManager = BeaconManager.getInstanceForApplication(this);
        // ibeacon layout
        beaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.bind(this);

        Button attd = findViewById(R.id.attd_button);

        attd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notification(true, true);
            }
        });

        //ListView Setting
        ListView listView = findViewById(R.id.student_list);
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
        class NewRunnable implements Runnable {
            @Override
            public void run() {
                    try{
                        Thread.sleep(3000);
                        notification(true, true);
                    } catch(Exception e)
                    {
                    }
                }
        }
        NewRunnable nr = new NewRunnable() ;
        Thread t = new Thread(nr) ;
        t.start() ;
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.addMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                beacon = true;
                TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                if(tm.getSimState() == TelephonyManager.SIM_STATE_ABSENT)
                {
                    usim = false;
                }
                else
                {
                    usim = true;
                }
                notification(beacon, usim);
            }

            @Override
            public void didExitRegion(Region region) {
                //Log.i(TAG, "I no longer see an beacon");
            }

            @Override
            public void didDetermineStateForRegion(int state, Region region) {
                //Log.i(TAG, "I have just switched from seeing/not seeing beacons: "+state);
            }
        });

        try {
            beaconManager.startMonitoringBeaconsInRegion(new Region("myMonitoringUniqueId", null, null, null));
        } catch (RemoteException ignored) {    }
    }

    public void notification(Boolean beacon, Boolean usim)
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");

        if(beacon == false && usim == false)
        {
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("출석 싪패");
            builder.setContentText("블루투스 또는 디바이스 USIM 상태를 확인해주세요");
        }
        else if(beacon == false && usim == true)
        {
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("출석 싪패");
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
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("출석 성공");
            builder.setContentText("출석이 정상적으로 처리되었습니다.");
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
}
