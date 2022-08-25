package com.example.weatherify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androdocs.httprequest.HttpRequest;
//import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



public class MainActivity extends AppCompatActivity {

    String CITY;
    String API = "98ed13acba37b8f64a391fd3a3748865";
    ImageView search,img;
    EditText etCity;
    TextView city,country,time,temp,forecast,humidity,min_temp,max_temp,sunrises,sunsets,pressure,windSpeed,visibility,feels_like,precipitation,date;
    ImageView [] t1Img = new ImageView[20];
    TextView [] tTemp = new TextView[20];
    TextView [] tTime = new TextView[20];
    LinearLayout [] todayll = new LinearLayout[20];
    TextView tstAPI;
    LinearLayout last;
    NestedScrollView mainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        {
            etCity = (EditText) findViewById(R.id.Your_city);
            search = (ImageView) findViewById(R.id.search);

            // CALL ALL ANSWERS :

            city = (TextView) findViewById(R.id.city);
            country = (TextView) findViewById(R.id.country);
            time = (TextView) findViewById(R.id.time);
            temp = (TextView) findViewById(R.id.temp);
            forecast = (TextView) findViewById(R.id.forecast);
            humidity = (TextView) findViewById(R.id.humidity);
            min_temp = (TextView) findViewById(R.id.min_temp);
            max_temp = (TextView) findViewById(R.id.max_temp);

            pressure = (TextView) findViewById(R.id.pressure);
            windSpeed = (TextView) findViewById(R.id.wind_speed);
            visibility = (TextView)findViewById(R.id.idvisibilty);
            feels_like = (TextView)findViewById(R.id.feelslike);
            precipitation = (TextView) findViewById(R.id.TVprecipitation);
//            tstAPI = (TextView)findViewById(R.id.testAPI);
            img =(ImageView)findViewById(R.id.IVimg);

            mainLayout = (NestedScrollView) findViewById(R.id.main);

            date = (TextView)findViewById(R.id.date);

            t1Img[0] = (ImageView)findViewById(R.id.t0Image);
            t1Img[1] = (ImageView)findViewById(R.id.t1Image);
            t1Img[2] = (ImageView)findViewById(R.id.t2Image);
            t1Img[3] = (ImageView)findViewById(R.id.t3Image);
            t1Img[4] = (ImageView)findViewById(R.id.t4Image);
            t1Img[5] = (ImageView)findViewById(R.id.t5Image);
            t1Img[6] = (ImageView)findViewById(R.id.t6Image);
            t1Img[7] = (ImageView)findViewById(R.id.t7Image);

            tTemp[0] = (TextView)findViewById(R.id.t0temp);
            tTemp[1] = (TextView)findViewById(R.id.t1temp);
            tTemp[2] = (TextView)findViewById(R.id.t2temp);
            tTemp[3] = (TextView)findViewById(R.id.t3temp);
            tTemp[4] = (TextView)findViewById(R.id.t4temp);
            tTemp[5] = (TextView)findViewById(R.id.t5temp);
            tTemp[6] = (TextView)findViewById(R.id.t6temp);
            tTemp[7] = (TextView)findViewById(R.id.t7temp);

            tTime[0] = (TextView)findViewById(R.id.t0Time);
            tTime[1] = (TextView)findViewById(R.id.t1Time);
            tTime[2] = (TextView)findViewById(R.id.t2Time);
            tTime[3] = (TextView)findViewById(R.id.t3Time);
            tTime[4] = (TextView)findViewById(R.id.t4Time);
            tTime[5] = (TextView)findViewById(R.id.t5Time);
            tTime[6] = (TextView)findViewById(R.id.t6Time);
            tTime[7] = (TextView)findViewById(R.id.t7Time);

            todayll[0] = (LinearLayout)findViewById(R.id.ll0);
            todayll[1] = (LinearLayout)findViewById(R.id.ll1);
            todayll[2] = (LinearLayout)findViewById(R.id.ll2);
            todayll[3] = (LinearLayout)findViewById(R.id.ll3);
            todayll[4] = (LinearLayout)findViewById(R.id.ll4);
            todayll[5] = (LinearLayout)findViewById(R.id.ll5);
            todayll[6] = (LinearLayout)findViewById(R.id.ll6);
            todayll[7] = (LinearLayout)findViewById(R.id.ll7);
            // CLICK ON SEARCH BUTTON :

            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CITY = etCity.getText().toString();
                    new weatherTask().execute();
                }
            });
        }
    }

    class weatherTask extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {
//
            String response = HttpRequest.excuteGet("https://api.openweathermap.org/data/2.5/forecast?q="+CITY+"&appid=c73f9fb467ba320a62b71f48f3a17264");
            return response;
        }

        @Override
        protected void onPostExecute(String result) {

            try {
//                Log.d("myJSon", result);
//                JSONObject jsonObj = new JSONObject(result);
//                JSONObject main = jsonObj.getJSONObject("main");
//                JSONObject weather = jsonObj.getJSONArray("weather").getJSONObject(0);
//                JSONObject wind = jsonObj.getJSONObject("wind");
//                JSONObject sys = jsonObj.getJSONObject("sys");
                final DecimalFormat dfZero = new DecimalFormat("0.00");
                JSONObject jsonObject = new JSONObject(result);
                JSONObject cityObj = jsonObject.getJSONObject("city");
                JSONArray dateArr = jsonObject.getJSONArray("list");



                // CALL VALUE IN API :
//                String city_name = jsonObj.getString("name");
//                String countryname = sys.getString("country");
//                Long updatedAt = jsonObj.getLong("dt");
//                String updatedAtText = "Last Updated at: " + new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(new Date(updatedAt * 1000));
//                String temperature = main.getString("temp");
//                String cast = weather.getString("description");
//                String humi_dity = main.getString("humidity");
//                String temp_min = main.getString("temp_min");
//                String temp_max = main.getString("temp_max");
//                String pre = main.getString("pressure");
//                String windspeed = wind.getString("speed");
//                Long rise = sys.getLong("sunrise");
//                String sunrise = new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date(rise * 1000));
//                Long set = sys.getLong("sunset");
//                String sunset = new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date(set * 1000));

                String city_name = cityObj.getString("name");
                String countryname = cityObj.getString("country");
                Long rise = cityObj.getLong("sunrise");
                String sunrise = new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date(rise*1000));
                Long set = cityObj.getLong("sunset");
                String sunset = new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date(set*1000));

                JSONObject day1 = (JSONObject) dateArr.get(0);
                String dt = day1.getString("dt_txt");

                JSONObject main = day1.getJSONObject("main");
                JSONObject wind = day1.getJSONObject("wind");
                JSONObject weather = (JSONObject) day1.getJSONArray("weather").get(0);

                Float tempk =Float.parseFloat(main.getString("temp"));
                String temperature =String.valueOf( convertFromKelvinToCelsius(tempk));
                temperature = trimString(temperature);


                String cast = weather.getString("main");
                String ico = weather.getString("icon");
                Float fminTemp =Float.parseFloat(main.getString("temp_min"));
                Float fmaxTemp =Float.parseFloat( main.getString("temp_max"));
                Float ffeelslike =Float.parseFloat(main.getString("feels_like"));
                String minTemp = String.valueOf(convertFromKelvinToCelsius(fminTemp));
                minTemp = trimString(minTemp);

                String maxTemp = String.valueOf(convertFromKelvinToCelsius(fmaxTemp));
                maxTemp = trimString(maxTemp);

                String feelsLike = String.valueOf(convertFromKelvinToCelsius(ffeelslike));
                feelsLike = trimString(feelsLike);

                String hum = main.getString("humidity");
                String pre = main.getString("pressure");
                String speed = wind.getString("speed");
                String vis = day1.getString("visibility");
                Float fpop =Float.parseFloat( day1.getString("pop"));
                fpop = fpop * 100;
                String pop = String.valueOf(fpop);

                mainLayout.setBackground(setImg("bg"+ico));
//                t1Temp.setText("hello");




                // SET ALL VALUES IN TEXTBOX :
                city.setText(city_name);
                country.setText(countryname);
//                time.setText(updatedAtText);
                temp.setText(temperature + "°C");
                forecast.setText(cast);
                humidity.setText(hum+"%");
                min_temp.setText("Min "+ minTemp + "°C");
                max_temp.setText("Max "+ maxTemp + "°C");
//                sunrises.setText(sunrise);
//                sunsets.setText(sunset);
                pressure.setText(pre+"hPa");
                windSpeed.setText(speed);
//                tstAPI.setText(dt);
                visibility.setText(vis+"m");
                feels_like.setText(feelsLike + "°C");
                precipitation.setText(pop+"%");
                date.setText(dt.substring(0,10));


                //Today ScrollView
//                t1Temp.setText(temp(0, dateArr) + "°C");
//
//                t1Img.setImageURI(Uri.fromFile(new File("file:////home/jeena/AndroidStudioProjects/Weather-Information-App-in-Android-JAVA2/app/src/main/res/drawable/pressure.png")));

                int pos=0;
                JSONObject j = dateArr.getJSONObject(pos);
                String dd = j.getString("dt_txt");

                while(!getDate().equals(dd.substring(0, 10))){
                    pos+=1;
                    j = dateArr.getJSONObject(pos);
                    dd = j.getString("dt_txt");
                }
                int count =1;
                for(int i=pos; i<=40-pos;){
                    JSONObject obj = dateArr.getJSONObject(i);
                    String date1 = obj.getString("dt_txt");
                    Long yourTime = Long.parseLong(obj.getString("dt"));
                    String transformedDate = new SimpleDateFormat("h:mm a").format(new Date(yourTime*1000));
                    JSONObject iconTodayobj = (JSONObject)obj.getJSONArray("weather").get(0);
                    String iconToday = iconTodayobj.getString("icon");
                    System.out.println(iconToday);

                    if(getDate().equals(date1.substring(0, 10)) ){
                        count += 1;
                        t1Img[i-pos].setImageDrawable(setImg("fc"+iconToday));
                        Float valueinK =Float.parseFloat(obj.getJSONObject("main").getString("temp"));
                        tTemp[i-pos].setText(trimString(String.valueOf(convertFromKelvinToCelsius(valueinK))) + "°C");
                        tTime[i-pos].setText(transformedDate);
//
                        i++;
                    }
                    else if(count<=8){
                        while(count <=8){
                            todayll[count-1].setVisibility(View.INVISIBLE);
                            count++;
                        }
                    }
                    else{
                        i++;
                        break;
                    }

                }

            } catch (Exception e) {

                Toast.makeText(MainActivity.this, "Error:" + e.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        private Float convertFromKelvinToCelsius(Float tempk) {
            return  tempk - 273.15f;
        }

        private  String trimString(String x){
            x = x.substring(0, x.lastIndexOf(".") + 2);
            return x;
        }

        private String temp(int i, JSONArray dateArr){
            try {
                JSONObject day1 = (JSONObject) dateArr.get(i);

                JSONObject main = (JSONObject) day1.getJSONObject("main");
                Float tempk =Float.parseFloat(main.getString("temp"));
                String temperature =String.valueOf( convertFromKelvinToCelsius(tempk));
                temperature = trimString(temperature);
                return temperature;
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return "";
        }

        private  Drawable setImg(String icon){
            String uri = "@drawable/"+ icon ;  // where myresource (without the extension) is the file

            int imageResource = getResources().getIdentifier(uri, null, getPackageName());

            Drawable res = getResources().getDrawable(imageResource);
            return res;
        }

        private String getDate() {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            return dateFormat.format(date);
        }

    }
}
