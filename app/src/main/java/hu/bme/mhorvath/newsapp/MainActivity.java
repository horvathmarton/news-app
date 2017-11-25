package hu.bme.mhorvath.newsapp;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.bme.mhorvath.newsapp.adapter.ScreenSliderPagerAdapter;
import hu.bme.mhorvath.newsapp.interfaces.OnSourceChangedListener;
import hu.bme.mhorvath.newsapp.transformer.ZoomOutPageTransformer;

public class MainActivity extends AppCompatActivity implements OnSourceChangedListener {

    @BindView(R.id.pager)
    ViewPager pager;
    private ScreenSliderPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        pagerAdapter = new ScreenSliderPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setPageTransformer(true, new ZoomOutPageTransformer());

        pagerAdapter.addSource("bbc-news");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onSourceAdded(String source) {
        pagerAdapter.addSource(source);
    }

    @Override
    public void onSourceRemoved(int position) {
        pagerAdapter.removeSource(position);
    }

    @Override
    public void onAllSourceRemoved() {
        pagerAdapter.removeAllSources();
    }
}
