package com.example.helloworld;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.helloworld.model.NodeMachine;
import com.example.helloworld.model.Temperatures;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity1 extends AppCompatActivity {

    private EditText addBox;
    private Button addButt1;
    private ListView dataView;
    private ImageView imageView1;
    private MySQLConnect1 mySQLConnect1;

    private List<String> items;
    private ArrayList<Temperatures.ResultBean> _array;
    private ArrayList<NodeMachine.ResultBean> _arrayOfNode;
    private static Adepter adapter;

    private ConstraintLayout mRelativeLayout;

    private PopupWindow mPopupWindow;

    private Button btshow, btnNode01,btnNode02,btnNode03,btnNode04,btnNode05;
    private ImageView iv1,iv2,iv3,iv4,iv5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btshow = (Button) findViewById(R.id.btShow);
        btnNode01 = (Button) findViewById(R.id.addButt1);
        btnNode02 = (Button) findViewById(R.id.addButt2);
        btnNode03 = (Button) findViewById(R.id.addButt3);
        btnNode04 = (Button) findViewById(R.id.addButt4);
        btnNode05 = (Button) findViewById(R.id.addButt5);
        iv1 = (ImageView) findViewById(R.id.imageView1);
        iv2 = (ImageView) findViewById(R.id.imageView2);
        iv3 = (ImageView) findViewById(R.id.imageView3);
        iv4 = (ImageView) findViewById(R.id.imageView4);
        iv5 = (ImageView) findViewById(R.id.imageView5);


        mRelativeLayout = (ConstraintLayout) findViewById(R.id.re);

        btnNode01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataModelNew("get_post1.php");
            }
        });

         btnNode02.setOnClickListener((new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 getDataModelNew("get_post2.php");
             }
         }));

        btnNode03.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataModelNew("get_post3.php");
            }
        }));

        btnNode04.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataModelNew("get_post4.php");
            }
        }));

        btnNode05.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataModelNew("get_post5.php");
            }
        }));

        btshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://unhurt-beans.000webhostapp.com/testphp/table.php"));
                startActivity(intent);
                //Intent in_t = new Intent(MainActivity.this, Result.class);
                //startActivity(in_t);

            }
        });
//        init();

//        //คำสั่งให้แอพพลิเคชั่นทำงานตลอดเวลา
//        new Thread(new Runnable() {
//            public void run() {
//                for (int i=1;i>0;i++) {
//                    try {
//                        Thread.sleep(1000*30);
//                        Log.d("Runnable", "Realtime running");
//                        //เรียก Method ดึงข้อมูลจาก database
////                        getDataModel();
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                            }
//                        });
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                        Log.d("Runnable", "Realtime Interrupted");
//                        return;
//                    }
//                }
//            }
//        }).start();
    }

    public void getDataModelNew(final String fileName){

        String url = "https://unhurt-beans.000webhostapp.com/testphp/" + fileName;

        _arrayOfNode = new ArrayList<NodeMachine.ResultBean>();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                _array = new ArrayList<>();

                String node_Id = "";
                String temp = "";
                String moist = "";
                String pm1 = "";
                String pm2_5 = "";
                String pm10 = "";


                try{

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("result");

                    for (int i = 0; i < result.length(); i++){
                        JSONObject collectData = result.getJSONObject(i);
                        node_Id = collectData.getString("node_id");
                        temp = collectData.getString("temp");
                        moist = collectData.getString("moist");
                        pm1 = collectData.getString("pm1");
                        pm2_5 = collectData.getString("pm2_5");
                        pm10 = collectData.getString("pm10");

                        //เพิ่มข้อมูลจาก database ลง ArrayList object
                        _arrayOfNode.add(new NodeMachine.ResultBean(node_Id,temp,moist,pm1,pm2_5,pm10));
                        Log.d("ArrayOfNode", _arrayOfNode.get(0).getNode_id());

                        LayoutInflater inflater = (LayoutInflater) MainActivity1.this.getSystemService(LAYOUT_INFLATER_SERVICE);
                        // Inflate the custom layout/view
                        View customView = inflater.inflate(R.layout.popup_template,null);
                        mPopupWindow = new PopupWindow(
                                customView,
                                RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT
                        );
                        mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER,0,0);

                        //ประกาศเพิ่ม
                        TextView tvShowTemp = (TextView) customView.findViewById(R.id.tvTemp);
                        TextView tvShowMoist = (TextView) customView.findViewById(R.id.tvMoist);
                        TextView tvShowPm1 = (TextView) customView.findViewById(R.id.tvPm1);
                        TextView tvShowPm2_5 = (TextView) customView.findViewById(R.id.tvPm2_5);
                        TextView tvShowPm10 = (TextView) customView.findViewById(R.id.tvPm10);
                        TextView tvNode = (TextView) customView.findViewById(R.id.nodeName);

//                        ใส่ค่าลงไป
                        tvShowTemp.setText("อุณหภูมิ \t\t\t\t\t\t " + _arrayOfNode.get(0).getTemp() + "\t ℃");
                        tvShowMoist.setText("ความชื้น \t\t\t\t\t\t " + _arrayOfNode.get(0).getMoist() + "\t %");
                        tvShowPm1.setText("ฝุ่น pm1 \t\t\t\t\t\t " + _arrayOfNode.get(0).getPm1() + "\t\t\t ug/m3");
                        tvShowPm2_5.setText("ฝุ่น pm2.5 \t\t\t\t " + _arrayOfNode.get(0).getPm2_5() + "\t\t\t ug/m3");
                        tvShowPm10.setText("ฝุ่น pm10 \t\t\t\t\t " + _arrayOfNode.get(0).getPm10() + "\t\t\t ug/m3");

                        /*
                        if(true) {
                            TextView.setTextColor(this.getResources().getColor(R.color.orange));
                        }else {
                            TextView.setTextColor(this.getResources().getColor(R.color.orange));
                        }

                         */

                        int pm11 = Integer.parseInt(_arrayOfNode.get(0).getPm1());
                        int pm2_51 = Integer.parseInt(_arrayOfNode.get(0).getPm2_5());
                        int pm101 = Integer.parseInt(_arrayOfNode.get(0).getPm10());

                        int pm12 = Integer.parseInt(_arrayOfNode.get(0).getPm1());
                        int pm2_52 = Integer.parseInt(_arrayOfNode.get(0).getPm2_5());
                        int pm102 = Integer.parseInt(_arrayOfNode.get(0).getPm10());

                        int pm13 = Integer.parseInt(_arrayOfNode.get(0).getPm1());
                        int pm2_53 = Integer.parseInt(_arrayOfNode.get(0).getPm2_5());
                        int pm103 = Integer.parseInt(_arrayOfNode.get(0).getPm10());

                        int pm14 = Integer.parseInt(_arrayOfNode.get(0).getPm1());
                        int pm2_54 = Integer.parseInt(_arrayOfNode.get(0).getPm2_5());
                        int pm104 = Integer.parseInt(_arrayOfNode.get(0).getPm10());

                        int pm15 = Integer.parseInt(_arrayOfNode.get(0).getPm1());
                        int pm2_55 = Integer.parseInt(_arrayOfNode.get(0).getPm2_5());
                        int pm105 = Integer.parseInt(_arrayOfNode.get(0).getPm10());

                        if (pm11 > 50 || pm2_51 > 50 || pm101 > 120) {
                            btnNode01.setBackgroundColor(ContextCompat.getColor(MainActivity1.this,R.color.Blue));
                            //iv1.setImageResource (R.drawable.foursquare_check_in );
                            iv1.setImageResource (R.drawable.close);
                            iv1.setScaleType (ImageView.ScaleType.CENTER_INSIDE);
                            /*
                            final AlertDialog.Builder dDialog = new AlertDialog.Builder(this);

                            final Button btn1 = (Button) findViewById(R.id.addButt1);
                            btn1.setOnClickListener((v) {
                                dDialog.setTitle("แนะนำ");
                                dDialog.setIcon(android.R.drawable.btn_star_big_on);
                                dDialog.setMessage("โปรเจ็ค");
                                dDialog.setPositiveButton("ปิด",null);
                                dDialog.show();

                            });

                             */
                        } else if (pm11 == 0 || pm2_51 == 0 || pm101 == 0){
                            btnNode01.setBackgroundColor(ContextCompat.getColor(MainActivity1.this,R.color.Gray));
                            iv1.setImageResource (R.drawable.close);
                            iv1.setScaleType (ImageView.ScaleType.CENTER_INSIDE);
                        }
                        if (pm12 > 50 || pm2_52 > 50 || pm102 > 120) {
                            btnNode02.setBackgroundColor(ContextCompat.getColor(MainActivity1.this,R.color.Blue));
                            iv2.setImageResource (R.drawable.close);
                            iv2.setScaleType (ImageView.ScaleType.CENTER_INSIDE);
                        } else if (pm12 == 0 || pm2_52 == 0 || pm102 == 0) {
                            btnNode02.setBackgroundColor(ContextCompat.getColor(MainActivity1.this,R.color.Gray));
                            iv2.setImageResource (R.drawable.close);
                            iv2.setScaleType (ImageView.ScaleType.CENTER_INSIDE);
                        }
                        if (pm13 > 50 || pm2_53 > 50 || pm103 > 120) {
                            btnNode03.setBackgroundColor(ContextCompat.getColor(MainActivity1.this,R.color.Blue));
                            iv3.setImageResource (R.drawable.close);
                            iv3.setScaleType (ImageView.ScaleType.CENTER_INSIDE);
                        } else if (pm13 == 0 || pm2_53 == 0 || pm103 == 0) {
                            btnNode03.setBackgroundColor(ContextCompat.getColor(MainActivity1.this,R.color.Gray));
                            iv3.setImageResource (R.drawable.close);
                            iv3.setScaleType (ImageView.ScaleType.CENTER_INSIDE);
                        }
                        if (pm14 > 50 || pm2_54 > 50 || pm104 > 120) {
                            btnNode04.setBackgroundColor(ContextCompat.getColor(MainActivity1.this,R.color.Blue));
                            iv4.setImageResource (R.drawable.close);
                            iv4.setScaleType (ImageView.ScaleType.CENTER_INSIDE);
                        } else if (pm14 == 0 || pm2_54 == 0 || pm104 == 0) {
                            btnNode04.setBackgroundColor(ContextCompat.getColor(MainActivity1.this,R.color.Gray));
                            iv4.setImageResource (R.drawable.close);
                            iv4.setScaleType (ImageView.ScaleType.CENTER_INSIDE);
                        }
                        if (pm15 > 50 || pm2_55 > 50 || pm105 > 120) {
                            btnNode05.setBackgroundColor(ContextCompat.getColor(MainActivity1.this,R.color.Blue));
                            iv5.setImageResource (R.drawable.close);
                            iv5.setScaleType (ImageView.ScaleType.CENTER_INSIDE);
                        } else if (pm15 == 0 || pm2_55 == 0 || pm105 == 0) {
                            btnNode05.setBackgroundColor(ContextCompat.getColor(MainActivity1.this,R.color.Gray));
                            iv5.setImageResource (R.drawable.close);
                            iv5.setScaleType (ImageView.ScaleType.CENTER_INSIDE);
                        }

                        tvNode.setText("Node " + _arrayOfNode.get(0).getNode_id());

                        TextView btn_Closed = (TextView) customView.findViewById(R.id.buttonClose);
                        btn_Closed.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPopupWindow.dismiss();
                            }
                        });
                    }

                }catch (JSONException ex){ ex.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity1.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity1.this);
        requestQueue.add(stringRequest);

    }

//    public void getDataModel(){
//
//        String url = "http://unhurt-beans.000webhostapp.com/testphp/get_post1.php";
//
//        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                _array = new ArrayList<>();
//
//                String temp = "";
//                String moist = "";
//                String pm1 = "";
//                String pm2_5 = "";
//                String pm10 = "";
//                try{
//
//                    JSONObject jsonObject = new JSONObject(response);
//                    JSONArray result = jsonObject.getJSONArray("result");
//
//                    for (int i = 0; i < result.length(); i++){
//                        JSONObject collectData = result.getJSONObject(i);
//                        temp = collectData.getString("temp");
//                        moist = collectData.getString("moist");
//                        pm1 = collectData.getString("pm1");
//                        pm2_5 = collectData.getString("pm2_5");
//                        pm10 = collectData.getString("pm10");
//
//                        //เพิ่มข้อมูลจาก database ลง ArrayList object
//                        _array.add(new Temperatures.ResultBean(temp,moist,pm1,pm2_5,pm10));
//                        //set custom adepter
//                        adapter= new Adepter(_array,getApplicationContext());
//                        dataView.setAdapter(adapter);
//                        adapter.notifyDataSetChanged();
//                        Log.d("Runnable", "" + _array);
//                    }
//
//                }catch (JSONException ex){ ex.printStackTrace();}
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity1.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        );
//
//        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity1.this);
//        requestQueue.add(stringRequest);
//    }

//    public void update(){
//        items = mySQLConnect.getData();
//        dataView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items));
//    }

//    public void init(){
//        addButt = (Button)findViewById(R.id.addButt1);
//        dataView = (ListView)findViewById(R.id.dataView1);
//        mySQLConnect1 = new MySQLConnect1 (MainActivity1.this) ;
//    }
}
