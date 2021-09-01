package com.daiw.recyclerviewcustom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.daiw.recyclerviewcustom.adapter.SlideAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private RecyclerView mSlideRv;
    private SmartRefreshLayout mSmart;

    private Handler myHandler = null;

    private static class MyHandler extends Handler{
        private Reference reference = null;
        private MyHandler(Activity activity){
            reference = new WeakReference(activity);
        }
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Activity activity = (Activity) reference.get();
            if(null == activity){
                return;
            }
            MainActivity mainActivity = (MainActivity) activity;
            switch (msg.what){
                case 0:
                    Log.d(TAG,"=====handleMessage onLoadMore=====");
                    mainActivity.endRefreshAndLoadMore();
                    break;
                case 1:
                    Log.d(TAG,"=====handleMessage onRefresh=====");
                    mainActivity.endRefreshAndLoadMore();
                    break;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myHandler = new MyHandler(this);

        mSlideRv = findViewById(R.id.rv_slide);
        SlideAdapter adapter = new SlideAdapter(this);
        mSlideRv.setAdapter(adapter);
        mSlideRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mSlideRv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 2;
                outRect.bottom = 2;
            }
        });

        mSmart = findViewById(R.id.smart);

        mSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Log.d(TAG,"=====onCreate onLoadMore=====");
                myHandler.sendEmptyMessageDelayed(0,3000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Log.d(TAG,"=====onCreate onRefresh=====");
                myHandler.sendEmptyMessageDelayed(1,3000);
            }
        });
    }

    public void endRefreshAndLoadMore(){
        mSmart.finishRefresh();
        mSmart.finishLoadMore();
    }
}