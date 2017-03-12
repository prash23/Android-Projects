package creativecrew.geology_creativecrew;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by S525050 on 9/8/2016.
 */
public class Categories extends AppCompatActivity{
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = (TextView) mToolbar.findViewById(R.id.actionbar_textview);
        title.setText("CATEGORIES");
        CardView threepoint = (CardView) findViewById(R.id.threepointcard);
        CardView simplePlan = (CardView) findViewById(R.id.simplePlanCard);
        CardView trigonometry = (CardView) findViewById(R.id.trigonometryCard);

        threepoint.setOnClickListener(new CardView.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Categories.this,Threepoint.class);
                startActivity(it);
            }
        });



        simplePlan.setOnClickListener(new CardView.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Categories.this,TypeofProblemSimplePlanView.class);
                startActivity(it);
            }
        });



        trigonometry.setOnClickListener(new CardView.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Categories.this,TrigonometricCategories.class);
                startActivity(it);
            }
        });
    }
}
