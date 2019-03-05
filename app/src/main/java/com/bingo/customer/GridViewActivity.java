package com.bingo.customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bingo.customer.util.GlideUtils;
import com.bingo.customer.view.gridview.GridviewAdapter;
import com.bingo.customer.view.gridview.LineGridView;

import java.util.ArrayList;

/**
  * ================================
  * @author:  zcb
  * @email:   zhang-cb@foxmail.com
  * @time:    2019/3/5 17:49
  * @version: 1.0
  * @description: 九宫格布局
  * =================================
  */
public class GridViewActivity extends AppCompatActivity {

    private GridviewAdapter gridviewAdapter;
    LineGridView gridView;
    private ArrayList<GridviewAdapter.Icon> mIconDatas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        gridView = findViewById(R.id.my_grid_view);

        mIconDatas = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            mIconDatas.add(new GridviewAdapter.Icon("https://img5.duitang.com/uploads/item/201409/23/20140923094045_BNYji.thumb.700_0.png", "第"+i));
        }
        gridviewAdapter= new GridviewAdapter<GridviewAdapter.Icon>(mIconDatas, R.layout.item_grid_view) {

            @Override
            public void bindView(ViewHolder holder, Icon obj) {
                ImageView imageView = holder.getView(R.id.privilege_img);
                GlideUtils.loadImageView(GridViewActivity.this,obj.getImgPath(),imageView);
                holder.setText(R.id.privilege_title_tv, obj.getiName());
            }
        };
        gridView.setAdapter(gridviewAdapter);

    }
}
