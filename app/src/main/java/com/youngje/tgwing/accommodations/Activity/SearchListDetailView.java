package com.youngje.tgwing.accommodations.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.youngje.tgwing.accommodations.R;

/**
 * Created by JiwoonWon on 2016. 10. 22..
 */

public class SearchListDetailView extends AppCompatActivity{

    private TextView btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_detail);

        btnExit = (TextView) findViewById(R.id.list_detail_view_exit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
