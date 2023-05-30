package com.example.mop125;


    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Context;
    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.widget.Button;
    import android.widget.LinearLayout;


public class ShowMatches extends AppCompatActivity {
    LinearLayout container;
    LayoutInflater inflater;
    Button getInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_matches);


        container = (LinearLayout) findViewById(R.id.container);
        inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        getInfo.setOnClickListener(new getInfoClickListener());}

    protected class getInfoClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            container.removeAllViews();
            inflater.inflate(R.layout.activity_show_data, container, true);
        }
    }
}
