package com.hdy.mykaiyan.FastCopy;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hdy.mykaiyan.Base.BaseActivity;
import com.hdy.mykaiyan.R;
import org.json.JSONArray;
import org.json.JSONException;

public class activity_recyclerview extends BaseActivity {
    private adapter_recyclerview adapter_test1_1;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView idRecycleview;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recyclerview, menu);//加载menu文件到布局
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) //得到被点击的item的itemId
        {
            case  R.id.birth_pic :  //对应的ID就是在add方法中所设定的Id
                break;

        }
        return true;
    }

    @Override
    protected void initLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_recyclerview);
    }

    @Override
    protected void initView() {
        idRecycleview = findView(R.id.id_recycleview);
        swipeRefreshLayout=findView(R.id.test1_1_swipeRefreshLayout);
        swipeRefreshLayout.setRefreshing(true);
        idRecycleview.setLayoutManager(new LinearLayoutManager(this));
        idRecycleview.setItemAnimator(new DefaultItemAnimator());
        load();
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                load();
            }
        });
    }
    public void load(){
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://qiusunshine.github.io/HdyLove/Json/birthdayjson.js", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                swipeRefreshLayout.setRefreshing(false);
                try {
                    final JSONArray jsonArray=new JSONArray(response);
                    adapter_test1_1 = new adapter_recyclerview(activity_recyclerview.this, jsonArray);
                    idRecycleview.setAdapter(adapter_test1_1);
                    adapter_test1_1.setOnItemClickListener(new adapter_recyclerview.onItemClickListener() {
                        @Override
                        public void onItemClickPlayer(View view, int position) {
                        }
                        @Override
                        public void onItemClickHead(View view, int position) {
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(activity_recyclerview.this);
        requestQueue.add(stringRequest);
    }
}
